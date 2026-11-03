package com.nihongo.academy

// Octava unidad gramatical N3: apariencia, información, inferencia y expectativa.
internal val n3AppearanceInformationProbabilityDays = listOf(
    KanaDay(1, "そうだ: apariencia observable", "Expresar una impresión basada en lo que se ve o en señales inmediatas.",
        listOf(
            "Formar verbo en raíz (ます sin ます) + そうだ: 雨が降りそうです.",
            "Con adjetivos い, quitar い y añadir そう: おいしそうです.",
            "Con adjetivos な, añadir そう: 元気そうです.",
            "Recordar las excepciones いい → よさそう y ない → なさそう.",
            "No confundir este そうだ visual con el そうだ que transmite información o rumor."
        )),
    KanaDay(2, "そうだ: información transmitida", "Comunicar algo que se ha oído o leído, sin presentarlo como observación propia.",
        listOf(
            "Usar la forma simple del verbo o adjetivo + そうだ: 雨が降るそうです.",
            "Con sustantivos y adjetivos な, mantener la forma simple correspondiente: 便利だそうです y 学生だそうです.",
            "Identificar la fuente cuando el contexto lo permite: noticias, una persona o un anuncio.",
            "Contrastar 雨が降りそうです (parece que va a llover) con 雨が降るそうです (dicen que lloverá)."
        )),
    KanaDay(3, "ようだ: inferencia y comparación", "Expresar una conclusión basada en indicios o establecer una comparación.",
        listOf(
            "Usar verbo en forma simple + ようだ para una inferencia contextual.",
            "Con sustantivo + のようだ y adjetivo な + なようだ.",
            "Practicar 電気が消えているので、寝たようです.",
            "Reconocer ように y ような en funciones adverbiales y modificadoras.",
            "Distinguir una inferencia razonada de un rumor recibido."
        )),
    KanaDay(4, "みたいだ: semejanza e inferencia coloquial", "Expresar una impresión o semejanza en un registro más conversacional que ようだ.",
        listOf(
            "Usar forma simple + みたいだ en conversaciones.",
            "Practicar 彼は疲れているみたいです.",
            "Usar sustantivo + みたいだ para expresar semejanza: 夢みたいです.",
            "Comparar 雨が降るようです y 雨が降るみたいです según registro y contexto.",
            "Evitar tratar みたいだ como una forma formal en escritos académicos."
        )),
    KanaDay(5, "らしい: rumor y característica típica", "Transmitir información indirecta o describir algo como característico de una persona o cosa.",
        listOf(
            "Usar forma simple + らしい para información indirecta: 来週は休みらしいです.",
            "Reconocer el uso de らしい para rasgos típicos: 子どもらしい考え方.",
            "Distinguir el rumor de una descripción de característica por el contexto.",
            "Comparar そうだ de transmisión y らしい: ambos pueden comunicar información indirecta, con matices distintos.",
            "No confundir らしい con そうだ de apariencia visual."
        )),
    KanaDay(6, "はずだ y はずがない: expectativa lógica", "Expresar lo que se espera razonablemente o lo que se considera imposible según la información disponible.",
        listOf(
            "Usar verbo y adjetivo い en forma simple + はずだ.",
            "Con adjetivo な y sustantivo, usar なはずだ: 静かなはずです y 休みのはずです.",
            "Practicar もう出発したので、そろそろ着くはずです.",
            "Usar はずがない para negar con fuerza una posibilidad: そんなことがあるはずがない.",
            "Distinguir una expectativa fundamentada de una certeza absoluta."
        )),
    KanaDay(7, "Comparación y evaluación de matices", "Elegir entre apariencia, rumor, inferencia, semejanza y expectativa según la evidencia y el registro.",
        listOf(
            "Contrastar そうだ de apariencia con そうだ de información transmitida.",
            "Distinguir ようだ y みたいだ por registro y contexto.",
            "Comparar らしい como información indirecta y como característica típica.",
            "Elegir はずだ o はずがない cuando existe una expectativa razonada.",
            "Completar la evaluación semanal y justificar cada respuesta por el contexto."
        ))
)

internal val n3AppearanceInformationProbabilityAssessment = listOf(
    KanaQuizQuestion("¿Qué significa おいしそうです?", listOf("Parece delicioso", "Dicen que es delicioso", "Debe ser delicioso según una regla", "No puede ser delicioso"), 0),
    KanaQuizQuestion("¿Qué forma transmite información o rumor en 雨が降るそうです?", listOf("Forma simple + そうだ", "Raíz del verbo + そうだ", "Forma て + そうだ", "Sustantivo + のそうだ"), 0),
    KanaQuizQuestion("¿Qué significa 雨が降りそうです?", listOf("Parece que va a llover", "Dicen que lloverá", "Es imposible que llueva", "Siempre llueve"), 0),
    KanaQuizQuestion("¿Qué estructura puede expresar una inferencia basada en indicios?", listOf("ようだ", "あげる", "ておく", "たびに"), 0),
    KanaQuizQuestion("¿Cuál es la construcción correcta con un sustantivo antes de ようだ?", listOf("学生のようだ", "学生なようだ", "学生ようだ", "学生だのようだ"), 0),
    KanaQuizQuestion("¿Qué matiz suele tener みたいだ frente a ようだ?", listOf("Es más coloquial", "Es siempre más formal", "Solo expresa tiempo pasado", "Solo se usa con verbos"), 0),
    KanaQuizQuestion("En 子どもらしい考え方, ¿qué expresa らしい?", listOf("Una característica típica de un niño", "Una apariencia observada en ese instante", "Una imposibilidad", "Una acción futura obligatoria"), 0),
    KanaQuizQuestion("¿Qué puede expresar 来週は休みらしいです?", listOf("Información indirecta: parece que la semana que viene hay descanso", "Una apariencia visual", "Una orden", "Una decisión personal"), 0),
    KanaQuizQuestion("¿Qué estructura se usa para una expectativa razonada?", listOf("はずだ", "てあげる", "間に", "ようとする"), 0),
    KanaQuizQuestion("¿Cuál es la forma adecuada con un adjetivo な antes de はずだ?", listOf("静かなはずだ", "静かのはずだ", "静かそうはずだ", "静かにはずだ"), 0),
    KanaQuizQuestion("¿Qué expresa はずがない?", listOf("Que algo se considera imposible o muy improbable según el contexto", "Que algo parece delicioso", "Que se recibió un rumor", "Que una acción se prepara de antemano"), 0),
    KanaQuizQuestion("¿Cuál es la diferencia entre ようだ y そうだ de transmisión?", listOf("ようだ suele expresar inferencia; そうだ de transmisión comunica información recibida", "Ambos solo describen apariencia visual", "そうだ siempre expresa imposibilidad", "ようだ solo se usa para rumores"), 0)
)
