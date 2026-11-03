package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3ConditionalTest {
    @Test fun fourthGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3ConditionalDays.size)
        assertEquals((1..7).toList(), n3ConditionalDays.map { it.day })
        assertTrue(n3ConditionalDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverFormationAndMeaningOfFourConditionals() {
        val text = n3ConditionalDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("ば", "たら", "なら", "と", "書けば", "食べれば", "すれば", "読んだら", "来たら").forEach { term ->
            assertTrue("Missing conditional lesson content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3ConditionalAssessment.size)
        n3ConditionalAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun fourGrammarWeeksRouteToTheirOwnLessonsAndAssessments() {
        assertEquals(n3ConditionalDays, n3GrammarDaysForWeek(4))
        assertEquals(n3ConditionalAssessment, n3GrammarAssessmentForWeek(4))
        assertEquals(n3GivingReceivingDays, n3GrammarDaysForWeek(3))
        assertEquals(n3GivingReceivingAssessment, n3GrammarAssessmentForWeek(3))
        assertEquals(n3CausativeDays, n3GrammarDaysForWeek(2))
        assertEquals(n3CausativeAssessment, n3GrammarAssessmentForWeek(2))
        assertEquals(n3PassiveVoiceDays, n3GrammarDaysForWeek(1))
        assertEquals(n3PassiveVoiceAssessment, n3GrammarAssessmentForWeek(1))
        assertEquals(n3TimeExpressionsDays, n3GrammarDaysForWeek(5))
        assertEquals(n3TimeExpressionsAssessment, n3GrammarAssessmentForWeek(5))
        assertEquals(n3CauseReasonResultDays, n3GrammarDaysForWeek(6))
        assertEquals(n3CauseReasonResultAssessment, n3GrammarAssessmentForWeek(6))
        assertEquals(n3ChangeDecisionHabitsDays, n3GrammarDaysForWeek(7))
        assertEquals(n3ChangeDecisionHabitsAssessment, n3GrammarAssessmentForWeek(7))
        assertTrue(n3GrammarDaysForWeek(8).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(8).isEmpty())
    }
}
