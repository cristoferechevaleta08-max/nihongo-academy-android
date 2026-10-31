package com.nihongoacademy.domain

import kotlin.math.max

data class ReviewCard(
    val id: String,
    val prompt: String,
    val answer: String,
    val dueAt: Long,
    val streak: Int,
    val intervalDays: Int
)

class AdaptiveReviewEngine {
    fun scheduleAfterResult(card: ReviewCard, success: Boolean): ReviewCard {
        val nextStreak = if (success) card.streak + 1 else 0
        val nextInterval = if (success) {
            when {
                nextStreak >= 3 -> max(card.intervalDays * 2, 3)
                nextStreak >= 2 -> max(card.intervalDays + 1, 2)
                else -> 1
            }
        } else {
            0
        }

        val delayMs = if (success) {
            nextInterval * 24L * 60L * 60L * 1000L
        } else {
            60L * 60L * 1000L
        }

        return card.copy(
            streak = nextStreak,
            intervalDays = nextInterval,
            dueAt = System.currentTimeMillis() + delayMs
        )
    }
}
