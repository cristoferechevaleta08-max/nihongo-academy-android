package com.nihongo.academy

/**
 * Bloque 1 del catálogo pedagógico de kanji N2.
 *
 * Selección de estudio propia: el JLPT no publica una lista oficial y definitiva
 * de kanji por nivel. Los caracteres de este bloque se comprobaron contra los
 * catálogos N5 (100), N4 (181) y N3 (370) existentes en esta aplicación para
 * evitar repetir kanji ya enseñados.
 *
 * Lecturas y trazos: referencia KANJIDIC2 / Japanese Language Data.
 * KANJIDIC2 © Electronic Dictionary Research and Development Group (EDRDG).
 * https://github.com/jkindrix/japanese-language-data
 */
internal data class N2KanjiEntry(
    val character: String,
    val meaningEs: String,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val strokeCount: Int
)

internal fun displayN2KunYomi(reading: String): String =
    reading.replace(".", "").replace("-", "")

/** Primer bloque: 30 caracteres nuevos, sin solapamiento con N5/N4/N3. */
internal val n2KanjiBlock1: List<N2KanjiEntry> = listOf(
    N2KanjiEntry("曖", "ambiguo; poco claro", listOf("アイ"), listOf(), 17),
    N2KanjiEntry("握", "agarrar; sujetar; empuñar", listOf("アク"), listOf("にぎ.る"), 12),
    N2KanjiEntry("扱", "manejar; tratar; ocuparse de", listOf("ソウ"), listOf("あつか.う"), 6),
    N2KanjiEntry("依", "depender de; confiar en", listOf("イ", "エ"), listOf(), 8),
    N2KanjiEntry("威", "autoridad; intimidar; imponerse", listOf("イ"), listOf("おど.す"), 9),
    N2KanjiEntry("為", "hacer; realizar; beneficio", listOf("イ"), listOf("ため", "な.る", "な.す"), 9),
    N2KanjiEntry("維", "mantener; conservar; fibra", listOf("イ"), listOf(), 14),
    N2KanjiEntry("緯", "latitud; trama; dirección horizontal", listOf("イ"), listOf("よこいと"), 16),
    N2KanjiEntry("遺", "dejar atrás; legado; heredar", listOf("イ", "ユイ"), listOf(), 15),
    N2KanjiEntry("域", "región; territorio; zona", listOf("イキ"), listOf(), 11),
    N2KanjiEntry("宇", "espacio; universo; alero", listOf("ウ"), listOf(), 6),
    N2KanjiEntry("浦", "bahía; ensenada; costa", listOf("ホ"), listOf("うら"), 10),
    N2KanjiEntry("影", "sombra; silueta; reflejo", listOf("エイ"), listOf("かげ"), 15),
    N2KanjiEntry("鋭", "afilado; agudo; perspicaz", listOf("エイ"), listOf("するど.い"), 15),
    N2KanjiEntry("疫", "epidemia; enfermedad contagiosa", listOf("エキ", "ヤク"), listOf(), 9),
    N2KanjiEntry("閲", "examinar; revisar; inspeccionar", listOf("エツ"), listOf(), 15),
    N2KanjiEntry("延", "prolongar; extender; aplazar", listOf("エン"), listOf("の.びる", "の.べる", "の.ばす"), 8),
    N2KanjiEntry("沿", "bordear; seguir; a lo largo de", listOf("エン"), listOf("そ.う"), 8),
    N2KanjiEntry("縁", "vínculo; relación; borde; destino", listOf("エン"), listOf("ふち", "ゆかり", "よすが"), 15),
    N2KanjiEntry("汚", "sucio; ensuciar; contaminar", listOf("オ"), listOf("けが.す", "けが.れる", "よご.す", "よご.れる", "きたな.い"), 6),
    N2KanjiEntry("奥", "interior; fondo; profundo", listOf("オウ"), listOf("おく"), 12),
    N2KanjiEntry("憶", "recordar; memoria; recuerdo", listOf("オク"), listOf(), 16),
    N2KanjiEntry("乙", "segundo; extraño; elegante", listOf("オツ", "イツ"), listOf("おと", "きのと"), 1),
    N2KanjiEntry("卸", "venta al por mayor; descargar", listOf("シャ"), listOf("おろ.す", "おろし"), 9),
    N2KanjiEntry("穏", "tranquilo; apacible; sereno", listOf("オン"), listOf("おだ.やか"), 16),
    N2KanjiEntry("佳", "excelente; hermoso; bueno", listOf("カ"), listOf(), 8),
    N2KanjiEntry("架", "construir; tender; estantería", listOf("カ"), listOf("か.ける", "か.かる"), 9),
    N2KanjiEntry("菓", "dulce; golosina; confitería", listOf("カ"), listOf(), 11),
    N2KanjiEntry("暇", "tiempo libre; ocio; descanso", listOf("カ"), listOf("ひま"), 13),
    N2KanjiEntry("禍", "desgracia; calamidad; desastre", listOf("カ"), listOf("わざわい"), 13)
)

internal val n2KanjiCharacters: List<String> =
    n2KanjiBlock1.map { it.character }

internal val n2KanjiBlocks: List<List<String>> =
    listOf(n2KanjiCharacters)
