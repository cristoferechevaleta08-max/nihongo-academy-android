package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5ListeningPracticeTest {
    @Test
    fun sixOriginalDialoguesHaveFourTurnsAndThreeQuestionsEach() {
        assertEquals(6, n5ListeningDialogues.size)
        n5ListeningDialogues.forEach { dialogue ->
            assertEquals(4, dialogue.lines.size)
            assertEquals(3, dialogue.questions.size)
            assertTrue(dialogue.lines.all { it.japanese.isNotBlank() && it.reading.isNotBlank() && it.spanish.isNotBlank() })
            dialogue.questions.forEach { question ->
                assertEquals(3, question.options.size)
                assertTrue(question.answer in question.options.indices)
            }
        }
    }

    @Test
    fun n5ListeningAndFinalMockAssessmentsAreAvailable() {
        assertEquals(18, weeklyAssessmentForWeek(11).size)
        assertEquals(3, n5ReadingAssessment.size)
        assertEquals(30, weeklyAssessmentForWeek(12).size)
    }

    @Test
    fun kanjiWeeksUseTheIntegratedCatalogForTenQuestionAssessments() {
        assertEquals(10, weeklyAssessmentForWeek(9).size)
        assertEquals(10, weeklyAssessmentForWeek(10).size)
    }
}
