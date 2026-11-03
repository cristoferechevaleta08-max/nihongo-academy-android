package com.nihongo.academy

/** Plan pedagógico N4 de 16 semanas: gramática, kanji, lectura y escucha. */
internal data class N4StudyWeek(
    val week: Int,
    val title: String,
    val focus: String,
    val activities: List<String>,
    val assessment: String
)

internal val n4SixteenWeekPlan = listOf(
    N4StudyWeek(1, "Consolidar la forma te", "Forma te, acciones en curso y estados resultantes",
        listOf("Repasar conjugación de verbos grupo 1, 2 e irregulares", "Distinguir acciones en curso y estados", "Estudiar kanji y vocabulario de la unidad 1"),
        "Prueba de forma te y comprensión de ejemplos"),
    N4StudyWeek(2, "Negación y obligación", "Forma nai, obligación y ausencia de obligación",
        listOf("Practicar formas nai de verbos frecuentes", "Contrastar nakereba narimasen y nakute mo ii", "Repasar kanji de semanas 1 y 2"),
        "Prueba de formas negativas y obligación"),
    N4StudyWeek(3, "Expresar capacidad", "Forma potencial y diferencia entre capacidad y permiso",
        listOf("Conjugar verbos en forma potencial", "Crear frases sobre habilidades y posibilidades", "Escuchar diálogos breves de capacidad"),
        "Prueba de forma potencial"),
    N4StudyWeek(4, "Deseos y preferencias", "Tai y hoshii",
        listOf("Distinguir deseo de realizar una acción y deseo de un objeto", "Practicar preguntas y respuestas sobre preferencias", "Repasar vocabulario acumulado"),
        "Prueba de deseos y preferencias"),
    N4StudyWeek(5, "Comparar", "Comparaciones, superioridad y elección",
        listOf("Construir comparaciones entre dos elementos", "Practicar preguntas con dochira y respuestas con hou", "Leer un texto corto comparativo"),
        "Prueba de estructuras comparativas"),
    N4StudyWeek(6, "Dar y recibir", "Ageru, morau y kureru",
        listOf("Identificar quién da y quién recibe", "Practicar perspectiva del hablante", "Repasar vocabulario de relaciones y objetos"),
        "Prueba de dar y recibir"),
    N4StudyWeek(7, "Opiniones y posibilidad", "To omoimasu, deshou y kamoshiremasen",
        listOf("Expresar opiniones propias", "Distinguir predicción y posibilidad", "Practicar shitte imasu y shirimasen"),
        "Prueba de opiniones y grados de certeza"),
    N4StudyWeek(8, "Condiciones", "Tara y to",
        listOf("Distinguir condición hipotética y resultado habitual", "Completar frases condicionales", "Realizar repaso acumulativo de unidades 1 a 8"),
        "Evaluación de gramática N4"),
    N4StudyWeek(9, "Kanji: bloque 1", "Lectura, significado y vocabulario de los primeros kanji N4",
        listOf("Estudiar kanji del bloque inicial", "Practicar orden de trazos y escritura libre", "Repasar con tarjetas espaciadas"),
        "Prueba de reconocimiento y lectura de kanji"),
    N4StudyWeek(10, "Kanji: bloque 2", "Ampliar lecturas y vocabulario",
        listOf("Estudiar el segundo bloque de kanji", "Formar palabras con vocabulario aprendido", "Repasar kanji vencidos en la cola espaciada"),
        "Prueba mixta de kanji y vocabulario"),
    N4StudyWeek(11, "Kanji: bloque 3", "Consolidar el catálogo completo",
        listOf("Estudiar el bloque final de kanji", "Revisar lecturas on y kun en palabras reales", "Completar examen mixto de kanji"),
        "Evaluación global de kanji"),
    N4StudyWeek(12, "Escucha: comprensión", "Comprender diálogos N4",
        listOf("Escuchar los seis diálogos originales", "Identificar intención, datos y secuencia", "Repetir escucha con TextToSpeech"),
        "Prueba de comprensión auditiva"),
    N4StudyWeek(13, "Lectura: comprensión", "Leer textos breves y localizar información",
        listOf("Leer los textos originales N4", "Identificar tema, detalles y referencias", "Justificar respuestas con información del texto"),
        "Prueba de comprensión lectora"),
    N4StudyWeek(14, "Integración", "Combinar gramática, kanji y vocabulario",
        listOf("Resolver ejercicios mezclados de las ocho unidades", "Repasar errores recurrentes", "Realizar lectura y escucha combinadas"),
        "Prueba integrada parcial"),
    N4StudyWeek(15, "Simulacro y recuperación", "Practicar con tiempo y reforzar debilidades",
        listOf("Realizar el examen integrado de práctica", "Revisar resultados por sección", "Repetir contenidos con bajo rendimiento"),
        "Segundo intento del simulacro N4"),
    N4StudyWeek(16, "Cierre N4", "Consolidación y evaluación final",
        listOf("Repasar fichas y cola de repetición espaciada", "Completar escucha y lectura finales", "Revisar el cuaderno de errores"),
        "Evaluación final integrada N4")
)

internal const val N4_PLAN_WEEKS = 16
internal const val N4_DAILY_TARGET_MINUTES = 40
internal const val N4_WEEKLY_PASS_PERCENT = 70

internal fun isN4WeekUnlocked(completedWeeks: Set<Int>, week: Int): Boolean =
    week in 1..N4_PLAN_WEEKS && (week == 1 || (1 until week).all { it in completedWeeks })

internal data class N4StudyProgress(
    val currentWeek: Int = 1,
    val completedWeeks: Set<Int> = emptySet(),
    val attemptsByWeek: Map<Int, Int> = emptyMap(),
    val lastScoreByWeek: Map<Int, Int> = emptyMap()
) {
    init {
        require(currentWeek in 1..N4_PLAN_WEEKS)
        require(completedWeeks.all { it in 1..N4_PLAN_WEEKS })
        require(attemptsByWeek.all { (week, attempts) -> week in 1..N4_PLAN_WEEKS && attempts >= 0 })
        require(lastScoreByWeek.all { (week, score) -> week in 1..N4_PLAN_WEEKS && score in 0..100 })
    }

    val isComplete: Boolean get() = (1..N4_PLAN_WEEKS).all { it in completedWeeks }
}

/** Una semana N4 requiere 70%; si no, se repite sin desbloquear la siguiente. */
internal fun submitN4WeeklyAssessment(
    progress: N4StudyProgress,
    scorePercent: Int
): N4StudyProgress {
    require(!progress.isComplete) { "El plan N4 ya está completo." }
    require(progress.currentWeek !in progress.completedWeeks) { "Esta semana N4 ya está aprobada." }
    require(isN4WeekUnlocked(progress.completedWeeks, progress.currentWeek)) {
        "Aprobá las semanas anteriores antes de continuar."
    }
    require(scorePercent in 0..100) { "El resultado debe estar entre 0 y 100." }

    val week = progress.currentWeek
    val attempts = (progress.attemptsByWeek[week] ?: 0) + 1
    val scores = progress.lastScoreByWeek + (week to scorePercent)
    return if (scorePercent >= N4_WEEKLY_PASS_PERCENT) {
        progress.copy(
            currentWeek = minOf(N4_PLAN_WEEKS, week + 1),
            completedWeeks = progress.completedWeeks + week,
            attemptsByWeek = progress.attemptsByWeek + (week to attempts),
            lastScoreByWeek = scores
        )
    } else {
        progress.copy(
            attemptsByWeek = progress.attemptsByWeek + (week to attempts),
            lastScoreByWeek = scores
        )
    }
}
