package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiBlock3Test {
    @Test fun blockHasExpectedSizeAndExactSelectedOrder() {
        assertEquals(124, n3KanjiBlock3.size)
        assertEquals(n3KanjiBlocks[2], n3KanjiBlock3.map { it.character })
    }

    @Test fun allEntriesHaveValidSpanishMeaningReadingsAndStrokeCounts() {
        n3KanjiBlock3.forEach { entry ->
            assertEquals(1, entry.character.codePointCount(0, entry.character.length))
            assertTrue(entry.meaningEs.isNotBlank())
            assertTrue(entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
            assertTrue(entry.strokeCount in 1..30)
            assertTrue(entry.onyomi.all { it.isNotBlank() })
            assertTrue(entry.kunyomi.all { it.isNotBlank() })
        }
    }

    @Test fun blockDoesNotDuplicateEarlierN3Blocks() {
        val previous = n3KanjiBlocks.take(2).flatten().toSet()
        assertTrue(n3KanjiBlock3.none { it.character in previous })
    }
}
