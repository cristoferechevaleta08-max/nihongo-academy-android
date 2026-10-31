package com.nihongoacademy.model

data class LanguageEntry(
    val code: String,
    val nameEs: String,
    val nameNative: String,
    val script: String,
    val direction: String,
    val modules: List<String>
)

data class Lesson(
    val id: String,
    val title: String,
    val prompt: String,
    val options: List<String>,
    val correctAnswer: String,
    val languageCode: String
)
