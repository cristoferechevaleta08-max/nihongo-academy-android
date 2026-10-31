package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.nihongoacademy.model.StudyProgress
import com.nihongoacademy.model.StudyProgressLogic
import kotlinx.coroutines.flow.first

private val Context.studyProgressDataStore by preferencesDataStore(name = "nihongo_study_progress")

class StudyProgressStore(private val context: Context) {
    private val gson = Gson()
    private val progressKey = stringPreferencesKey("progress_v1")

    suspend fun saveProgress(progress: StudyProgress) {
        context.studyProgressDataStore.edit { prefs ->
            prefs[progressKey] = gson.toJson(progress)
        }
    }

    suspend fun getProgress(): StudyProgress {
        val json = context.studyProgressDataStore.data.first()[progressKey] ?: return StudyProgress()
        return runCatching { gson.fromJson(json, StudyProgress::class.java) }
            .getOrDefault(StudyProgress())
    }

    suspend fun recordLessonResult(
        languageCode: String,
        moduleId: String,
        lessonId: String,
        correctAnswers: Int,
        totalQuestions: Int = 1
    ): StudyProgress {
        val current = getProgress().copy(languageCode = languageCode)
        val updated = StudyProgressLogic.completeLesson(
            progress = current,
            languageCode = languageCode,
            moduleId = moduleId,
            lessonId = lessonId,
            correctAnswers = correctAnswers,
            totalQuestions = totalQuestions
        )
        saveProgress(updated)
        return updated
    }

    suspend fun completeModule(languageCode: String, moduleId: String): StudyProgress {
        val current = getProgress().copy(languageCode = languageCode)
        val updated = StudyProgressLogic.completeModule(
            progress = current,
            languageCode = languageCode,
            moduleId = moduleId
        )
        saveProgress(updated)
        return updated
    }
}
