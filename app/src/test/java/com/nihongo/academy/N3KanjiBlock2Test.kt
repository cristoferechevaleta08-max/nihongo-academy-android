package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiBlock2Test {
    @Test fun blockHasExpectedSizeAndExactSelectedOrder() {
        assertEquals(123, n3KanjiBlock2.size)
        assertEquals(n3KanjiCharacters.drop(123).take(123), n3KanjiBlock2.map { it.character })
    }

    @Test fun allEntriesHaveValidSpanishMeaningReadingsAndStrokeCounts() {
        n3KanjiBlock2.forEach { entry ->
            assertEquals(1, entry.character.codePointCount(0, entry.character.length))
            assertTrue(entry.meaningEs.isNotBlank())
            assertTrue(entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
            assertTrue(entry.strokeCount in 1..30)
            assertTrue(entry.onyomi.all { it.isNotBlank() })
            assertTrue(entry.kunyomi.all { it.isNotBlank() })
        }
    }

    @Test fun blockDoesNotDuplicateEarlierN3Blocks() {
        val previous = n3KanjiBlocks.take(1).flatten().toSet()
        assertTrue(n3KanjiBlock2.none { it.character in previous })
    }
}
