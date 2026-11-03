package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5AdjectiveWeeksTest {
    @Test fun weeksSevenAndEightContainFourteenDailyActivities() {
        assertEquals((43..56).toList(), n5AdjectiveWeeks.map { it.day })
        assertEquals(7, adjectiveDaysForWeek(7).size)
        assertEquals(7, adjectiveDaysForWeek(8).size)
        assertTrue(n5AdjectiveWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun adjectiveAssessmentsHaveTenValidQuestionsEach() {
        listOf(iAdjectivesWeekAssessment, naAdjectivesWeekAssessment).forEach { questions ->
            assertEquals(10, questions.size)
            questions.forEach {
                assertEquals(4, it.options.size)
                assertTrue(it.answerIndex in it.options.indices)
            }
        }
    }

    @Test fun assessmentRouterIncludesWeeksSevenAndEight() {
        assertEquals(10, weeklyAssessmentForWeek(7).size)
        assertEquals(10, weeklyAssessmentForWeek(8).size)
        assertEquals(10, weeklyAssessmentForWeek(9).size)
    }
}
