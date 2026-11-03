package com.nihongo.academy

import kotlin.random.Random

internal enum class N4KanjiQuestionType { MEANING, READING, VOCABULARY }

internal data class N4KanjiQuizQuestion(
    val type: N4KanjiQuestionType,
    val prompt: String,
    val display: String,
    val options: List<String>,
    val answer: String,
    val explanation: String
)

internal fun buildN4KanjiQuiz(
    catalog: List<N4KanjiEntry> = n4KanjiCatalog,
    random: Random = Random.Default
): List<N4KanjiQuizQuestion> {
    require(catalog.size >= 4) { "El catálogo necesita al menos cuatro kanji." }

    fun options(correct: String, pool: List<String>): List<String> {
        val distractors = pool.filter { it.isNotBlank() && it != correct }.distinct().shuffled(random).take(3)
        require(distractors.size == 3) { "No hay suficientes respuestas alternativas únicas." }
        return (distractors + correct).shuffled(random)
    }

    val meanings = catalog.shuffled(random).take(4).map { entry ->
        N4KanjiQuizQuestion(
            N4KanjiQuestionType.MEANING,
            "¿Qué significado tiene este kanji?",
            entry.character,
            options(entry.meaningEs, catalog.map { it.meaningEs }),
            entry.meaningEs,
            "${entry.character} significa «${entry.meaningEs}»."
        )
    }

    val readingEntries = catalog.filter { it.onyomi.isNotEmpty() || it.kunyomi.isNotEmpty() }.shuffled(random).take(3)
    val readingPool = catalog.flatMap { entry -> entry.onyomi + entry.kunyomi.map { it.replace(".", "") } }
    val readings = readingEntries.map { entry ->
        val correct = (entry.onyomi + entry.kunyomi.map { it.replace(".", "") }).shuffled(random).first()
        N4KanjiQuizQuestion(
            N4KanjiQuestionType.READING,
            "¿Cuál es una lectura de este kanji?",
            entry.character,
            options(correct, readingPool),
            correct,
            "Una lectura de ${entry.character} es ${correct}."
        )
    }

    val vocabulary = catalog.flatMap { entry -> entry.vocabulary }.filter { it.reading.isNotBlank() }.shuffled(random).take(3)
    val vocabularyPool = catalog.flatMap { entry -> entry.vocabulary.map { it.reading } }
    val vocabularyQuestions = vocabulary.map { word ->
        N4KanjiQuizQuestion(
            N4KanjiQuestionType.VOCABULARY,
            "¿Cómo se lee esta palabra?",
            word.word,
            options(word.reading, vocabularyPool),
            word.reading,
            "${word.word} se lee ${word.reading}."
        )
    }

    return (meanings + readings + vocabularyQuestions).shuffled(random)
}
