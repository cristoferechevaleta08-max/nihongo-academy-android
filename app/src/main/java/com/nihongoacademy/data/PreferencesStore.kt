package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "nihongo_prefs")

data class StudySettings(
    val showPronunciation: Boolean = true,
    val reduceMotion: Boolean = false
)

class PreferencesStore(private val context: Context) {
    private val activeLanguageKey = stringPreferencesKey("active_language")
    private val showPronunciationKey = booleanPreferencesKey("show_pronunciation")
    private val reduceMotionKey = booleanPreferencesKey("reduce_motion")
    private val themeModeKey = stringPreferencesKey("theme_mode")

    suspend fun saveActiveLanguage(code: String) {
        context.dataStore.edit { it[activeLanguageKey] = code }
    }

    suspend fun getActiveLanguage(): String? = context.dataStore.data.first()[activeLanguageKey]

    fun themeModeFlow(): Flow<String> = context.dataStore.data.map { it[themeModeKey] ?: "system" }

    suspend fun setThemeMode(mode: String) {
        require(mode in setOf("system", "light", "dark"))
        context.dataStore.edit { it[themeModeKey] = mode }
    }

    fun studySettingsFlow(): Flow<StudySettings> = context.dataStore.data.map { preferences ->
        StudySettings(
            showPronunciation = preferences[showPronunciationKey] ?: true,
            reduceMotion = preferences[reduceMotionKey] ?: false
        )
    }

    suspend fun getStudySettings(): StudySettings = studySettingsFlow().first()

    suspend fun setShowPronunciation(enabled: Boolean) {
        context.dataStore.edit { it[showPronunciationKey] = enabled }
    }

    suspend fun setReduceMotion(enabled: Boolean) {
        context.dataStore.edit { it[reduceMotionKey] = enabled }
    }
}
