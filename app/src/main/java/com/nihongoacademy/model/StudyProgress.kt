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
        val safeCorrectAnswers = correctAnswers.coerceIn(0, totalQuestions.coerceAtLeast(1))
        val xpEarned = ((safeCorrectAnswers.toFloat() / totalQuestions.coerceAtLeast(1).toFloat()) * LESSON_XP).toInt().coerceAtLeast(5)
        val now = System.currentTimeMillis()
        val updatedLessons = progress.completedLessons + lessonId
        val updatedResults = (progress.recentResults + StudyResult(
            lessonId = lessonId,
            moduleId = moduleId,
            xpEarned = xpEarned,
            correctAnswers = safeCorrectAnswers,
            totalQuestions = totalQuestions,
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
        if (progress.lastStudyDate == 0L) return 0

        val now = System.currentTimeMillis()
        val calendar = Calendar.getInstance().apply { timeInMillis = now }
        val today = calendar.get(Calendar.DAY_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)

        var streak = 0
        var currentDay = today
        var currentYearValue = currentYear

        val dates = mutableSetOf<Int>()
        for (result in progress.recentResults) {
            val date = Calendar.getInstance().apply { timeInMillis = result.answeredAt }.let {
                it.get(Calendar.DAY_OF_YEAR) * 10000 + it.get(Calendar.YEAR)
            }
            dates.add(date)
        }

        if (progress.lastStudyDate > 0L) {
            dates.add(Calendar.getInstance().apply { timeInMillis = progress.lastStudyDate }.let {
                it.get(Calendar.DAY_OF_YEAR) * 10000 + it.get(Calendar.YEAR)
            })
        }

        while (true) {
            val key = currentDay * 10000 + currentYearValue
            if (key in dates) {
                streak += 1
                currentDay -= 1
                if (currentDay <= 0) {
                    currentYearValue -= 1
                    val temp = Calendar.getInstance().apply { set(Calendar.YEAR, currentYearValue) }
                    currentDay = temp.getActualMaximum(Calendar.DAY_OF_YEAR)
                }
            } else {
                break
            }
        }

        return streak.coerceAtLeast(1)
    }
}

val StudyProgress.completedModuleCount: Int
    get() = completedModules.size
