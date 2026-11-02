package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5VerbWeeksTest {
    @Test fun weeksFiveAndSixHaveFourteenDailyActivities() {
        assertEquals((29..42).toList(), n5VerbWeeks.map { it.day })
        assertEquals(7, verbDaysForWeek(5).size)
        assertEquals(7, verbDaysForWeek(6).size)
        assertTrue(n5VerbWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun verbAssessmentsContainTenFourOptionQuestions() {
        listOf(masuWeekAssessment, masuPastNegativeAssessment).forEach { questions ->
            assertEquals(10, questions.size)
            questions.forEach {
                assertEquals(4, it.options.size)
                assertTrue(it.answerIndex in it.options.indices)
            }
        }
    }

    @Test fun assessmentRouterIncludesWeeksFiveAndSix() {
        assertEquals(10, weeklyAssessmentForWeek(5).size)
        assertEquals(10, weeklyAssessmentForWeek(6).size)
        assertEquals(10, weeklyAssessmentForWeek(7).size)
    }
}
