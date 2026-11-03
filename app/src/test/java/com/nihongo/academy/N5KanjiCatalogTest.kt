package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5KanjiCatalogTest {
    @Test
    fun catalogHasUniqueCharacters() {
        val characters = n5KanjiCatalog.map { it.character }
        assertEquals(characters.size, characters.toSet().size)
    }

    @Test
    fun everyEntryHasCoreLearningContent() {
        assertEquals(100, n5KanjiCatalog.size)
        n5KanjiCatalog.forEach { entry ->
            assertTrue(entry.character.isNotBlank())
            assertTrue(entry.meaningEs.isNotBlank())
            assertTrue(entry.strokeCount > 0)
            assertTrue(entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
            assertTrue(entry.vocabulary.isNotEmpty())
            entry.vocabulary.forEach { word ->
                assertTrue(word.word.isNotBlank())
                assertTrue(word.reading.isNotBlank())
                assertTrue(word.meaningEs.isNotBlank())
            }
            assertTrue(entry.exampleJa.isNotBlank())
            assertTrue(entry.exampleReading.isNotBlank())
            assertTrue(entry.exampleEs.isNotBlank())
        }
    }

    @Test
    fun allEntriesBelongToN5CatalogOnly() {
        assertTrue(n5KanjiCatalog.all { it.character.codePointCount(0, it.character.length) == 1 })
    }
    @Test
    fun strokeOrderDataCoversEveryEntryAndMatchesStrokeCount() {
        assertEquals(n5KanjiCatalog.map { it.character }.toSet(), n5KanjiStrokePaths.keys)
        n5KanjiCatalog.forEach { entry ->
            assertEquals(
                "Stroke data mismatch for ${entry.character}",
                entry.strokeCount,
                n5KanjiStrokePaths[entry.character]?.size
            )
        }
    }

}
