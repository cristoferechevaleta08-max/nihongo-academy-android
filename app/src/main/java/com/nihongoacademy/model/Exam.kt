package com.nihongoacademy.model

enum class ExamModuleType(val label: String) {
    READING("Lectura"),
    WRITING("Escritura"),
    LISTENING("Comprensión auditiva")
}

enum class ExamDifficulty(val label: String) {
    BASIC("Básico"),
    INTERMEDIATE("Intermedio"),
    ADVANCED("Avanzado")
}

data class ExamQuestion(
    val id: String,
    val module: ExamModuleType,
    val difficulty: ExamDifficulty,
    val prompt: String,
    val options: List<String> = emptyList(),
    val correctAnswer: String,
    val feedback: String,
    val hint: String
)

data class ExamHistoryEntry(
    val topic: String,
    val score: Int,
    val attempts: Int,
    val lastReview: String
)
