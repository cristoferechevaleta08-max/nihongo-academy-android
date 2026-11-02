package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5WeeklyQuizTest {
    @Test
    fun everyAvailableN5WeeklyAssessmentHasValidQuestionsAndAnswers() {
        (1..8).forEach { week ->
            val questions = weeklyAssessmentForWeek(week)
            assertEquals("week $week should have ten questions", 10, questions.size)
            questions.forEach { question ->
                assertTrue(question.prompt.isNotBlank())
                assertTrue(question.options.size >= 2)
                assertEquals("options must be unique: ${question.prompt}", question.options.size, question.options.distinct().size)
                assertTrue(question.answerIndex in question.options.indices)
            }
        }
        (9..12).forEach { week ->
            assertTrue("week $week must remain pending until its content is validated", weeklyAssessmentForWeek(week).isEmpty())
        }
    }

    @Test
    fun optionShuffleIsAPermutationAndKeepsTheOriginalCorrectAnswerIndex() {
        val question = weeklyAssessmentForWeek(3).first()
        val order = shuffledQuizOptionIndices(question.options.size, kotlin.random.Random(42))

        assertEquals(question.options.indices.toSet(), order.toSet())
        assertEquals(question.options[question.answerIndex], question.options[order[order.indexOf(question.answerIndex)]])
    }
}
