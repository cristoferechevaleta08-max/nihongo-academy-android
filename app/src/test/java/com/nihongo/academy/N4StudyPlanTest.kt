package com.nihongo.academy

import org.junit.Assert.*
import org.junit.Test

class N4StudyPlanTest {
    @Test fun planHasSixteenOrderedWeeksWithCompleteContent() {
        assertEquals(N4_PLAN_WEEKS, n4SixteenWeekPlan.size)
        assertEquals((1..16).toList(), n4SixteenWeekPlan.map { it.week })
        n4SixteenWeekPlan.forEach { week ->
            assertTrue("week ${week.week} title", week.title.isNotBlank())
            assertTrue("week ${week.week} focus", week.focus.isNotBlank())
            assertTrue("week ${week.week} activities", week.activities.size >= 3)
            assertTrue("week ${week.week} assessment", week.assessment.isNotBlank())
        }
    }

    @Test fun curriculumCoversEightGrammarUnitsKanjiListeningAndReading() {
        assertTrue(n4SixteenWeekPlan[0].focus.contains("te", ignoreCase = true))
        assertTrue(n4SixteenWeekPlan[7].title.contains("Condiciones"))
        assertTrue(n4SixteenWeekPlan.slice(8..10).all { it.title.contains("Kanji") })
        assertTrue(n4SixteenWeekPlan[11].title.contains("Escucha"))
        assertTrue(n4SixteenWeekPlan[12].title.contains("Lectura"))
        assertTrue(n4SixteenWeekPlan.last().title.contains("Cierre"))
    }

    @Test fun firstWeekCanBeAssessedButLaterWeeksStayLocked() {
        assertTrue(isN4WeekUnlocked(emptySet(), 1))
        assertFalse(isN4WeekUnlocked(emptySet(), 2))
        assertFalse(isN4WeekUnlocked(emptySet(), 17))
    }

    @Test fun scoreBelowSeventyRepeatsCurrentWeek() {
        val result = submitN4WeeklyAssessment(N4StudyProgress(), 69)
        assertEquals(1, result.currentWeek)
        assertTrue(result.completedWeeks.isEmpty())
        assertEquals(1, result.attemptsByWeek[1])
        assertEquals(69, result.lastScoreByWeek[1])
    }

    @Test fun exactlySeventyPassesAndUnlocksNextWeek() {
        val result = submitN4WeeklyAssessment(N4StudyProgress(), 70)
        assertEquals(2, result.currentWeek)
        assertTrue(1 in result.completedWeeks)
        assertTrue(isN4WeekUnlocked(result.completedWeeks, 2))
        assertFalse(isN4WeekUnlocked(result.completedWeeks, 3))
    }

    @Test fun repeatedAttemptsAccumulateUntilWeekIsPassed() {
        val first = submitN4WeeklyAssessment(N4StudyProgress(), 50)
        val second = submitN4WeeklyAssessment(first, 65)
        val third = submitN4WeeklyAssessment(second, 80)
        assertEquals(3, third.attemptsByWeek[1])
        assertTrue(1 in third.completedWeeks)
        assertEquals(2, third.currentWeek)
    }

    @Test fun finalWeekCompletesPlanWithoutSeventeenthWeek() {
        val progress = N4StudyProgress(
            currentWeek = N4_PLAN_WEEKS,
            completedWeeks = (1 until N4_PLAN_WEEKS).toSet()
        )
        val result = submitN4WeeklyAssessment(progress, 100)
        assertTrue(result.isComplete)
        assertEquals(N4_PLAN_WEEKS, result.currentWeek)
    }

    @Test fun pausedPlanRejectsAssessmentUntilResumed() {
        val paused = N4StudyProgress().pause()
        assertTrue(paused.paused)
        try {
            submitN4WeeklyAssessment(paused, 90)
            fail("A paused plan must reject an assessment.")
        } catch (_: IllegalArgumentException) {
            // Expected: no assessment can be submitted while paused.
        }
        val resumed = paused.resume()
        assertFalse(resumed.paused)
        assertEquals(2, submitN4WeeklyAssessment(resumed, 90).currentWeek)
    }

    @Test fun scoreAndAttemptHistoryAreRetainedAcrossWeeks() {
        val weekTwo = submitN4WeeklyAssessment(N4StudyProgress(), 80)
        val failed = submitN4WeeklyAssessment(weekTwo, 60)
        assertEquals(2, failed.currentWeek)
        assertEquals(1, failed.attemptsByWeek[2])
        assertEquals(80, failed.lastScoreByWeek[1])
        assertEquals(60, failed.lastScoreByWeek[2])
        val passed = submitN4WeeklyAssessment(failed, 75)
        assertEquals(3, passed.currentWeek)
        assertEquals(2, passed.attemptsByWeek[2])
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidScoreIsRejected() {
        submitN4WeeklyAssessment(N4StudyProgress(), 101)
    }

    @Test(expected = IllegalArgumentException::class)
    fun cannotSkipAnIncompleteWeek() {
        submitN4WeeklyAssessment(N4StudyProgress(currentWeek = 3, completedWeeks = setOf(1)), 90)
    }
}
