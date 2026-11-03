package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3KanjiSelectionTest {
    @Test fun selectionContainsExactlyThreeHundredSeventyUniqueKanji() {
        assertEquals(370, n3KanjiCharacters.size)
        assertEquals(370, n3KanjiCharacters.toSet().size)
        n3KanjiCharacters.forEach { character ->
            assertEquals(1, character.codePointCount(0, character.length))
        }
    }

    @Test fun selectionDoesNotDuplicateN4OrN5() {
        val lowerLevels = n4KanjiCatalog.map { it.character }.toSet() + n5KanjiCatalog.map { it.character }.toSet()
        assertTrue(n3KanjiCharacters.none { it in lowerLevels })
    }

    @Test fun curatedSubstitutionsReplaceExactlyTheLowerLevelOverlaps() {
        assertEquals(28, n3KanjiExcludedForLowerLevelOverlap.size)
        assertEquals(28, n3KanjiCuratedSubstitutions.size)
        assertEquals(370, n3KanjiReferenceCharacters.size)
        assertEquals(342, n3KanjiReferenceCharacters.count { it !in n3KanjiExcludedForLowerLevelOverlap })
        assertTrue(n3KanjiCuratedSubstitutions.none { it in n3KanjiReferenceCharacters })
    }

    @Test fun studySelectionIsSplitIntoThreeOrderedBlocks() {
        assertEquals(listOf(123, 123, 124), n3KanjiBlocks.map { it.size })
        assertEquals(n3KanjiCharacters, n3KanjiBlocks.flatten())
    }
}
