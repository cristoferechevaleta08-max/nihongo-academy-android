package com.nihongo.academy

/**
 * Semanas 9–10 del Anexo E del PDF.
 *
 * El catálogo local de 100 kanji, sus lecturas, vocabulario y ejemplos está integrado
 * en N5KanjiCatalog.kt. Estas semanas organizan la práctica y usan evaluaciones
 * construidas desde ese catálogo curricular propio, no desde una lista oficial JLPT.
 */
internal val n5KanjiWeeks = listOf(
    KanaDay(57, "Introducción a los kanji", "Reconocer qué son los kanji y cómo se trabajan en la ruta N5.", listOf(
        "Identificar los kanji como caracteres de origen chino usados en japonés.",
        "Observar el modelo del carácter correspondiente del catálogo N5.",
        "Practicar la escritura siguiendo el modelo y respetando el orden de trazos disponible.",
        "Leer en voz alta la lectura del catálogo N5."
    )),
    KanaDay(58, "Primer bloque de escritura", "Iniciar la práctica repetida de escritura de los kanji frecuentes seleccionados.", listOf(
        "Copiar cada carácter del catálogo N5 varias veces.",
        "Observar la forma general antes de escribir.",
        "Comparar la escritura propia con el modelo.",
        "Repasar la lectura asociada a cada carácter."
    )),
    KanaDay(59, "Lectura y reconocimiento", "Relacionar cada carácter estudiado con su lectura.", listOf(
        "Mirar el carácter e intentar recordar su lectura antes de consultar.",
        "Leer cada carácter del bloque en voz alta.",
        "Separar los caracteres que se reconocen de los que todavía cuestan.",
        "Repetir la escritura de los caracteres con más errores."
    )),
    KanaDay(60, "Escritura de memoria", "Recuperar los caracteres estudiados sin copiar directamente el modelo.", listOf(
        "Intentar escribir cada carácter sin mirar el modelo.",
        "Comparar después con el material de referencia.",
        "Corregir la forma de los caracteres que no coincidan.",
        "Volver a leer las lecturas correspondientes."
    )),
    KanaDay(61, "Repaso acumulativo", "Reforzar la lectura y escritura de todos los caracteres del bloque semanal.", listOf(
        "Repasar todos los caracteres vistos desde el día 57.",
        "Leerlos en orden mezclado.",
        "Escribir varias veces los caracteres más difíciles.",
        "Registrar los errores para repasarlos al final de la semana."
    )),
    KanaDay(62, "Preparación de la prueba", "Consolidar lectura y escritura antes de la evaluación semanal.", listOf(
        "Hacer una ronda de reconocimiento sin consultar las lecturas.",
        "Escribir los caracteres estudiados de memoria.",
        "Revisar los errores y repetir la práctica necesaria.",
        "Preparar el cuaderno de errores para la prueba."
    )),
    KanaDay(63, "Evaluación semanal de kanji", "Comprobar lectura y escritura de los kanji frecuentes estudiados.", listOf(
        "Completar la prueba semanal con el catálogo N5 integrado.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con ejercicios adicionales."
    )),
    KanaDay(64, "Segundo bloque de kanji", "Ampliar el conjunto de kanji frecuentes del nivel N5 con lectura y escritura.", listOf(
        "Incorporar el siguiente grupo del catálogo N5 integrado.",
        "Observar la forma de cada carácter y su lectura.",
        "Copiar cada carácter varias veces siguiendo el modelo.",
        "Repasar brevemente los caracteres de la semana anterior."
    )),
    KanaDay(65, "Lectura del segundo bloque", "Reconocer las lecturas del nuevo grupo de kanji.", listOf(
        "Leer cada carácter del nuevo bloque.",
        "Intentar recordar su lectura antes de consultar.",
        "Distinguir los caracteres nuevos de los ya repasados.",
        "Anotar las lecturas que todavía generan dudas."
    )),
    KanaDay(66, "Escritura del segundo bloque", "Practicar la escritura repetida de los nuevos caracteres.", listOf(
        "Copiar cada carácter varias veces.",
        "Observar y corregir la forma de cada escritura.",
        "Intentar reproducir los caracteres sin mirar el modelo.",
        "Repasar la lectura asociada a cada uno."
    )),
    KanaDay(67, "Lectura mezclada", "Combinar la lectura de los dos bloques de kanji.", listOf(
        "Leer los caracteres de ambos bloques en orden mezclado.",
        "Recordar la lectura de cada carácter sin ayuda.",
        "Separar los caracteres que requieren más repaso.",
        "Volver a practicar la escritura de los más difíciles."
    )),
    KanaDay(68, "Escritura de memoria", "Recuperar por escrito los kanji de las dos semanas.", listOf(
        "Escribir de memoria los caracteres estudiados.",
        "Comparar cada resultado con el modelo validado.",
        "Corregir los trazos o partes que no coincidan.",
        "Releer las lecturas de los caracteres corregidos."
    )),
    KanaDay(69, "Repaso general de kanji", "Preparar la prueba final del bloque de dos semanas.", listOf(
        "Repasar la lectura de todos los caracteres incorporados al banco.",
        "Practicar la escritura repetida de los caracteres difíciles.",
        "Revisar el cuaderno de errores.",
        "Repetir lectura y escritura antes de la evaluación."
    )),
    KanaDay(70, "Evaluación semanal de kanji", "Comprobar lectura y escritura de los kanji frecuentes del bloque.", listOf(
        "Completar la prueba semanal con el catálogo N5 integrado.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con ejercicios adicionales."
    ))
)

internal fun kanjiDaysForWeek(week: Int): List<KanaDay> = when (week) {
    9 -> n5KanjiWeeks.take(7)
    10 -> n5KanjiWeeks.drop(7).take(7)
    else -> emptyList()
}


/** Evaluaciones internas para los dos bloques de kanji ya incorporados al curso. */
internal fun kanjiAssessmentForWeek(week: Int): List<KanaQuizQuestion> {
    if (week !in 9..10 || n5KanjiCatalog.size < 20) return emptyList()
    val block = n5KanjiCatalog.drop((week - 9) * 50).take(50)
    return block.take(10).mapIndexed { index, entry ->
        val distractors = (block.drop(index + 1).map { it.meaningEs } +
            n5KanjiCatalog.map { it.meaningEs })
            .filter { it != entry.meaningEs }
            .distinct()
            .take(3)
        KanaQuizQuestion(
            "¿Qué significado corresponde al kanji ${entry.character}?",
            (listOf(entry.meaningEs) + distractors).take(4),
            0
        )
    }
}
