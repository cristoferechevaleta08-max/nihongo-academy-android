package com.nihongoacademy.data

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "nihongo_prefs")

class PreferencesStore(private val context: Context) {
    private val activeLanguageKey = stringPreferencesKey("active_language")
    suspend fun saveActiveLanguage(code: String) {
        context.dataStore.edit { it[activeLanguageKey] = code }
    }
    suspend fun getActiveLanguage(): String? = context.dataStore.data.first()[activeLanguageKey]
}
