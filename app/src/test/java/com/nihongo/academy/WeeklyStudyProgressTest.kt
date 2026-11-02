package com.nihongo.academy

import org.junit.Assert.*
import org.junit.Test

class WeeklyStudyProgressTest {
    @Test fun moreThan30PercentIncorrectRepeatsSameWeek() {
        val result = submitWeeklyAssessment(WeeklyStudyProgress(), 6, 10)
        assertEquals(1, result.currentWeek)
        assertTrue(result.lastAssessment!!.requiresExtraPractice)
        assertFalse(result.lastAssessment!!.passed)
        assertEquals(1, result.attemptsByWeek[1])
        assertTrue(result.completedWeeks.isEmpty())
    }

    @Test fun exactly30PercentIncorrectPassesAndUnlocksNextWeek() {
        val result = submitWeeklyAssessment(WeeklyStudyProgress(), 7, 10)
        assertEquals(2, result.currentWeek)
        assertTrue(1 in result.completedWeeks)
        assertTrue(isStudyWeekUnlocked(result, 2))
        assertFalse(isStudyWeekUnlocked(result, 3))
    }

    @Test fun pausePreservesWeekAndBlocksAssessmentUntilResumed() {
        val paused = WeeklyStudyProgress().pause()
        assertEquals(1, paused.currentWeek)
        assertTrue(paused.paused)
        try {
            submitWeeklyAssessment(paused, 10, 10)
            fail("No debe aceptar una evaluación con el plan pausado")
        } catch (_: IllegalArgumentException) {
            // Expected.
        }
        assertEquals(1, paused.resume().currentWeek)
        assertFalse(paused.resume().paused)
    }

    @Test fun repeatedAssessmentsAccumulateAttemptsWithoutUnlocking() {
        val first = submitWeeklyAssessment(WeeklyStudyProgress(), 5, 10)
        val second = submitWeeklyAssessment(first, 6, 10)
        assertEquals(2, second.attemptsByWeek[1])
        assertEquals(1, second.currentWeek)
        assertTrue(second.completedWeeks.isEmpty())
    }

    @Test fun lastWeekCompletesPlanWithoutWeekThirteen() {
        val progress = WeeklyStudyProgress(
            currentWeek = N5_PLAN_WEEKS,
            completedWeeks = (1 until N5_PLAN_WEEKS).toSet()
        )
        val result = submitWeeklyAssessment(progress, 10, 10)
        assertEquals(N5_PLAN_WEEKS, result.currentWeek)
        assertTrue(result.isPlanComplete)
    }


    @Test fun cannotSubmitAssessmentForLaterWeekWhenEarlierWeeksAreIncomplete() {
        val inconsistentProgress = WeeklyStudyProgress(
            currentWeek = 3,
            completedWeeks = setOf(1)
        )
        try {
            submitWeeklyAssessment(inconsistentProgress, 10, 10)
            fail("No debe permitir saltar la semana 2")
        } catch (_: IllegalArgumentException) {
            // Expected: week 2 has not been approved.
        }
    }

    @Test fun planCompletionRequiresEveryWeekToBeApproved() {
        val incomplete = WeeklyStudyProgress(
            currentWeek = N5_PLAN_WEEKS,
            completedWeeks = (1..N5_PLAN_WEEKS).filter { it != 7 }.toSet()
        )
        assertFalse(incomplete.isPlanComplete)
    }

    @Test(expected = IllegalArgumentException::class)
    fun emptyAssessmentIsRejected() {
        submitWeeklyAssessment(WeeklyStudyProgress(), 0, 0)
    }
}
