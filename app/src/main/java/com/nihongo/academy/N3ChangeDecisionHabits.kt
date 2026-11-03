package com.nihongo.academy

// Séptima unidad gramatical N3: cambios, decisiones y hábitos.
internal val n3ChangeDecisionHabitsDays = listOf(
    KanaDay(1, "ようになる: cambios y nuevas capacidades", "Expresar que una capacidad, estado o hábito aparece o cambia con el tiempo.",
        listOf(
            "Usar verbo en forma diccionario o negativa + ようになる.",
            "Practicar 日本語が少し話せるようになりました.",
            "Distinguir un cambio gradual de una decisión puntual.",
            "Reconocer ようになった para un cambio que ya se produjo."
        )),
    KanaDay(2, "ようにする: esfuerzo y hábito consciente", "Expresar que alguien procura hacer algo o mantener una conducta de forma deliberada.",
        listOf(
            "Usar verbo en forma diccionario o negativa + ようにする.",
            "Practicar 毎日、日本語の記事を読むようにしています.",
            "Distinguir ようにしている como esfuerzo o hábito mantenido.",
            "Crear objetivos realistas con acciones observables."
        )),
    KanaDay(3, "ことになる: decisión o resultado establecido", "Presentar una decisión, acuerdo o situación que queda establecida, sin centrar el foco en quién decide.",
        listOf(
            "Usar verbo en forma diccionario o negativa + ことになる.",
            "Practicar 来月から大阪で働くことになりました.",
            "Reconocer decisiones institucionales, acuerdos y circunstancias resultantes.",
            "Comparar con ことにする, que destaca la decisión propia."
        )),
    KanaDay(4, "ことにする: tomar una decisión", "Expresar una decisión tomada por el hablante o por quien actúa.",
        listOf(
            "Usar verbo en forma diccionario o negativa + ことにする.",
            "Practicar 夜はスマートフォンを使わないことにしました.",
            "Reconocer ことにした como decisión ya tomada.",
            "Comparar decisiones personales con decisiones establecidas externamente."
        )),
    KanaDay(5, "ようとする: intentar o estar a punto de", "Expresar un intento de realizar una acción o el momento inmediatamente anterior a ella.",
        listOf(
            "Formar verbo en volitiva + とする.",
            "Practicar 出かけようとしたとき、電話が鳴りました.",
            "Distinguir el intento de una acción completada.",
            "Reconocer ようとしない cuando alguien no muestra intención de hacer algo."
        )),
    KanaDay(6, "ておく: preparación y acción anticipada", "Expresar que se realiza algo con antelación para una necesidad futura o que se deja preparado.",
        listOf(
            "Usar forma て + おく.",
            "Practicar 試験の前に、単語を復習しておきます.",
            "Reconocer la contracción coloquial ておく → とく y でおく → どく.",
            "Distinguir preparación anticipada de una acción que simplemente continúa."
        )),
    KanaDay(7, "Comparación y evaluación de cambios y decisiones", "Elegir la estructura según cambio, esfuerzo, decisión, intento o preparación.",
        listOf(
            "Contrastar ようになる y ようにする.",
            "Distinguir ことになる de ことにする según quién establece la decisión.",
            "Comparar ようとする con ておく en contexto.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3ChangeDecisionHabitsAssessment = listOf(
    KanaQuizQuestion("¿Qué expresa 日本語が話せるようになりました?", listOf("Decidí dejar de hablar japonés", "Intenté hablar una vez", "Llegué a poder hablar japonés", "Preparé japonés con antelación"), 2),
    KanaQuizQuestion("¿Qué estructura expresa un esfuerzo o hábito consciente?", listOf("ようにする", "ようになる", "ことになる", "途中で"), 0),
    KanaQuizQuestion("¿Qué matiz suele tener ことになる?", listOf("Una decisión personal destacada", "Una acción repetida cada vez", "Un resultado favorable", "Una decisión o situación que queda establecida"), 3),
    KanaQuizQuestion("¿Qué estructura destaca una decisión propia?", listOf("ことになる", "ことにする", "ようになる", "たびに"), 1),
    KanaQuizQuestion("¿Qué significa 夜は使わないことにしました?", listOf("Se decidió que no lo use otra persona", "No puedo usarlo por la noche", "Decidí no usarlo por la noche", "Lo usé cada noche"), 2),
    KanaQuizQuestion("¿Cómo se forma ようとする?", listOf("Forma volitiva + とする", "Forma て + おく", "Forma pasada + ら", "Sustantivo + の + ため"), 0),
    KanaQuizQuestion("¿Qué puede expresar 出かけようとしたとき?", listOf("Cada vez que salía", "Después de haber salido durante horas", "Antes de decidir no salir", "Justo cuando iba a intentar salir"), 3),
    KanaQuizQuestion("¿Qué estructura indica preparar algo con antelación?", listOf("てしまう", "ておく", "てみる", "てあげる"), 1),
    KanaQuizQuestion("¿Cuál es un ejemplo natural de ようにしています?", listOf("毎日、本を読んだようにしています", "毎日、本を読むことになりました (si se quiere expresar un hábito propio)", "毎日、本を読むようにしています", "毎日、本を読んでいる間にしています"), 2),
    KanaQuizQuestion("¿Qué diferencia principal hay entre ようになる y ようにする?", listOf("Cambio que se produce frente a esfuerzo consciente", "Pasado frente a futuro exclusivamente", "Causa positiva frente a negativa", "Lugar frente a dirección"), 0),
    KanaQuizQuestion("¿Qué expresa ておく en 試験の前に復習しておきます?", listOf("Repaso cada vez que hay un examen", "Dejé de repasar", "Me obligaron a repasar", "Repasaré de antemano para el examen"), 3),
    KanaQuizQuestion("¿Qué debes observar para elegir entre ことになる y ことにする?", listOf("Solo el número de kanji", "Si se presenta una decisión establecida o una decisión propia", "Si la frase tiene sujeto explícito", "La cantidad de sílabas"), 1)
)
