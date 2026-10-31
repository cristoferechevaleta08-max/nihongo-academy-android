package com.nihongoacademy.repository

import android.content.Context
import com.google.gson.Gson
import com.nihongoacademy.model.LanguageEntry
import com.nihongoacademy.model.Lesson

class LanguageCatalogRepository(private val context: Context) {
    private val gson = Gson()

    fun loadLanguages(): List<LanguageEntry> {
        val input = context.assets.open("languages.json").bufferedReader().use { it.readText() }
        val entries = gson.fromJson(input, Array<LanguageEntry>::class.java)
        return entries?.toList() ?: emptyList()
    }

    fun buildDemoLessons(languageCode: String): List<Lesson> {
        return when (languageCode) {
            "ja" -> listOf(
                Lesson("ja_1", "Lección 1", "¿Qué significa こんにちは?", listOf("Buenas tardes", "Hola", "Gracias", "Hasta luego"), "Hola", "ja"),
                Lesson("ja_2", "Lección 2", "¿Qué significa ありがとう?", listOf("Gracias", "Agua", "Escuela", "Casa"), "Gracias", "ja"),
                Lesson("ja_3", "Lección 3", "¿Cuál es el significado de 水?", listOf("Tierra", "Agua", "Libro", "Luz"), "Agua", "ja")
            )
            "ar" -> listOf(
                Lesson("ar_1", "Lección 1", "¿Cuál es el significado de السلام؟", listOf("Paz", "Libro", "Casa", "Amigo"), "Paz", "ar"),
                Lesson("ar_2", "Lección 2", "¿Qué significa البيت?", listOf("Casa", "Calle", "Escuela", "Aire"), "Casa", "ar")
            )
            else -> listOf(
                Lesson("generic_1", "Lección 1", "¿Qué significa 'aprender'?", listOf("Leer", "Estudiar", "Comer", "Dormir"), "Estudiar", languageCode)
            )
        }
    }
}
