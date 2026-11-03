package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N3Week2ContentTest {
    @Test
    fun containsSevenOrderedDailyLessons() {
        assertEquals((1..7).toList(), n3Week2Lessons.map { it.day })
    }

    @Test
    fun everyLessonHasExamplesAndValidExercises() {
        assertTrue(n3Week2Lessons.all { lesson ->
            lesson.objective.isNotBlank() &&
                lesson.explanation.isNotBlank() &&
                lesson.examples.size >= 2 &&
                lesson.exercises.size >= 3 &&
                lesson.exercises.all { exercise ->
                    exercise.options.size == 4 &&
                        exercise.answerIndex in exercise.options.indices &&
                        exercise.explanation.isNotBlank()
                }
        })
    }

    @Test
    fun weeklyAssessmentHasTwelveValidQuestions() {
        assertEquals(12, n3Week2Assessment.size)
        assertTrue(n3Week2Assessment.all { question ->
            question.options.size == 4 &&
                question.answerIndex in question.options.indices &&
                question.explanation.isNotBlank()
        })
    }

    @Test
    fun contentValidationPasses() {
        assertTrue(isN3Week2ContentValid())
    }
}
