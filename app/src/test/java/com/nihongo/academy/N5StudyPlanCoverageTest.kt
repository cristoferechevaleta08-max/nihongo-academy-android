package com.nihongo.academy

import org.junit.Assert.assertEquals
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
    fun weeksNineThroughTwelveStayLockedUntilRequiredValidatedMaterialsExist() {
        (9..12).forEach { week ->
            assertTrue("week $week assessment must not be fabricated", weeklyAssessmentForWeek(week).isEmpty())
        }
    }
}


class N5ToN4GateTest {
    @Test fun n4StaysLockedUntilAllTwelveN5WeeksAreComplete() {
        assertFalse(isN4Unlocked(false))
        assertTrue(isN4Unlocked(true))
    }
}
