package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5FinalWeeksTest {
    @Test fun weeksElevenAndTwelveHaveSevenDaysEach() {
        assertEquals((71..84).toList(), n5FinalWeeks.map { it.day })
        assertEquals(7, finalPlanDaysForWeek(11).size)
        assertEquals(7, finalPlanDaysForWeek(12).size)
        assertTrue(n5FinalWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun weekElevenRequiresRealAudioAndValidatedListeningQuestions() {
        val text = n5FinalWeeks.take(7).joinToString(" ") { it.focus + " " + it.practice.joinToString(" ") }
        assertTrue(text.contains("diálogos cortos", ignoreCase = true))
        assertTrue(text.contains("audios", ignoreCase = true))
        assertTrue(weeklyAssessmentForWeek(11).isEmpty())
    }

    @Test fun weekTwelveUsesOfficialN5SectionTimesWithoutInventingAnExamBank() {
        assertEquals(listOf(20, 40, 30), n5OfficialSectionTimings.map { it.minutes })
        assertEquals(90, n5OfficialSectionTimings.sumOf { it.minutes })
        assertEquals(90, N5_OFFICIAL_TOTAL_MINUTES)
        assertTrue(weeklyAssessmentForWeek(12).isEmpty())
    }
}
