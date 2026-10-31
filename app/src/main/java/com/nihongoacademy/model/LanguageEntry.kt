package com.nihongoacademy.model

data class LanguageEntry(
    val code: String,
    val nameEs: String,
    val nameNative: String,
    val script: String,
    val direction: String,
    val modules: List<String>
)
