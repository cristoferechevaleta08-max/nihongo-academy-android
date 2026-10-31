package com.nihongoacademy.data.repository

import android.content.Context
import com.google.gson.Gson
import com.nihongoacademy.model.LanguageEntry

class LanguageCatalogRepository(private val context: Context) {
    private val gson = Gson()

    fun loadLanguages(): List<LanguageEntry> {
        val input = context.assets.open("languages.json").bufferedReader().use { it.readText() }
        val entries = gson.fromJson(input, Array<LanguageEntry>::class.java)
        return entries?.toList() ?: emptyList()
    }
}
