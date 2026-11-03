package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N3CauseReasonResultTest {
    @Test fun sixthGrammarWeekHasSevenOrderedLessons() {
        assertEquals(7, n3CauseReasonResultDays.size)
        assertEquals((1..7).toList(), n3CauseReasonResultDays.map { it.day })
        assertTrue(n3CauseReasonResultDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.size >= 3 })
    }

    @Test fun lessonsCoverCauseReasonAndResultExpressions() {
        val text = n3CauseReasonResultDays.joinToString(" ") { it.title + " " + it.focus + " " + it.practice.joinToString(" ") }
        listOf("から", "ので", "ために", "おかげで", "せいで", "そのため", "その結果", "によって", "により").forEach { term ->
            assertTrue("Missing cause/result content for $term", text.contains(term))
        }
    }

    @Test fun assessmentHasTwelveValidQuestions() {
        assertEquals(12, n3CauseReasonResultAssessment.size)
        n3CauseReasonResultAssessment.forEach { question ->
            assertEquals(4, question.options.size)
            assertTrue(question.answerIndex in question.options.indices)
            assertFalse(question.prompt.isBlank())
        }
    }

    @Test fun sixthWeekRoutesCorrectlyAndSeventhWeekRemainsEmpty() {
        assertEquals(n3CauseReasonResultDays, n3GrammarDaysForWeek(6))
        assertEquals(n3CauseReasonResultAssessment, n3GrammarAssessmentForWeek(6))
        assertTrue(n3GrammarDaysForWeek(7).isEmpty())
        assertTrue(n3GrammarAssessmentForWeek(7).isEmpty())
    }
}
