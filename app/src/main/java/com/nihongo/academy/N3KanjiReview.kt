package com.nihongo.academy

import java.time.LocalDate

internal enum class N3ReviewRating { HARD, GOOD, EASY }

internal data class N3ReviewRecord(
    val character: String,
    val dueDate: LocalDate,
    val intervalDays: Int
)

/** Guarda cada ficha como carácter|día-epoch|intervalo, evitando dependencias extra. */
internal fun parseN3ReviewRecords(values: Set<String>): Map<String, N3ReviewRecord> =
    values.mapNotNull { value ->
        val parts = value.split("|")
        if (parts.size != 3 || parts[0].isBlank()) return@mapNotNull null
        val epochDay = parts[1].toLongOrNull() ?: return@mapNotNull null
        val interval = parts[2].toIntOrNull()?.takeIf { it >= 1 } ?: return@mapNotNull null
        val date = runCatching { LocalDate.ofEpochDay(epochDay) }.getOrNull()
            ?: return@mapNotNull null
        parts[0] to N3ReviewRecord(parts[0], date, interval)
    }.toMap()

internal fun serializeN3ReviewRecords(records: Map<String, N3ReviewRecord>): Set<String> =
    records.values.mapTo(mutableSetOf()) {
        "${it.character}|${it.dueDate.toEpochDay()}|${it.intervalDays}"
    }

internal fun n3KanjiDueForReview(
    catalog: List<N3KanjiEntry>,
    stored: Set<String>,
    today: LocalDate
): List<N3KanjiEntry> {
    val records = parseN3ReviewRecords(stored)
    return catalog.filter { entry ->
        val record = records[entry.character]
        record == null || !record.dueDate.isAfter(today)
    }
}

internal fun scheduleN3KanjiReview(
    stored: Set<String>,
    character: String,
    rating: N3ReviewRating,
    today: LocalDate
): Set<String> {
    val records = parseN3ReviewRecords(stored).toMutableMap()
    val previous = records[character]
    val nextInterval = when (rating) {
        N3ReviewRating.HARD -> if (previous == null) 1 else maxOf(1, previous.intervalDays / 2)
        N3ReviewRating.GOOD -> if (previous == null) 3 else previous.intervalDays * 2
        N3ReviewRating.EASY -> if (previous == null) 7 else previous.intervalDays * 3
    }
    records[character] = N3ReviewRecord(character, today.plusDays(nextInterval.toLong()), nextInterval)
    return serializeN3ReviewRecords(records)
}
