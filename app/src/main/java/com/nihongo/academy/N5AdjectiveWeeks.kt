package com.nihongo.academy

/** Semanas 7–8 del Anexo E y unidades 5–6 del catálogo N5 del PDF. */
internal val n5AdjectiveWeeks = listOf(
    KanaDay(43, "Adjetivos i: reconocer", "Describir cosas con adjetivos que terminan en i.", listOf(
        "Relacionar ookii con grande.",
        "Relacionar chiisai con pequeño.",
        "Leer ambas palabras y reconocer la terminación i.",
        "Repasar los significados sin mirar la traducción."
    )),
    KanaDay(44, "Adjetivos i: precio y tamaño", "Ampliar la descripción con takai y yasui.", listOf(
        "Relacionar takai con caro o alto.",
        "Relacionar yasui con barato.",
        "Distinguir los significados según el contexto.",
        "Escribir una frase corta con takai y otra con yasui."
    )),
    KanaDay(45, "Adjetivos i: temperatura", "Reconocer atsui y samui.", listOf(
        "Relacionar atsui con caliente.",
        "Relacionar samui con frío.",
        "Leer los dos ejemplos del catálogo.",
        "Repasar los seis adjetivos i estudiados."
    )),
    KanaDay(46, "Negativo de adjetivos i", "Formar el negativo con ~kunai desu.", listOf(
        "Transformar atsui en atsukunai desu.",
        "Transformar samui en samukunai desu.",
        "Identificar la terminación ~kunai desu.",
        "Practicar la forma negativa de cuatro adjetivos i."
    )),
    KanaDay(47, "Adjetivos i en frases", "Comprender adjetivos i en ejemplos cotidianos.", listOf(
        "Leer Kono hon wa takai desu. (Este libro es caro.)",
        "Leer Kyou wa samui desu. (Hoy hace frío.)",
        "Identificar el adjetivo en cada frase.",
        "Crear frases usando los adjetivos estudiados."
    )),
    KanaDay(48, "Repaso de adjetivos i", "Repasar vocabulario y formas afirmativa y negativa.", listOf(
        "Repasar ookii, chiisai, takai, yasui, atsui y samui.",
        "Escribir el significado de cada adjetivo.",
        "Practicar la forma negativa de cuatro adjetivos i.",
        "Corregir y repetir los ejercicios con errores."
    )),
    KanaDay(49, "Evaluación de adjetivos i", "Comprobar el uso de cuatro adjetivos i en forma negativa.", listOf(
        "Completar la evaluación semanal.",
        "Usar cuatro adjetivos i con su forma negativa.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con práctica adicional."
    )),
    KanaDay(50, "Adjetivos na: reconocer", "Describir cosas con adjetivos que van seguidos de na.", listOf(
        "Relacionar kirei con bonito.",
        "Relacionar shizuka con tranquilo.",
        "Reconocer la forma ~desu en frases afirmativas.",
        "Repasar los significados sin mirar la traducción."
    )),
    KanaDay(51, "Adjetivos na: estado y utilidad", "Ampliar el vocabulario con genki y benri.", listOf(
        "Relacionar genki con sano o animado.",
        "Relacionar benri con práctico.",
        "Leer los adjetivos y decir sus significados.",
        "Practicar frases afirmativas con ~desu."
    )),
    KanaDay(52, "Adjetivos na: tiempo libre y gusto", "Reconocer hima y suki.", listOf(
        "Relacionar hima con libre o desocupado.",
        "Relacionar suki con gustar.",
        "Leer y repetir las dos palabras.",
        "Repasar los seis adjetivos na del catálogo."
    )),
    KanaDay(53, "Negativo de adjetivos na", "Usar ~ja arimasen para expresar negación.", listOf(
        "Transformar kirei desu en kirei ja arimasen.",
        "Reconocer la forma negativa en una frase.",
        "Comparar ~desu y ~ja arimasen.",
        "Practicar frases afirmativas y negativas."
    )),
    KanaDay(54, "Adjetivos na en frases", "Comprender adjetivos na en ejemplos del catálogo.", listOf(
        "Leer Kono machi wa shizuka desu. (Esta ciudad es tranquila.)",
        "Leer Sono heya wa kirei ja arimasen. (Esa habitación no es bonita.)",
        "Identificar el adjetivo y su forma.",
        "Escribir frases afirmativas y negativas."
    )),
    KanaDay(55, "Repaso de adjetivos na", "Repasar vocabulario y las formas afirmativa y negativa.", listOf(
        "Repasar kirei, shizuka, genki, benri, hima y suki.",
        "Escribir tres frases con adjetivos na.",
        "Incluir formas afirmativas y negativas.",
        "Corregir y repetir los ejercicios con errores."
    )),
    KanaDay(56, "Evaluación de adjetivos na", "Comprobar el uso afirmativo y negativo de adjetivos na.", listOf(
        "Completar la evaluación semanal.",
        "Escribir tres frases con adjetivos na en afirmativo y negativo.",
        "Revisar cada respuesta incorrecta.",
        "Si los errores superan el 30%, repetir la semana con práctica adicional."
    ))
)

internal val iAdjectivesWeekAssessment = listOf(
    KanaQuizQuestion("¿Qué significa ookii?", listOf("Grande","Pequeño","Caro","Frío"),0),
    KanaQuizQuestion("¿Qué significa chiisai?", listOf("Pequeño","Grande","Barato","Caliente"),0),
    KanaQuizQuestion("¿Qué significa takai?", listOf("Caro o alto","Barato","Frío","Tranquilo"),0),
    KanaQuizQuestion("¿Qué significa yasui?", listOf("Barato","Caro","Grande","Caliente"),0),
    KanaQuizQuestion("¿Qué significa atsui?", listOf("Caliente","Frío","Pequeño","Tranquilo"),0),
    KanaQuizQuestion("¿Qué significa samui?", listOf("Frío","Caliente","Caro","Grande"),0),
    KanaQuizQuestion("Forma negativa de atsui", listOf("atsukunai desu","atsui desu","atsukatta","atsuna desu"),0),
    KanaQuizQuestion("Forma negativa de samui", listOf("samukunai desu","samui desu","samukatta","samuna desu"),0),
    KanaQuizQuestion("Completá: Kono hon wa takai ___.", listOf("desu","kunai","ja arimasen","mashita"),0),
    KanaQuizQuestion("¿Qué terminación forma el negativo de los adjetivos i?", listOf("kunai desu","ja arimasen","mashita","masen"),0)
)

internal val naAdjectivesWeekAssessment = listOf(
    KanaQuizQuestion("¿Qué significa kirei?", listOf("Bonito","Tranquilo","Práctico","Libre"),0),
    KanaQuizQuestion("¿Qué significa shizuka?", listOf("Tranquilo","Grande","Animado","Caro"),0),
    KanaQuizQuestion("¿Qué significa genki?", listOf("Sano o animado","Práctico","Libre","Frío"),0),
    KanaQuizQuestion("¿Qué significa benri?", listOf("Práctico","Bonito","Tranquilo","Pequeño"),0),
    KanaQuizQuestion("¿Qué significa hima?", listOf("Libre o desocupado","Sano","Caro","Caliente"),0),
    KanaQuizQuestion("¿Qué significa suki?", listOf("Gustar","Práctico","Tranquilo","Grande"),0),
    KanaQuizQuestion("Forma afirmativa de kirei", listOf("kirei desu","kirei kunai","kirei mashita","kirei masu"),0),
    KanaQuizQuestion("Forma negativa de benri desu", listOf("benri ja arimasen","benri kunai","benri mashita","benri nai desu"),0),
    KanaQuizQuestion("¿Qué expresa la forma ~ja arimasen?", listOf("Negación","Pasado","Futuro","Pregunta"),0),
    KanaQuizQuestion("¿Qué formas se practican con adjetivos na en esta unidad?", listOf("~desu y ~ja arimasen","~masu y ~mashita","~kunai y ~masen","~te y ~nai"),0)
)

internal fun adjectiveDaysForWeek(week: Int): List<KanaDay> = when (week) {
    7 -> n5AdjectiveWeeks.take(7)
    8 -> n5AdjectiveWeeks.drop(7).take(7)
    else -> emptyList()
}
