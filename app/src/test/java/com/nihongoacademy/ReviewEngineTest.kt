package com.nihongoacademy

import com.nihongoacademy.domain.AdaptiveReviewEngine
import com.nihongoacademy.domain.PasswordValidator
import com.nihongoacademy.domain.ReviewCard
import com.nihongoacademy.data.FlashcardSchedule
import com.nihongoacademy.data.ReviewRating
import com.nihongoacademy.data.SpacedRepetitionScheduler
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ReviewEngineTest {
    @Test
    fun failedCardGetsShortRetryWindow() {
        val engine = AdaptiveReviewEngine()
        val card = ReviewCard("1", "こんにちは", "hola", System.currentTimeMillis(), 2, 3)
        val result = engine.scheduleAfterResult(card, false)
        assertThat(result.streak).isEqualTo(0)
    }

    @Test
    fun validPasswordSucceeds() {
        val validator = PasswordValidator()
        val result = validator.validate("abc12345")
        assertThat(result.valid).isTrue()
    }
    @Test
    fun againSchedulesTenMinuteRetry() {
        val now = 1_000_000L
        val result = SpacedRepetitionScheduler.review(
            FlashcardSchedule("card-1", 0L), ReviewRating.AGAIN, now
        )
        assertThat(result.nextDueAt).isEqualTo(now + 10 * 60_000L)
        assertThat(result.schedule.repetitions).isEqualTo(0)
    }

    @Test
    fun goodSchedulesOneDayThenThreeDays() {
        val now = 1_000_000L
        val first = SpacedRepetitionScheduler.review(
            FlashcardSchedule("card-1", 0L), ReviewRating.GOOD, now
        )
        val second = SpacedRepetitionScheduler.review(
            first.schedule, ReviewRating.GOOD, now
        )
        assertThat(first.nextDueAt).isEqualTo(now + 86_400_000L)
        assertThat(second.nextDueAt).isEqualTo(now + 3 * 86_400_000L)
    }

    @Test
    fun easyRatingStartsWithFourDayInterval() {
        val now = 1_000_000L
        val result = SpacedRepetitionScheduler.review(
            FlashcardSchedule("card-1", 0L), ReviewRating.EASY, now
        )
        assertThat(result.nextDueAt).isEqualTo(now + 4 * 86_400_000L)
    }

}
