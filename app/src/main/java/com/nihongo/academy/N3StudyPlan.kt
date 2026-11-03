package com.nihongo.academy

/**
 * Programa pedagógico propio para organizar el futuro recorrido N3.
 * No representa una lista oficial de contenidos publicada por JLPT.
 */
internal data class N3StudyWeek(
    val week: Int,
    val title: String,
    val focus: String,
    val activities: List<String>,
    val assessment: String
)

internal val n3SixteenWeekPlan = listOf(
    N3StudyWeek(1, "Voz pasiva", "Formación y usos de la voz pasiva en situaciones cotidianas",
        listOf("Repasar la conjugación de verbos en forma pasiva", "Distinguir quién realiza y quién recibe la acción", "Practicar frases sobre experiencias y situaciones"),
        "Evaluación de formación y comprensión de la voz pasiva"),
    N3StudyWeek(2, "Causativa y causativa-pasiva", "Expresar permiso, obligación y acciones causadas por otra persona",
        listOf("Formar la voz causativa en los tres grupos verbales", "Contrastar causativa y causativa-pasiva", "Practicar situaciones de escuela y trabajo"),
        "Evaluación de causativa y causativa-pasiva"),
    N3StudyWeek(3, "Dar, recibir y consideración", "Perspectiva, beneficio y acciones realizadas por otras personas",
        listOf("Repasar ageru, kureru y morau en estructuras ampliadas", "Practicar expresiones de favor y consideración", "Distinguir el punto de vista del hablante"),
        "Evaluación de perspectiva y beneficio"),
    N3StudyWeek(4, "Condiciones y supuestos", "Contrastar ba, tara, nara y to según intención y contexto",
        listOf("Comparar las cuatro formas condicionales", "Identificar restricciones con órdenes, invitaciones y resultados naturales", "Completar diálogos con condiciones adecuadas"),
        "Evaluación comparativa de estructuras condicionales"),
    N3StudyWeek(5, "Tiempo y simultaneidad", "Expresar intervalos, cambios y acciones que ocurren durante un periodo",
        listOf("Practicar aida, aida ni y uchi ni", "Distinguir duración y momento oportuno", "Usar tabi ni y tochuu en ejemplos contextualizados"),
        "Evaluación de expresiones temporales"),
    N3StudyWeek(6, "Causa, motivo y resultado", "Explicar causas con matices positivos, negativos y formales",
        listOf("Contrastar okage de y sei de", "Practicar tame ni y ni yotte", "Relacionar causas y consecuencias en textos breves"),
        "Evaluación de causa y consecuencia"),
    N3StudyWeek(7, "Cambios, decisiones y hábitos", "Expresar decisiones, cambios de estado y esfuerzos continuados",
        listOf("Distinguir koto ni suru y koto ni naru", "Practicar you ni suru y you ni naru", "Crear ejemplos sobre hábitos y objetivos personales"),
        "Evaluación de decisiones y cambios"),
    N3StudyWeek(8, "Apariencia, información y probabilidad", "Expresar suposición, apariencia, rumor y expectativa",
        listOf("Contrastar sou da, you da, mitai da y rashii", "Practicar hazu da y posibilidad contextual", "Justificar inferencias a partir de información"),
        "Evaluación de matices de certeza"),
    N3StudyWeek(9, "Kanji y vocabulario: bloque 1", "Reconocimiento, lecturas y palabras frecuentes de nivel intermedio",
        listOf("Estudiar el primer bloque de kanji seleccionado para N3", "Relacionar kanji con vocabulario en contexto", "Practicar lectura y escritura con repaso espaciado"),
        "Prueba de reconocimiento, lectura y vocabulario"),
    N3StudyWeek(10, "Kanji y vocabulario: bloque 2", "Ampliar lecturas y distinguir palabras de significado cercano",
        listOf("Estudiar el segundo bloque de kanji seleccionado para N3", "Practicar compuestos y lecturas dentro de palabras", "Repasar los caracteres pendientes"),
        "Prueba mixta de kanji y vocabulario"),
    N3StudyWeek(11, "Kanji y vocabulario: consolidación", "Integrar los bloques de kanji y vocabulario estudiados",
        listOf("Completar el bloque final de kanji seleccionado para N3", "Revisar lecturas y usos en frases", "Realizar repasos acumulativos"),
        "Evaluación global de kanji y vocabulario"),
    N3StudyWeek(12, "Comprensión auditiva", "Identificar intención, detalles y relaciones en conversaciones más extensas",
        listOf("Escuchar diálogos originales de situaciones reales", "Identificar información explícita e implícita", "Practicar escucha repetida y toma de notas"),
        "Evaluación de comprensión auditiva"),
    N3StudyWeek(13, "Comprensión lectora", "Leer textos funcionales y explicativos y localizar información relevante",
        listOf("Leer avisos, correos y textos narrativos originales", "Distinguir idea principal, detalles y referencias", "Justificar respuestas con fragmentos del texto"),
        "Evaluación de comprensión lectora"),
    N3StudyWeek(14, "Integración de habilidades", "Combinar gramática, vocabulario, kanji, lectura y escucha",
        listOf("Resolver ejercicios mixtos de las unidades gramaticales", "Repasar errores de kanji y vocabulario", "Completar una práctica integrada de lectura y escucha"),
        "Evaluación integrada por secciones"),
    N3StudyWeek(15, "Simulacro y recuperación", "Practicar con tiempo y reforzar los contenidos de menor rendimiento",
        listOf("Realizar un simulacro original de nivel N3", "Analizar resultados por sección", "Repetir contenidos con errores recurrentes"),
        "Simulacro N3 y plan de recuperación"),
    N3StudyWeek(16, "Cierre y evaluación final", "Consolidar el recorrido y comprobar el dominio de los objetivos trabajados",
        listOf("Repasar fichas y repeticiones pendientes", "Completar ejercicios finales de lectura y escucha", "Revisar el cuaderno de errores y resultados"),
        "Evaluación final integrada N3")
)

internal const val N3_PLAN_WEEKS = 16
internal const val N3_DAILY_TARGET_MINUTES = 45
internal const val N3_WEEKLY_PASS_PERCENT = 70
