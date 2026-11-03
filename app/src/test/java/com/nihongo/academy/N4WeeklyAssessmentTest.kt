package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class N4WeeklyAssessmentTest {
    @Test fun everyWeekHasARealAssessmentWithValidQuestions() {
        val expectedCounts = listOf(8, 8, 8, 8, 8, 6, 8, 8, 10, 10, 10, 18, 9, 15, 20, 30)
        (1..N4_PLAN_WEEKS).forEach { week ->
            val questions = buildN4WeeklyAssessment(week, Random(week))
            assertEquals("week $week question count", expectedCounts[week - 1], questions.size)
            questions.forEach { question ->
                assertTrue("week $week prompt", question.prompt.isNotBlank())
                assertTrue("week $week section", question.section.isNotBlank())
                assertEquals("week $week options", 4, question.options.size)
                assertEquals("week $week unique options", 4, question.options.distinct().size)
                assertTrue("week $week answer index", question.answerIndex in question.options.indices)
                assertTrue("week $week explanation", question.explanation.isNotBlank())
            }
        }
    }

    @Test fun grammarWeeksUseTheirMatchingN4Units() {
        (1..8).forEach { week ->
            val questions = buildN4WeeklyAssessment(week, Random(100 + week))
            assertTrue(questions.all { it.section.contains("N4", ignoreCase = true) || it.section.startsWith("Gramática") })
        }
        assertTrue(buildN4WeeklyAssessment(1, Random(1)).any { it.prompt.contains("tabete imasu") })
        assertTrue(buildN4WeeklyAssessment(8, Random(8)).any { it.prompt.contains("tara", ignoreCase = true) || it.prompt.contains("condicional", ignoreCase = true) })
    }

    @Test fun kanjiWeeksCoverAllThreeCatalogBlocks() {
        val allPrompts = (9..11).flatMap { buildN4WeeklyAssessment(it, Random(it)) }
        assertEquals(30, allPrompts.size)
        assertTrue(allPrompts.all { it.section.startsWith("Kanji") })
    }

    @Test fun listeningAndReadingWeeksUseOriginalIntegratedMaterial() {
        assertEquals(n4ListeningDialogues.sumOf { it.questions.size },
            buildN4WeeklyAssessment(12, Random(12)).size)
        assertEquals(n4ReadingPassages.sumOf { it.questions.size },
            buildN4WeeklyAssessment(13, Random(13)).size)
    }

    @Test fun assessmentQuestionOrderAndOptionsCanBeRandomizedWithoutLosingAnswer() {
        val first = buildN4WeeklyAssessment(16, Random(42))
        val second = buildN4WeeklyAssessment(16, Random(99))
        assertEquals(30, first.size)
        assertEquals(30, second.size)
        assertTrue(first.all { it.answerIndex in it.options.indices })
        assertTrue(first.zip(second).any { (a, b) -> a.prompt != b.prompt || a.options != b.options })
    }

    @Test(expected = IllegalArgumentException::class)
    fun weekOutsidePlanIsRejected() {
        buildN4WeeklyAssessment(17, Random(1))
    }
}
