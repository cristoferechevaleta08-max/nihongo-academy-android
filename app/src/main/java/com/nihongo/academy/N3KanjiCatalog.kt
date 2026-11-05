package com.nihongo.academy

/**
 * Catálogo continuo de 370 kanji de estudio para JLPT N3.
 *
 * Lecturas, significados en español y trazos adaptados de KANJIDIC2.
 * Fuente: Japanese Language Data, https://github.com/jkindrix/japanese-language-data
 * Datos KANJIDIC2 © Electronic Dictionary Research and Development Group (EDRDG).
 * Licencia de los datos: CC BY-SA 4.0. Se conserva la atribución.
 *
 * Selección pedagógica propia; el JLPT no publica una lista oficial y cerrada.
 */
internal data class N3KanjiEntry(
    val character: String,
    val meaningEs: String,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val strokeCount: Int
)

/** Quita marcadores editoriales de KANJIDIC2 para mostrar lecturas legibles. */
internal fun displayN3KunYomi(reading: String): String =
    reading.replace(".", "").replace("-", "")

internal val n3KanjiCatalog: List<N3KanjiEntry> = listOf(
    N3KanjiEntry(
        character = "政",
        meaningEs = "política; gobierno; reglas",
        onyomi = listOf("セイ", "ショウ"),
        kunyomi = listOf("まつりごと"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "議",
        meaningEs = "debate; deliberación; consulta",
        onyomi = listOf("ギ"),
        kunyomi = listOf(),
        strokeCount = 20
    ),
    N3KanjiEntry(
        character = "連",
        meaningEs = "unir; acompañar; llevar consigo",
        onyomi = listOf("レン"),
        kunyomi = listOf("つら.なる", "つら.ねる", "つ.れる", "-づ.れ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "対",
        meaningEs = "opuesto; frente a; pareja",
        onyomi = listOf("タイ", "ツイ"),
        kunyomi = listOf("あいて", "こた.える", "そろ.い", "つれあ.い", "なら.ぶ", "むか.う"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "部",
        meaningEs = "sección; departamento; parte",
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
        onyomi = listOf("ベイ", "マイ"),
        kunyomi = listOf("こめ", "よね"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "実",
        meaningEs = "realidad; verdad; fruto; contenido; sustancia; dar frutos; madurar",
        onyomi = listOf("ジツ"),
        kunyomi = listOf("み", "みの.る", "まこと", "みち.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "関",
        meaningEs = "barrera; puesto de control; relación",
        onyomi = listOf("カン"),
        kunyomi = listOf("せき", "-ぜき", "かか.わる", "かんぬき"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "決",
        meaningEs = "decidir; fijar; quedar decidido",
        onyomi = listOf("ケツ"),
        kunyomi = listOf("き.める", "-ぎ.め", "き.まる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "全",
        meaningEs = "todo; completo; entero",
        onyomi = listOf("ゼン"),
        kunyomi = listOf("まった.く", "すべ.て"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "表",
        meaningEs = "superficie; exterior; tabla; expresar; mostrar",
        onyomi = listOf("ヒョウ"),
        kunyomi = listOf("おもて", "-おもて", "あらわ.す", "あらわ.れる", "あら.わす"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "戦",
        meaningEs = "guerra; batalla; lucha; combatir",
        onyomi = listOf("セン"),
        kunyomi = listOf("いくさ", "たたか.う"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "経",
        meaningEs = "pasar por; transcurrir; administrar; sutra",
        onyomi = listOf("ケイ", "キョウ"),
        kunyomi = listOf("へ.る", "た.つ", "たていと", "はか.る", "のり"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "最",
        meaningEs = "el más; máximo; superlativo",
        onyomi = listOf("サイ"),
        kunyomi = listOf("もっと.も", "つま"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "現",
        meaningEs = "actual; presente; real; aparecer; hacerse visible",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("あらわ.れる", "あらわ.す"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "調",
        meaningEs = "tono; melodía; investigar; ajustar; poner en orden",
        onyomi = listOf("チョウ"),
        kunyomi = listOf("しら.べる", "しら.べ", "ととの.う", "ととの.える"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "化",
        meaningEs = "cambiar; transformarse; modificarse; disfrazarse",
        onyomi = listOf("カ", "ケ"),
        kunyomi = listOf("ば.ける", "ば.かす"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "当",
        meaningEs = "acertar; corresponder; tocar; apropiado",
        onyomi = listOf("トウ"),
        kunyomi = listOf("あ.たる", "あ.たり", "あ.てる", "あ.て", "まさ.に"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "約",
        meaningEs = "promesa; aproximadamente; resumir",
        onyomi = listOf("ヤク"),
        kunyomi = listOf("つづ.める"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "法",
        meaningEs = "método; ley; doctrina",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("のり"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "性",
        meaningEs = "naturaleza; carácter; sexo",
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
        meaningEs = "sistema; control; regulación",
        onyomi = listOf("セイ"),
        kunyomi = listOf(),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "治",
        meaningEs = "gobernar; administrar; curar; sanar",
        onyomi = listOf("ジ", "チ"),
        kunyomi = listOf("おさ.める", "おさ.まる", "なお.る", "なお.す"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "務",
        meaningEs = "tarea; deber; servicio; desempeñar un cargo",
        onyomi = listOf("ム"),
        kunyomi = listOf("つと.める"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "成",
        meaningEs = "convertirse en; llegar a ser; lograr",
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
        onyomi = listOf("ワ", "オ"),
        kunyomi = listOf("やわ.らぐ", "やわ.らげる", "なご.む", "なご.やか", "あ.える"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "機",
        meaningEs = "máquina; mecanismo; oportunidad; ocasión",
        onyomi = listOf("キ"),
        kunyomi = listOf("はた"),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "平",
        meaningEs = "llano; plano; tranquilo; pacífico",
        onyomi = listOf("ヘイ", "ビョウ", "ヒョウ"),
        kunyomi = listOf("たい.ら", "たい.らげる", "ひら"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "加",
        meaningEs = "añadir; agregar; unirse; Canadá (abreviatura)",
        onyomi = listOf("カ"),
        kunyomi = listOf("くわ.える", "くわ.わる"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "受",
        meaningEs = "recibir; aceptar; tomar; aprobar un examen",
        onyomi = listOf("ジュ"),
        kunyomi = listOf("う.ける", "-う.け", "う.かる"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "続",
        meaningEs = "continuación; serie; secuela; continuar; seguir",
        onyomi = listOf("ゾク", "ショク", "コウ", "キョウ"),
        kunyomi = listOf("つづ.く", "つづ.ける"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "数",
        meaningEs = "número; cifra; contar",
        onyomi = listOf("スウ", "ス"),
        kunyomi = listOf("かず", "かぞ.える"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "記",
        meaningEs = "registro; relato; anotar; consignar por escrito",
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
        meaningEs = "derechos; autoridad; poder",
        onyomi = listOf("ケン", "ゴン"),
        kunyomi = listOf("はか.る"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "支",
        meaningEs = "apoyo; sostén; rama; pagar; sostener",
        onyomi = listOf("シ"),
        kunyomi = listOf("ささ.える", "つか.える", "か.う"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "点",
        meaningEs = "punto; marca",
        onyomi = listOf("テン"),
        kunyomi = listOf("つ.ける", "つ.く", "た.てる", "さ.す", "とも.す"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "報",
        meaningEs = "informar; noticia; recompensa; responder",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("むく.いる"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "済",
        meaningEs = "terminar; concluir; completar; ayudar",
        onyomi = listOf("サイ", "セイ"),
        kunyomi = listOf("す.む", "-ず.み", "-ずみ", "す.まない", "す.ます", "すく.う"),
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
        meaningEs = "origen; pradera; campo",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("はら"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "共",
        meaningEs = "juntos; en común; ambos",
        onyomi = listOf("キョウ"),
        kunyomi = listOf("とも", "とも.に", "-ども"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "得",
        meaningEs = "beneficio; ventaja; obtener; conseguir",
        onyomi = listOf("トク"),
        kunyomi = listOf("え.る", "う.る"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "解",
        meaningEs = "resolver; aclarar; desatar; liberar",
        onyomi = listOf("カイ", "ゲ"),
        kunyomi = listOf("と.く", "と.かす", "と.ける", "ほど.く", "ほぐ.す", "わか.る"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "交",
        meaningEs = "cruzarse; intercambiar; mezclarse; relacionarse",
        onyomi = listOf("コウ"),
        kunyomi = listOf("まじ.わる", "まじ.える", "ま.じる", "まじ.る", "ま.ざる", "ま.ぜる", "か.わす", "かわ.す"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "資",
        meaningEs = "recursos; capital; medios",
        onyomi = listOf("シ"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "予",
        meaningEs = "de antemano; previo",
        onyomi = listOf("ヨ"),
        kunyomi = listOf("あらかじ.め"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "向",
        meaningEs = "dirigirse hacia; mirar hacia; hacer frente",
        onyomi = listOf("コウ"),
        kunyomi = listOf("む.く", "む.い", "-む.き", "む.ける", "-む.け", "む.かう", "む.かい", "む.こう"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "際",
        meaningEs = "ocasión; límite; frontera",
        onyomi = listOf("サイ"),
        kunyomi = listOf("きわ", "-ぎわ"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "勝",
        meaningEs = "ganar; sobresalir",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("か.つ", "-が.ち", "まさ.る"),
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
        meaningEs = "informar; anunciar; comunicar",
        onyomi = listOf("コク"),
        kunyomi = listOf("つ.げる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "反",
        meaningEs = "opuesto; contrario; devolver; reflejarse; curvarse",
        onyomi = listOf("ハン"),
        kunyomi = listOf("そ.る", "そ.らす", "かえ.す", "かえ.る"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "判",
        meaningEs = "juzgar; distinguir; sello; marca",
        onyomi = listOf("ハン", "バン"),
        kunyomi = listOf("わか.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "認",
        meaningEs = "reconocer; admitir; consentir",
        onyomi = listOf("ニン"),
        kunyomi = listOf("みと.める"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "参",
        meaningEs = "visitar; asistir; participar (forma humilde); ser derrotado",
        onyomi = listOf("サン"),
        kunyomi = listOf("まい.る"),
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
        meaningEs = "existir; estar; residir",
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
        meaningEs = "buscar; pedir; solicitar; reclamar",
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
        meaningEs = "funcionario; autoridad; órgano del cuerpo",
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
        meaningEs = "encargado; relación; asunto",
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
        meaningEs = "sentimiento; emoción; circunstancias",
        onyomi = listOf("ジョウ"),
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
        meaningEs = "cambio; cambiar; extraño; inusual",
        onyomi = listOf("ヘン"),
        kunyomi = listOf("か.わる", "か.わり", "か.える"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "打",
        meaningEs = "golpear; pegar",
        onyomi = listOf("ダ"),
        kunyomi = listOf("う.つ", "う.ち-", "ぶ.つ"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "直",
        meaningEs = "recto; correcto; corregir; reparar; directamente",
        onyomi = listOf("チョク", "ジキ"),
        kunyomi = listOf("なお.す", "-なお.す", "なお.る", "ただ.ちに"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "両",
        meaningEs = "ambos; contador de vehículos",
        onyomi = listOf("リョウ"),
        kunyomi = listOf("ふたつ"),
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
        meaningEs = "seguro; cierto; confirmar",
        onyomi = listOf("カク"),
        kunyomi = listOf("たし.か", "たし.かめる"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "果",
        meaningEs = "fruto; resultado; lograr; completar",
        onyomi = listOf("カ"),
        kunyomi = listOf("は.たす", "は.てる", "は.て"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "容",
        meaningEs = "contenido; apariencia; permitir",
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
        kunyomi = listOf("あらそ.う"),
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
        meaningEs = "capacidad; talento; habilidad; teatro Nō",
        onyomi = listOf("ノウ"),
        kunyomi = listOf("よ.く"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "位",
        meaningEs = "posición; rango; aproximadamente",
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
        kunyomi = listOf("なが.れる", "なが.れ", "なが.す"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "格",
        meaningEs = "categoría; rango; estándar; condición",
        onyomi = listOf("カク", "コウ"),
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
        meaningEs = "pasar; exceder; sobrepasar; error",
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
        kunyomi = listOf("はな.す", "-っぱな.し", "はな.つ", "はな.れる", "ほう.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "常",
        meaningEs = "habitual; normal; ordinario; siempre",
        onyomi = listOf("ジョウ"),
        kunyomi = listOf("つね"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "状",
        meaningEs = "estado; condición; situación",
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
        meaningEs = "dar; otorgar; conceder",
        onyomi = listOf("ヨ"),
        kunyomi = listOf("あた.える", "あずか.る", "くみ.する", "ともに"),
        strokeCount = 3
    ),
    N3KanjiEntry(
        character = "供",
        meaningEs = "ofrecer; acompañar; servir",
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
        meaningEs = "estructura; preparar; montar; preocuparse por",
        onyomi = listOf("コウ"),
        kunyomi = listOf("かま.える", "かま.う"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "割",
        meaningEs = "dividir; repartir; proporción",
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
        kunyomi = listOf("つ.ける", "つ.く", "つ.き", "-づ.ける", "-づ.く"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "由",
        meaningEs = "razón; motivo; causa",
        onyomi = listOf("ユ", "ユウ", "ユイ"),
        kunyomi = listOf("よし", "よ.る"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "難",
        meaningEs = "dificultad; problema; difícil",
        onyomi = listOf("ナン"),
        kunyomi = listOf("かた.い", "-がた.い", "むずか.しい", "むづか.しい", "むつか.しい", "-にく.い"),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "優",
        meaningEs = "excelente; superior; amable; gentil",
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
        meaningEs = "recoger; almacenar; obtener; recibir",
        onyomi = listOf("シュウ"),
        kunyomi = listOf("おさ.める", "おさ.まる"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "断",
        meaningEs = "cortar; interrumpir; rechazar; negarse",
        onyomi = listOf("ダン"),
        kunyomi = listOf("た.つ", "ことわ.る", "さだ.める"),
        strokeCount = 11
    )
,
    N3KanjiEntry(
        character = "石",
        meaningEs = "piedra",
        onyomi = listOf("セキ", "シャク", "コク"),
        kunyomi = listOf("いし"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "違",
        meaningEs = "diferencia; diferir; violar",
        onyomi = listOf("イ"),
        kunyomi = listOf("ちが.う", "ちが.い", "ちが.える", "-ちが.える", "たが.う", "たが.える"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "消",
        meaningEs = "extinguirse; apagarse; desaparecer",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("き.える", "け.す"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "神",
        meaningEs = "dios; alma",
        onyomi = listOf("シン", "ジン"),
        kunyomi = listOf("かみ", "かん-", "こう-"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "番",
        meaningEs = "turno; número; orden",
        onyomi = listOf("バン"),
        kunyomi = listOf("つが.い"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "規",
        meaningEs = "regla; norma; estándar",
        onyomi = listOf("キ"),
        kunyomi = listOf(),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "術",
        meaningEs = "arte; técnica; manera de hacer las cosas",
        onyomi = listOf("ジュツ"),
        kunyomi = listOf("すべ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "備",
        meaningEs = "equipar; aprovisionar; preparación",
        onyomi = listOf("ビ"),
        kunyomi = listOf("そな.える", "そな.わる", "つぶさ.に"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "宅",
        meaningEs = "hogar; casa; residencia",
        onyomi = listOf("タク"),
        kunyomi = listOf(),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "害",
        meaningEs = "daño; perjuicio; obstáculo",
        onyomi = listOf("ガイ"),
        kunyomi = listOf(),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "配",
        meaningEs = "distribuir; racionar",
        onyomi = listOf("ハイ"),
        kunyomi = listOf("くば.る"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "警",
        meaningEs = "advertir; vigilar; policía",
        onyomi = listOf("ケイ"),
        kunyomi = listOf("いまし.める"),
        strokeCount = 19
    ),
    N3KanjiEntry(
        character = "育",
        meaningEs = "criar; educar; crecer",
        onyomi = listOf("イク"),
        kunyomi = listOf("そだ.つ", "そだ.ち", "そだ.てる", "はぐく.む"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "席",
        meaningEs = "asiento; plaza; posición",
        onyomi = listOf("セキ"),
        kunyomi = listOf("むしろ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "訪",
        meaningEs = "visitar; hacer una visita",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("おとず.れる", "たず.ねる", "と.う"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "残",
        meaningEs = "resto; quedar; permanecer",
        onyomi = listOf("ザン", "サン"),
        kunyomi = listOf("のこ.る", "のこ.す", "そこな.う", "のこ.り"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "想",
        meaningEs = "concepto; idea; pensamiento",
        onyomi = listOf("ソウ", "ソ"),
        kunyomi = listOf("おも.う"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "助",
        meaningEs = "ayudar; rescatar; salvar",
        onyomi = listOf("ジョ"),
        kunyomi = listOf("たす.ける", "たす.かる", "す.ける", "すけ"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "労",
        meaningEs = "trabajo; esfuerzo; fatiga",
        onyomi = listOf("ロウ"),
        kunyomi = listOf("ろう.する", "いたわ.る", "いた.ずき", "ねぎら", "つか.れる", "ねぎら.う"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "例",
        meaningEs = "ejemplo; caso; comparar",
        onyomi = listOf("レイ"),
        kunyomi = listOf("たと.える"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "然",
        meaningEs = "así; de esa manera; natural",
        onyomi = listOf("ゼン", "ネン"),
        kunyomi = listOf("しか", "しか.り", "しか.し", "さ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "限",
        meaningEs = "limitar; restringir; confinar",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("かぎ.る", "かぎ.り", "-かぎ.り"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "追",
        meaningEs = "perseguir; seguir; alcanzar",
        onyomi = listOf("ツイ"),
        kunyomi = listOf("お.う"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "商",
        meaningEs = "comercio; trato; vender",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("あきな.う"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "葉",
        meaningEs = "hoja; contador de cosas planas; follaje",
        onyomi = listOf("ヨウ"),
        kunyomi = listOf("は"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "伝",
        meaningEs = "transmitir; tradición; ser transmitido",
        onyomi = listOf("デン", "テン"),
        kunyomi = listOf("つた.わる", "つた.える", "つた.う", "つだ.う", "-づた.い", "つて"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "形",
        meaningEs = "forma; figura; tipo",
        onyomi = listOf("ケイ", "ギョウ"),
        kunyomi = listOf("かた", "-がた", "かたち", "なり"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "景",
        meaningEs = "paisaje; vista; panorama",
        onyomi = listOf("ケイ"),
        kunyomi = listOf(),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "落",
        meaningEs = "caer; dejar caer; omitir",
        onyomi = listOf("ラク"),
        kunyomi = listOf("お.ちる", "お.ち", "お.とす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "退",
        meaningEs = "retroceder; retirarse; renunciar",
        onyomi = listOf("タイ"),
        kunyomi = listOf("しりぞ.く", "しりぞ.ける", "ひ.く", "の.く", "の.ける", "ど.く"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "負",
        meaningEs = "llevar; tomar a su cargo; deber",
        onyomi = listOf("フ"),
        kunyomi = listOf("ま.ける", "ま.かす", "お.う"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "渡",
        meaningEs = "vadear; cruzar; pasar",
        onyomi = listOf("ト"),
        kunyomi = listOf("わた.る", "-わた.る", "わた.す"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "失",
        meaningEs = "pérdida; error; defecto",
        onyomi = listOf("シツ"),
        kunyomi = listOf("うしな.う", "う.せる"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "差",
        meaningEs = "distinción; diferencia; remanente",
        onyomi = listOf("サ"),
        kunyomi = listOf("さ.す", "さ.し"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "末",
        meaningEs = "final; extremo; punta",
        onyomi = listOf("マツ", "バツ"),
        kunyomi = listOf("すえ", "うら", "うれ"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "守",
        meaningEs = "proteger; defender; guardar",
        onyomi = listOf("シュ", "ス"),
        kunyomi = listOf("まも.る", "まも.り", "もり", "-もり", "かみ"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "若",
        meaningEs = "joven; poco; algunos",
        onyomi = listOf("ジャク", "ニャク", "ニャ"),
        kunyomi = listOf("わか.い", "わか-", "も.しくは", "も.し", "ごと.し"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "種",
        meaningEs = "semilla; tipo; variedad",
        onyomi = listOf("シュ"),
        kunyomi = listOf("たね", "-ぐさ"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "美",
        meaningEs = "belleza; bello",
        onyomi = listOf("ビ", "ミ"),
        kunyomi = listOf("うつく.しい"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "命",
        meaningEs = "vida; destino; orden",
        onyomi = listOf("メイ", "ミョウ"),
        kunyomi = listOf("いのち"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "福",
        meaningEs = "fortuna; suerte; dicha",
        onyomi = listOf("フク"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "望",
        meaningEs = "esperanza; deseo; aspiración",
        onyomi = listOf("ボウ", "モウ"),
        kunyomi = listOf("のぞ.む", "もち"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "非",
        meaningEs = "no; negativo; incorrecto",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("あら.ず"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "観",
        meaningEs = "observación; punto de vista; contemplar",
        onyomi = listOf("カン"),
        kunyomi = listOf("み.る"),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "察",
        meaningEs = "adivinar; suponer; percibir",
        onyomi = listOf("サツ"),
        kunyomi = listOf(),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "段",
        meaningEs = "escalón; escalera; grado",
        onyomi = listOf("ダン", "タン"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "横",
        meaningEs = "lateral; lado; horizontal",
        onyomi = listOf("オウ"),
        kunyomi = listOf("よこ"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "深",
        meaningEs = "profundo; hondo; profundizar",
        onyomi = listOf("シン"),
        kunyomi = listOf("ふか.い", "-ぶか.い", "ふか.まる", "ふか.める", "み-"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "様",
        meaningEs = "forma; manera; situación",
        onyomi = listOf("ヨウ", "ショウ"),
        kunyomi = listOf("さま", "さん"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "財",
        meaningEs = "propiedad; dinero; riqueza",
        onyomi = listOf("ザイ", "サイ", "ゾク"),
        kunyomi = listOf("たから"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "港",
        meaningEs = "puerto; portuario",
        onyomi = listOf("コウ"),
        kunyomi = listOf("みなと"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "識",
        meaningEs = "conocimiento; discernimiento; distinguir",
        onyomi = listOf("シキ"),
        kunyomi = listOf("し.る", "しる.す"),
        strokeCount = 19
    ),
    N3KanjiEntry(
        character = "呼",
        meaningEs = "llamar; convocar",
        onyomi = listOf("コ"),
        kunyomi = listOf("よ.ぶ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "達",
        meaningEs = "alcanzar; lograr; llegar",
        onyomi = listOf("タツ", "ダ"),
        kunyomi = listOf("-たち"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "良",
        meaningEs = "bueno; sobresaliente",
        onyomi = listOf("リョウ"),
        kunyomi = listOf("よ.い", "-よ.い", "い.い", "-い.い"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "阪",
        meaningEs = "cuesta; pendiente (también aparece en el nombre de Osaka)",
        onyomi = listOf("ハン"),
        kunyomi = listOf("さか"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "候",
        meaningEs = "clima; estación; esperar (uso clásico)",
        onyomi = listOf("コウ"),
        kunyomi = listOf("そうろう"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "程",
        meaningEs = "grado; extensión; distancia",
        onyomi = listOf("テイ"),
        kunyomi = listOf("ほど", "-ほど"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "満",
        meaningEs = "completo; pleno; plenitud",
        onyomi = listOf("マン", "バン"),
        kunyomi = listOf("み.ちる", "み.つ", "み.たす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "敗",
        meaningEs = "fallo; derrota; fracaso",
        onyomi = listOf("ハイ"),
        kunyomi = listOf("やぶ.れる"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "値",
        meaningEs = "precio; coste; valor",
        onyomi = listOf("チ"),
        kunyomi = listOf("ね", "あたい"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "突",
        meaningEs = "empujar; embestir; repentino",
        onyomi = listOf("トツ", "カ"),
        kunyomi = listOf("つ.く"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "路",
        meaningEs = "camino; ruta; carretera",
        onyomi = listOf("ロ", "ル"),
        kunyomi = listOf("-じ", "みち"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "科",
        meaningEs = "asignatura; materia; departamento",
        onyomi = listOf("カ"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "積",
        meaningEs = "amontonar; apilar; amplitud",
        onyomi = listOf("セキ"),
        kunyomi = listOf("つ.む", "-づ.み", "つ.もる", "つ.もり"),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "他",
        meaningEs = "otro; los demás",
        onyomi = listOf("タ"),
        kunyomi = listOf("ほか"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "処",
        meaningEs = "lugar; deshacerse de; ingeniárselas",
        onyomi = listOf("ショ"),
        kunyomi = listOf("ところ", "-こ", "お.る"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "客",
        meaningEs = "huésped; visitante; cliente",
        onyomi = listOf("キャク", "カク"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "否",
        meaningEs = "negativa; rechazo",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("いな", "いや"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "師",
        meaningEs = "maestro; profesor; experto",
        onyomi = listOf("シ"),
        kunyomi = listOf("いくさ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "登",
        meaningEs = "ascensión; escalada; subir",
        onyomi = listOf("トウ", "ト", "ドウ", "ショウ", "チョウ"),
        kunyomi = listOf("のぼ.る", "あ.がる"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "易",
        meaningEs = "fácil; simple; cambio",
        onyomi = listOf("エキ", "イ"),
        kunyomi = listOf("やさ.しい", "やす.い"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "速",
        meaningEs = "rápido; veloz; darse prisa",
        onyomi = listOf("ソク"),
        kunyomi = listOf("はや.い", "はや-", "はや.める", "すみ.やか"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "存",
        meaningEs = "existir; estar presente; conservar",
        onyomi = listOf("ソン", "ゾン"),
        kunyomi = listOf("ながら.える", "あ.る", "たも.つ", "と.う"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "飛",
        meaningEs = "volar; omitir",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("と.ぶ", "と.ばす", "-と.ばす"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "殺",
        meaningEs = "matar; quitar la vida",
        onyomi = listOf("サツ", "サイ", "セツ"),
        kunyomi = listOf("ころ.す", "-ごろ.し", "そ.ぐ", "あや.める"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "号",
        meaningEs = "número",
        onyomi = listOf("ゴウ"),
        kunyomi = listOf("さけ.ぶ", "よびな"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "単",
        meaningEs = "sencillo; uno; unidad",
        onyomi = listOf("タン"),
        kunyomi = listOf("ひとえ"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "座",
        meaningEs = "sitio; asiento; constelación",
        onyomi = listOf("ザ"),
        kunyomi = listOf("すわ.る"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "破",
        meaningEs = "dañar; romper; rasgar",
        onyomi = listOf("ハ"),
        kunyomi = listOf("やぶ.る", "やぶ.れる", "わ.れる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "除",
        meaningEs = "eliminar; quitar; excluir",
        onyomi = listOf("ジョ", "ジ"),
        kunyomi = listOf("のぞ.く", "-よ.け"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "完",
        meaningEs = "completar; terminar; conclusión",
        onyomi = listOf("カン"),
        kunyomi = listOf(),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "降",
        meaningEs = "caer; bajar; descender",
        onyomi = listOf("コウ", "ゴ"),
        kunyomi = listOf("お.りる", "お.ろす", "ふ.る", "ふ.り", "くだ.る", "くだ.す"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "責",
        meaningEs = "responsabilidad; reproche; censura",
        onyomi = listOf("セキ"),
        kunyomi = listOf("せ.める"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "捕",
        meaningEs = "atrapar; capturar; coger",
        onyomi = listOf("ホ"),
        kunyomi = listOf("と.らえる", "と.らわれる", "と.る", "つか.まえる", "つか.まる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "危",
        meaningEs = "peligroso; dudoso",
        onyomi = listOf("キ"),
        kunyomi = listOf("あぶ.ない", "あや.うい", "あや.ぶむ"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "給",
        meaningEs = "salario; sueldo; suministrar",
        onyomi = listOf("キュウ"),
        kunyomi = listOf("たま.う", "たも.う", "-たま.え"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "苦",
        meaningEs = "sufrimiento; dolor; difícil",
        onyomi = listOf("ク"),
        kunyomi = listOf("くる.しい", "-ぐる.しい", "くる.しむ", "くる.しめる", "にが.い", "にが.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "迎",
        meaningEs = "bienvenida; recibir; ir a recibir",
        onyomi = listOf("ゲイ"),
        kunyomi = listOf("むか.える"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "園",
        meaningEs = "parque; jardín; academia",
        onyomi = listOf("エン"),
        kunyomi = listOf("その"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "具",
        meaningEs = "herramienta; utensilio; ingredientes",
        onyomi = listOf("グ"),
        kunyomi = listOf("そな.える", "つぶさ.に"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "辞",
        meaningEs = "renunciar; dimisión; palabra",
        onyomi = listOf("ジ"),
        kunyomi = listOf("や.める", "いな.む"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "因",
        meaningEs = "causa; origen; motivo",
        onyomi = listOf("イン"),
        kunyomi = listOf("よ.る", "ちな.む"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "馬",
        meaningEs = "caballo",
        onyomi = listOf("バ"),
        kunyomi = listOf("うま", "うま-", "ま"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "愛",
        meaningEs = "amor; afecto; favorito",
        onyomi = listOf("アイ"),
        kunyomi = listOf("いと.しい", "かな.しい", "め.でる", "お.しむ", "まな"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "富",
        meaningEs = "riqueza; fortuna; ser rico",
        onyomi = listOf("フ", "フウ"),
        kunyomi = listOf("と.む", "とみ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "彼",
        meaningEs = "él; esa persona; aquel",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("かれ", "かの", "か.の"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "未",
        meaningEs = "todavía no; aún no",
        onyomi = listOf("ミ", "ビ"),
        kunyomi = listOf("いま.だ", "ま.だ", "ひつじ"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "舞",
        meaningEs = "danza; baile; revoloteo",
        onyomi = listOf("ブ"),
        kunyomi = listOf("ま.う", "-ま.う", "まい"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "亡",
        meaningEs = "morir; fallecer; desaparecer",
        onyomi = listOf("ボウ", "モウ"),
        kunyomi = listOf("な.い", "な.き-", "ほろ.びる", "ほろ.ぶ", "ほろ.ぼす"),
        strokeCount = 3
    ),
    N3KanjiEntry(
        character = "冷",
        meaningEs = "frío; enfriar; enfriarse",
        onyomi = listOf("レイ"),
        kunyomi = listOf("つめ.たい", "ひ.える", "ひ.や", "ひ.ややか", "ひ.やす", "ひ.やかす", "さ.める", "さ.ます"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "適",
        meaningEs = "adecuado; apropiado; apto",
        onyomi = listOf("テキ"),
        kunyomi = listOf("かな.う"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "婦",
        meaningEs = "mujer; esposa; mujer casada",
        onyomi = listOf("フ"),
        kunyomi = listOf("よめ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "寄",
        meaningEs = "acercarse; apoyarse en; visitar",
        onyomi = listOf("キ"),
        kunyomi = listOf("よ.る", "-よ.り", "よ.せる"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "込",
        meaningEs = "abarrotar; llenarse; apiñarse",
        onyomi = listOf(),
        kunyomi = listOf("-こ.む", "こ.む", "こ.み", "-こ.み", "こ.める"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "類",
        meaningEs = "variedad; clase; parecido",
        onyomi = listOf("ルイ"),
        kunyomi = listOf("たぐ.い"),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "余",
        meaningEs = "remanente; exceso; resto",
        onyomi = listOf("ヨ"),
        kunyomi = listOf("あま.る", "あま.り", "あま.す", "あんま.り"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "王",
        meaningEs = "rey; monarca",
        onyomi = listOf("オウ", "-ノウ"),
        kunyomi = listOf(),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "返",
        meaningEs = "regresar; devolver",
        onyomi = listOf("ヘン"),
        kunyomi = listOf("かえ.す", "-かえ.す", "かえ.る", "-かえ.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "妻",
        meaningEs = "esposa; mujer; esposa propia",
        onyomi = listOf("サイ"),
        kunyomi = listOf("つま"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "背",
        meaningEs = "espalda; detrás; altura",
        onyomi = listOf("ハイ"),
        kunyomi = listOf("せ", "せい", "そむ.く", "そむ.ける"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "熱",
        meaningEs = "calor; fiebre; pasión",
        onyomi = listOf("ネツ"),
        kunyomi = listOf("あつ.い"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "宿",
        meaningEs = "posada; alojamiento; hostal",
        onyomi = listOf("シュク"),
        kunyomi = listOf("やど", "やど.る", "やど.す"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "険",
        meaningEs = "escarpado; peligroso; arriesgado",
        onyomi = listOf("ケン"),
        kunyomi = listOf("けわ.しい"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "頼",
        meaningEs = "confiar; pedir; depender de",
        onyomi = listOf("ライ"),
        kunyomi = listOf("たの.む", "たの.もしい", "たよ.る"),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "覚",
        meaningEs = "recordar; memorizar; despertar",
        onyomi = listOf("カク"),
        kunyomi = listOf("おぼ.える", "さ.ます", "さ.める", "さと.る"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "船",
        meaningEs = "barco; buque; bote",
        onyomi = listOf("セン"),
        kunyomi = listOf("ふね", "ふな-"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "途",
        meaningEs = "ruta; camino; carretera",
        onyomi = listOf("ト"),
        kunyomi = listOf("みち"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "許",
        meaningEs = "permitir; perdonar; dejar",
        onyomi = listOf("キョ"),
        kunyomi = listOf("ゆる.す", "もと"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "抜",
        meaningEs = "extraer; sacar; omitir",
        onyomi = listOf("バツ", "ハツ", "ハイ"),
        kunyomi = listOf("ぬ.く", "-ぬ.く", "ぬ.き", "ぬ.ける", "ぬ.かす", "ぬ.かる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "留",
        meaningEs = "quedarse; permanecer; detener",
        onyomi = listOf("リュウ", "ル"),
        kunyomi = listOf("と.める", "と.まる", "とど.める", "とど.まる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "罪",
        meaningEs = "culpa; pecado; delito",
        onyomi = listOf("ザイ"),
        kunyomi = listOf("つみ"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "努",
        meaningEs = "esforzarse; hacer un esfuerzo",
        onyomi = listOf("ド"),
        kunyomi = listOf("つと.める"),
        strokeCount = 7
    )
,
    N3KanjiEntry(
        character = "精",
        meaningEs = "refinar; purificar; preciso",
        onyomi = listOf("セイ", "ショウ"),
        kunyomi = listOf("しら.げる", "くわ.しい"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "散",
        meaningEs = "esparcir; dispersar; derramar",
        onyomi = listOf("サン"),
        kunyomi = listOf("ち.る", "ち.らす", "-ち.らす", "ち.らかす", "ち.らかる", "ち.らばる", "ばら", "ばら.ける"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "静",
        meaningEs = "quieto; tranquilo; calmado",
        onyomi = listOf("セイ", "ジョウ"),
        kunyomi = listOf("しず-", "しず.か", "しず.まる", "しず.める"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "婚",
        meaningEs = "matrimonio; casamiento",
        onyomi = listOf("コン"),
        kunyomi = listOf(),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "喜",
        meaningEs = "alegría; júbilo; divertirse",
        onyomi = listOf("キ"),
        kunyomi = listOf("よろこ.ぶ", "よろこ.ばす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "浮",
        meaningEs = "flotar; venir a la memoria; salir a la superficie",
        onyomi = listOf("フ"),
        kunyomi = listOf("う.く", "う.かれる", "う.かぶ", "う.かべる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "絶",
        meaningEs = "extinguir; eliminar; rechazar",
        onyomi = listOf("ゼツ"),
        kunyomi = listOf("た.える", "た.やす", "た.つ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "幸",
        meaningEs = "felicidad; bendición; fortuna",
        onyomi = listOf("コウ"),
        kunyomi = listOf("さいわ.い", "さち", "しあわ.せ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "押",
        meaningEs = "empujar; presionar; forzar",
        onyomi = listOf("オウ"),
        kunyomi = listOf("お.す", "お.し-", "お.っ-", "お.さえる"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "倒",
        meaningEs = "caer; derribar; derrocar",
        onyomi = listOf("トウ"),
        kunyomi = listOf("たお.れる", "-だお.れ", "たお.す", "さかさま", "さかさ", "さかしま"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "等",
        meaningEs = "clase; categoría; igual; etcétera",
        onyomi = listOf("トウ"),
        kunyomi = listOf("ひと.しい", "など", "-ら"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "老",
        meaningEs = "envejecer; viejo; experimentado",
        onyomi = listOf("ロウ"),
        kunyomi = listOf("お.いる", "ふ.ける"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "曲",
        meaningEs = "melodía; curva; encorvado",
        onyomi = listOf("キョク"),
        kunyomi = listOf("ま.がる", "ま.げる", "くま"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "払",
        meaningEs = "pagar; saldar; liquidar",
        onyomi = listOf("フツ", "ヒツ", "ホツ"),
        kunyomi = listOf("はら.う", "-はら.い", "-ばら.い"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "庭",
        meaningEs = "patio; jardín",
        onyomi = listOf("テイ"),
        kunyomi = listOf("にわ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "徒",
        meaningEs = "persona; seguidor; en vano",
        onyomi = listOf("ト"),
        kunyomi = listOf("いたずら", "あだ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "勤",
        meaningEs = "trabajo; servicio; deber",
        onyomi = listOf("キン", "ゴン"),
        kunyomi = listOf("つと.める", "-づと.め", "つと.まる", "いそ.しむ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "遅",
        meaningEs = "despacio; tarde; llegar tarde",
        onyomi = listOf("チ"),
        kunyomi = listOf("おく.れる", "おく.らす", "おそ.い"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "居",
        meaningEs = "sentarse; residir; vivir con",
        onyomi = listOf("キョ", "コ"),
        kunyomi = listOf("い.る", "-い", "お.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "雑",
        meaningEs = "misceláneo; varios; mezclado",
        onyomi = listOf("ザツ", "ゾウ"),
        kunyomi = listOf("まじ.える", "まじ.る"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "招",
        meaningEs = "invitar; convocar",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("まね.く"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "困",
        meaningEs = "dificultades; preocupaciones; sufrir",
        onyomi = listOf("コン"),
        kunyomi = listOf("こま.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "欠",
        meaningEs = "falta; carestía; romperse",
        onyomi = listOf("ケツ", "ケン"),
        kunyomi = listOf("か.ける", "か.く"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "更",
        meaningEs = "renovar; cambiar; caer la noche",
        onyomi = listOf("コウ"),
        kunyomi = listOf("さら", "さら.に", "ふ.ける", "ふ.かす"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "刻",
        meaningEs = "grabar; esculpir; momento",
        onyomi = listOf("コク"),
        kunyomi = listOf("きざ.む", "きざ.み"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "賛",
        meaningEs = "aprobar; elogiar; alabar",
        onyomi = listOf("サン"),
        kunyomi = listOf("たす.ける", "たた.える"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "抱",
        meaningEs = "abrazo; sostener en los brazos; abrazar",
        onyomi = listOf("ホウ"),
        kunyomi = listOf("だ.く", "いだ.く", "かか.える"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "犯",
        meaningEs = "crimen; delito; ofensa",
        onyomi = listOf("ハン", "ボン"),
        kunyomi = listOf("おか.す"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "恐",
        meaningEs = "temor; miedo; tener miedo",
        onyomi = listOf("キョウ"),
        kunyomi = listOf("おそ.れる", "おそ.る", "おそ.ろしい", "こわ.い", "こわ.がる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "息",
        meaningEs = "respiración; aliento; descanso; hijo",
        onyomi = listOf("ソク"),
        kunyomi = listOf("いき"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "戻",
        meaningEs = "volver; regresar; devolver",
        onyomi = listOf("レイ"),
        kunyomi = listOf("もど.す", "もど.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "願",
        meaningEs = "petición; deseo; anhelo",
        onyomi = listOf("ガン"),
        kunyomi = listOf("ねが.う", "-ねがい"),
        strokeCount = 19
    ),
    N3KanjiEntry(
        character = "絵",
        meaningEs = "cuadro; dibujo; pintura",
        onyomi = listOf("カイ", "エ"),
        kunyomi = listOf(),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "越",
        meaningEs = "sobrepasar; cruzar; exceder",
        onyomi = listOf("エツ", "オツ"),
        kunyomi = listOf("こ.す", "-こ.す", "-ご.し", "こ.える", "-ご.え"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "欲",
        meaningEs = "anhelo; ansia; deseo",
        onyomi = listOf("ヨク"),
        kunyomi = listOf("ほっ.する", "ほ.しい"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "痛",
        meaningEs = "dolor; daño; herida",
        onyomi = listOf("ツウ"),
        kunyomi = listOf("いた.い", "いた.む", "いた.ましい", "いた.める"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "笑",
        meaningEs = "risa; sonrisa; reír",
        onyomi = listOf("ショウ"),
        kunyomi = listOf("わら.う", "え.む"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "互",
        meaningEs = "mutuo; recíproco; conjunto",
        onyomi = listOf("ゴ"),
        kunyomi = listOf("たが.い", "かたみ.に"),
        strokeCount = 4
    ),
    N3KanjiEntry(
        character = "束",
        meaningEs = "manojo; legajo (papeles); haz (leños)",
        onyomi = listOf("ソク"),
        kunyomi = listOf("たば", "たば.ねる", "つか", "つか.ねる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "似",
        meaningEs = "parecerse; ser similar",
        onyomi = listOf("ジ"),
        kunyomi = listOf("に.る", "ひ.る"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "列",
        meaningEs = "fila; línea; hilera",
        onyomi = listOf("レツ", "レ"),
        kunyomi = listOf(),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "探",
        meaningEs = "explorar; indagar; buscar",
        onyomi = listOf("タン"),
        kunyomi = listOf("さぐ.る", "さが.す"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "逃",
        meaningEs = "escapar; huir; fuga",
        onyomi = listOf("トウ"),
        kunyomi = listOf("に.げる", "に.がす", "のが.す", "のが.れる"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "遊",
        meaningEs = "jugar; viajar; moverse libremente",
        onyomi = listOf("ユウ", "ユ"),
        kunyomi = listOf("あそ.ぶ", "あそ.ばす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "迷",
        meaningEs = "extraviarse; estar perdido; perderse",
        onyomi = listOf("メイ"),
        kunyomi = listOf("まよ.う"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "夢",
        meaningEs = "sueño; visión; ilusión",
        onyomi = listOf("ム", "ボウ"),
        kunyomi = listOf("ゆめ", "ゆめ.みる", "くら.い"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "君",
        meaningEs = "señor; don; tú (familiarmente)",
        onyomi = listOf("クン"),
        kunyomi = listOf("きみ", "-ぎみ"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "緒",
        meaningEs = "principio; comienzo; hilo",
        onyomi = listOf("ショ", "チョ"),
        kunyomi = listOf("お", "いとぐち"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "折",
        meaningEs = "plegar; doblar; romper",
        onyomi = listOf("セツ", "シャク"),
        kunyomi = listOf("お.る", "おり", "お.り", "-お.り", "お.れる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "草",
        meaningEs = "hierba; planta",
        onyomi = listOf("ソウ"),
        kunyomi = listOf("くさ", "くさ-", "-ぐさ"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "暮",
        meaningEs = "anochecer; vivir; pasar el día",
        onyomi = listOf("ボ"),
        kunyomi = listOf("く.れる", "く.らす"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "酒",
        meaningEs = "sake; bebida alcohólica japonesa",
        onyomi = listOf("シュ"),
        kunyomi = listOf("さけ", "さか-"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "悲",
        meaningEs = "tristeza; pena; estar triste",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("かな.しい", "かな.しむ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "晴",
        meaningEs = "cielo despejado; buen tiempo",
        onyomi = listOf("セイ"),
        kunyomi = listOf("は.れる", "は.れ", "は.れ-", "-ば.れ", "は.らす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "掛",
        meaningEs = "colgar; enganchar; colocar",
        onyomi = listOf("カイ", "ケイ"),
        kunyomi = listOf("か.ける", "-か.ける", "か.け", "-か.け", "-が.け", "か.かる", "-か.かる", "-が.かる", "か.かり", "-が.かり", "かかり", "-がかり"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "到",
        meaningEs = "llegar; alcanzar",
        onyomi = listOf("トウ"),
        kunyomi = listOf("いた.る"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "寝",
        meaningEs = "acostarse; dormir",
        onyomi = listOf("シン"),
        kunyomi = listOf("ね.る", "ね.かす", "い.ぬ", "みたまや", "や.める"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "盗",
        meaningEs = "robar; robo; hurtar",
        onyomi = listOf("トウ"),
        kunyomi = listOf("ぬす.む", "ぬす.み"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "吸",
        meaningEs = "aspirar; inhalar; absorber",
        onyomi = listOf("キュウ"),
        kunyomi = listOf("す.う"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "陽",
        meaningEs = "sol; luz solar; yang",
        onyomi = listOf("ヨウ"),
        kunyomi = listOf("ひ"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "御",
        meaningEs = "prefijo honorífico; respeto; imperial",
        onyomi = listOf("ギョ", "ゴ"),
        kunyomi = listOf("おん-", "お-", "み-"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "歯",
        meaningEs = "diente; diente de engranaje",
        onyomi = listOf("シ"),
        kunyomi = listOf("よわい", "は", "よわ.い", "よわい.する"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "忘",
        meaningEs = "olvidar; dejar atrás",
        onyomi = listOf("ボウ"),
        kunyomi = listOf("わす.れる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "雪",
        meaningEs = "nieve",
        onyomi = listOf("セツ"),
        kunyomi = listOf("ゆき"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "吹",
        meaningEs = "soplar; exhalar; respirar",
        onyomi = listOf("スイ"),
        kunyomi = listOf("ふ.く"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "娘",
        meaningEs = "hija; chica; muchacha",
        onyomi = listOf("ジョウ"),
        kunyomi = listOf("むすめ", "こ"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "誤",
        meaningEs = "error; equivocarse",
        onyomi = listOf("ゴ"),
        kunyomi = listOf("あやま.る", "-あやま.る"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "慣",
        meaningEs = "costumbre; acostumbrarse; acostumbrar",
        onyomi = listOf("カン"),
        kunyomi = listOf("な.れる", "な.らす"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "礼",
        meaningEs = "saludo; reverencia; cortesía",
        onyomi = listOf("レイ", "ライ"),
        kunyomi = listOf(),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "窓",
        meaningEs = "ventana; habitación",
        onyomi = listOf("ソウ", "ス"),
        kunyomi = listOf("まど", "てんまど", "けむだし"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "昔",
        meaningEs = "antiguamente; antigüedad; viejos tiempos",
        onyomi = listOf("セキ", "シャク"),
        kunyomi = listOf("むかし"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "貧",
        meaningEs = "pobreza; pobre; humilde",
        onyomi = listOf("ヒン", "ビン"),
        kunyomi = listOf("まず.しい"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "怒",
        meaningEs = "enojo; enfado; enojarse",
        onyomi = listOf("ド", "ヌ"),
        kunyomi = listOf("いか.る", "おこ.る"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "泳",
        meaningEs = "nadar",
        onyomi = listOf("エイ"),
        kunyomi = listOf("およ.ぐ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "祖",
        meaningEs = "antepasado; ancestro",
        onyomi = listOf("ソ"),
        kunyomi = listOf(),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "杯",
        meaningEs = "copa; vaso; taza",
        onyomi = listOf("ハイ"),
        kunyomi = listOf("さかずき"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "疲",
        meaningEs = "cansancio; fatiga; estar cansado",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("つか.れる", "-づか.れ", "つか.らす"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "皆",
        meaningEs = "todo; todos; cada uno",
        onyomi = listOf("カイ"),
        kunyomi = listOf("みな", "みんな"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "鳴",
        meaningEs = "sonar; emitir sonidos; cantar (animales)",
        onyomi = listOf("メイ"),
        kunyomi = listOf("な.く", "な.る", "な.らす"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "腹",
        meaningEs = "barriga; estómago",
        onyomi = listOf("フク"),
        kunyomi = listOf("はら"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "煙",
        meaningEs = "humo; humear",
        onyomi = listOf("エン"),
        kunyomi = listOf("けむ.る", "けむり", "けむ.い"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "眠",
        meaningEs = "dormir; sueño; estar somnoliento",
        onyomi = listOf("ミン"),
        kunyomi = listOf("ねむ.る", "ねむ.い"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "怖",
        meaningEs = "temor; miedo; estar asustado",
        onyomi = listOf("フ", "ホ"),
        kunyomi = listOf("こわ.い", "こわ.がる", "お.じる", "おそ.れる"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "頂",
        meaningEs = "cima; cumbre; colocar sobre",
        onyomi = listOf("チョウ"),
        kunyomi = listOf("いただ.く", "いただき"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "箱",
        meaningEs = "caja; cofre",
        onyomi = listOf("ソウ"),
        kunyomi = listOf("はこ"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "晩",
        meaningEs = "noche; tarde-noche",
        onyomi = listOf("バン"),
        kunyomi = listOf(),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "髪",
        meaningEs = "pelo; cabello",
        onyomi = listOf("ハツ"),
        kunyomi = listOf("かみ"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "忙",
        meaningEs = "ocupado; atareado",
        onyomi = listOf("ボウ", "モウ"),
        kunyomi = listOf("いそが.しい", "せわ.しい", "おそ.れる", "うれえるさま"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "才",
        meaningEs = "talento; capacidad; habilidad",
        onyomi = listOf("サイ"),
        kunyomi = listOf(),
        strokeCount = 3
    ),
    N3KanjiEntry(
        character = "靴",
        meaningEs = "zapatos",
        onyomi = listOf("カ"),
        kunyomi = listOf("くつ"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "恥",
        meaningEs = "vergüenza; rubor; deshonra",
        onyomi = listOf("チ"),
        kunyomi = listOf("は.じる", "はじ", "は.じらう", "は.ずかしい"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "偶",
        meaningEs = "por casualidad; accidental; número par",
        onyomi = listOf("グウ"),
        kunyomi = listOf("たま"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "偉",
        meaningEs = "grandioso; admirable; excelente",
        onyomi = listOf("イ"),
        kunyomi = listOf("えら.い"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "猫",
        meaningEs = "gato",
        onyomi = listOf("ビョウ"),
        kunyomi = listOf("ねこ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "幾",
        meaningEs = "cuántos; cuánto; algunos",
        onyomi = listOf("キ"),
        kunyomi = listOf("いく-", "いく.つ", "いく.ら"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "誰",
        meaningEs = "quién; quiénes (interrogativo)",
        onyomi = listOf("スイ"),
        kunyomi = listOf("だれ", "たれ", "た"),
        strokeCount = 15
    ),
    N3KanjiEntry(
        character = "圧",
        meaningEs = "presión; apretar",
        onyomi = listOf("アツ", "エン", "オウ"),
        kunyomi = listOf("お.す", "へ.す", "おさ.える", "お.さえる"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "囲",
        meaningEs = "rodear; cercar; incluir",
        onyomi = listOf("イ"),
        kunyomi = listOf("かこ.む", "かこ.う", "かこ.い"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "移",
        meaningEs = "mover; trasladar; cambiar de lugar",
        onyomi = listOf("イ"),
        kunyomi = listOf("うつ.る", "うつ.す"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "営",
        meaningEs = "administrar; gestionar; negocio",
        onyomi = listOf("エイ"),
        kunyomi = listOf("いとな.む", "いとな.み"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "衛",
        meaningEs = "defensa; protección; guardia",
        onyomi = listOf("エイ", "エ"),
        kunyomi = listOf(),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "液",
        meaningEs = "líquido; fluido",
        onyomi = listOf("エキ"),
        kunyomi = listOf(),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "応",
        meaningEs = "responder; corresponder; adaptarse",
        onyomi = listOf("オウ", "ヨウ", "-ノウ"),
        kunyomi = listOf("あた.る", "まさに", "こた.える"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "往",
        meaningEs = "ir; dirigirse; antiguo (uso literario)",
        onyomi = listOf("オウ"),
        kunyomi = listOf("い.く", "いにしえ", "さき.に", "ゆ.く"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "桜",
        meaningEs = "cerezo; flor de cerezo",
        onyomi = listOf("オウ", "ヨウ"),
        kunyomi = listOf("さくら"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "恩",
        meaningEs = "gracia; bondad; favor",
        onyomi = listOf("オン"),
        kunyomi = listOf(),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "価",
        meaningEs = "valor; precio; coste",
        onyomi = listOf("カ", "ケ"),
        kunyomi = listOf("あたい"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "河",
        meaningEs = "río; corriente",
        onyomi = listOf("カ"),
        kunyomi = listOf("かわ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "快",
        meaningEs = "agradable; cómodo; placentero",
        onyomi = listOf("カイ"),
        kunyomi = listOf("こころよ.い"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "額",
        meaningEs = "frente; cantidad; marco",
        onyomi = listOf("ガク"),
        kunyomi = listOf("ひたい"),
        strokeCount = 18
    ),
    N3KanjiEntry(
        character = "幹",
        meaningEs = "tronco del árbol; punto importante; base",
        onyomi = listOf("カン"),
        kunyomi = listOf("みき"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "眼",
        meaningEs = "ojo; vista; perspicacia",
        onyomi = listOf("ガン", "ゲン"),
        kunyomi = listOf("まなこ", "め"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "基",
        meaningEs = "fundamento; origen; base",
        onyomi = listOf("キ"),
        kunyomi = listOf("もと", "もとい"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "技",
        meaningEs = "habilidad; arte; técnica",
        onyomi = listOf("ギ"),
        kunyomi = listOf("わざ"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "義",
        meaningEs = "justicia; deber; rectitud",
        onyomi = listOf("ギ"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "逆",
        meaningEs = "opuesto; inverso; contrario",
        onyomi = listOf("ギャク", "ゲキ"),
        kunyomi = listOf("さか", "さか.さ", "さか.らう"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "救",
        meaningEs = "salvar; rescatar; ayudar",
        onyomi = listOf("キュウ"),
        kunyomi = listOf("すく.う"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "境",
        meaningEs = "límite; frontera; territorio",
        onyomi = listOf("キョウ", "ケイ"),
        kunyomi = listOf("さかい"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "禁",
        meaningEs = "prohibición; proscripción; prohibir",
        onyomi = listOf("キン"),
        kunyomi = listOf(),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "検",
        meaningEs = "examinar; investigar; analizar",
        onyomi = listOf("ケン"),
        kunyomi = listOf("しら.べる"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "減",
        meaningEs = "disminuir; reducirse",
        onyomi = listOf("ゲン"),
        kunyomi = listOf("へ.る", "へ.らす"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "個",
        meaningEs = "individuo; individual; contador para objetos pequeños",
        onyomi = listOf("コ", "カ"),
        kunyomi = listOf(),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "講",
        meaningEs = "conferencia; discurso; explicación",
        onyomi = listOf("コウ"),
        kunyomi = listOf(),
        strokeCount = 17
    ),
    N3KanjiEntry(
        character = "査",
        meaningEs = "investigar; examinar; inspeccionar",
        onyomi = listOf("サ"),
        kunyomi = listOf(),
        strokeCount = 9
    )
)

internal fun n3KanjiByCharacter(character: String): N3KanjiEntry? =
    n3KanjiCatalog.firstOrNull { it.character == character }
