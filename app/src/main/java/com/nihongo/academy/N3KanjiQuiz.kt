package com.nihongo.academy

import kotlin.random.Random

internal enum class N3KanjiQuestionType { MEANING, READING }

internal data class N3KanjiQuizQuestion(
    val type: N3KanjiQuestionType,
    val prompt: String,
    val display: String,
    val options: List<String>,
    val answer: String,
    val explanation: String
)

internal fun buildN3KanjiQuiz(
    catalog: List<N3KanjiEntry> = n3KanjiCatalog,
    random: Random = Random.Default
): List<N3KanjiQuizQuestion> {
    require(catalog.size >= 4) { "El catálogo necesita al menos cuatro kanji." }

    fun makeOptions(correct: String, pool: List<String>): List<String> {
        val alternatives = pool.filter { it.isNotBlank() && it != correct }
            .distinct().shuffled(random).take(3)
        require(alternatives.size == 3) { "No hay suficientes alternativas únicas." }
        return (alternatives + correct).shuffled(random)
    }

    val meaningQuestions = catalog.shuffled(random).take(6).map { entry ->
        N3KanjiQuizQuestion(
            type = N3KanjiQuestionType.MEANING,
            prompt = "¿Qué significado tiene este kanji?",
            display = entry.character,
            options = makeOptions(entry.meaningEs, catalog.map { it.meaningEs }),
            answer = entry.meaningEs,
            explanation = "${entry.character}: ${entry.meaningEs}."
        )
    }

    val readable = catalog.filter { it.onyomi.isNotEmpty() || it.kunyomi.isNotEmpty() }
    require(readable.size >= 4) { "No hay suficientes kanji con lecturas." }
    val readingPool = readable.flatMap { it.onyomi + it.kunyomi.map { reading -> displayN3KunYomi(reading) } }
    val readingQuestions = readable.shuffled(random).take(4).map { entry ->
        val correct = (entry.onyomi + entry.kunyomi.map { displayN3KunYomi(it) }).shuffled(random).first()
        N3KanjiQuizQuestion(
            type = N3KanjiQuestionType.READING,
            prompt = "¿Cuál es una lectura de este kanji?",
            display = entry.character,
            options = makeOptions(correct, readingPool),
            answer = correct,
            explanation = "Una lectura de ${entry.character} es ${correct}."
        )
    }

    return (meaningQuestions + readingQuestions).shuffled(random)
}
