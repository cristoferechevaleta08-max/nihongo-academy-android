package com.nihongo.academy

import kotlin.math.ceil

/** Estado local de repetición espaciada. nextReviewAt usa milisegundos Unix. */
internal data class KanjiReviewState(
    val nextReviewAt: Long,
    val intervalDays: Int,
    val repetitions: Int,
    val easeFactor: Double
) {
    fun encode(): String = "$nextReviewAt|$intervalDays|$repetitions|$easeFactor"

    companion object {
        val initial = KanjiReviewState(
            nextReviewAt = 0L,
            intervalDays = 0,
            repetitions = 0,
            easeFactor = 2.5
        )

        fun decode(value: String): KanjiReviewState? {
            val parts = value.split("|")
            if (parts.size != 4) return null
            return runCatching {
                KanjiReviewState(
                    nextReviewAt = parts[0].toLong(),
                    intervalDays = parts[1].toInt(),
                    repetitions = parts[2].toInt(),
                    easeFactor = parts[3].toDouble()
                )
            }.getOrNull()?.takeIf {
                it.intervalDays >= 0 && it.repetitions >= 0 && it.easeFactor in 1.3..3.0
            }
        }
    }
}

internal enum class KanjiReviewGrade { AGAIN, HARD, GOOD, EASY }

internal fun scheduleKanjiReview(
    previous: KanjiReviewState,
    grade: KanjiReviewGrade,
    nowMillis: Long
): KanjiReviewState {
    val ease = when (grade) {
        KanjiReviewGrade.AGAIN -> (previous.easeFactor - 0.2).coerceAtLeast(1.3)
        KanjiReviewGrade.HARD -> (previous.easeFactor - 0.15).coerceAtLeast(1.3)
        KanjiReviewGrade.GOOD -> previous.easeFactor
        KanjiReviewGrade.EASY -> (previous.easeFactor + 0.15).coerceAtMost(3.0)
    }

    if (grade == KanjiReviewGrade.AGAIN) {
        return KanjiReviewState(
            nextReviewAt = nowMillis + 10 * 60 * 1000L,
            intervalDays = 0,
            repetitions = 0,
            easeFactor = ease
        )
    }

    val interval = when (grade) {
        KanjiReviewGrade.HARD -> when (previous.repetitions) {
            0 -> 1
            else -> ceil((previous.intervalDays.coerceAtLeast(1) * 1.2)).toInt()
        }
        KanjiReviewGrade.GOOD -> when (previous.repetitions) {
            0 -> 1
            1 -> 3
            else -> ceil(previous.intervalDays.coerceAtLeast(1) * previous.easeFactor).toInt()
        }
        KanjiReviewGrade.EASY -> when (previous.repetitions) {
            0 -> 2
            1 -> 4
            else -> ceil(previous.intervalDays.coerceAtLeast(1) * (previous.easeFactor + 0.3)).toInt()
        }
        KanjiReviewGrade.AGAIN -> 0
    }.coerceAtLeast(1)

    return KanjiReviewState(
        nextReviewAt = nowMillis + interval * 24 * 60 * 60 * 1000L,
        intervalDays = interval,
        repetitions = previous.repetitions + 1,
        easeFactor = ease
    )
}
