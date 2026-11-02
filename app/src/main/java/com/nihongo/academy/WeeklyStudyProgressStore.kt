package com.nihongo.academy

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val weeklyCurrentKey = intPreferencesKey("n5_weekly_current")
private val weeklyCompletedKey = stringSetPreferencesKey("n5_weekly_completed")
private val weeklyAttemptsKey = stringSetPreferencesKey("n5_weekly_attempts")
private val weeklyPausedKey = booleanPreferencesKey("n5_weekly_paused")
private val weeklyAssessmentKey = stringPreferencesKey("n5_weekly_last_assessment")

/** Lee el avance semanal guardado localmente para sobrevivir al cierre de la app. */
internal fun DataStore<Preferences>.weeklyStudyProgress(): Flow<WeeklyStudyProgress> =
    data.map { preferences ->
        val completed = preferences[weeklyCompletedKey].orEmpty()
            .mapNotNull(String::toIntOrNull).filter { it in 1..N5_PLAN_WEEKS }.toSet()
        val attempts = preferences[weeklyAttemptsKey].orEmpty().mapNotNull { entry ->
            val parts = entry.split(":")
            val week = parts.getOrNull(0)?.toIntOrNull()
            val count = parts.getOrNull(1)?.toIntOrNull()
            if (week != null && count != null && week in 1..N5_PLAN_WEEKS && count >= 0) week to count else null
        }.toMap()
        WeeklyStudyProgress(
            currentWeek = (preferences[weeklyCurrentKey] ?: 1).coerceIn(1, N5_PLAN_WEEKS),
            completedWeeks = completed,
            attemptsByWeek = attempts,
            paused = preferences[weeklyPausedKey] ?: false,
            lastAssessment = decodeWeeklyAssessment(preferences[weeklyAssessmentKey])
        )
    }

internal suspend fun DataStore<Preferences>.saveWeeklyStudyProgress(progress: WeeklyStudyProgress) {
    edit { preferences ->
        preferences[weeklyCurrentKey] = progress.currentWeek
        preferences[weeklyCompletedKey] = progress.completedWeeks.map(Int::toString).toSet()
        preferences[weeklyAttemptsKey] = progress.attemptsByWeek.map { (week, count) -> "$week:$count" }.toSet()
        preferences[weeklyPausedKey] = progress.paused
        progress.lastAssessment?.let { preferences[weeklyAssessmentKey] = encodeWeeklyAssessment(it) }
            ?: preferences.remove(weeklyAssessmentKey)
    }
}

private fun encodeWeeklyAssessment(value: WeeklyAssessment): String =
    listOf(value.week, value.correctAnswers, value.totalQuestions, value.incorrectPercent,
        value.passed, value.requiresExtraPractice).joinToString("|")

private fun decodeWeeklyAssessment(value: String?): WeeklyAssessment? {
    if (value == null) return null
    val fields = value.split("|")
    if (fields.size != 6) return null
    val week = fields[0].toIntOrNull() ?: return null
    val correct = fields[1].toIntOrNull() ?: return null
    val total = fields[2].toIntOrNull() ?: return null
    val incorrect = fields[3].toIntOrNull() ?: return null
    if (week !in 1..N5_PLAN_WEEKS || total <= 0 || correct !in 0..total) return null
    return WeeklyAssessment(week, correct, total, incorrect, fields[4].toBoolean(), fields[5].toBoolean())
}

/** Aplica una evaluación y persiste el resultado en una única operación DataStore. */
internal suspend fun DataStore<Preferences>.recordWeeklyAssessment(
    correctAnswers: Int,
    totalQuestions: Int
): WeeklyStudyProgress {
    var updated = WeeklyStudyProgress()
    edit { preferences ->
        val completed = preferences[weeklyCompletedKey].orEmpty()
            .mapNotNull(String::toIntOrNull).filter { it in 1..N5_PLAN_WEEKS }.toSet()
        val attempts = preferences[weeklyAttemptsKey].orEmpty().mapNotNull { entry ->
            val parts = entry.split(":")
            val week = parts.getOrNull(0)?.toIntOrNull()
            val count = parts.getOrNull(1)?.toIntOrNull()
            if (week != null && count != null && week in 1..N5_PLAN_WEEKS && count >= 0) week to count else null
        }.toMap()
        val current = WeeklyStudyProgress(
            currentWeek = (preferences[weeklyCurrentKey] ?: 1).coerceIn(1, N5_PLAN_WEEKS),
            completedWeeks = completed,
            attemptsByWeek = attempts,
            paused = preferences[weeklyPausedKey] ?: false,
            lastAssessment = decodeWeeklyAssessment(preferences[weeklyAssessmentKey])
        )
        updated = submitWeeklyAssessment(current, correctAnswers, totalQuestions)
        preferences[weeklyCurrentKey] = updated.currentWeek
        preferences[weeklyCompletedKey] = updated.completedWeeks.map(Int::toString).toSet()
        preferences[weeklyAttemptsKey] = updated.attemptsByWeek.map { (week, count) -> "$week:$count" }.toSet()
        preferences[weeklyPausedKey] = updated.paused
        updated.lastAssessment?.let { preferences[weeklyAssessmentKey] = encodeWeeklyAssessment(it) }
    }
    return updated
}
