#!/usr/bin/env python3
"""Compare all N3 catalog stroke counts with the KanjiVG stroke-path source."""
import concurrent.futures
import json
import re
import sys
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = "https://raw.githubusercontent.com/boomtakalaka/kanjivg-json/master/kanji_json/{}.json"
ENTRY = re.compile(
    r'N3KanjiEntry\\s*\\(\\s*character\\s*=\\s*"([^"]+)"[\\s\\S]*?strokeCount\\s*=\\s*(\\d+)',
    re.MULTILINE,
)
source = ROOT / "app/src/main/java/com/nihongo/academy/N3KanjiCatalog.kt"
matches = ENTRY.findall(source.read_text(encoding="utf-8"))
entries = [(character, int(count)) for character, count in matches]
characters = [character for character, _ in entries]
if len(entries) != 370 or len(set(characters)) != 370:
    raise SystemExit(f"Expected 370 unique N3 kanji, found {len(entries)} entries / {len(set(characters))} unique")

def check(entry):
    character, expected = entry
    code = f"{ord(character):05x}"
    url = SOURCE.format(code)
    request = urllib.request.Request(url, headers={"User-Agent": "NihongoAcademy-stroke-audit"})
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
print(f"PASS: all {len(entries)} N3 kanji have a KanjiVG source file and matching stroke count.")
