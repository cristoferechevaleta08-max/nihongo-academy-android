package com.nihongoacademy.model

import java.util.Locale

data class ModuleLesson(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val skillTags: List<String>,
    val prompt: String = description,
    val options: List<String> = emptyList(),
    val correctAnswer: String = "",
    val explanation: String = ""
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
    private fun buildLanguageModules(languageCode: String): List<LanguageModule> {
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

            val content = lessonContent(languageCode, moduleNumber)
            val lessons = listOf(
                ModuleLesson(
                    id = "m${moduleNumber}_l1",
                    title = content.first.title,
                    description = content.first.description,
                    durationMinutes = 20,
                    skillTags = listOf("vocabulario", "lectura"),
                    prompt = content.first.prompt,
                    options = content.first.options,
                    correctAnswer = content.first.answer,
                    explanation = content.first.explanation
                ),
                ModuleLesson(
                    id = "m${moduleNumber}_l2",
                    title = content.second.title,
                    description = content.second.description,
                    durationMinutes = 15,
                    skillTags = listOf("práctica", "escritura"),
                    prompt = content.second.prompt,
                    options = content.second.options,
                    correctAnswer = content.second.answer,
                    explanation = content.second.explanation
                )
            )

            val midExam = SurpriseExam(
                id = "m${moduleNumber}_mid",
                type = SurpriseExamType.MID,
                title = "Evaluación de lectura · ${content.first.title}",
                questions = listOf(
                    ExamQuestion(
                        id = "q_mid_${moduleNumber}_1",
                        module = ExamModuleType.READING,
                        difficulty = ExamDifficulty.BASIC,
                        prompt = content.first.prompt,
                        options = content.first.options,
                        correctAnswer = content.first.answer,
                        feedback = content.first.explanation,
                        hint = content.first.description
                    ),
                    ExamQuestion(
                        id = "q_mid_${moduleNumber}_2",
                        module = ExamModuleType.READING,
                        difficulty = ExamDifficulty.BASIC,
                        prompt = content.second.prompt,
                        options = content.second.options,
                        correctAnswer = content.second.answer,
                        feedback = content.second.explanation,
                        hint = content.second.description
                    )
                )
            )

            val finalExam = SurpriseExam(
                id = "m${moduleNumber}_final",
                type = SurpriseExamType.FINAL,
                title = "Evaluación final · ${content.second.title}",
                questions = listOf(
                    ExamQuestion(
                        id = "q_final_${moduleNumber}_1",
                        module = ExamModuleType.READING,
                        difficulty = ExamDifficulty.INTERMEDIATE,
                        prompt = content.first.prompt,
                        options = content.first.options,
                        correctAnswer = content.first.answer,
                        feedback = content.first.explanation,
                        hint = content.first.description
                    ),
                    ExamQuestion(
                        id = "q_final_${moduleNumber}_2",
                        module = ExamModuleType.READING,
                        difficulty = ExamDifficulty.INTERMEDIATE,
                        prompt = content.second.prompt,
                        options = content.second.options,
                        correctAnswer = content.second.answer,
                        feedback = content.second.explanation,
                        hint = content.second.description
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


    private data class LessonSeed(
        val title: String,
        val description: String,
        val prompt: String,
        val options: List<String>,
        val answer: String,
        val explanation: String
    )

    private val japaneseLessonPairs = listOf(
        Pair(LessonSeed("Saludos básicos", "Reconoce un saludo cotidiano japonés.", "¿Qué significa こんにちは?", listOf("Hola", "Gracias", "Agua", "Adiós"), "Hola", "こんにちは es un saludo habitual durante el día."), LessonSeed("Agradecimientos", "Aprende una expresión educada para agradecer.", "¿Qué significa ありがとうございます?", listOf("Por favor", "Gracias", "Perdón", "Buenos días"), "Gracias", "ありがとうございます es una forma cortés de dar las gracias.")),
        Pair(LessonSeed("Sí y no", "Identifica respuestas afirmativas y negativas.", "¿Qué significa はい?", listOf("Sí", "No", "Gracias", "Hola"), "Sí", "はい significa sí; いいえ significa no."), LessonSeed("Disculpas", "Aprende una disculpa cotidiana.", "¿Qué significa すみません?", listOf("Perdón / disculpe", "Buenos días", "Agua", "Hasta luego"), "Perdón / disculpe", "すみません sirve para disculparse o llamar la atención con cortesía.")),
        Pair(LessonSeed("Personas", "Reconoce palabras para personas.", "¿Qué significa 先生?", listOf("Profesor", "Estudiante", "Amigo", "Médico"), "Profesor", "先生 (せんせい) se usa para profesor o maestro."), LessonSeed("Estudios", "Aprende una palabra frecuente del aula.", "¿Qué significa 学生?", listOf("Estudiante", "Libro", "Escuela", "Papel"), "Estudiante", "学生 (がくせい) significa estudiante.")),
        Pair(LessonSeed("Objetos", "Identifica objetos cotidianos.", "¿Qué significa 本?", listOf("Libro", "Agua", "Mesa", "Puerta"), "Libro", "本 (ほん) significa libro."), LessonSeed("Demostrativos", "Reconoce cómo señalar algo cercano.", "¿Qué significa これ?", listOf("Esto", "Aquello", "Allí", "Quién"), "Esto", "これ significa esto y señala algo cerca de quien habla.")),
        Pair(LessonSeed("Números", "Aprende los primeros números japoneses.", "¿Qué significa 三 (さん)?", listOf("Uno", "Dos", "Tres", "Cuatro"), "Tres", "三 se lee さん (san) y significa tres."), LessonSeed("Contar", "Refuerza los números básicos.", "¿Cómo se dice uno en japonés?", listOf("いち", "に", "さん", "よん"), "いち", "一 se lee いち (ichi) y significa uno.")),
        Pair(LessonSeed("Lugares", "Reconoce lugares comunes.", "¿Qué significa 学校?", listOf("Escuela", "Hospital", "Estación", "Tienda"), "Escuela", "学校 (がっこう) significa escuela."), LessonSeed("Transporte", "Aprende una palabra para moverse por la ciudad.", "¿Qué significa 駅?", listOf("Estación", "Aeropuerto", "Parque", "Casa"), "Estación", "駅 (えき) significa estación.")),
        Pair(LessonSeed("Tiempo", "Reconoce una palabra de tiempo.", "¿Qué significa 今日?", listOf("Hoy", "Ayer", "Mañana", "Semana"), "Hoy", "今日 (きょう) significa hoy."), LessonSeed("Días", "Aprende una expresión de tiempo.", "¿Qué significa 明日?", listOf("Ayer", "Hoy", "Mañana", "Noche"), "Mañana", "明日 (あした) significa mañana.")),
        Pair(LessonSeed("Comida", "Identifica alimentos cotidianos.", "¿Qué significa 水?", listOf("Agua", "Arroz", "Té", "Pan"), "Agua", "水 (みず) significa agua."), LessonSeed("Comer", "Aprende una palabra de comida.", "¿Qué significa ご飯?", listOf("Arroz / comida", "Pescado", "Carne", "Fruta"), "Arroz / comida", "ご飯 (ごはん) puede significar arroz cocido o comida.")),
        Pair(LessonSeed("Adjetivos", "Reconoce una descripción sencilla.", "¿Qué significa 大きい?", listOf("Grande", "Pequeño", "Nuevo", "Viejo"), "Grande", "大きい (おおきい) significa grande."), LessonSeed("Descripciones", "Identifica un adjetivo frecuente.", "¿Qué significa おいしい?", listOf("Delicioso", "Caro", "Frío", "Rápido"), "Delicioso", "おいしい significa delicioso o sabroso.")),
        Pair(LessonSeed("Repaso general", "Consolida vocabulario básico.", "¿Qué significa 友だち?", listOf("Amigo", "Familia", "Profesor", "Compañero de trabajo"), "Amigo", "友だち (ともだち) significa amigo."), LessonSeed("Frases útiles", "Aplica una expresión de uso cotidiano.", "¿Qué significa また明日?", listOf("Hasta mañana", "Buenos días", "Muchas gracias", "Buenas noches"), "Hasta mañana", "また明日 significa hasta mañana."))
    )

    private fun lessonContent(code: String, moduleNumber: Int): Pair<LessonSeed, LessonSeed> {
        val seeds = when (code.lowercase(Locale.ROOT)) {
            "ja" -> japaneseLessonPairs[(moduleNumber - 1).coerceIn(japaneseLessonPairs.indices)]
            "en" -> Pair(LessonSeed("Saludos", "Aprende a saludar en inglés.", "¿Qué significa 'Good morning'?", listOf("Buenas noches", "Buenos días", "Hasta luego", "Gracias"), "Buenos días", "Good morning se usa por la mañana."), LessonSeed("Cortesía", "Practica una expresión de agradecimiento.", "¿Qué significa 'Thank you'?", listOf("Gracias", "Perdón", "Hola", "Adiós"), "Gracias", "Thank you significa gracias."))
            "ko" -> Pair(LessonSeed("Saludos", "Reconoce un saludo coreano.", "¿Qué significa 안녕하세요?", listOf("Hola", "Gracias", "Agua", "Adiós"), "Hola", "안녕하세요 es un saludo educado."), LessonSeed("Agradecimientos", "Aprende a dar las gracias.", "¿Qué significa 감사합니다?", listOf("Perdón", "Gracias", "Hasta mañana", "Casa"), "Gracias", "감사합니다 es una expresión formal de agradecimiento."))
            "zh-hans", "zh" -> Pair(LessonSeed("Saludos", "Reconoce un saludo en mandarín.", "¿Qué significa 你好?", listOf("Hola", "Gracias", "Adiós", "Agua"), "Hola", "你好 (nǐ hǎo) significa hola."), LessonSeed("Agradecimientos", "Aprende a agradecer.", "¿Qué significa 谢谢?", listOf("Por favor", "Gracias", "Perdón", "Buenos días"), "Gracias", "谢谢 (xièxie) significa gracias."))
            "fr" -> Pair(LessonSeed("Saludos", "Aprende un saludo francés.", "¿Qué significa Bonjour?", listOf("Hola / buenos días", "Gracias", "Adiós", "Por favor"), "Hola / buenos días", "Bonjour se utiliza como saludo durante el día."), LessonSeed("Cortesía", "Practica agradecimientos.", "¿Qué significa Merci?", listOf("Gracias", "Perdón", "Hasta luego", "Agua"), "Gracias", "Merci significa gracias."))
            "de" -> Pair(LessonSeed("Saludos", "Aprende un saludo alemán.", "¿Qué significa Guten Morgen?", listOf("Buenas tardes", "Buenos días", "Buenas noches", "Gracias"), "Buenos días", "Guten Morgen significa buenos días."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Danke?", listOf("Hola", "Gracias", "Adiós", "Por favor"), "Gracias", "Danke significa gracias."))
            "it" -> Pair(LessonSeed("Saludos", "Aprende un saludo italiano.", "¿Qué significa Buongiorno?", listOf("Buenos días", "Gracias", "Adiós", "Por favor"), "Buenos días", "Buongiorno es buenos días."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Grazie?", listOf("Perdón", "Gracias", "Hola", "Hasta luego"), "Gracias", "Grazie significa gracias."))
            "pt" -> Pair(LessonSeed("Saludos", "Aprende un saludo portugués.", "¿Qué significa Bom dia?", listOf("Buenas noches", "Buenos días", "Gracias", "Adiós"), "Buenos días", "Bom dia significa buenos días."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Obrigado?", listOf("Por favor", "Gracias", "Hola", "Perdón"), "Gracias", "Obrigado es gracias dicho por un hombre; obrigada, por una mujer."))
            "es" -> Pair(LessonSeed("Saludos", "Repasa saludos cotidianos.", "¿Qué significa 'Hola'?", listOf("Hello", "Goodbye", "Please", "Water"), "Hello", "Hola equivale a hello en inglés."), LessonSeed("Cortesía", "Repasa expresiones de cortesía.", "¿Qué significa 'Gracias'?", listOf("Thank you", "Sorry", "Good night", "Please"), "Thank you", "Gracias equivale a thank you en inglés."))
            "ru" -> Pair(LessonSeed("Saludos", "Reconoce un saludo ruso.", "¿Qué significa Привет?", listOf("Hola", "Gracias", "Adiós", "Agua"), "Hola", "Привет es un saludo informal."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Спасибо?", listOf("Por favor", "Gracias", "Perdón", "Buenos días"), "Gracias", "Спасибо significa gracias."))
            "ar" -> Pair(LessonSeed("Saludos", "Reconoce un saludo árabe.", "¿Qué significa مرحباً?", listOf("Hola", "Gracias", "Adiós", "Casa"), "Hola", "مرحباً es una forma común de decir hola."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa شكراً?", listOf("Perdón", "Gracias", "Por favor", "Agua"), "Gracias", "شكراً significa gracias."))
            "nl" -> Pair(LessonSeed("Saludos", "Aprende un saludo neerlandés.", "¿Qué significa Hallo?", listOf("Hola", "Gracias", "Adiós", "Por favor"), "Hola", "Hallo significa hola."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Dank je?", listOf("Gracias", "Perdón", "Buenos días", "Agua"), "Gracias", "Dank je significa gracias."))
            "tr" -> Pair(LessonSeed("Saludos", "Aprende un saludo turco.", "¿Qué significa Merhaba?", listOf("Hola", "Gracias", "Adiós", "Agua"), "Hola", "Merhaba significa hola."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa Teşekkürler?", listOf("Por favor", "Gracias", "Perdón", "Buenos días"), "Gracias", "Teşekkürler significa gracias."))
            "hi" -> Pair(LessonSeed("Saludos", "Aprende un saludo hindi.", "¿Qué significa नमस्ते?", listOf("Hola", "Gracias", "Adiós", "Casa"), "Hola", "नमस्ते es un saludo respetuoso."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa धन्यवाद?", listOf("Perdón", "Gracias", "Por favor", "Agua"), "Gracias", "धन्यवाद significa gracias."))
            "th" -> Pair(LessonSeed("Saludos", "Aprende un saludo tailandés.", "¿Qué significa สวัสดี?", listOf("Hola", "Gracias", "Adiós", "Casa"), "Hola", "สวัสดี es un saludo común."), LessonSeed("Cortesía", "Aprende a agradecer.", "¿Qué significa ขอบคุณ?", listOf("Por favor", "Gracias", "Perdón", "Agua"), "Gracias", "ขอบคุณ significa gracias."))
            else -> Pair(LessonSeed("Saludos", "Practica saludos cotidianos.", "Selecciona la traducción de 'Hello'.", listOf("Hola", "Gracias", "Adiós", "Agua"), "Hola", "Hello significa hola."), LessonSeed("Cortesía", "Practica expresiones útiles.", "Selecciona la traducción de 'Thank you'.", listOf("Gracias", "Perdón", "Casa", "Agua"), "Gracias", "Thank you significa gracias."))
        }
        return seeds
    }

    fun buildSupportedLanguages(): List<LanguageCatalog> {
        val languageNames = listOf(
            Triple("Japonés", "日本語", "ja"),
            Triple("Inglés", "English", "en"),
            Triple("Coreano", "한국어", "ko"),
            Triple("Chino mandarín", "中文", "zh-Hans"),
            Triple("Francés", "Français", "fr"),
            Triple("Alemán", "Deutsch", "de"),
            Triple("Italiano", "Italiano", "it"),
            Triple("Portugués", "Português", "pt"),
            Triple("Español", "Español", "es"),
            Triple("Ruso", "Русский", "ru"),
            Triple("Árabe", "العربية", "ar"),
            Triple("Neerlandés", "Nederlands", "nl"),
            Triple("Turco", "Türkçe", "tr"),
            Triple("Hindi", "हिन्दी", "hi"),
            Triple("Tailandés", "ไทย", "th")
        )

        return languageNames.map { (name, nativeName, code) ->
            LanguageCatalog(
                id = code,
                name = name,
                nativeName = nativeName,
                code = code.uppercase(Locale.ROOT),
                modules = buildLanguageModules(code)
            )
        }
    }
}
