package com.nihongoacademy.data.asset

import android.content.Context
import com.google.gson.Gson
import com.nihongoacademy.domain.model.LanguageEntry

class LanguageCatalogRepository(private val context: Context) {
    private val gson = Gson()

    fun loadLanguages(): List<LanguageEntry> {
        return context.assets.open("languages.json").bufferedReader().use { reader ->
            val entries = gson.fromJson(reader.readText(), Array<LanguageEntry>::class.java)
            entries?.toList() ?: emptyList()
        }
    }
}
