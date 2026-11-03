package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3TimeExpressionsTest {
    @Test fun fifthGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3TimeExpressionsDays.size)
        assertEquals((1..7).toList(), n3TimeExpressionsDays.map { it.day })
        assertTrue(n3TimeExpressionsDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverTheFiveTimeExpressionsAndTheirContrasts() {
        val text = n3TimeExpressionsDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("間", "間に", "うちに", "たびに", "途中で", "途中に", "料理をしている間", "出かけている間に", "若いうちに", "見るたびに").forEach { term ->
            assertTrue("Missing time-expression content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3TimeExpressionsAssessment.size)
        n3TimeExpressionsAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun fifthWeekRoutesCorrectlyAndSixthWeekRoutesToCauseLessons() {
        assertEquals(n3TimeExpressionsDays, n3GrammarDaysForWeek(5))
        assertEquals(n3TimeExpressionsAssessment, n3GrammarAssessmentForWeek(5))
        assertEquals(n3CauseReasonResultDays, n3GrammarDaysForWeek(6))
        assertEquals(n3CauseReasonResultAssessment, n3GrammarAssessmentForWeek(6))
        assertEquals(n3ChangeDecisionHabitsDays, n3GrammarDaysForWeek(7))
        assertEquals(n3ChangeDecisionHabitsAssessment, n3GrammarAssessmentForWeek(7))
        assertEquals(n3AppearanceInformationProbabilityDays, n3GrammarDaysForWeek(8))
        assertEquals(n3AppearanceInformationProbabilityAssessment, n3GrammarAssessmentForWeek(8))
        assertTrue(n3GrammarDaysForWeek(9).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(9).isEmpty())
    }
}
