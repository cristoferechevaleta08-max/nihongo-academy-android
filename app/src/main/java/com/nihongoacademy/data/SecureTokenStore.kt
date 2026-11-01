package com.nihongoacademy.data

import android.content.Context

/**
 * Stores only the random local user identifier for session restoration.
 * Passwords are never stored here; password verifiers are PBKDF2 hashes in Room.
 */
class SessionStore(context: Context, fileName: String = "nihongo_session") {
    private val prefs = context.applicationContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("session_user_id", token).apply()
    }

    fun readToken(): String? = prefs.getString("session_user_id", null)

    fun clearToken() {
        prefs.edit().remove("session_user_id").apply()
    }
}
