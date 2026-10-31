package com.nihongoacademy.model

import java.util.Calendar

private const val LESSON_XP = 25
private const val MODULE_XP = 100

data class StudyProgress(
    val languageCode: String = "JA",
    val completedModules: Set<String> = emptySet(),
    val completedLessons: Set<String> = emptySet(),
    val totalXp: Int = 0,
    val streakDays: Int = 0,
    val lastStudyDate: Long = 0L,
    val recentResults: List<StudyResult> = emptyList()
)

data class StudyResult(
    val lessonId: String,
    val moduleId: String,
    val xpEarned: Int,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val answeredAt: Long
)

object StudyProgressLogic {
    fun completeLesson(
        progress: StudyProgress,
        languageCode: String,
        moduleId: String,
        lessonId: String,
        correctAnswers: Int,
        totalQuestions: Int = 1
    ): StudyProgress {
        val safeTotal = totalQuestions.coerceAtLeast(1)
        val safeCorrect = correctAnswers.coerceIn(0, safeTotal)
        val ratio = safeCorrect.toFloat() / safeTotal.toFloat()
        val xpEarned = ((ratio * LESSON_XP).toInt()).coerceAtLeast(5)
        val now = System.currentTimeMillis()
        val updatedLessons = progress.completedLessons + lessonId
        val updatedResults = (progress.recentResults + StudyResult(
            lessonId = lessonId,
            moduleId = moduleId,
            xpEarned = xpEarned,
            correctAnswers = safeCorrect,
            totalQuestions = safeTotal,
            answeredAt = now
        )).takeLast(10)

        val updatedProgress = progress.copy(
            languageCode = languageCode,
            completedLessons = updatedLessons,
            totalXp = progress.totalXp + xpEarned,
            lastStudyDate = now,
            recentResults = updatedResults
        )

        return updatedProgress.copy(streakDays = calculateStreakDays(updatedProgress))
    }

    fun completeModule(
        progress: StudyProgress,
        languageCode: String,
        moduleId: String
    ): StudyProgress {
        val now = System.currentTimeMillis()
        val updatedModules = progress.completedModules + moduleId
        val updatedProgress = progress.copy(
            languageCode = languageCode,
            completedModules = updatedModules,
            totalXp = progress.totalXp + MODULE_XP,
            lastStudyDate = now
        )

        return updatedProgress.copy(streakDays = calculateStreakDays(updatedProgress))
    }

    fun calculateStreakDays(progress: StudyProgress): Int {
        if (progress.lastStudyDate == 0L && progress.recentResults.isEmpty()) return 0

        val dates = mutableSetOf<Int>()
        progress.recentResults.forEach { dates += dateKey(it.answeredAt) }
        if (progress.lastStudyDate != 0L) {
            dates += dateKey(progress.lastStudyDate)
        }

        if (dates.isEmpty()) return 0

        var streak = 0
        var currentDayKey = dateKey(System.currentTimeMillis())

        while (dates.contains(currentDayKey)) {
            streak += 1
            currentDayKey = previousDayKey(currentDayKey)
        }

        return streak.coerceAtLeast(1)
    }

    private fun dateKey(epochMillis: Long): Int {
        val calendar = Calendar.getInstance().apply { timeInMillis = epochMillis }
        return calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR)
    }

    private fun previousDayKey(dayKey: Int): Int {
        val year = dayKey / 1000
        val dayOfYear = dayKey % 1000
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.DAY_OF_YEAR, dayOfYear)
        }
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return calendar.get(Calendar.YEAR) * 1000 + calendar.get(Calendar.DAY_OF_YEAR)
    }
}

val StudyProgress.completedModuleCount: Int
    get() = completedModules.size
