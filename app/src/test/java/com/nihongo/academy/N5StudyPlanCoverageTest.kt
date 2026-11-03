package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class N5StudyPlanCoverageTest {
    @Test
    fun twelveWeekPlanContainsEveryDayOnceInPdfOrder() {
        val allDays = n5KanaWeeks + n5GrammarWeeks + n5VerbWeeks +
            n5AdjectiveWeeks + n5KanjiWeeks + n5FinalWeeks

        assertEquals((1..84).toList(), allDays.map { it.day })
        assertEquals(84, allDays.map { it.day }.distinct().size)
        assertTrue(allDays.all { it.title.isNotBlank() && it.focus.isNotBlank() && it.practice.isNotEmpty() })
    }

    @Test
    fun eachOfTheTwelveWeeksContainsExactlySevenDailyBlocks() {
        val weeks = listOf(
            n5KanaWeeks.take(7), n5KanaWeeks.drop(7),
            n5GrammarWeeks.take(7), n5GrammarWeeks.drop(7),
            n5VerbWeeks.take(7), n5VerbWeeks.drop(7),
            n5AdjectiveWeeks.take(7), n5AdjectiveWeeks.drop(7),
            kanjiDaysForWeek(9), kanjiDaysForWeek(10),
            finalPlanDaysForWeek(11), finalPlanDaysForWeek(12)
        )
        weeks.forEachIndexed { index, days ->
            assertEquals("week ${index + 1}", 7, days.size)
        }
    }

    @Test
    fun weeksNineThroughTwelveHaveTheirIntegratedAssessments() {
        assertEquals(10, weeklyAssessmentForWeek(9).size)
        assertEquals(10, weeklyAssessmentForWeek(10).size)
        assertEquals(18, weeklyAssessmentForWeek(11).size)
        assertEquals(30, weeklyAssessmentForWeek(12).size)
    }
}


class N5ToN4GateTest {
    @Test fun n4StaysLockedUntilAllTwelveN5WeeksAreComplete() {
        assertFalse(isN4Unlocked(false))
        assertTrue(isN4Unlocked(true))
    }
}
