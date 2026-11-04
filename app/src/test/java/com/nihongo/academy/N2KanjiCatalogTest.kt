package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N2KanjiCatalogTest {
    @Test
    fun catalogHasUniqueEntries() {
        assertEquals(120, n2KanjiCatalog.size)
        assertEquals(n2KanjiCatalog.size, n2KanjiCharacters.distinct().size)
    }

    @Test
    fun catalogDoesNotRepeatLowerLevelKanji() {
        val lowerLevel = (
            n3KanjiCharacters +
                n4KanjiCatalog.map { it.character } +
                n5KanjiCatalog.map { it.character }
            ).toSet()
        val duplicates = n2KanjiCharacters.filter { it in lowerLevel }
        assertTrue("N2 repite kanji de N3/N4/N5: $duplicates", duplicates.isEmpty())
    }

    @Test
    fun everyEntryHasMeaningReadingsVocabularyAndExample() {
        n2KanjiCatalog.takeLast(30).forEach { entry ->
            assertTrue("Falta vocabulario para ${entry.character}", entry.vocabulary.size >= 2)
            assertTrue("Falta ejemplo para ${entry.character}", entry.exampleJa.isNotBlank() && entry.exampleReading.isNotBlank() && entry.exampleEs.isNotBlank())
        }
    }

    @Test
    fun everyEntryHasMeaningReadingsAndValidStrokeCount() {
        n2KanjiCatalog.forEach { entry ->
            assertTrue("Falta significado para ${entry.character}", entry.meaningEs.isNotBlank())
            assertTrue("Trazos inválidos para ${entry.character}", entry.strokeCount in 1..30)
            assertTrue("Falta lectura para ${entry.character}", entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
        }
    }
    @Test
    fun noCharacterIsRepeatedInsideN2Catalog() {
        val duplicates = n2KanjiCharacters.groupBy { it }.filterValues { it.size > 1 }.keys
        assertTrue("N2 contiene caracteres duplicados: $duplicates", duplicates.isEmpty())
    }

    @Test
    fun catalogProgressMatchesUniqueEntries() {
        assertEquals(n2KanjiCatalog.size, n2KanjiCharacters.size)
        assertEquals(n2KanjiCatalog.size, n2KanjiCharacters.toSet().size)
    }

}
