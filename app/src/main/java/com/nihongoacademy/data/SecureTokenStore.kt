package com.nihongoacademy.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureTokenStore(
    context: Context,
    fileName: String = "secure_session"
) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        fileName,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        prefs.edit().putString("session_token", token).apply()
    }

    fun readToken(): String? {
        return prefs.getString("session_token", null)
    }

    fun clearToken() {
        prefs.edit().remove("session_token").apply()
    }
}
