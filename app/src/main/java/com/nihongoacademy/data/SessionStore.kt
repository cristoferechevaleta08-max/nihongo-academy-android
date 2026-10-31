package com.nihongoacademy.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.sessionDataStore by preferencesDataStore(name = "nihongo_session")

class SessionStore(private val context: Context) {
    private val activeLanguage = stringPreferencesKey("active_language")
    private val currentUser = stringPreferencesKey("current_user")

    suspend fun saveLanguage(code: String) {
        context.sessionDataStore.edit { prefs -> prefs[activeLanguage] = code }
    }

    suspend fun saveUser(userId: String) {
        context.sessionDataStore.edit { prefs -> prefs[currentUser] = userId }
    }

    suspend fun readLanguage(): String? = context.sessionDataStore.data.first()[activeLanguage]
    suspend fun readUser(): String? = context.sessionDataStore.data.first()[currentUser]
}
