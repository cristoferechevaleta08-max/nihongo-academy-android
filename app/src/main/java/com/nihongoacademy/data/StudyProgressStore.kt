package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.nihongoacademy.model.StudyProgress
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

    suspend fun markLessonCompleted(languageCode: String, lessonId: String): StudyProgress {
        val current = getProgress().copy(languageCode = languageCode)
        val updatedLessons = current.completedLessons.toMutableSet().apply { add(lessonId) }
        val updated = current.copy(
            completedLessons = updatedLessons,
            totalXp = current.totalXp + 15,
            lastStudyDate = System.currentTimeMillis()
        )
        saveProgress(updated)
        return updated
    }

    suspend fun markModuleCompleted(languageCode: String, moduleId: String): StudyProgress {
        val current = getProgress().copy(languageCode = languageCode)
        val updatedModules = current.completedModules.toMutableSet().apply { add(moduleId) }
        val updated = current.copy(
            completedModules = updatedModules,
            totalXp = current.totalXp + 100,
            streakDays = if (current.lastStudyDate == 0L) 1 else current.streakDays + 1,
            lastStudyDate = System.currentTimeMillis()
        )
        saveProgress(updated)
        return updated
    }
}
