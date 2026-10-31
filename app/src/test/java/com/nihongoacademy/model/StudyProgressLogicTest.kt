package com.nihongoacademy.model

import java.util.Calendar

private const val EXAM_XP_BASE = 120
private const val EXAM_XP_BONUS = 60
private const val LEVEL_THRESHOLD = 500

data class StudyProgress(
    val languageCode: String = "JA",
    val completedLessons: Set<String> = emptySet(),
    val completedExams: Set<String> = emptySet(),
    val totalXp: Int = 0,
    val currentLevel: Int = 1,
    val xpToNextLevel: Int = LEVEL_THRESHOLD,
    val unlockedRewards: Set<String> = emptySet(),
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
    val answeredAt: Long,
    val isExam: Boolean = false
)

object StudyProgressLogic {
    fun completeExam(
        progress: StudyProgress,
        languageCode: String,
        moduleId: String,
        examId: String,
        correctAnswers: Int,
        totalQuestions: Int
    ): StudyProgress {
        val safeTotal = totalQuestions.coerceAtLeast(1)
        val safeCorrect = correctAnswers.coerceIn(0, safeTotal)
        val ratio = safeCorrect.toFloat() / safeTotal.toFloat()

        val examXp = when {
            ratio >= 1f -> EXAM_XP_BASE + EXAM_XP_BONUS + 80
            ratio >= 0.8f -> EXAM_XP_BASE + EXAM_XP_BONUS
            ratio >= 0.6f -> EXAM_XP_BASE
            else -> 0
        }

        val now = System.currentTimeMillis()
        val updatedExams = progress.completedExams + examId
        val updatedResults = (progress.recentResults + StudyResult(
            lessonId = examId,
            moduleId = moduleId,
            xpEarned = examXp,
            correctAnswers = safeCorrect,
            totalQuestions = safeTotal,
            answeredAt = now,
            isExam = true
        )).takeLast(10)

        val baseXp = progress.totalXp + examXp
        val nextLevel = calculateLevel(baseXp)
        val rewards = buildRewards(nextLevel, progress.unlockedRewards)

        val updatedProgress = progress.copy(
            languageCode = languageCode,
            completedExams = updatedExams,
            totalXp = baseXp,
            currentLevel = nextLevel,
            xpToNextLevel = calculateXpToNextLevel(baseXp),
            unlockedRewards = rewards,
            lastStudyDate = now,
            recentResults = updatedResults
        )

        return updatedProgress.copy(streakDays = calculateStreakDays(updatedProgress))
    }

    fun calculateLevel(totalXp: Int): Int = (totalXp / LEVEL_THRESHOLD) + 1

    fun calculateXpToNextLevel(totalXp: Int): Int {
        val currentLevel = calculateLevel(totalXp)
        val currentFloor = (currentLevel - 1) * LEVEL_THRESHOLD
        return LEVEL_THRESHOLD - (totalXp - currentFloor)
    }

    fun buildRewards(level: Int, currentRewards: Set<String>): Set<String> {
        val rewards = mutableSetOf<String>()
        rewards.addAll(currentRewards)

        val rewardsByLevel = mapOf(
            1 to "Insignia de Iniciado",
            2 to "Trofeo de Estudiante",
            3 to "Bono de Práctica",
            4 to "Medalla de Dominio",
            5 to "Lote de Reforzamiento"
        )

        rewardsByLevel[level]?.let { rewards += it }
        return rewards
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
    get() = completedExams.size
