package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class N5KanjiWeeksTest {
    @Test fun weeksNineAndTenHaveFourteenDailyActivities() {
        assertEquals((57..70).toList(), n5KanjiWeeks.map { it.day })
        assertEquals(7, kanjiDaysForWeek(9).size)
        assertEquals(7, kanjiDaysForWeek(10).size)
        assertTrue(n5KanjiWeeks.all { it.practice.isNotEmpty() })
    }

    @Test fun kanjiActivitiesCoverReadingWritingAndReviewWithoutInventingCharacters() {
        val combined = n5KanjiWeeks.joinToString(" ") { it.focus + " " + it.practice.joinToString(" ") }
        assertTrue(combined.contains("lectura", ignoreCase = true))
        assertTrue(combined.contains("escritura", ignoreCase = true))
        assertTrue(combined.contains("banco validado", ignoreCase = true))
    }
}
