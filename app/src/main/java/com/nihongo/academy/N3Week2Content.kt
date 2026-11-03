package com.nihongo.academy

/**
 * Material didáctico original para N3, semana 2.
 * Programa pedagógico propio: no es una lista oficial ni material afiliado al JLPT.
 */
internal data class N3Week2Exercise(
    val prompt: String,
    val options: List<String>,
    val answerIndex: Int,
    val explanation: String
)

internal data class N3Week2Lesson(
    val day: Int,
    val title: String,
    val objective: String,
    val explanation: String,
    val examples: List<String>,
    val exercises: List<N3Week2Exercise>
)

internal val n3Week2Lessons = listOf(
    N3Week2Lesson(
        1, "La forma causativa: significado y uso",
        "Expresar que alguien hace que otra persona realice una acción, o que le permite hacerla.",
        "La causativa puede expresar permiso o hacer que alguien actúe. El contexto determina el matiz. La persona a quien se hace actuar suele marcarse con に cuando el verbo conserva un objeto directo; con verbos intransitivos es frecuente を. No traduzcas la estructura mecánicamente: identificá quién causa la acción y quién la realiza.",
        listOf(
            "先生は学生に本を読ませます。— El profesor hace que el estudiante lea un libro.",
            "母は子どもを公園で遊ばせました。— La madre dejó que el niño jugara en el parque.",
            "部長は私に資料を作らせました。— El jefe me hizo preparar el material."
        ),
        listOf(
            N3Week2Exercise("En 先生は学生に本を読ませます, ¿quién realiza la lectura?", listOf("El profesor", "El estudiante", "El director", "El hablante"), 1, "El estudiante es quien lee; el profesor causa o permite la acción."),
            N3Week2Exercise("¿Qué matiz puede expresar la causativa según el contexto?", listOf("Solo pasado", "Permiso o acción impuesta", "Solo posibilidad", "Comparación"), 1, "La causativa puede indicar que se deja hacer algo o que se hace actuar a alguien."),
            N3Week2Exercise("En una causativa con un verbo intransitivo, ¿qué marca es frecuente para la persona afectada?", listOf("を", "から", "まで", "より"), 0, "Con verbos intransitivos es frecuente を para marcar a la persona a quien se hace actuar.")
        )
    ),
    N3Week2Lesson(
        2, "Causativa del grupo 1 (godan)",
        "Transformar verbos godan a la forma causativa.",
        "En los verbos del grupo 1, la última mora cambia a la fila あ y se añade せる. Para los verbos terminados en う, se usa わせる. La forma resultante se conjuga como un verbo del grupo 2.",
        listOf(
            "書く → 書かせる (kaku → kakaseru) — hacer/dejar escribir.",
            "読む → 読ませる (yomu → yomaseru) — hacer/dejar leer.",
            "話す → 話させる (hanasu → hanasaseru) — hacer/dejar hablar.",
            "待つ → 待たせる (matsu → mataseru) — hacer/dejar esperar.",
            "買う → 買わせる (kau → kawaseru) — hacer/dejar comprar."
        ),
        listOf(
            N3Week2Exercise("Forma causativa de 書く (kaku)", listOf("書かせる", "書きさせる", "書けさせる", "書かれる"), 0, "く cambia a か y se añade せる: 書かせる."),
            N3Week2Exercise("Forma causativa de 話す (hanasu)", listOf("話させる", "話せさせる", "話される", "話してさせる"), 0, "す cambia a さ y se añade せる: 話させる."),
            N3Week2Exercise("Forma causativa de 買う (kau)", listOf("買わせる", "買あせる", "買させる", "買われる"), 0, "う cambia a わ antes de せる: 買わせる.")
        )
    ),
    N3Week2Lesson(
        3, "Causativa del grupo 2 e irregulares",
        "Formar la causativa de verbos ichidan y de los verbos irregulares.",
        "En el grupo 2 se elimina る y se añade させる. Los irregulares son する → させる y 来る（くる）→ 来させる（こさせる）. Recordá que la forma causativa resultante se comporta como un verbo del grupo 2.",
        listOf(
            "食べる → 食べさせる (taberu → tabesaseru) — hacer/dejar comer.",
            "見る → 見させる (miru → misaseru) — hacer/dejar mirar.",
            "する → させる (suru → saseru) — hacer/dejar hacer.",
            "来る → 来させる (kuru → kosaseru) — hacer/dejar venir."
        ),
        listOf(
            N3Week2Exercise("Forma causativa de 食べる (taberu)", listOf("食べさせる", "食べられる", "食べさる", "食べてさせる"), 0, "Se elimina る y se añade させる."),
            N3Week2Exercise("Forma causativa de する (suru)", listOf("させる", "しさせる", "される", "している"), 0, "する es irregular y forma させる."),
            N3Week2Exercise("Lectura de 来させる", listOf("こさせる", "きさせる", "くさせる", "こらせる"), 0, "来る se lee くる y su causativa 来させる se lee こさせる.")
        )
    ),
    N3Week2Lesson(
        4, "Conjugación y partículas en contexto",
        "Usar la causativa en presente, pasado, negativo y con partículas adecuadas.",
        "La causativa se conjuga como un verbo del grupo 2: 読ませます, 読ませました, 読ませません, 読ませなかった. La partícula de la persona depende de la construcción y del tipo de verbo. Si hay un objeto directo marcado con を, に suele evitar la ambigüedad: 子どもに本を読ませる.",
        listOf(
            "先生は学生に漢字を書かせました。— El profesor hizo que el estudiante escribiera kanji.",
            "母は弟に野菜を食べさせませんでした。— Mi madre no hizo/dejó que mi hermano menor comiera verduras.",
            "今日は子どもを早く寝させます。— Hoy haré/dejaré que el niño se acueste temprano."
        ),
        listOf(
            N3Week2Exercise("Pasado cortés de 読ませる", listOf("読ませました", "読ませません", "読ませます", "読ませて"), 0, "La forma pasada cortés se construye con ました."),
            N3Week2Exercise("Elegí la frase más clara para decir que el profesor hizo leer un libro al alumno.", listOf("先生は学生に本を読ませました。", "先生は学生を本が読みました。", "先生は学生に本が読ませました。", "先生は学生を本に読みました。"), 0, "学生に marca a la persona que realiza la lectura y 本を marca el objeto."),
            N3Week2Exercise("Negativo cortés de 食べさせる", listOf("食べさせません", "食べさせました", "食べさせて", "食べさせる"), 0, "La forma negativa cortés añade ません al tema 食べさせ.")
        )
    ),
    N3Week2Lesson(
        5, "La forma causativa-pasiva",
        "Expresar que alguien se ve obligado a realizar una acción por otra persona.",
        "La causativa-pasiva combina causativa y pasiva y suele expresar una experiencia no deseada: alguien obliga al hablante a hacer algo. En los verbos godan es común la forma contraída con あ + される (por ejemplo, 書かされる); también existe la forma larga 書かせられる. En el grupo 2 se usa させられる.",
        listOf(
            "私は先生に作文を書かされました。— El profesor me hizo escribir una composición (a mi pesar).",
            "弟は母に野菜を食べさせられました。— Mi hermano fue obligado por mi madre a comer verduras.",
            "私は部長に長い説明をさせられました。— El jefe me obligó a dar una explicación larga."
        ),
        listOf(
            N3Week2Exercise("¿Qué matiz suele transmitir la causativa-pasiva?", listOf("Acción obligada o no deseada", "Permiso solicitado", "Acción habitual", "Deseo propio"), 0, "La causativa-pasiva suele presentar la acción como impuesta al sujeto."),
            N3Week2Exercise("Forma causativa-pasiva contraída común de 書く", listOf("書かされる", "書かせる", "書けられる", "書いてもらう"), 0, "En godan se usa frecuentemente la forma contraída 書かされる; la forma larga es 書かせられる."),
            N3Week2Exercise("Forma causativa-pasiva de 食べる", listOf("食べさせられる", "食べらせる", "食べさされる", "食べさせる"), 0, "En ichidan se añade させられる tras quitar る.")
        )
    ),
    N3Week2Lesson(
        6, "Causativa frente a causativa-pasiva",
        "Distinguir quién permite o provoca la acción y quién la sufre como obligación.",
        "En la causativa, el foco está en la persona que hace que otra actúe o le da permiso. En la causativa-pasiva, el sujeto es quien termina realizando la acción por imposición. Compará el sujeto y el punto de vista antes de traducir.",
        listOf(
            "先生は学生に漢字を書かせました。— El profesor hizo/dejó que el estudiante escribiera kanji.",
            "学生は先生に漢字を書かされました。— El estudiante fue obligado por el profesor a escribir kanji.",
            "母は子どもを早く寝させました。— La madre hizo/dejó que el niño se acostara temprano."
        ),
        listOf(
            N3Week2Exercise("¿Cuál oración presenta al estudiante como persona obligada a escribir?", listOf("学生は先生に漢字を書かされました。", "先生は学生に漢字を書かせました。", "学生は漢字を書きます。", "先生は漢字を書きました。"), 0, "La causativa-pasiva pone al estudiante como sujeto que recibe la obligación."),
            N3Week2Exercise("En 先生は学生に漢字を書かせました, ¿quién es el sujeto que causa la acción?", listOf("先生", "学生", "漢字", "Ninguno"), 0, "先生 es quien hace o permite que el estudiante escriba."),
            N3Week2Exercise("¿Qué forma corresponde a 'me obligaron a esperar' usando 待つ?", listOf("待たされました", "待たせました", "待ちました", "待てました"), 0, "待たされました es la forma causativa-pasiva contraída común de 待つ.")
        )
    ),
    N3Week2Lesson(
        7, "Aplicación: escuela, trabajo y vida diaria",
        "Comprender y producir causativas y causativas-pasivas en situaciones contextualizadas.",
        "Antes de elegir la forma, identificá tres elementos: quién provoca la acción, quién la realiza y si el contexto comunica permiso o imposición. En la causativa-pasiva, atendé al punto de vista de quien fue obligado. No confundas causativa con potencial ni con pasiva simple.",
        listOf(
            "先生は私に発表させました。— El profesor me hizo realizar una presentación.",
            "私は毎朝、母に部屋を掃除させられます。— Mi madre me obliga a limpiar la habitación todas las mañanas.",
            "店長は新人にレジを練習させました。— El encargado hizo que el empleado nuevo practicara la caja."
        ),
        listOf(
            N3Week2Exercise("¿Qué estructura expresa 'el encargado hizo que el empleado practicara'?", listOf("店長は新人に練習させました。", "店長は新人に練習させられました。", "店長は新人が練習できます。", "店長は新人と練習しました。"), 0, "練習させました expresa que el encargado hizo o permitió que el empleado practicara."),
            N3Week2Exercise("¿Qué conviene identificar primero al analizar una causativa?", listOf("Quién causa la acción y quién la realiza", "Solo el tiempo verbal", "El número de sílabas", "La traducción literal de cada kanji"), 0, "La relación entre causante y ejecutor determina el sentido de la construcción."),
            N3Week2Exercise("¿Cuál es una traducción adecuada de 私は先生に作文を書かされました?", listOf("El profesor me obligó a escribir una composición", "Yo permití que el profesor escribiera", "El profesor escribió mi composición", "Quiero escribir una composición"), 0, "El sujeto 私は es quien sufrió la obligación de escribir.")
        )
    )
)

internal val n3Week2Assessment = listOf(
    N3Week2Exercise("Forma causativa de 読む", listOf("読ませる", "読まれる", "読める", "読んでいる"), 0, "読む cambia a 読ま + せる."),
    N3Week2Exercise("Forma causativa de 話す", listOf("話させる", "話される", "話せる", "話して"), 0, "話す cambia a 話さ + せる."),
    N3Week2Exercise("Forma causativa de 食べる", listOf("食べさせる", "食べられる", "食べたい", "食べて"), 0, "En grupo 2 se elimina る y se añade させる."),
    N3Week2Exercise("Forma causativa de 来る", listOf("来させる（こさせる）", "来られる（こられる）", "来させられる", "来ている"), 0, "来る forma irregularmente 来させる, leído こさせる."),
    N3Week2Exercise("¿Qué puede expresar la causativa?", listOf("Permiso o acción provocada", "Solo obligación sufrida", "Solo capacidad", "Solo pasado"), 0, "El contexto decide entre permiso y hacer que alguien actúe."),
    N3Week2Exercise("¿Qué partícula es frecuente para la persona que realiza una acción transitiva en una causativa con objeto を?", listOf("に", "へ", "から", "まで"), 0, "に ayuda a distinguir a la persona del objeto directo marcado con を."),
    N3Week2Exercise("Forma causativa-pasiva contraída frecuente de 待つ", listOf("待たされる", "待たせる", "待てる", "待ってもらう"), 0, "待つ puede formar 待たされる en la causativa-pasiva contraída."),
    N3Week2Exercise("Forma causativa-pasiva de 見る", listOf("見させられる", "見られる", "見させる", "見える"), 0, "En grupo 2 se elimina る y se añade させられる."),
    N3Week2Exercise("¿Qué expresa 私は先生に漢字を書かされました?", listOf("El profesor me obligó a escribir kanji", "Yo hice que el profesor escribiera kanji", "El profesor puede escribir kanji", "Escribí kanji por voluntad propia"), 0, "La causativa-pasiva presenta al sujeto como obligado a realizar la acción."),
    N3Week2Exercise("¿Cuál es la forma larga causativa-pasiva de 書く?", listOf("書かせられる", "書かされる", "書かせる", "書ける"), 0, "La forma larga se construye como 書かせる + られる; la forma contraída común es 書かされる."),
    N3Week2Exercise("En 先生は学生に本を読ませます, ¿quién lee el libro?", listOf("El estudiante", "El profesor", "El libro", "El hablante"), 0, "学生に identifica a quien realiza la lectura."),
    N3Week2Exercise("¿Qué diferencia principal hay entre causativa y causativa-pasiva?", listOf("La causativa presenta a quien provoca/permite; la causativa-pasiva al sujeto obligado", "Son siempre intercambiables", "La causativa-pasiva solo expresa permiso", "La causativa solo se usa en pasado"), 0, "La perspectiva y el papel del sujeto cambian el significado.")
)

internal fun isN3Week2ContentValid(): Boolean =
    n3Week2Lessons.size == 7 &&
        n3Week2Lessons.map { it.day } == (1..7).toList() &&
        n3Week2Lessons.all { lesson ->
            lesson.title.isNotBlank() &&
                lesson.objective.isNotBlank() &&
                lesson.explanation.isNotBlank() &&
                lesson.examples.size >= 2 &&
                lesson.exercises.size >= 3 &&
                lesson.exercises.all { it.options.size == 4 && it.answerIndex in it.options.indices }
        } &&
        n3Week2Assessment.size == 12 &&
        n3Week2Assessment.all { it.options.size == 4 && it.answerIndex in it.options.indices }
