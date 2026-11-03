package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3CausativeTest {
    @Test fun secondGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3CausativeDays.size)
        assertEquals((1..7).toList(), n3CausativeDays.map { it.day })
        assertTrue(n3CausativeDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverRegularAndIrregularCausativeForms() {
        val text = n3CausativeDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("書く", "読む", "食べる", "見る", "する", "来る", "書かせられる", "食べさせられる").forEach { term ->
            assertTrue("Missing causative lesson content for $term", text.contains(term))
        }
    }

    @Test fun causativeAssessmentHasTwelveValidQuestions() {
        assertEquals(12, n3CausativeAssessment.size)
        n3CausativeAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun weeklyRoutingKeepsWeeksOneAndTwoSeparate() {
        assertEquals(n3CausativeDays, n3GrammarDaysForWeek(2))
        assertEquals(n3CausativeAssessment, n3GrammarAssessmentForWeek(2))
        assertEquals(n3PassiveVoiceDays, n3GrammarDaysForWeek(1))
        assertEquals(n3PassiveVoiceAssessment, n3GrammarAssessmentForWeek(1))
        assertTrue(n3GrammarDaysForWeek(3).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(3).isEmpty())
    }
}
