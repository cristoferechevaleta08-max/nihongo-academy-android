package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiCatalogTest {
    @Test fun catalogContainsExactly370UniqueCharactersInSelectedOrder() {
        assertEquals(370, n3KanjiCatalog.size)
        assertEquals(370, n3KanjiCatalog.map { it.character }.toSet().size)
        assertEquals(n3KanjiCharacters, n3KanjiCatalog.map { it.character })
    }

    @Test fun everyEntryHasMeaningReadingsAndValidStrokeCount() {
        n3KanjiCatalog.forEach { entry ->
            assertEquals(1, entry.character.codePointCount(0, entry.character.length))
            assertTrue("Blank meaning for ${entry.character}", entry.meaningEs.isNotBlank())
            assertTrue("Missing readings for ${entry.character}", entry.onyomi.isNotEmpty() || entry.kunyomi.isNotEmpty())
            assertTrue("Invalid stroke count for ${entry.character}", entry.strokeCount in 1..30)
            assertTrue(entry.onyomi.all { it.isNotBlank() })
            assertTrue(entry.kunyomi.all { it.isNotBlank() })
        }
    }

    @Test fun catalogDoesNotDuplicateN4OrN5() {
        val lowerLevels = (n4KanjiCatalog.map { it.character } + n5KanjiCatalog.map { it.character }).toSet()
        assertTrue(n3KanjiCatalog.none { it.character in lowerLevels })
    }

    @Test fun lookupReturnsEveryMatchingEntry() {
        n3KanjiCatalog.forEach { entry ->
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
