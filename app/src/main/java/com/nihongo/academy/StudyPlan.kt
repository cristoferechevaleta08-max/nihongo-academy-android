import java.time.LocalDate

package com.nihongo.academy

internal data class StudyPlanBlock(
    val weeks: String,
    val focus: String,
    val activities: String,
    val finalAssessment: String
)

internal val n5TwelveWeekPlan = listOf(
    StudyPlanBlock("1–2", "Hiragana y katakana", "Lectura y escritura de caracteres básicos, con repaso diario.", "Prueba de lectura de caracteres"),
    StudyPlanBlock("3–4", "Saludos y partículas", "Frases básicas y partículas wa, ga, o, ni y de.", "Prueba de partículas"),
    StudyPlanBlock("5–6", "Verbos en forma masu", "Presente, pasado y negativo de verbos comunes.", "Simulacro de gramática N5"),
    StudyPlanBlock("7–8", "Adjetivos y vocabulario", "Adjetivos i y na, y vocabulario de la vida diaria.", "Prueba de vocabulario"),
    StudyPlanBlock("9–10", "Kanji básicos", "Kanji de uso frecuente con práctica de escritura y lectura.", "Prueba de kanji"),
    StudyPlanBlock("11", "Comprensión auditiva", "Escucha de diálogos cortos del nivel N5.", "Simulacro de escucha"),
    StudyPlanBlock("12", "Simulacro completo", "Examen de práctica N5 con tiempo real.", "Revisión del cuaderno de errores")
)

internal const val N5_DAILY_STUDY_MINUTES = 30
internal const val N5_DAILY_STUDY_SECONDS = N5_DAILY_STUDY_MINUTES * 60
internal const val N4_DAILY_STUDY_MINUTES = 40
internal const val N4_TRANSITION_WEEKS = 16
internal const val WEEKLY_TEST_MAX_FAILURE_PERCENT = 30

internal fun isLessonPassed(correctAnswers: Int, totalQuestions: Int): Boolean =
    totalQuestions > 0 && correctAnswers.toFloat() / totalQuestions >= 0.70f

internal fun shouldRepeatStudyWeek(incorrectAnswers: Int, totalQuestions: Int): Boolean =
    totalQuestions > 0 && incorrectAnswers.toFloat() / totalQuestions > 0.30f

internal fun isDailyStudyGoalReached(studiedSeconds: Int): Boolean = studiedSeconds >= N5_DAILY_STUDY_SECONDS

internal fun nextDailyStreak(currentStreak: Int, lastGoalDate: String?, today: String): Int =
    if (lastGoalDate == today) currentStreak
    else if (lastGoalDate == LocalDate.parse(today).minusDays(1).toString()) currentStreak + 1
    else 1
