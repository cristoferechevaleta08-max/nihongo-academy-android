package com.nihongo.academy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class N5KanjiSpacedRepetitionTest {
    @Test
    fun firstGoodReviewSchedulesOneDay() {
        val result = scheduleKanjiReview(
            KanjiReviewState.initial,
            KanjiReviewGrade.GOOD,
            1_000L
        )
        assertEquals(1, result.intervalDays)
        assertEquals(1, result.repetitions)
        assertEquals(86_401_000L, result.nextReviewAt)
    }

    @Test
    fun againSchedulesShortRetryAndResetsRepetitionCount() {
        val previous = KanjiReviewState(100L, 8, 4, 2.5)
        val result = scheduleKanjiReview(previous, KanjiReviewGrade.AGAIN, 1_000L)
        assertEquals(0, result.intervalDays)
        assertEquals(0, result.repetitions)
        assertEquals(601_000L, result.nextReviewAt)
    }

    @Test
    fun encodedStateCanBeDecoded() {
        val original = KanjiReviewState(123L, 3, 2, 2.35)
        assertEquals(original, KanjiReviewState.decode(original.encode()))
    }

    @Test
    fun invalidStoredStateIsIgnored() {
        assertNull(KanjiReviewState.decode("not-a-review-state"))
        assertNotNull(KanjiReviewState.decode(KanjiReviewState.initial.encode()))
    }
}
