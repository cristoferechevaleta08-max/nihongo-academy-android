package com.nihongo.academy

/**
 * Datos base del bloque 1 del catálogo pedagógico N3 (123 kanji).
 *
 * Lecturas, significados en español y número de trazos adaptados de KANJIDIC2.
 * Fuente: Japanese Language Data, https://github.com/jkindrix/japanese-language-data
 * Datos KANJIDIC2 © Electronic Dictionary Research and Development Group (EDRDG).
 * Licencia de los datos: CC BY-SA 4.0. Este archivo conserva la atribución.
 *
 * El nivel N3 es una selección pedagógica propia; no constituye una lista oficial JLPT.
 */
internal data class N3KanjiEntry(
    val character: String,
    val meaningEs: String,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val strokeCount: Int
)

internal val n3KanjiBlock1: List<N3KanjiEntry> = listOf(
    N3KanjiEntry(
        character = "政",
        meaningEs = "política; gobierno; reglas",
        onyomi = listOf("セイ", "ショウ"),
        kunyomi = listOf("まつりごと", "まん"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "議",
        meaningEs = "opinión; consulta; consideración",
        onyomi = listOf("ギ"),
        kunyomi = listOf(),
        strokeCount = 20
    ),
    N3KanjiEntry(
        character = "連",
        meaningEs = "unir; acompañar; estar junto; grupo; agrupar",
        onyomi = listOf("レン"),
        kunyomi = listOf("つら.なる", "つら.ねる", "つ.れる", "-づ.れ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "対",
        meaningEs = "opuesto; contrario; por serie",
        onyomi = listOf("タイ", "ツイ"),
        kunyomi = listOf("あいて", "こた.える", "そろ.い", "つれあ.い", "なら.ぶ", "むか.う"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "部",
        meaningEs = "sección; departamento; parte; copia",
        onyomi = listOf("ブ"),
        kunyomi = listOf("-べ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "内",
        meaningEs = "interior; dentro de; privado",
        onyomi = listOf("ナイ", "ダイ"),
        kunyomi = listOf("うち"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "相",
        meaningEs = "apariencia; aspecto; fase; ministro de estado; conjuntamente; mutuamente",
        onyomi = listOf("ソウ", "ショウ"),
        kunyomi = listOf("あい-"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "定",
        meaningEs = "determinar; establecer; decidir",
        onyomi = listOf("テイ", "ジョウ"),
        kunyomi = listOf("さだ.める", "さだ.まる", "さだ.か"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "選",
        meaningEs = "elegir; seleccionar",
        onyomi = listOf("セン"),
        kunyomi = listOf("えら.ぶ", "え.る", "よ.る"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "米",
        meaningEs = "arroz; América; arroz (crudo)",
        onyomi = listOf("ベイ", "マイ", "メエトル"),
        kunyomi = listOf("こめ", "よね"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "実",
        meaningEs = "realidad; verdad; fruta; nuez; semilla; contenido; buen resultado; dar frutos; madurar",
        onyomi = listOf("ジツ", "シツ"),
        kunyomi = listOf("み", "みの.る", "まこと", "みの", "みち.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "関",
        meaningEs = "barrera; puerta; relación; mecanismo; valla",
        onyomi = listOf("カン"),
        kunyomi = listOf("せき", "-ぜき", "かか.わる", "からくり", "かんぬき"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "決",
        meaningEs = "decidir; fijar; quedar decidido",
        onyomi = listOf("ケツ"),
        kunyomi = listOf("き.める", "-ぎ.め", "き.まる", "さ.く"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "全",
        meaningEs = "completo; total; enteramente; completamente",
        onyomi = listOf("ゼン"),
        kunyomi = listOf("まった.く", "すべ.て"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "表",
        meaningEs = "superficie; tabla; diagrama; expresar; mostrar",
        onyomi = listOf("ヒョウ"),
        kunyomi = listOf("おもて", "-おもて", "あらわ.す", "あらわ.れる", "あら.わす"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "戦",
        meaningEs = "guerra; batalla; lucha; pelear; batallar; combatir; luchar",
        onyomi = listOf("セン"),
        kunyomi = listOf("いくさ", "たたか.う", "おのの.く", "そよ.ぐ", "わなな.く"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "経",
        meaningEs = "sutra; longitud; ley; pasar; dirigir; transcurrir; experimentar",
        onyomi = listOf("ケイ", "キョウ", "キン"),
        kunyomi = listOf("へ.る", "た.つ", "たていと", "はか.る", "のり"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "最",
        meaningEs = "prefijo para superlativo; más; sumamente; extremadamente",
        onyomi = listOf("サイ", "シュ"),
        kunyomi = listOf("もっと.も", "つま"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "現",
        meaningEs = "presente; actual; real; aparecer; hacerse visible; mostrarse; manifestar; expresar",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("あらわ.れる", "あらわ.す", "うつつ", "うつ.つ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "調",
        meaningEs = "melodía; tono; investigar; comprobar; arreglar; poner en orden",
        onyomi = listOf("チョウ"),
        kunyomi = listOf("しら.べる", "しら.べ", "ととの.う", "ととの.える"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "化",
        meaningEs = "cambiar; transformarse; modificarse; disfrazarse",
        onyomi = listOf("カ", "ケ"),
        kunyomi = listOf("ば.ける", "ば.かす", "ふ.ける", "け.する"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "当",
        meaningEs = "acertar; realizarse; tocar; ganar; adivinar",
        onyomi = listOf("トウ"),
        kunyomi = listOf("あ.たる", "あ.たり", "あ.てる", "あ.て", "まさ.に", "まさ.にべし"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "約",
        meaningEs = "promesa; aproximadamente; encogerse; economizar",
        onyomi = listOf("ヤク"),
        kunyomi = listOf("つづ.まる", "つづ.める", "つづま.やか"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "法",
        meaningEs = "método; ley; doctrina",
        onyomi = listOf("ホウ", "ハッ", "ホッ", "フラン"),
        kunyomi = listOf("のり"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "性",
        meaningEs = "sexo; género; naturaleza; temperamento",
        onyomi = listOf("セイ", "ショウ"),
        kunyomi = listOf("さが"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "的",
        meaningEs = "objetivo; propósito; sufijo para formar adjetivos",
        onyomi = listOf("テキ"),
        kunyomi = listOf("まと"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "要",
        meaningEs = "necesidad; punto central; esencia; necesitar",
        onyomi = listOf("ヨウ"),
        kunyomi = listOf("い.る", "かなめ"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "制",
        meaningEs = "determinar; establecer; comprobar; cesar; poner fin; sistema; construir",
        onyomi = listOf("セイ"),
        kunyomi = listOf(),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "治",
        meaningEs = "reinar; gobernar; política; curar; calmar; curarse",
        onyomi = listOf("ジ", "チ"),
        kunyomi = listOf("おさ.める", "おさ.まる", "なお.る", "なお.す"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "務",
        meaningEs = "tarea; obligaciones; deber; cargo; servir; desempeñar un cargo",
        onyomi = listOf("ム"),
        kunyomi = listOf("つと.める"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "成",
        meaningEs = "convertirse en; resultar; consistir en; hacerse; llegar a ser; llevar a cabo; lograr",
        onyomi = listOf("セイ", "ジョウ"),
        kunyomi = listOf("な.る", "な.す", "-な.す"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "期",
        meaningEs = "período; plazo; intención; propósito",
        onyomi = listOf("キ", "ゴ"),
        kunyomi = listOf(),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "取",
        meaningEs = "tomar; coger",
        onyomi = listOf("シュ"),
        kunyomi = listOf("と.る", "と.り", "と.り-", "とり", "-ど.り"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "和",
        meaningEs = "armonía; paz; estilo japonés; suavizar; calmar; tranquilo; armonioso",
        onyomi = listOf("ワ", "オ", "カ"),
        kunyomi = listOf("やわ.らぐ", "やわ.らげる", "なご.む", "なご.やか", "あ.える"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "機",
        meaningEs = "mecanismo; máquina; avión; ocasión; telar",
        onyomi = listOf("キ"),
        kunyomi = listOf("はた"),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "平",
        meaningEs = "llanura; liso; llano; plano; sencillo",
        onyomi = listOf("ヘイ", "ビョウ", "ヒョウ"),
        kunyomi = listOf("たい.ら", "たい.らげる", "ひら"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "加",
        meaningEs = "agregar; añadir; incrementar; Canadá; sumar; unirse",
        onyomi = listOf("カ"),
        kunyomi = listOf("くわ.える", "くわ.わる"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "受",
        meaningEs = "recipiente; acepción; aceptar; recibir; tomar; aprobar examen",
        onyomi = listOf("ジュ"),
        kunyomi = listOf("う.ける", "-う.け", "う.かる"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "続",
        meaningEs = "continuación; serie; secuela; continuar; seguir",
        onyomi = listOf("ゾク", "ショク", "コウ", "キョウ"),
        kunyomi = listOf("つづ.く", "つづ.ける", "つぐ.ない"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "数",
        meaningEs = "número; cifra; contar",
        onyomi = listOf("スウ", "ス", "サク", "ソク", "シュ"),
        kunyomi = listOf("かず", "かぞ.える", "しばしば", "せ.める", "わずらわ.しい"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "記",
        meaningEs = "crónica; narración; memorizar; anotar; consignar por escrito",
        onyomi = listOf("キ"),
        kunyomi = listOf("しる.す"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "初",
        meaningEs = "comienzo; primero",
        onyomi = listOf("ショ"),
        kunyomi = listOf("はじ.め", "はじ.めて", "はつ", "はつ-", "うい-", "-そ.める", "-ぞ.め"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "指",
        meaningEs = "dedo; apuntar; señalar",
        onyomi = listOf("シ"),
        kunyomi = listOf("ゆび", "さ.す", "-さ.し"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "権",
        meaningEs = "derechos; autoridad; poder; establecer temporalmente",
        onyomi = listOf("ケン", "ゴン"),
        kunyomi = listOf("おもり", "かり", "はか.る"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "支",
        meaningEs = "soporte; apoyo; rama; pago; interferencia; soportar; sostener",
        onyomi = listOf("シ"),
        kunyomi = listOf("ささ.える", "つか.える", "か.う"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "点",
        meaningEs = "punto; marca",
        onyomi = listOf("テン"),
        kunyomi = listOf("つ.ける", "つ.く", "た.てる", "さ.す", "とぼ.す", "とも.す", "ぼち"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "報",
        meaningEs = "informar; recompensar; responder; compensar; reembolsar",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("むく.いる"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "済",
        meaningEs = "ayudar; rescatar; terminar; finalizar; acabar; concluir; reembolsar; devolver",
        onyomi = listOf("サイ", "セイ"),
        kunyomi = listOf("す.む", "-ず.み", "-ずみ", "す.まない", "す.ます", "-す.ます", "すく.う", "な.す", "わたし", "わた.る"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "活",
        meaningEs = "vida; energía; actividad",
        onyomi = listOf("カツ"),
        kunyomi = listOf("い.きる", "い.かす", "い.ける"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "原",
        meaningEs = "origen; pradera; prado; campo",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("はら"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "共",
        meaningEs = "ambos; conjuntamente; juntos; también",
        onyomi = listOf("キョウ"),
        kunyomi = listOf("とも", "とも.に", "-ども"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "得",
        meaningEs = "ventaja; beneficio; provecho; conseguir; obtener",
        onyomi = listOf("トク"),
        kunyomi = listOf("え.る", "う.る"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "解",
        meaningEs = "desenredar; desatar; liberar; aclarar; resolver; peinarse; aflojar",
        onyomi = listOf("カイ", "ゲ"),
        kunyomi = listOf("と.く", "と.かす", "と.ける", "ほど.く", "ほぐ.す", "わか.る", "さと.る"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "交",
        meaningEs = "cambio; reemplazo; mezcla; asociación; cruzarse; mezclarse; cruzar; mezclar",
        onyomi = listOf("コウ"),
        kunyomi = listOf("まじ.わる", "まじ.える", "ま.じる", "まじ.る", "ま.ざる", "ま.ぜる", "-か.う", "か.わす", "かわ.す", "こもごも"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "資",
        meaningEs = "recurso; capital; naturaleza; posición social",
        onyomi = listOf("シ"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "予",
        meaningEs = "de antemano; previo",
        onyomi = listOf("ヨ", "シャ"),
        kunyomi = listOf("あらかじ.め"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "向",
        meaningEs = "mirar hacia; volverse; hacer frente; oponerse",
        onyomi = listOf("コウ"),
        kunyomi = listOf("む.く", "む.い", "-む.き", "む.ける", "-む.け", "む.かう", "む.かい", "む.こう", "む.こう-", "むこ", "むか.い"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "際",
        meaningEs = "límite; frontera; asociación; unión; ocasión",
        onyomi = listOf("サイ"),
        kunyomi = listOf("きわ", "-ぎわ"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "勝",
        meaningEs = "ganar; sobresalir",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("か.つ", "-が.ち", "まさ.る", "すぐ.れる", "かつ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "面",
        meaningEs = "cara; semblante; superficie",
        onyomi = listOf("メン", "ベン"),
        kunyomi = listOf("おも", "おもて", "つら"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "告",
        meaningEs = "revelación; contar; informar; anunciar",
        onyomi = listOf("コク"),
        kunyomi = listOf("つ.げる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "反",
        meaningEs = "anti-; opuesto; devolver; reflejar; curvarse",
        onyomi = listOf("ハン", "ホン", "タン", "ホ"),
        kunyomi = listOf("そ.る", "そ.らす", "かえ.す", "かえ.る", "-かえ.る"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "判",
        meaningEs = "diferenciar; distinguir; juzgar; sello; marca; señal; sufijo para tamaño (papel)",
        onyomi = listOf("ハン", "バン"),
        kunyomi = listOf("わか.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "認",
        meaningEs = "reconocer; admitir; consentir",
        onyomi = listOf("ニン"),
        kunyomi = listOf("みと.める", "したた.める"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "参",
        meaningEs = "visita; asistencia; consulta; derrota; ir; venir; visitar [humilde]; asistir; ser derrotado",
        onyomi = listOf("サン", "シン"),
        kunyomi = listOf("まい.る", "まい-", "まじわる", "みつ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "利",
        meaningEs = "ganancia; ventaja; beneficio; ser efectivo",
        onyomi = listOf("リ"),
        kunyomi = listOf("き.く"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "組",
        meaningEs = "grupo; equipo; juntar; unir; agrupar",
        onyomi = listOf("ソ"),
        kunyomi = listOf("く.む", "くみ", "-ぐみ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "信",
        meaningEs = "confianza; sinceridad; información; verdad",
        onyomi = listOf("シン"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "在",
        meaningEs = "existir; pueblo natal; alrededores; ser",
        onyomi = listOf("ザイ"),
        kunyomi = listOf("あ.る"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "件",
        meaningEs = "asunto; caso",
        onyomi = listOf("ケン"),
        kunyomi = listOf("くだん"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "側",
        meaningEs = "lado; fila",
        onyomi = listOf("ソク"),
        kunyomi = listOf("かわ", "がわ", "そば"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "任",
        meaningEs = "responsabilidad; deber; confiar a; dejar a cargo de",
        onyomi = listOf("ニン"),
        kunyomi = listOf("まか.せる", "まか.す"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "求",
        meaningEs = "petición; demanda; buscar; pedir; demandar; reclamar; desear",
        onyomi = listOf("キュウ", "グ"),
        kunyomi = listOf("もと.める"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "次",
        meaningEs = "siguiente; seguir",
        onyomi = listOf("ジ", "シ"),
        kunyomi = listOf("つ.ぐ", "つぎ"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "昨",
        meaningEs = "ayer; previo; anterior",
        onyomi = listOf("サク"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "論",
        meaningEs = "teoría; argumento; discurso",
        onyomi = listOf("ロン"),
        kunyomi = listOf("あげつら.う"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "官",
        meaningEs = "gobierno; gubernamental (lugar; persona); órgano del cuerpo",
        onyomi = listOf("カン"),
        kunyomi = listOf(),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "増",
        meaningEs = "incremento; aumento; aumentar; incrementar; agregar; añadir",
        onyomi = listOf("ゾウ"),
        kunyomi = listOf("ま.す", "ま.し", "ふ.える", "ふ.やす"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "係",
        meaningEs = "cargo; deber; concernir; afectar",
        onyomi = listOf("ケイ"),
        kunyomi = listOf("かか.る", "かかり", "-がかり", "かか.わる"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "感",
        meaningEs = "sentimiento; sensación; sentir",
        onyomi = listOf("カン"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "情",
        meaningEs = "sentimientos; emoción; simpatía",
        onyomi = listOf("ジョウ", "セイ"),
        kunyomi = listOf("なさ.け"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "投",
        meaningEs = "lanzar; arrojar",
        onyomi = listOf("トウ"),
        kunyomi = listOf("な.げる", "-な.げ"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "示",
        meaningEs = "mostrar; enseñar; indicar; revelar",
        onyomi = listOf("ジ", "シ"),
        kunyomi = listOf("しめ.す"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "変",
        meaningEs = "inusual; extraño; cambio; cambiar; alterar; modificar",
        onyomi = listOf("ヘン"),
        kunyomi = listOf("か.わる", "か.わり", "か.える"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "打",
        meaningEs = "golpear; pegar",
        onyomi = listOf("ダ", "ダース"),
        kunyomi = listOf("う.つ", "う.ち-", "ぶ.つ"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "直",
        meaningEs = "derecho; recto; correcto; de inmediato; arreglar; reparar; arreglarse; repararse",
        onyomi = listOf("チョク", "ジキ", "ジカ"),
        kunyomi = listOf("ただ.ちに", "なお.す", "-なお.す", "なお.る", "なお.き", "す.ぐ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "両",
        meaningEs = "ambos; contador de vehículos",
        onyomi = listOf("リョウ"),
        kunyomi = listOf("てる", "ふたつ"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "式",
        meaningEs = "ceremonia; método; sistema; forma",
        onyomi = listOf("シキ"),
        kunyomi = listOf(),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "確",
        meaningEs = "seguro; cierto; genuino; capaz; asegurarse; afirmar",
        onyomi = listOf("カク", "コウ"),
        kunyomi = listOf("たし.か", "たし.かめる"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "果",
        meaningEs = "fruto; resultado; llevar a cabo; finalizar",
        onyomi = listOf("カ"),
        kunyomi = listOf("は.たす", "はた.す", "-は.たす", "は.てる", "-は.てる", "は.て"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "容",
        meaningEs = "contenido; figura; perdonar; fácil",
        onyomi = listOf("ヨウ"),
        kunyomi = listOf("い.れる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "必",
        meaningEs = "sin falta; necesariamente; inevitable; ciertamente",
        onyomi = listOf("ヒツ"),
        kunyomi = listOf("かなら.ず"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "演",
        meaningEs = "expresar; realizar; llevar a cabo; poner en práctica; representar",
        onyomi = listOf("エン"),
        kunyomi = listOf(),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "歳",
        meaningEs = "año; edad",
        onyomi = listOf("サイ", "セイ"),
        kunyomi = listOf("とし", "とせ", "よわい"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "争",
        meaningEs = "pelear; disputar; discutir; competir",
        onyomi = listOf("ソウ"),
        kunyomi = listOf("あらそ.う", "いか.でか"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "談",
        meaningEs = "conversar; hablar",
        onyomi = listOf("ダン"),
        kunyomi = listOf(),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "能",
        meaningEs = "habilidad; talento; capacidad; aptitud; teatro japonés",
        onyomi = listOf("ノウ"),
        kunyomi = listOf("よ.く", "あた.う"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "位",
        meaningEs = "grado; posición; aproximadamente; trono",
        onyomi = listOf("イ"),
        kunyomi = listOf("くらい", "ぐらい"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "置",
        meaningEs = "poner; colocar; situar",
        onyomi = listOf("チ"),
        kunyomi = listOf("お.く", "-お.き"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "流",
        meaningEs = "corriente; derramar; verter",
        onyomi = listOf("リュウ", "ル"),
        kunyomi = listOf("なが.れる", "なが.れ", "なが.す", "-なが.す"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "格",
        meaningEs = "estado; rango; posición; regla; ley; aptitud; capacidad; carácter",
        onyomi = listOf("カク", "コウ", "キャク", "ゴウ"),
        kunyomi = listOf(),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "疑",
        meaningEs = "duda; desconfianza; sospecha; dudar; desconfiar; sospechar",
        onyomi = listOf("ギ"),
        kunyomi = listOf("うたが.う"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "過",
        meaningEs = "pasar; extralimitarse; exceder; fallo; error; traspasar; fallar",
        onyomi = listOf("カ"),
        kunyomi = listOf("す.ぎる", "す.ごす", "あやま.ち", "あやま.つ", "よぎ.る", "よ.ぎる"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "局",
        meaningEs = "oficina; departamento",
        onyomi = listOf("キョク"),
        kunyomi = listOf("つぼね"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "放",
        meaningEs = "dejar libre; soltar; liberar; emitir; separarse; abandonar",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("はな.す", "-っぱな.し", "はな.つ", "はな.れる", "こ.く", "ほう.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "常",
        meaningEs = "habitual; usual; ordinario; normal; siempre; interminable",
        onyomi = listOf("ジョウ"),
        kunyomi = listOf("つね", "とこ-"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "状",
        meaningEs = "estado; condición; circunstancias; situación",
        onyomi = listOf("ジョウ"),
        kunyomi = listOf(),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "球",
        meaningEs = "pelota; esfera",
        onyomi = listOf("キュウ"),
        kunyomi = listOf("たま"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "職",
        meaningEs = "puesto; empleo; ocupación",
        onyomi = listOf("ショク", "ソク"),
        kunyomi = listOf(),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "与",
        meaningEs = "otorgar; conceder; proveer; unir fuerzas",
        onyomi = listOf("ヨ"),
        kunyomi = listOf("あた.える", "あずか.る", "くみ.する", "ともに"),
        strokeCount = 3
    ),
    N3KanjiEntry(
        character = "供",
        meaningEs = "ofrecer; entregar; expresar; mencionar; acompañante",
        onyomi = listOf("キョウ", "ク", "クウ", "グ"),
        kunyomi = listOf("そな.える", "とも", "-ども"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "役",
        meaningEs = "oficio; deber; servicio; papel; (guerra)",
        onyomi = listOf("ヤク", "エキ"),
        kunyomi = listOf(),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "構",
        meaningEs = "construir; ensamblar; preparación; preocupación; establecerse; montar; poner; interesarse por; preocuparse por",
        onyomi = listOf("コウ"),
        kunyomi = listOf("かま.える", "かま.う"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "割",
        meaningEs = "dividir; distribuir; proporción; separar; fracturar",
        onyomi = listOf("カツ"),
        kunyomi = listOf("わ.る", "わり", "わ.り", "わ.れる", "さ.く"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "身",
        meaningEs = "cuerpo; uno mismo; posición",
        onyomi = listOf("シン"),
        kunyomi = listOf("み"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "費",
        meaningEs = "gasto; coste; gastar",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("つい.やす", "つい.える"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "付",
        meaningEs = "adherir; pegar; adjuntar; unir; atar",
        onyomi = listOf("フ"),
        kunyomi = listOf("つ.ける", "-つ.ける", "-づ.ける", "つ.け", "つ.け-", "-つ.け", "-づ.け", "-づけ", "つ.く", "-づ.く", "つ.き", "-つ.き", "-つき", "-づ.き", "-づき"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "由",
        meaningEs = "razón; significado; causa",
        onyomi = listOf("ユ", "ユウ", "ユイ"),
        kunyomi = listOf("よし", "よ.る"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "難",
        meaningEs = "desastre; dificultad; sufrimiento; ataque al punto débil; difícil; imposible",
        onyomi = listOf("ナン"),
        kunyomi = listOf("かた.い", "-がた.い", "むずか.しい", "むづか.しい", "むつか.しい", "-にく.い"),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "優",
        meaningEs = "excelente; sobresaliente; especial; papel; función; elegante; tierno; amable; cariñoso; sobrepasar; dejar atrás; sobresalir",
        onyomi = listOf("ユウ", "ウ"),
        kunyomi = listOf("やさ.しい", "すぐ.れる", "まさ.る"),
        strokeCount = 17
    ),
    N3KanjiEntry(
        character = "夫",
        meaningEs = "marido; hombre; esposo",
        onyomi = listOf("フ", "フウ", "ブ"),
        kunyomi = listOf("おっと", "それ"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "収",
        meaningEs = "ingresar; obtener; recibir; almacenar; aceptar; ser obtenido; ser pagado",
        onyomi = listOf("シュウ"),
        kunyomi = listOf("おさ.める", "おさ.まる"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "断",
        meaningEs = "cortar; interrumpir; rehusar; declinar; rechazar",
        onyomi = listOf("ダン"),
        kunyomi = listOf("た.つ", "ことわ.る", "さだ.める"),
        strokeCount = 11
    )
)

internal val n3KanjiCatalog: List<N3KanjiEntry> =
    n3KanjiBlock1 + n3KanjiBlock2 + n3KanjiBlock3

internal fun n3KanjiByCharacter(character: String): N3KanjiEntry? =
    n3KanjiCatalog.firstOrNull { it.character == character }
