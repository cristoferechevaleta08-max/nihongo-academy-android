package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3PassiveVoiceTest {
    @Test fun firstGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3PassiveVoiceDays.size)
        assertEquals((1..7).toList(), n3PassiveVoiceDays.map { it.day })
        assertTrue(n3PassiveVoiceDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun firstWeekCoversAllPassiveConjugationGroupsAndUsage() {
        val text = n3PassiveVoiceDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("書く", "読む", "食べる", "見る", "する", "来る", "に").forEach { term ->
            assertTrue("Missing lesson content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestionsAndAllAnswerOptions() {
        assertEquals(12, n3PassiveVoiceAssessment.size)
        n3PassiveVoiceAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun firstWeekRoutesToItsOwnLessonsAndAssessment() {
        assertEquals(n3PassiveVoiceDays, n3GrammarDaysForWeek(1))
        assertEquals(n3PassiveVoiceAssessment, n3GrammarAssessmentForWeek(1))
        assertEquals(n3CausativeDays, n3GrammarDaysForWeek(2))
        assertEquals(n3CausativeAssessment, n3GrammarAssessmentForWeek(2))
    }
}
