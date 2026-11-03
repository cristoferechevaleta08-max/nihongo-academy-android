package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3ChangeDecisionHabitsTest {
    @Test fun seventhGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3ChangeDecisionHabitsDays.size)
        assertEquals((1..7).toList(), n3ChangeDecisionHabitsDays.map { it.day })
        assertTrue(n3ChangeDecisionHabitsDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverChangeDecisionAttemptAndPreparation() {
        val text = n3ChangeDecisionHabitsDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("ようになる", "ようにする", "ことになる", "ことにする", "ようとする", "ておく", "ことにした", "ておきます").forEach { term ->
            assertTrue("Missing change/decision content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3ChangeDecisionHabitsAssessment.size)
        n3ChangeDecisionHabitsAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun seventhAndEighthWeeksRouteCorrectly() {
        assertEquals(n3ChangeDecisionHabitsDays, n3GrammarDaysForWeek(7))
        assertEquals(n3ChangeDecisionHabitsAssessment, n3GrammarAssessmentForWeek(7))
        assertEquals(n3AppearanceInformationProbabilityDays, n3GrammarDaysForWeek(8))
        assertEquals(n3AppearanceInformationProbabilityAssessment, n3GrammarAssessmentForWeek(8))
        assertTrue(n3GrammarDaysForWeek(9).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(9).isEmpty())
    }
}
