package com.nihongoacademy

import com.nihongoacademy.domain.AdaptiveReviewEngine
import com.nihongoacademy.domain.PasswordValidator
import com.nihongoacademy.domain.ReviewCard
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
}
