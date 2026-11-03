package com.nihongo.academy

import kotlin.random.Random

/** Preguntas semanales construidas con el material N4 que ya está integrado en la app. */
internal data class N4WeeklyQuestion(
    val section: String,
    val prompt: String,
    val options: List<String>,
    val answerIndex: Int,
    val explanation: String
)

internal fun buildN4WeeklyAssessment(
    week: Int,
    random: Random = Random.Default
): List<N4WeeklyQuestion> {
    require(week in 1..N4_PLAN_WEEKS) { "La semana debe estar entre 1 y 16." }

    fun question(section: String, prompt: String, options: List<String>, answer: Int, explanation: String): N4WeeklyQuestion {
        require(options.size >= 2 && answer in options.indices)
        val correctValue = options[answer]
        val completeOptions = options.distinct().toMutableList()
        val distractorPool = n4FinalAssessment.flatMap { it.options } +
            n4ListeningDialogues.flatMap { dialogue -> dialogue.questions.flatMap { it.options } } +
            n4ReadingPassages.flatMap { passage -> passage.questions.flatMap { it.options } }
        distractorPool.filter { it.isNotBlank() && it !in completeOptions }
            .distinct().shuffled(random).take((4 - completeOptions.size).coerceAtLeast(0))
            .forEach(completeOptions::add)
        require(completeOptions.size >= 4) { "No hay suficientes opciones únicas para completar la pregunta." }
        val shuffled = completeOptions.take(4).shuffled(random)
        return N4WeeklyQuestion(section, prompt, shuffled, shuffled.indexOf(correctValue), explanation)
    }

    fun fromLesson(unit: Int): List<N4WeeklyQuestion> {
        val id = "n4-u%02d".format(unit)
        val lesson = lessons.firstOrNull { it.id == id && it.level == "N4" } ?: return emptyList()
        return lesson.exercises.map { exercise ->
            question("Gramática · ${lesson.title}", exercise.prompt, exercise.options, exercise.answer, exercise.note)
        }.shuffled(random)
    }

    return when (week) {
        in 1..8 -> fromLesson(week)
        in 9..11 -> {
            val block = n4KanjiCatalog.chunked(61).getOrNull(week - 9).orEmpty()
            buildN4KanjiQuiz(block, random).map { item ->
                question("Kanji · ${item.type.name.lowercase()}", item.prompt, item.options,
                    item.options.indexOf(item.answer), item.explanation)
            }
        }
        12 -> n4ListeningDialogues.flatMap { dialogue ->
            dialogue.questions.map { q ->
                question("Escucha · ${dialogue.title}", q.prompt, q.options, q.answer, q.explanation)
            }
        }.shuffled(random)
        13 -> n4ReadingPassages.flatMap { passage ->
            passage.questions.map { q ->
                question("Lectura · ${passage.title}", q.prompt, q.options, q.answer, q.explanation)
            }
        }.shuffled(random)
        14 -> {
            val bySection = n4FinalAssessment.groupBy { it.section }
            listOf("Gramática" to 5, "Vocabulario" to 4, "Lectura" to 3, "Comprensión" to 3)
                .flatMap { (section, count) -> bySection[section].orEmpty().shuffled(random).take(count) }
                .map { q -> question(q.section, q.prompt, q.options, q.answer, q.explanation) }
                .shuffled(random)
        }
        15 -> n4FinalAssessment.shuffled(random).take(20).map { q ->
            question(q.section, q.prompt, q.options, q.answer, q.explanation)
        }
        16 -> n4FinalAssessment.shuffled(random).map { q ->
            question(q.section, q.prompt, q.options, q.answer, q.explanation)
        }
        else -> emptyList()
    }
}
