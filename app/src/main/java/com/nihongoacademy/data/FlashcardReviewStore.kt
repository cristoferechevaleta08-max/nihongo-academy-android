package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

private val Context.flashcardDataStore by preferencesDataStore(name = "nihongo_flashcards")

enum class ReviewRating { AGAIN, HARD, GOOD, EASY }

data class FlashcardSchedule(
    val cardId: String,
    val dueAt: Long,
    val intervalDays: Double = 0.0,
    val easeFactor: Double = 2.5,
    val repetitions: Int = 0,
    val lastReviewedAt: Long = 0L
)

data class FlashcardReviewResult(
    val schedule: FlashcardSchedule,
    val nextDueAt: Long
)

object SpacedRepetitionScheduler {
    private const val MINUTE = 60_000L
    private const val DAY = 86_400_000L

    fun review(current: FlashcardSchedule, rating: ReviewRating, now: Long = System.currentTimeMillis()): FlashcardReviewResult {
        val ease = when (rating) {
            ReviewRating.AGAIN -> (current.easeFactor - 0.2).coerceAtLeast(1.3)
            ReviewRating.HARD -> (current.easeFactor - 0.15).coerceAtLeast(1.3)
            ReviewRating.GOOD -> current.easeFactor
            ReviewRating.EASY -> current.easeFactor + 0.15
        }
        val repetitions = if (rating == ReviewRating.AGAIN) 0 else current.repetitions + 1
        val interval = when (rating) {
            ReviewRating.AGAIN -> 10.0 / (24.0 * 60.0)
            ReviewRating.HARD -> when (current.repetitions) {
                0 -> 1.0
                1 -> 2.0
                else -> (current.intervalDays * 1.2).coerceAtLeast(1.0)
            }
            ReviewRating.GOOD -> when (current.repetitions) {
                0 -> 1.0
                1 -> 3.0
                else -> (current.intervalDays * ease).coerceAtLeast(1.0)
            }
            ReviewRating.EASY -> when (current.repetitions) {
                0 -> 4.0
                1 -> 6.0
                else -> (current.intervalDays * ease * 1.3).coerceAtLeast(2.0)
            }
        }
        val delay = if (rating == ReviewRating.AGAIN) 10 * MINUTE else (interval * DAY).toLong()
        val updated = current.copy(
            dueAt = now + delay,
            intervalDays = interval,
            easeFactor = ease,
            repetitions = repetitions,
            lastReviewedAt = now
        )
        return FlashcardReviewResult(updated, updated.dueAt)
    }
}

class FlashcardReviewStore(private val context: Context) {
    private val gson = Gson()
    private fun key(languageCode: String) = stringPreferencesKey("schedule_${languageCode.lowercase()}_v1")

    suspend fun getSchedules(languageCode: String): Map<String, FlashcardSchedule> {
        val json = context.flashcardDataStore.data.first()[key(languageCode)] ?: return emptyMap()
        val type = object : TypeToken<Map<String, FlashcardSchedule>>() {}.type
        return runCatching { gson.fromJson<Map<String, FlashcardSchedule>>(json, type) ?: emptyMap() }
            .getOrDefault(emptyMap())
    }

    suspend fun recordReview(languageCode: String, cardId: String, rating: ReviewRating, now: Long = System.currentTimeMillis()): FlashcardSchedule {
        var updated = FlashcardSchedule(cardId = cardId, dueAt = now)
        context.flashcardDataStore.edit { preferences ->
            val type = object : TypeToken<Map<String, FlashcardSchedule>>() {}.type
            val existing = runCatching {
                gson.fromJson<Map<String, FlashcardSchedule>>(preferences[key(languageCode)], type) ?: emptyMap()
            }.getOrDefault(emptyMap())
            val current = existing[cardId] ?: FlashcardSchedule(cardId = cardId, dueAt = 0L)
            updated = SpacedRepetitionScheduler.review(current, rating, now).schedule
            preferences[key(languageCode)] = gson.toJson(existing + (cardId to updated))
        }
        return updated
    }
}
