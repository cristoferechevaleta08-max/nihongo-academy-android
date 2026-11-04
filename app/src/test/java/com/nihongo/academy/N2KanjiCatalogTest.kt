package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N2KanjiCatalogTest {
    @Test
    fun block1AndBlock2HaveThirtyUniqueKanjiEach() {
        assertEquals(30, n2KanjiBlock1.size)
        assertEquals(30, n2KanjiBlock2.size)
        assertEquals(60, n2KanjiCatalog.size)
        assertEquals(60, n2KanjiCharacters.distinct().size)
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
    fun everyEntryHasMeaningReadingsAndValidStrokeCount() {
        n2KanjiCatalog.forEach { entry ->
            assertTrue("Falta significado para ${entry.character}", entry.meaningEs.isNotBlank())
            assertTrue("Trazos inválidos para ${entry.character}", entry.strokeCount in 1..30)
            assertTrue("Falta lectura para ${entry.character}", entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
        }
    }
}