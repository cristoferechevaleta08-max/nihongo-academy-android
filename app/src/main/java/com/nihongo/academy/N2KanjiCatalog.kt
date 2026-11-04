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
 * Datos de lectura/trazos contrastados también con OpenJLPT (evanclan/OpenJLPT),
 * data/json/kanji/n2.json. Ese conjunto se distribuye bajo CC BY-SA 4.0:
 * https://github.com/evanclan/OpenJLPT
 * Las traducciones al español son adaptaciones editoriales propias.
 */
internal data class N2KanjiWord(
    val word: String,
    val reading: String,
    val meaningEs: String
)

internal data class N2KanjiEntry(
    val character: String,
    val meaningEs: String,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val strokeCount: Int,
    val vocabulary: List<N2KanjiWord> = emptyList(),
    val exampleJa: String = "",
    val exampleReading: String = "",
    val exampleEs: String = ""
)

internal fun displayN2KunYomi(reading: String): String =
    reading.replace(".", "").replace("-", "")

/** Primer bloque: 30 caracteres nuevos, sin solapamiento con N5/N4/N3. */
internal val n2KanjiCatalog: List<N2KanjiEntry> = listOf(
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
,

    N2KanjiEntry("壊", "romper; destruir; deteriorarse", listOf("カイ"), listOf("こわ.す", "こわ.れる"), 16),
    N2KanjiEntry("懐", "nostalgia; bolsillo; añorar", listOf("カイ"), listOf("ふところ", "なつ.かしい", "なつ.く"), 16),
    N2KanjiEntry("慨", "lamentar; indignación; pesar", listOf("ガイ"), listOf(), 13),
    N2KanjiEntry("該", "correspondiente; dicho; mencionado", listOf("ガイ"), listOf(), 13),
    N2KanjiEntry("概", "general; aproximadamente; resumen", listOf("ガイ"), listOf(), 14),
    N2KanjiEntry("垣", "cerca; valla; seto", listOf("エン"), listOf("かき"), 9),
    N2KanjiEntry("核", "núcleo; central; nuclear", listOf("カク"), listOf(), 10),
    N2KanjiEntry("殻", "cáscara; caparazón; vaina", listOf("カク"), listOf("から"), 11),
    N2KanjiEntry("獲", "capturar; obtener; adquirir", listOf("カク"), listOf("え.る"), 16),
    N2KanjiEntry("嚇", "amenazar; intimidar", listOf("カク"), listOf(), 17),
    N2KanjiEntry("較", "comparar; contraste", listOf("カク"), listOf(), 13),
    N2KanjiEntry("隔", "separar; intervalo; distancia", listOf("カク"), listOf("へだ.てる", "へだ.たる"), 13),
    N2KanjiEntry("岳", "montaña alta; pico", listOf("ガク"), listOf("たけ"), 8),
    N2KanjiEntry("潟", "laguna; ensenada", listOf("セキ"), listOf("かた"), 15),
    N2KanjiEntry("括", "agrupar; resumir; abarcar", listOf("カツ"), listOf("くく.る"), 9),
    N2KanjiEntry("喝", "gritar; reprender", listOf("カツ"), listOf(), 11),
    N2KanjiEntry("渇", "tener sed; secarse", listOf("カツ"), listOf("かわ.く"), 11),
    N2KanjiEntry("滑", "resbalar; deslizarse; fluido", listOf("カツ", "コツ"), listOf("すべ.る", "なめ.らか"), 13),
    N2KanjiEntry("褐", "marrón; pardo", listOf("カツ"), listOf(), 13),
    N2KanjiEntry("轄", "control; jurisdicción; supervisar", listOf("カツ"), listOf(), 17),
    N2KanjiEntry("且", "además; y también; por ahora", listOf("ショ"), listOf("か.つ"), 5),
    N2KanjiEntry("釜", "olla; caldero", listOf("フ"), listOf("かま"), 10),
    N2KanjiEntry("鎌", "hoz; guadaña", listOf("レン"), listOf("かま"), 18),
    N2KanjiEntry("刈", "segar; cortar hierba", listOf("ガイ"), listOf("か.る"), 4),
    N2KanjiEntry("甘", "dulce; indulgente; ingenuo", listOf("カン"), listOf("あま.い", "あま.える", "あま.やかす"), 5),
    N2KanjiEntry("監", "supervisar; vigilar; inspeccionar", listOf("カン"), listOf(), 15),
    N2KanjiEntry("冠", "corona; coronar; primero", listOf("カン"), listOf("かんむり"), 9),
    N2KanjiEntry("勘", "intuición; percepción; presentimiento", listOf("カン"), listOf(), 11),
    N2KanjiEntry("堪", "soportar; resistir; aguantar", listOf("カン", "タン"), listOf("た.える", "こら.える", "こた.える"), 12),
    N2KanjiEntry("喚", "llamar; convocar; gritar", listOf("カン"), listOf("わめ.く"), 12)
,
// Continuación del catálogo único: selección contrastada con la fuente N2 y sin
    // caracteres repetidos en N5, N4, N3 ni en las fichas anteriores.
    N2KanjiEntry("党", "partido político; facción", listOf("トウ"), listOf("なかま", "むら"), 10),
    N2KanjiEntry("協", "cooperación; colaborar", listOf("キョウ"), listOf(), 8),
    N2KanjiEntry("総", "total; conjunto; general", listOf("ソウ"), listOf("す.べて", "すべ.て", "ふさ"), 14),
    N2KanjiEntry("領", "territorio; jurisdicción; dominio", listOf("リョウ"), listOf("えり"), 14),
    N2KanjiEntry("設", "establecer; instalación; provisión", listOf("セツ"), listOf("もう.ける"), 11),
    N2KanjiEntry("改", "reformar; cambiar; modificar", listOf("カイ"), listOf("あらた.める", "あらた.まる"), 7),
    N2KanjiEntry("府", "organismo gubernamental; prefectura urbana", listOf("フ"), listOf(), 8),
    N2KanjiEntry("委", "comité; confiar; delegar", listOf("イ"), listOf("ゆだ.ねる"), 8),
    N2KanjiEntry("軍", "ejército; tropas; fuerza militar", listOf("グン"), listOf("いくさ"), 9),
    N2KanjiEntry("団", "grupo; organización; asociación", listOf("ダン", "トン"), listOf("かたまり", "まる.い"), 6),
    N2KanjiEntry("各", "cada uno; cada; respectivo", listOf("カク"), listOf("おのおの"), 6),
    N2KanjiEntry("島", "isla", listOf("トウ"), listOf("しま"), 10),
    N2KanjiEntry("革", "cuero; piel; reforma", listOf("カク"), listOf("かわ"), 9),
    N2KanjiEntry("勢", "fuerza; energía; ímpetu", listOf("セイ", "ゼイ"), listOf("いきお.い", "はずみ"), 13),
    N2KanjiEntry("再", "otra vez; de nuevo; repetir", listOf("サイ", "サ"), listOf("ふたた.び"), 6),
    N2KanjiEntry("税", "impuesto; tributación", listOf("ゼイ"), listOf(), 12),
    N2KanjiEntry("比", "comparar; proporción", listOf("ヒ"), listOf("くら.べる"), 4),
    N2KanjiEntry("防", "proteger; prevenir; defender", listOf("ボウ"), listOf("ふせ.ぐ"), 7),
    N2KanjiEntry("補", "complementar; suplir; reponer", listOf("ホ"), listOf("おぎな.う"), 12),
    N2KanjiEntry("導", "guiar; conducir; orientación", listOf("ドウ"), listOf("みちび.く"), 15),
    N2KanjiEntry("副", "vice-; secundario; adjunto", listOf("フク"), listOf(), 11),
    N2KanjiEntry("算", "calcular; cálculo", listOf("サン"), listOf("そろ"), 14),
    N2KanjiEntry("輸", "transportar; transporte", listOf("ユ", "シュ"), listOf(), 16),
    N2KanjiEntry("述", "exponer; mencionar; describir", listOf("ジュツ"), listOf("の.べる"), 8),
    N2KanjiEntry("線", "línea; cable; trayectoria", listOf("セン"), listOf("すじ"), 15),
    N2KanjiEntry("農", "agricultura; agrícola", listOf("ノウ"), listOf(), 13),
    N2KanjiEntry("州", "estado; provincia; región", listOf("シュウ", "ス"), listOf("す"), 6),
    N2KanjiEntry("武", "militar; marcial; guerrero", listOf("ブ", "ム"), listOf("たけ", "たけ.し"), 8),
    N2KanjiEntry("象", "elefante; imagen; fenómeno", listOf("ショウ", "ゾウ"), listOf("かたど.る"), 12),
    N2KanjiEntry("欧", "Europa; europeo", listOf("オウ"), listOf("うた.う", "は.く"), 8)

)

internal val n2KanjiCharacters: List<String> = n2KanjiCatalog.map { it.character }
