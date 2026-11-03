package com.nihongo.academy

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val n4CurrentWeekKey = intPreferencesKey("n4_study_current_week")
private val n4CompletedWeeksKey = stringSetPreferencesKey("n4_study_completed_weeks")
private val n4AttemptsKey = stringSetPreferencesKey("n4_study_attempts")
private val n4ScoresKey = stringSetPreferencesKey("n4_study_last_scores")
private val n4PausedKey = booleanPreferencesKey("n4_study_paused")

/** El progreso N4 se almacena separado del N5 y permanece en el dispositivo. */
internal fun DataStore<Preferences>.n4StudyProgress(): Flow<N4StudyProgress> =
    data.map { preferences ->
        val completed = preferences[n4CompletedWeeksKey].orEmpty()
            .mapNotNull(String::toIntOrNull).filter { it in 1..N4_PLAN_WEEKS }.toSet()
        val attempts = decodeWeekValues(preferences[n4AttemptsKey])
        val scores = decodeWeekValues(preferences[n4ScoresKey]).filterValues { it in 0..100 }
        N4StudyProgress(
            currentWeek = (preferences[n4CurrentWeekKey] ?: 1).coerceIn(1, N4_PLAN_WEEKS),
            completedWeeks = completed,
            attemptsByWeek = attempts,
            lastScoreByWeek = scores,
            paused = preferences[n4PausedKey] ?: false
        )
    }

private fun decodeWeekValues(values: Set<String>?): Map<Int, Int> =
    values.orEmpty().mapNotNull { entry ->
        val parts = entry.split(":")
        val week = parts.getOrNull(0)?.toIntOrNull()
        val value = parts.getOrNull(1)?.toIntOrNull()
        if (week != null && value != null && week in 1..N4_PLAN_WEEKS && value >= 0) week to value else null
    }.toMap()

private fun encodeWeekValues(values: Map<Int, Int>): Set<String> =
    values.map { (week, value) -> "$week:$value" }.toSet()

private fun androidx.datastore.preferences.core.MutablePreferences.writeN4Progress(progress: N4StudyProgress) {
    this[n4CurrentWeekKey] = progress.currentWeek
    this[n4CompletedWeeksKey] = progress.completedWeeks.map(Int::toString).toSet()
    this[n4AttemptsKey] = encodeWeekValues(progress.attemptsByWeek)
    this[n4ScoresKey] = encodeWeekValues(progress.lastScoreByWeek)
    this[n4PausedKey] = progress.paused
}

internal suspend fun DataStore<Preferences>.saveN4StudyProgress(progress: N4StudyProgress) {
    edit { it.writeN4Progress(progress) }
}

/** Registra el intento y el avance en una sola transacción local. */
internal suspend fun DataStore<Preferences>.recordN4WeeklyAssessment(scorePercent: Int): N4StudyProgress {
    var updated = N4StudyProgress()
    edit { preferences ->
        val completed = preferences[n4CompletedWeeksKey].orEmpty()
            .mapNotNull(String::toIntOrNull).filter { it in 1..N4_PLAN_WEEKS }.toSet()
        val current = N4StudyProgress(
            currentWeek = (preferences[n4CurrentWeekKey] ?: 1).coerceIn(1, N4_PLAN_WEEKS),
            completedWeeks = completed,
            attemptsByWeek = decodeWeekValues(preferences[n4AttemptsKey]),
            lastScoreByWeek = decodeWeekValues(preferences[n4ScoresKey]).filterValues { it in 0..100 },
            paused = preferences[n4PausedKey] ?: false
        )
        updated = submitN4WeeklyAssessment(current, scorePercent)
        preferences.writeN4Progress(updated)
    }
    return updated
}
