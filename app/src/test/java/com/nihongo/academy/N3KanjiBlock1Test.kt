package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiBlock1Test {
    @Test fun blockOneContainsExactlyTheFirst123SelectedCharactersInOrder() {
        assertEquals(123, n3KanjiBlock1.size)
        assertEquals(n3KanjiBlocks.first(), n3KanjiBlock1.map { it.character })
    }

    @Test fun everyEntryHasSpanishMeaningReadingsAndValidStrokeCount() {
        n3KanjiBlock1.forEach { entry ->
            assertEquals(1, entry.character.codePointCount(0, entry.character.length))
            assertTrue(entry.meaningEs.isNotBlank())
            assertTrue(entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
            assertTrue(entry.strokeCount in 1..30)
            assertTrue(entry.onyomi.all { it.isNotBlank() })
            assertTrue(entry.kunyomi.all { it.isNotBlank() })
        }
    }

    @Test fun blockOneDoesNotDuplicateN4OrN5() {
        val lowerLevels = mutableSetOf<String>().apply {
            addAll(n4KanjiCatalog.map { it.character })
            addAll(n5KanjiCatalog.map { it.character })
        }
        assertTrue(n3KanjiBlock1.none { it.character in lowerLevels })
    }

    @Test fun lookupReturnsTheMatchingEntry() {
        n3KanjiBlock1.forEach { entry ->
            assertNotNull(n3KanjiByCharacter(entry.character))
            assertEquals(entry, n3KanjiByCharacter(entry.character))
        }
    }

    @Test fun dictionarySpecialReadingsAreNotPresentedAsStandardOnyomi() {
        assertTrue("メエトル" !in n3KanjiByCharacter("米")!!.onyomi)
        assertTrue("フラン" !in n3KanjiByCharacter("法")!!.onyomi)
        assertTrue("ダース" !in n3KanjiByCharacter("打")!!.onyomi)
    }

}
