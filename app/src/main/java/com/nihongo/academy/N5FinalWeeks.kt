package com.nihongo.academy

/**
 * Semanas 11–12 del Anexo E del PDF.
 *
 * El documento pide diálogos cortos N5 y un simulacro completo cronometrado.
 * Los guiones y preguntas de escucha son originales y se integran en la pestaña Escucha.
 * La voz se genera mediante el motor TTS japonés local si está instalado en el dispositivo.
 */
internal val n5FinalWeeks = listOf(
    KanaDay(71, "Primera escucha de diálogos N5", "Iniciar la comprensión auditiva con diálogos cortos del nivel N5.", listOf(
        "Escuchar un diálogo corto del material N5 validado desde la pestaña Escucha.",
        "Volver a escuchar el mismo diálogo para reconocer la información principal.",
        "Anotar qué partes del diálogo resultaron difíciles.",
        "Guardar las dudas para el repaso."
    )),
    KanaDay(72, "Repetición y comprensión", "Reforzar la comprensión de diálogos cortos mediante escuchas repetidas.", listOf(
        "Escuchar nuevamente un diálogo corto N5.",
        "Identificar la información que se logra comprender en la conversación.",
        "Repetir la escucha sin consultar las respuestas.",
        "Registrar las dudas de comprensión."
    )),
    KanaDay(73, "Escucha acumulativa", "Repasar los diálogos cortos practicados durante la semana.", listOf(
        "Volver a escuchar los diálogos N5 ya incorporados.",
        "Comparar lo comprendido en la primera escucha con la repetición.",
        "Anotar palabras o fragmentos que todavía cuestan.",
        "Repetir los audios que necesiten más práctica."
    )),
    KanaDay(74, "Comprensión de diálogos", "Practicar la comprensión general de diálogos cortos del nivel N5.", listOf(
        "Escuchar un diálogo corto nuevo del banco validado.",
        "Intentar comprenderlo sin detener el audio.",
        "Volver a escucharlo y comprobar la comprensión con el material asociado.",
        "Guardar los errores para repasarlos."
    )),
    KanaDay(75, "Repaso de escucha", "Consolidar la comprensión auditiva de los seis diálogos de la pestaña Escucha.", listOf(
        "Repetir los diálogos que hayan presentado más dificultad.",
        "Escuchar cada diálogo completo más de una vez.",
        "Revisar las dudas registradas.",
        "Preparar el simulacro de escucha."
    )),
    KanaDay(76, "Preparación del simulacro", "Preparar la escucha de diálogos cortos antes de la prueba semanal.", listOf(
        "Repasar los seis diálogos de la pestaña Escucha.",
        "Practicar la escucha sin consultar las respuestas durante la reproducción.",
        "Revisar las dudas y errores anteriores.",
        "Dejar listo el cuaderno de errores."
    )),
    KanaDay(77, "Simulacro de escucha", "Comprobar la comprensión auditiva mediante un simulacro N5.", listOf(
        "Completar el simulacro con los seis diálogos de la pestaña Escucha.",
        "Revisar las respuestas incorrectas.",
        "Guardar los errores en el cuaderno para repasarlos."
    )),
    KanaDay(78, "Preparación del simulacro completo", "Organizar el repaso final de las áreas N5 antes del examen completo.", listOf(
        "Repasar vocabulario estudiado en las semanas anteriores.",
        "Repasar gramática y lectura de las unidades completadas.",
        "Repasar kanji y sus lecturas cuando el banco validado esté disponible.",
        "Revisar el cuaderno de errores."
    )),
    KanaDay(79, "Repaso de vocabulario", "Preparar la sección de vocabulario del simulacro N5.", listOf(
        "Repasar el vocabulario de las unidades estudiadas.",
        "Revisar los errores de vocabulario guardados.",
        "Practicar lectura y reconocimiento del material disponible.",
        "Anotar los temas que necesitan otro repaso."
    )),
    KanaDay(80, "Repaso de gramática y lectura", "Preparar la sección de gramática y lectura del simulacro N5.", listOf(
        "Repasar las estructuras gramaticales de las semanas anteriores.",
        "Revisar los errores de gramática guardados.",
        "Practicar la lectura del material estudiado.",
        "Anotar las dudas que queden pendientes."
    )),
    KanaDay(81, "Repaso de comprensión auditiva", "Preparar la sección de escucha del simulacro N5.", listOf(
        "Escuchar los seis diálogos de la pestaña Escucha.",
        "Repetir los audios que hayan resultado difíciles.",
        "Revisar las dudas guardadas durante la semana 11.",
        "Preparar el examen sin consultar el material durante la prueba."
    )),
    KanaDay(82, "Preparación cronometrada", "Preparar el simulacro completo con los tiempos oficiales de referencia.", listOf(
        "Revisar la distribución de tiempo de las secciones N5.",
        "Preparar un entorno sin interrupciones ni consultas.",
        "Comprobar que el material de las tres secciones esté disponible.",
        "Revisar las instrucciones antes de comenzar."
    )),
    KanaDay(83, "Simulacro integrado de práctica N5", "Realizar la práctica integrada de 30 preguntas con la práctica integrada de 30 preguntas disponible.", listOf(
        "Completar vocabulario en el tiempo asignado.",
        "Completar gramática y lectura en el tiempo asignado.",
        "Completar comprensión auditiva con los audios correspondientes.",
        "Finalizar el simulacro sin consultar el material."
    )),
    KanaDay(84, "Revisión del cuaderno de errores", "Revisar los errores del simulacro completo y definir qué reforzar.", listOf(
        "Revisar las respuestas incorrectas del simulacro.",
        "Clasificar los errores por sección.",
        "Repasar los contenidos que presentaron dificultad.",
        "Consultar el cuaderno de errores para organizar el siguiente repaso."
    ))
)

internal fun finalPlanDaysForWeek(week: Int): List<KanaDay> = when (week) {
    11 -> n5FinalWeeks.take(7)
    12 -> n5FinalWeeks.drop(7).take(7)
    else -> emptyList()
}

/** Tiempos de referencia publicados por el sitio oficial del JLPT para N5. */
internal data class N5SectionTiming(val section: String, val minutes: Int)
internal val n5OfficialSectionTimings = listOf(
    N5SectionTiming("Vocabulario", 20),
    N5SectionTiming("Gramática y lectura", 40),
    N5SectionTiming("Comprensión auditiva", 30)
)
internal const val N5_OFFICIAL_TOTAL_MINUTES = 90
