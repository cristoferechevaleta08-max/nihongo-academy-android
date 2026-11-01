package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "nihongo_prefs")

data class StudySettings(
    val showPronunciation: Boolean = true,
    val paperGridEnabled: Boolean = false,
    val reduceMotion: Boolean = false
)

class PreferencesStore(private val context: Context) {
    private val activeLanguageKey = stringPreferencesKey("active_language")
    private val showPronunciationKey = booleanPreferencesKey("show_pronunciation")
    private val paperGridKey = booleanPreferencesKey("paper_grid_enabled")
    private val reduceMotionKey = booleanPreferencesKey("reduce_motion")

    suspend fun saveActiveLanguage(code: String) {
        context.dataStore.edit { it[activeLanguageKey] = code }
    }

    suspend fun getActiveLanguage(): String? = context.dataStore.data.first()[activeLanguageKey]

    suspend fun getStudySettings(): StudySettings {
        val preferences = context.dataStore.data.first()
        return StudySettings(
            showPronunciation = preferences[showPronunciationKey] ?: true,
            paperGridEnabled = preferences[paperGridKey] ?: false,
            reduceMotion = preferences[reduceMotionKey] ?: false
        )
    }

    suspend fun setShowPronunciation(enabled: Boolean) {
        context.dataStore.edit { it[showPronunciationKey] = enabled }
    }

    suspend fun setPaperGridEnabled(enabled: Boolean) {
        context.dataStore.edit { it[paperGridKey] = enabled }
    }

    suspend fun setReduceMotion(enabled: Boolean) {
        context.dataStore.edit { it[reduceMotionKey] = enabled }
    }
}
