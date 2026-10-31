package com.nihongoacademy.model

import java.util.Locale

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

data class ModuleLesson(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val skillTags: List<String>
)

data class SurpriseExam(
    val id: String,
    val type: SurpriseExamType,
    val title: String,
    val questions: List<ExamQuestion>,
    val isMandatory: Boolean = true
)

enum class SurpriseExamType(val label: String) {
    MID("Examen sorpresa a la mitad"),
    FINAL("Examen sorpresa final")
}

data class LanguageModule(
    val id: String,
    val number: Int,
    val title: String,
    val lessons: List<ModuleLesson>,
    val midExam: SurpriseExam,
    val finalExam: SurpriseExam,
    val isIntegrative: Boolean = false,
    val targetLevel: String = ""
)

data class LanguageCatalog(
    val id: String,
    val name: String,
    val nativeName: String,
    val code: String,
    val modules: List<LanguageModule>
)

object LanguageCatalogFactory {
    private fun buildLanguageModules(): List<LanguageModule> {
        val modules = mutableListOf<LanguageModule>()

        for (i in 1..10) {
            val moduleNumber = i
            val title = when (i) {
                1 -> "Fundamentos básicos"
                2 -> "Saludos y frases esenciales"
                3 -> "Vocabulario cotidiano"
                4 -> "Estructuras simples"
                5 -> "Conversación práctica"
                6 -> "Lectura guiada"
                7 -> "Escritura y frases"
                8 -> "Práctica intermedia"
                9 -> "Consolidación total"
                10 -> "Dominio final"
                else -> "Módulo $i"
            }

            val lessons = listOf(
                ModuleLesson(
                    id = "m${moduleNumber}_l1",
                    title = "Lección principal",
                    description = "Base temática del módulo.",
                    durationMinutes = 20,
                    skillTags = listOf("vocabulario", "lectura")
                ),
                ModuleLesson(
                    id = "m${moduleNumber}_l2",
                    title = "Práctica guiada",
                    description = "Reforzamiento y uso estructural.",
                    durationMinutes = 15,
                    skillTags = listOf("práctica", "escritura")
                )
            )

            val midExam = SurpriseExam(
                id = "m${moduleNumber}_mid",
                type = SurpriseExamType.MID,
                title = "Examen sorpresa a la mitad",
                questions = listOf(
                    ExamQuestion(
                        id = "q_mid_$moduleNumber",
                        module = ExamModuleType.READING,
                        difficulty = ExamDifficulty.BASIC,
                        prompt = "Pregunta del examen sorpresa de mitad de módulo $moduleNumber.",
                        options = listOf("Opción A", "Opción B", "Opción C", "Opción D"),
                        correctAnswer = "Opción A",
                        feedback = "Respuesta correcta. Este módulo está funcionando bien.",
                        hint = "Revisa la estructura del bloque actual."
                    )
                )
            )

            val finalExam = SurpriseExam(
                id = "m${moduleNumber}_final",
                type = SurpriseExamType.FINAL,
                title = "Examen sorpresa final",
                questions = listOf(
                    ExamQuestion(
                        id = "q_final_$moduleNumber",
                        module = ExamModuleType.WRITING,
                        difficulty = ExamDifficulty.INTERMEDIATE,
                        prompt = "Pregunta de aplicación final del módulo $moduleNumber.",
                        options = emptyList(),
                        correctAnswer = "Respuesta esperada del módulo",
                        feedback = "Correcto. Has aplicado bien lo visto en el módulo.",
                        hint = "Usa la estructura aprendida en este bloque."
                    )
                )
            )

            modules += LanguageModule(
                id = "module_${moduleNumber}",
                number = moduleNumber,
                title = title,
                lessons = lessons,
                midExam = midExam,
                finalExam = finalExam,
                isIntegrative = moduleNumber == 9 || moduleNumber == 10,
                targetLevel = if (moduleNumber in 9..10) "Dominio del módulo" else "Nivel básico"
            )
        }

        return modules
    }

    fun buildSupportedLanguages(): List<LanguageCatalog> {
        val languageNames = listOf(
            "Inglés" to "en",
            "Mandarín" to "zh",
            "Español" to "es",
            "Hindi" to "hi",
            "Árabe" to "ar",
            "Francés" to "fr",
            "Portugués" to "pt",
            "Bengalí" to "bn",
            "Ruso" to "ru",
            "Alemán" to "de",
            "Japonés" to "ja",
            "Coreano" to "ko",
            "Italiano" to "it",
            "Turco" to "tr",
            "Indonesio" to "id"
        )

        return languageNames.map { (name, code) ->
            LanguageCatalog(
                id = code,
                name = name,
                nativeName = name,
                code = code.uppercase(Locale.ROOT),
                modules = buildLanguageModules()
            )
        }
    }
}
