#!/usr/bin/env python3
"""Compare every N2 catalog stroke count with KanjiVG stroke-path data."""
import concurrent.futures
import json
import re
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = "https://raw.githubusercontent.com/boomtakalaka/kanjivg-json/master/kanji_json/{}.json"
CATALOG = ROOT / "app/src/main/java/com/nihongo/academy/N2KanjiCatalog.kt"
ENTRY = re.compile(r'^\s*N2KanjiEntry\("([^"]+)",.*?,\s*(\d+)(?:,|\))', re.MULTILINE)

entries = [(character, int(count)) for character, count in ENTRY.findall(CATALOG.read_text(encoding="utf-8"))]
characters = [character for character, _ in entries]
if len(entries) != 360 or len(set(characters)) != len(entries):
    raise SystemExit(f"Expected 360 unique N2 kanji, found {len(entries)} entries / {len(set(characters))} unique")

def check(entry):
    character, expected = entry
    code = f"{ord(character):05x}"
    request = urllib.request.Request(SOURCE.format(code), headers={"User-Agent": "NihongoAcademy-N2-stroke-audit"})
    with urllib.request.urlopen(request, timeout=20) as response:
        data = json.load(response)
    actual = len(data.get("strokes", []))
    return character, expected, actual

failures = []
with concurrent.futures.ThreadPoolExecutor(max_workers=12) as pool:
    futures = [pool.submit(check, entry) for entry in entries]
    for future in concurrent.futures.as_completed(futures):
        try:
            character, expected, actual = future.result()
            if actual != expected:
                failures.append((character, expected, actual))
        except Exception as error:
            failures.append(("SOURCE_ERROR", 0, str(error)))

if failures:
    for character, expected, actual in sorted(failures):
        print(f"MISMATCH {character}: catalog={expected}, KanjiVG={actual}")
    raise SystemExit(f"Stroke audit failed: {len(failures)} mismatches/errors out of {len(entries)}")
print(f"PASS: all {len(entries)} N2 kanji have a KanjiVG source file and matching stroke count.")
