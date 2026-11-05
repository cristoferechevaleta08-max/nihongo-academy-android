#!/usr/bin/env python3
"""Audit all N2 catalog stroke counts and KanjiVG stroke-path records."""
import concurrent.futures
import json
import re
import time
import urllib.error
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = "https://raw.githubusercontent.com/boomtakalaka/kanjivg-json/master/kanji_json/{}.json"
CATALOG = ROOT / "app/src/main/java/com/nihongo/academy/N2KanjiCatalog.kt"
ENTRY = re.compile(r'^\\s*N2KanjiEntry\\("([^"]+)",.*?,\\s*(\\d+)(?:,|\\))', re.MULTILINE)
EXPECTED_TOTAL = 400
MAX_ATTEMPTS = 3

entries = [(character, int(count)) for character, count in ENTRY.findall(CATALOG.read_text(encoding="utf-8"))]
characters = [character for character, _ in entries]
if len(entries) != EXPECTED_TOTAL or len(set(characters)) != EXPECTED_TOTAL:
    raise SystemExit(
        f"Expected {EXPECTED_TOTAL} unique N2 kanji, found "
        f"{len(entries)} entries / {len(set(characters))} unique"
    )

def check(entry):
    character, expected = entry
    code = f"{ord(character):05x}"
    request = urllib.request.Request(
        SOURCE.format(code),
        headers={"User-Agent": "NihongoAcademy-N2-stroke-audit/2.0"},
    )
    last_error = None
    for attempt in range(1, MAX_ATTEMPTS + 1):
        try:
            with urllib.request.urlopen(request, timeout=20) as response:
                data = json.load(response)
            strokes = data.get("strokes")
            if not isinstance(strokes, list) or not strokes:
                raise ValueError("missing or empty strokes array")
            if any(not isinstance(path, str) or not path.strip() for path in strokes):
                raise ValueError("one or more empty/invalid SVG stroke paths")
            return character, expected, len(strokes), None
        except (urllib.error.URLError, TimeoutError, json.JSONDecodeError, ValueError, KeyError) as error:
            last_error = error
            if attempt < MAX_ATTEMPTS:
                time.sleep(attempt * 1.5)
    return character, expected, None, str(last_error)

failures = []
checked = 0
with concurrent.futures.ThreadPoolExecutor(max_workers=8) as pool:
    futures = [pool.submit(check, entry) for entry in entries]
    for future in concurrent.futures.as_completed(futures):
        character, expected, actual, error = future.result()
        if error:
            failures.append((character, f"SOURCE/PATH ERROR: {error}"))
        elif actual != expected:
            failures.append((character, f"catalog={expected}, KanjiVG={actual}"))
        else:
            checked += 1

if failures:
    for character, detail in sorted(failures):
        print(f"FAIL {character}: {detail}")
    raise SystemExit(
        f"Stroke audit failed: {len(failures)} failures; "
        f"{checked}/{EXPECTED_TOTAL} kanji passed"
    )

print(
    f"PASS: {checked}/{EXPECTED_TOTAL} N2 kanji have a KanjiVG source file, "
    "non-empty stroke paths, and matching stroke counts."
)
