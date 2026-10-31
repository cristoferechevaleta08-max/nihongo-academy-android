package com.nihongoacademy

import com.google.common.truth.Truth.assertThat
import com.nihongoacademy.model.LanguageCatalogFactory
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.model.StudyProgressLogic
import org.junit.Test

class StudyProgressLogicTest {
    @Test
    fun firstPerfectLessonAwardsXpAndRecordsCompletion() {
        val result = StudyProgressLogic.completeLesson(
            progress = StudyProgress(),
            languageCode = "JA",
            moduleId = "module_1",
            lessonId = "m1_l1",
            correctAnswers = 1,
            totalQuestions = 1
        )

        assertThat(result.totalXp).isEqualTo(10)
        assertThat(result.completedLessons).contains("m1_l1")
    }

    @Test
    fun repeatingCompletedLessonDoesNotAwardXpAgain() {
        val first = StudyProgressLogic.completeLesson(
            StudyProgress(), "JA", "module_1", "m1_l1", 1, 1
        )
        val second = StudyProgressLogic.completeLesson(
            first, "JA", "module_1", "m1_l1", 1, 1
        )

        assertThat(second.totalXp).isEqualTo(10)
    }

    @Test
    fun incorrectLessonDoesNotAwardXp() {
        val result = StudyProgressLogic.completeLesson(
            StudyProgress(), "JA", "module_1", "m1_l1", 0, 1
        )
        assertThat(result.totalXp).isEqualTo(0)
    }

    @Test
    fun catalogContainsExactlyRequestedFifteenLanguages() {
        val codes = LanguageCatalogFactory.buildSupportedLanguages().map { it.id }.toSet()
        assertThat(codes).containsExactly(
            "ja", "en", "ko", "zh-Hans", "fr", "de", "it", "pt",
            "es", "ru", "ar", "nl", "tr", "hi", "th"
        )
    }
}
