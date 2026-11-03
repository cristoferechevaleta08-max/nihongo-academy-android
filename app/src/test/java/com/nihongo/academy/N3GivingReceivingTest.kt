package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3GivingReceivingTest {
    @Test fun thirdGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3GivingReceivingDays.size)
        assertEquals((1..7).toList(), n3GivingReceivingDays.map { it.day })
        assertTrue(n3GivingReceivingDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverPerspectiveAndBenefitExpressions() {
        val text = n3GivingReceivingDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("あげる", "くれる", "もらう", "てあげる", "てくれる", "てもらう", "てくれませんか", "てもらえませんか").forEach { term ->
            assertTrue("Missing lesson content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3GivingReceivingAssessment.size)
        n3GivingReceivingAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun allThreeGrammarWeeksRouteToTheirOwnContent() {
        assertEquals(n3GivingReceivingDays, n3GrammarDaysForWeek(3))
        assertEquals(n3GivingReceivingAssessment, n3GrammarAssessmentForWeek(3))
        assertEquals(n3CausativeDays, n3GrammarDaysForWeek(2))
        assertEquals(n3CausativeAssessment, n3GrammarAssessmentForWeek(2))
        assertEquals(n3PassiveVoiceDays, n3GrammarDaysForWeek(1))
        assertEquals(n3PassiveVoiceAssessment, n3GrammarAssessmentForWeek(1))
        assertTrue(n3GrammarDaysForWeek(4).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(4).isEmpty())
    }
}
