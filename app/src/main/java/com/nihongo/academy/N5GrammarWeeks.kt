package com.nihongo.academy

/** Semanas 3–4 del plan del PDF: saludos, presentación y partículas básicas. */
internal val n5GrammarWeeks = listOf(
    KanaDay(15, "Saludos básicos", "Reconocer saludos de uso diario y cuándo se utilizan.", listOf(
        "Relacionar ohayou gozaimasu con buenos días.",
        "Distinguir konnichiwa y konbanwa según el momento.",
        "Leer los saludos en kana cuando estén disponibles.",
        "Repasar cada saludo sin mirar la traducción."
    )),
    KanaDay(16, "Presentarse", "Usar watashi wa [nombre] desu para decir quién sos.", listOf(
        "Practicar watashi wa [nombre] desu.",
        "Reconocer watashi como yo y anata como tú.",
        "Leer la frase de presentación del material.",
        "Decir tu nombre con la estructura estudiada."
    )),
    KanaDay(17, "Cortesía al conocer personas", "Usar hajimemashite y expresiones de cortesía.", listOf(
        "Practicar hajimemashite al conocer a alguien.",
        "Reconocer arigatou como gracias.",
        "Reconocer sumimasen como disculpe o perdón.",
        "Repasar una presentación completa."
    )),
    KanaDay(18, "Pedir algo con cortesía", "Comprender el uso de onegaishimasu.", listOf(
        "Relacionar onegaishimasu con una petición cortés.",
        "Distinguir agradecimiento de petición.",
        "Leer ejemplos de presentación y cortesía.",
        "Practicar una petición sencilla."
    )),
    KanaDay(19, "Lectura de saludos", "Leer y reconocer las expresiones de saludo estudiadas.", listOf(
        "Leer los saludos sin traducción.",
        "Relacionar cada expresión con su significado.",
        "Escribir de memoria las expresiones principales.",
        "Anotar las expresiones confundidas."
    )),
    KanaDay(20, "Repaso de presentación", "Unir saludo, nombre y expresión de cortesía.", listOf(
        "Escribir dos frases de presentación.",
        "Practicar hajimemashite y onegaishimasu.",
        "Leer las frases en voz alta.",
        "Rehacer las respuestas falladas."
    )),
    KanaDay(21, "Evaluación de saludos", "Prueba corta de saludos y presentación.", listOf(
        "Completar la prueba semanal.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con práctica adicional."
    )),
    KanaDay(22, "Partícula wa", "Usar は para marcar el tema de una oración.", listOf(
        "Reconocer wa como marcador de tema.",
        "Completar watashi wa [nombre] desu.",
        "Leer frases cortas e identificar el tema.",
        "Crear dos frases con wa."
    )),
    KanaDay(23, "Partícula ga", "Reconocer が como marcador de sujeto en los ejemplos estudiados.", listOf(
        "Leer neko ga imasu.",
        "Identificar qué elemento se presenta como sujeto.",
        "Distinguir el uso de wa y ga en los ejemplos del curso.",
        "Completar frases con ga."
    )),
    KanaDay(24, "Partícula o", "Usar を para marcar el objeto directo.", listOf(
        "Reconocer hon o yomimasu como estructura de objeto.",
        "Identificar qué recibe la acción.",
        "Completar frases sencillas con o.",
        "Repasar wa y ga."
    )),
    KanaDay(25, "Partícula ni", "Reconocer に para destino o dirección en los ejemplos del curso.", listOf(
        "Leer gakkou ni ikimasu.",
        "Identificar el destino de la acción.",
        "Completar frases con ni.",
        "Distinguir ni de o."
    )),
    KanaDay(26, "Partícula de", "Usar で para indicar el lugar donde ocurre una acción.", listOf(
        "Leer gakkou de benkyou shimasu.",
        "Identificar el lugar donde ocurre la acción.",
        "Completar frases con de.",
        "Comparar ni como destino y de como lugar de acción."
    )),
    KanaDay(27, "Repaso de partículas", "Distinguir wa, ga, o, ni y de en frases breves.", listOf(
        "Clasificar la función de cada partícula.",
        "Completar cinco frases mezcladas.",
        "Explicar por qué elegiste cada partícula.",
        "Revisar los errores."
    )),
    KanaDay(28, "Evaluación de partículas", "Prueba corta de wa, ga, o, ni y de.", listOf(
        "Completar la prueba semanal.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con práctica adicional."
    ))
)

internal val greetingsWeekAssessment = listOf(
    KanaQuizQuestion("¿Qué significa ohayou gozaimasu?", listOf("Buenos días","Buenas noches","Gracias","Disculpe"),0),
    KanaQuizQuestion("¿Qué significa konnichiwa?", listOf("Hola / buenas tardes","Buenas noches","Hasta mañana","Gracias"),0),
    KanaQuizQuestion("¿Qué significa watashi?", listOf("Yo","Tú","Maestro","Estudiante"),0),
    KanaQuizQuestion("¿Qué significa hajimemashite?", listOf("Encantado de conocerte","Gracias","Disculpe","Adiós"),0),
    KanaQuizQuestion("¿Qué significa arigatou?", listOf("Gracias","Por favor","Disculpe","Buenos días"),0),
    KanaQuizQuestion("¿Qué significa sumimasen?", listOf("Disculpe / perdón","Gracias","Encantado","Hasta luego"),0),
    KanaQuizQuestion("¿Qué expresa onegaishimasu al pedir algo?", listOf("Una petición cortés","Una despedida","Una disculpa","Una pregunta de lugar"),0),
    KanaQuizQuestion("Completá: Watashi ___ Sakura desu.", listOf("wa","o","ni","de"),0),
    KanaQuizQuestion("¿Qué significa anata?", listOf("Tú","Yo","Estudiante","Maestro"),0),
    KanaQuizQuestion("¿Qué expresa ~san?", listOf("Sufijo de trato respetuoso","Pasado del verbo","Partícula de objeto","Negación"),0)
)

internal val particlesWeekAssessment = listOf(
    KanaQuizQuestion("¿Qué partícula marca el tema?", listOf("wa","ga","o","de"),0),
    KanaQuizQuestion("En neko ga imasu, ¿qué función cumple ga?", listOf("Marca el sujeto","Marca el objeto","Marca el destino","Marca el lugar de acción"),0),
    KanaQuizQuestion("¿Qué partícula marca el objeto directo?", listOf("o","wa","ni","ga"),0),
    KanaQuizQuestion("¿Qué partícula puede marcar destino o dirección?", listOf("ni","de","wa","o"),0),
    KanaQuizQuestion("¿Qué partícula indica dónde ocurre una acción?", listOf("de","ni","wa","ga"),0),
    KanaQuizQuestion("Completá: Watashi ___ gakusei desu.", listOf("wa","o","de","ni"),0),
    KanaQuizQuestion("Completá: Hon ___ yomimasu.", listOf("o","wa","ga","de"),0),
    KanaQuizQuestion("Completá: Gakkou ___ ikimasu.", listOf("ni","de","o","wa"),0),
    KanaQuizQuestion("Completá: Gakkou ___ benkyou shimasu.", listOf("de","ni","wa","o"),0),
    KanaQuizQuestion("¿Qué significa mo?", listOf("También","Objeto directo","Destino","Lugar de acción"),0)
)

internal fun weeklyAssessmentForWeek(week: Int): List<KanaQuizQuestion> = when (week) {
    1 -> hiraganaWeekAssessment
    2 -> katakanaWeekAssessment
    3 -> greetingsWeekAssessment
    4 -> particlesWeekAssessment
    5 -> masuWeekAssessment
    6 -> masuPastNegativeAssessment
    7 -> iAdjectivesWeekAssessment
    8 -> naAdjectivesWeekAssessment
    else -> emptyList()
}

internal fun grammarDaysForWeek(week: Int): List<KanaDay> = when (week) {
    3 -> n5GrammarWeeks.take(7)
    4 -> n5GrammarWeeks.drop(7).take(7)
    else -> emptyList()
}
