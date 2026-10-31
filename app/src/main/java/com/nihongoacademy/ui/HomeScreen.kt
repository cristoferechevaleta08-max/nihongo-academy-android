package com.nihongoacademy.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StudyProgressLogicTest {
    @Test
    fun `completeLesson awards xp and stores result`() {
        val progress = StudyProgress()

        val updated = StudyProgressLogic.completeLesson(
            progress = progress,
            languageCode = "JA",
            moduleId = "module_1",
            lessonId = "m1_l1",
            correctAnswers = 1,
            totalQuestions = 1
        )

        assertThat(updated.totalXp).isEqualTo(25)
        assertThat(updated.completedLessons).contains("m1_l1")
        assertThat(updated.recentResults).hasSize(1)
    }

    @Test
    fun `completeModule adds module bonus xp`() {
        val progress = StudyProgress(totalXp = 50)

        val updated = StudyProgressLogic.completeModule(
            progress = progress,
            languageCode = "JA",
            moduleId = "module_2"
        )

        assertThat(updated.totalXp).isEqualTo(150)
        assertThat(updated.completedModules).contains("module_2")
    }

    @Test
    fun `calculateStreakDays counts consecutive study days`() {
        val now = System.currentTimeMillis()
        val previousDay = now - 24 * 60 * 60 * 1000L

        val progress = StudyProgress(
            lastStudyDate = now,
            recentResults = listOf(
                StudyResult(
                    lessonId = "m1_l1",
                    moduleId = "module_1",
                    xpEarned = 25,
                    correctAnswers = 1,
                    totalQuestions = 1,
                    answeredAt = previousDay
                )
            )
        )

        val streak = StudyProgressLogic.calculateStreakDays(progress)
        assertThat(streak).isAtLeast(1)
    }
}
