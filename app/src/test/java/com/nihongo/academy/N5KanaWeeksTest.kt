package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5KanaWeeksTest {
    @Test fun bothBasicKanaTablesContain46Entries() {
        assertEquals(46, hiraganaBasic.size)
        assertEquals(46, katakanaBasic.size)
        assertEquals(46, hiraganaBasic.map { it.kana }.distinct().size)
        assertEquals(46, katakanaBasic.map { it.kana }.distinct().size)
    }

    @Test fun firstTwoWeeksContainFourteenDailyStudyBlocks() {
        assertEquals((1..14).toList(), n5KanaWeeks.map { it.day })
        assertTrue(n5KanaWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun weeklyKanaAssessmentsHaveTenValidQuestions() {
        listOf(hiraganaWeekAssessment, katakanaWeekAssessment).forEach { questions ->
            assertEquals(10, questions.size)
            questions.forEach { question ->
                assertEquals(4, question.options.size)
                assertTrue(question.answerIndex in question.options.indices)
            }
        }
    }

    @Test fun weekCatalogOnlyPublishesFirstTwoKanaWeeks() {
        assertEquals(hiraganaBasic, kanaForWeek(1))
        assertEquals(katakanaBasic, kanaForWeek(2))
        assertTrue(kanaForWeek(3).isEmpty())
    }
}
