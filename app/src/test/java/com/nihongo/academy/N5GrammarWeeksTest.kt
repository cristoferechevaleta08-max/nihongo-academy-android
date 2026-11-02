package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5GrammarWeeksTest {
    @Test fun weeksThreeAndFourProvideSevenDailyBlocksEach() {
        assertEquals((15..28).toList(), n5GrammarWeeks.map { it.day })
        assertEquals(7, grammarDaysForWeek(3).size)
        assertEquals(7, grammarDaysForWeek(4).size)
        assertTrue(n5GrammarWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun greetingsAndParticleAssessmentsHaveTenValidQuestions() {
        listOf(greetingsWeekAssessment, particlesWeekAssessment).forEach { questions ->
            assertEquals(10, questions.size)
            questions.forEach { question ->
                assertEquals(4, question.options.size)
                assertTrue(question.answerIndex in question.options.indices)
            }
        }
    }

    @Test fun assessmentRouterMapsWeeksOneThroughFour() {
        assertEquals(10, weeklyAssessmentForWeek(1).size)
        assertEquals(10, weeklyAssessmentForWeek(2).size)
        assertEquals(10, weeklyAssessmentForWeek(3).size)
        assertEquals(10, weeklyAssessmentForWeek(4).size)
        assertTrue(weeklyAssessmentForWeek(5).isEmpty())
    }
}
