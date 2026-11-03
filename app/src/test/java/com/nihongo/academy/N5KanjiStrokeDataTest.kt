package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test

class N5KanjiStrokeDataTest {
    @Test
    fun allCatalogKanjiHaveOrderedStrokePaths() {
        assertEquals("The N5 catalog should contain 100 unique characters", 100, n5KanjiCatalog.map { it.character }.toSet().size)
        assertEquals("Stroke data should cover all catalog characters", n5KanjiCatalog.map { it.character }.toSet(), n5KanjiStrokePaths.keys)
        n5KanjiCatalog.forEach { entry ->
            val paths = n5KanjiStrokePaths[entry.character]
            assertNotNull("Missing stroke paths for ${entry.character}", paths)
            assertFalse("Empty stroke paths for ${entry.character}", paths.isNullOrEmpty())
            assertEquals("Stroke count mismatch for ${entry.character}", entry.strokeCount, paths!!.size)
        }
    }
}
