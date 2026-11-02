package com.nihongo.academy

/** Primer bloque N5 del PDF: semanas 1–2, lectura/escritura de kana y repaso diario. */
internal data class KanaEntry(val kana: String, val romaji: String)
internal data class KanaDay(val day: Int, val title: String, val focus: String, val practice: List<String>)
internal data class KanaQuizQuestion(val prompt: String, val options: List<String>, val answerIndex: Int)

/** Devuelve índices mezclados sin perder la referencia al índice de respuesta original. */
internal fun shuffledQuizOptionIndices(optionCount: Int, random: kotlin.random.Random = kotlin.random.Random.Default): List<Int> =
    (0 until optionCount).shuffled(random)

internal val hiraganaBasic = listOf(
 KanaEntry("あ","a"),KanaEntry("い","i"),KanaEntry("う","u"),KanaEntry("え","e"),KanaEntry("お","o"),
 KanaEntry("か","ka"),KanaEntry("き","ki"),KanaEntry("く","ku"),KanaEntry("け","ke"),KanaEntry("こ","ko"),
 KanaEntry("さ","sa"),KanaEntry("し","shi"),KanaEntry("す","su"),KanaEntry("せ","se"),KanaEntry("そ","so"),
 KanaEntry("た","ta"),KanaEntry("ち","chi"),KanaEntry("つ","tsu"),KanaEntry("て","te"),KanaEntry("と","to"),
 KanaEntry("な","na"),KanaEntry("に","ni"),KanaEntry("ぬ","nu"),KanaEntry("ね","ne"),KanaEntry("の","no"),
 KanaEntry("は","ha"),KanaEntry("ひ","hi"),KanaEntry("ふ","fu"),KanaEntry("へ","he"),KanaEntry("ほ","ho"),
 KanaEntry("ま","ma"),KanaEntry("み","mi"),KanaEntry("む","mu"),KanaEntry("め","me"),KanaEntry("も","mo"),
 KanaEntry("や","ya"),KanaEntry("ゆ","yu"),KanaEntry("よ","yo"),
 KanaEntry("ら","ra"),KanaEntry("り","ri"),KanaEntry("る","ru"),KanaEntry("れ","re"),KanaEntry("ろ","ro"),
 KanaEntry("わ","wa"),KanaEntry("を","wo"),KanaEntry("ん","n")
)
internal val katakanaBasic = listOf(
 KanaEntry("ア","a"),KanaEntry("イ","i"),KanaEntry("ウ","u"),KanaEntry("エ","e"),KanaEntry("オ","o"),
 KanaEntry("カ","ka"),KanaEntry("キ","ki"),KanaEntry("ク","ku"),KanaEntry("ケ","ke"),KanaEntry("コ","ko"),
 KanaEntry("サ","sa"),KanaEntry("シ","shi"),KanaEntry("ス","su"),KanaEntry("セ","se"),KanaEntry("ソ","so"),
 KanaEntry("タ","ta"),KanaEntry("チ","chi"),KanaEntry("ツ","tsu"),KanaEntry("テ","te"),KanaEntry("ト","to"),
 KanaEntry("ナ","na"),KanaEntry("ニ","ni"),KanaEntry("ヌ","nu"),KanaEntry("ネ","ne"),KanaEntry("ノ","no"),
 KanaEntry("ハ","ha"),KanaEntry("ヒ","hi"),KanaEntry("フ","fu"),KanaEntry("ヘ","he"),KanaEntry("ホ","ho"),
 KanaEntry("マ","ma"),KanaEntry("ミ","mi"),KanaEntry("ム","mu"),KanaEntry("メ","me"),KanaEntry("モ","mo"),
 KanaEntry("ヤ","ya"),KanaEntry("ユ","yu"),KanaEntry("ヨ","yo"),
 KanaEntry("ラ","ra"),KanaEntry("リ","ri"),KanaEntry("ル","ru"),KanaEntry("レ","re"),KanaEntry("ロ","ro"),
 KanaEntry("ワ","wa"),KanaEntry("ヲ","wo"),KanaEntry("ン","n")
)
internal val n5KanaWeeks = listOf(
 KanaDay(1,"Vocales y primeras filas","Hiragana: あいうえお, か行 y さ行.",listOf("Leer cada carácter en voz alta.","Copiar los caracteres siguiendo un modelo de trazos.","Tapar el romaji y recordar el sonido.","Repasar los caracteres del día anterior.")),
 KanaDay(2,"Filas T y N","Hiragana: た行 y な行; atención a ち (chi) y つ (tsu).",listOf("Leer ambas filas sin ayuda.","Escribir cada carácter y luego reproducirlo de memoria.","Distinguir ち/さ y つ/し.","Repaso acumulativo de días 1 y 2.")),
 KanaDay(3,"Filas H y M","Hiragana: は行 y ま行; atención a ふ (fu).",listOf("Leer はひふへほ y まみむめも.","Practicar la forma de cada carácter.","Escribir los sonidos de memoria.","Repaso acumulativo de días 1–3.")),
 KanaDay(4,"Y, R y caracteres finales","Hiragana: や行, ら行, わ, を y ん.",listOf("Leer やゆよ y らりるれろ.","Reconocer わ, を y ん.","Copiar la tabla completa.","Repasar las filas más difíciles.")),
 KanaDay(5,"Lectura de hiragana","Reconocimiento mezclado de los 46 hiragana básicos.",listOf("Leer caracteres en orden aleatorio.","Escribir el kana correspondiente a un romaji.","Practicar lectura de secuencias cortas.","Anotar errores para repetirlos.")),
 KanaDay(6,"Escritura y repaso","Recuperación activa de toda la tabla de hiragana.",listOf("Escribir de memoria las cinco vocales.","Escribir todas las filas estudiadas.","Leer toda la tabla sin romaji.","Rehacer los caracteres fallados.")),
 KanaDay(7,"Evaluación semanal","Prueba de lectura y reconocimiento de hiragana.",listOf("Completar la prueba semanal.","Revisar cada respuesta incorrecta.","Si los errores superan el 30%, repetir la semana con práctica adicional.")),
 KanaDay(8,"Vocales y primeras filas","Katakana: アイウエオ, カ行 y サ行.",listOf("Comparar cada katakana con su hiragana equivalente.","Leer las filas sin romaji.","Copiar los caracteres siguiendo un modelo.","Repasar hiragana unos minutos.")),
 KanaDay(9,"Filas T y N","Katakana: タ行 y ナ行; atención a チ (chi) y ツ (tsu).",listOf("Leer タチツテト y ナニヌネノ.","Distinguir シ/ツ y ソ/ン.","Escribir cada carácter de memoria.","Repaso acumulativo de katakana.")),
 KanaDay(10,"Filas H y M","Katakana: ハ行 y マ行; atención a フ (fu).",listOf("Leer ハヒフヘホ y マミムメモ.","Practicar escritura de cada carácter.","Relacionar sonido y símbolo.","Repasar las filas anteriores.")),
 KanaDay(11,"Y, R y caracteres finales","Katakana: ヤ行, ラ行, ワ, ヲ y ン.",listOf("Leer ヤユヨ y ラリルレロ.","Reconocer ワ, ヲ y ン.","Copiar la tabla completa.","Comparar hiragana y katakana equivalentes.")),
 KanaDay(12,"Lectura de katakana","Reconocimiento mezclado de los 46 katakana básicos.",listOf("Leer caracteres en orden aleatorio.","Relacionar cada katakana con su sonido.","Practicar lectura de secuencias cortas.","Guardar errores para el repaso.")),
 KanaDay(13,"Escritura y repaso mixto","Recuperación activa de hiragana y katakana.",listOf("Escribir de memoria ambas tablas.","Identificar a qué silabario pertenece cada carácter.","Leer sin romaji.","Repetir los pares que se confundan.")),
 KanaDay(14,"Evaluación semanal","Prueba de lectura de katakana y repaso general.",listOf("Completar la prueba semanal.","Revisar respuestas incorrectas.","Si los errores superan el 30%, repetir la semana con práctica adicional."))
)
private fun kanaQuiz(entries: List<KanaEntry>, offset: Int): List<KanaQuizQuestion> =
 (0 until 10).map { index ->
  val answer = entries[(index * 4 + offset) % entries.size]
  val distractors = (1..3).map { step -> entries[(index * 4 + offset + step * 7) % entries.size].romaji }
  val options = (distractors + answer.romaji).distinct().take(4).sorted()
  KanaQuizQuestion("¿Qué sonido representa ${answer.kana}?", options, options.indexOf(answer.romaji))
 }
internal val hiraganaWeekAssessment = kanaQuiz(hiraganaBasic,0)
internal val katakanaWeekAssessment = kanaQuiz(katakanaBasic,3)
internal fun kanaForWeek(week:Int):List<KanaEntry> = when(week){1->hiraganaBasic;2->katakanaBasic;else->emptyList()}
internal fun kanaAssessmentForWeek(week:Int):List<KanaQuizQuestion> = when(week){1->hiraganaWeekAssessment;2->katakanaWeekAssessment;else->emptyList()}
