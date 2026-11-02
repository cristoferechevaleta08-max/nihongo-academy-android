package com.nihongo.academy

/**
 * Reglas de avance del plan N5 de 12 semanas descrito en el documento de producto.
 * Este modelo prepara la lógica; no declara completo el contenido de las evaluaciones.
 */
internal data class WeeklyStudyProgress(
    val currentWeek: Int = 1,
    val completedWeeks: Set<Int> = emptySet(),
    val attemptsByWeek: Map<Int, Int> = emptyMap(),
    val paused: Boolean = false,
    val lastAssessment: WeeklyAssessment? = null
) {
    init {
        require(currentWeek in 1..N5_PLAN_WEEKS)
        require(completedWeeks.all { it in 1..N5_PLAN_WEEKS })
        require(attemptsByWeek.all { (week, attempts) -> week in 1..N5_PLAN_WEEKS && attempts >= 0 })
    }

    val isPlanComplete: Boolean get() = completedWeeks.size == N5_PLAN_WEEKS
    fun pause(): WeeklyStudyProgress = copy(paused = true)
    fun resume(): WeeklyStudyProgress = copy(paused = false)
}

internal data class WeeklyAssessment(
    val week: Int,
    val correctAnswers: Int,
    val totalQuestions: Int,
    val incorrectPercent: Int,
    val passed: Boolean,
    val requiresExtraPractice: Boolean
)

internal const val N5_PLAN_WEEKS = 12
internal const val WEEKLY_REPEAT_THRESHOLD_PERCENT = 30

/**
 * La semana se repite solo cuando los errores superan el 30 %.
 * Con 30 % exacto se aprueba. No se avanza con el plan pausado.
 */
internal fun submitWeeklyAssessment(
    progress: WeeklyStudyProgress,
    correctAnswers: Int,
    totalQuestions: Int
): WeeklyStudyProgress {
    require(!progress.paused) { "Reanudá el plan antes de enviar la evaluación." }
    require(totalQuestions > 0) { "La evaluación debe tener preguntas." }
    require(correctAnswers in 0..totalQuestions) { "La cantidad de aciertos no es válida." }

    val incorrect = totalQuestions - correctAnswers
    val requiresRepeat = incorrect * 100 > totalQuestions * WEEKLY_REPEAT_THRESHOLD_PERCENT
    val assessment = WeeklyAssessment(
        week = progress.currentWeek,
        correctAnswers = correctAnswers,
        totalQuestions = totalQuestions,
        incorrectPercent = (incorrect * 100f / totalQuestions).toInt(),
        passed = !requiresRepeat,
        requiresExtraPractice = requiresRepeat
    )

    if (requiresRepeat) {
        val attempts = (progress.attemptsByWeek[progress.currentWeek] ?: 0) + 1
        return progress.copy(
            attemptsByWeek = progress.attemptsByWeek + (progress.currentWeek to attempts),
            lastAssessment = assessment
        )
    }

    val completed = progress.completedWeeks + progress.currentWeek
    return progress.copy(
        currentWeek = if (progress.currentWeek < N5_PLAN_WEEKS) progress.currentWeek + 1 else N5_PLAN_WEEKS,
        completedWeeks = completed,
        lastAssessment = assessment
    )
}

/** Una semana queda habilitada solo cuando todas las anteriores están aprobadas. */
internal fun isStudyWeekUnlocked(progress: WeeklyStudyProgress, week: Int): Boolean =
    week in 1..N5_PLAN_WEEKS && (week == 1 || (1 until week).all { it in progress.completedWeeks })
