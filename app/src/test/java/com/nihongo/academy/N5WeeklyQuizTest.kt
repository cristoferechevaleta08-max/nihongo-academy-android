package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5WeeklyQuizTest {
    @Test
    fun everyAvailableN5WeeklyAssessmentHasValidQuestionsAndAnswers() {
        val expectedSizes = mapOf(1 to 10, 2 to 10, 3 to 10, 4 to 10, 5 to 10, 6 to 10, 7 to 10, 8 to 10, 9 to 10, 10 to 10, 11 to 18, 12 to 30)
        expectedSizes.forEach { (week, expectedSize) ->
            val questions = weeklyAssessmentForWeek(week)
            assertEquals("week $week question count", expectedSize, questions.size)
            questions.forEach { question ->
                assertTrue(question.prompt.isNotBlank())
                assertTrue(question.options.size >= 2)
                assertEquals("options must be unique: ${question.prompt}", question.options.size, question.options.distinct().size)
                assertTrue(question.answerIndex in question.options.indices)
            }
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
