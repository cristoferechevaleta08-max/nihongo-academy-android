package com.nihongo.academy

/**
 * Datos base del bloque 2 del catálogo pedagógico N3 (123 kanji).
 *
 * Significados, lecturas y trazos: KANJIDIC2 vía Japanese Language Data.
 * Fuente: https://github.com/jkindrix/japanese-language-data
 * Datos KANJIDIC2 © Electronic Dictionary Research and Development Group (EDRDG).
 * Licencia de los datos: CC BY-SA 4.0. Se conserva la atribución.
 *
 * Selección pedagógica propia; no es una lista oficial JLPT.
 */
internal val n3KanjiBlock2: List<N3KanjiEntry> = listOf(
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
        meaningEs = "turno; orden; observar",
        onyomi = listOf("バン"),
        kunyomi = listOf("つが.い"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "規",
        meaningEs = "compás; regla; medida",
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
        meaningEs = "desastre; obstáculo; daño",
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
        meaningEs = "amonestar; advertir",
        onyomi = listOf("ケイ"),
        kunyomi = listOf("いまし.める"),
        strokeCount = 19
    ),
    N3KanjiEntry(
        character = "育",
        meaningEs = "educar; criar; ser educado",
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
        meaningEs = "resto; balance; quedar",
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
        meaningEs = "trabajo duro; cansancio; compensación",
        onyomi = listOf("ロウ"),
        kunyomi = listOf("ろう.する", "いたわ.る", "いた.ずき", "ねぎら", "つか.れる", "ねぎら.う"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "例",
        meaningEs = "ejemplo; normal; comparar",
        onyomi = listOf("レイ"),
        kunyomi = listOf("たと.える"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "然",
        meaningEs = "de esa forma; en ese caso",
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
        meaningEs = "perseguir; ahuyentar; expulsar",
        onyomi = listOf("ツイ"),
        kunyomi = listOf("お.う"),
        strokeCount = 9
    ),
    N3KanjiEntry(
        character = "商",
        meaningEs = "trato; negociación; vender",
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
        meaningEs = "caer; ser inferior; ser omitido",
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
        meaningEs = "aún; punta; extremo",
        onyomi = listOf("マツ", "バツ"),
        kunyomi = listOf("すえ", "うら", "うれ"),
        strokeCount = 5
    ),
    N3KanjiEntry(
        character = "守",
        meaningEs = "guardar proteger; defender; guardar",
        onyomi = listOf("シュ", "ス"),
        kunyomi = listOf("まも.る", "まも.り", "もり", "-もり", "かみ"),
        strokeCount = 6
    ),
    N3KanjiEntry(
        character = "若",
        meaningEs = "joven; algunos; posiblemente",
        onyomi = listOf("ジャク", "ニャク", "ニャ"),
        kunyomi = listOf("わか.い", "わか-", "も.しくわ", "も.し", "も.しくは", "ごと.し"),
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
        meaningEs = "orden; mandato; vida",
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
        meaningEs = "negativo; equivocado; culpar",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("あら.ず"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "観",
        meaningEs = "punto de vista; mirar cuidadosamente; mostrar",
        onyomi = listOf("カン"),
        kunyomi = listOf("み.る", "しめ.す"),
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
        meaningEs = "discernir; distinguir; símbolo",
        onyomi = listOf("シキ"),
        kunyomi = listOf("し.る", "しる.す"),
        strokeCount = 19
    ),
    N3KanjiEntry(
        character = "呼",
        meaningEs = "llamar; aspirar",
        onyomi = listOf("コ"),
        kunyomi = listOf("よ.ぶ"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "達",
        meaningEs = "realizar; alcanzar; llegar",
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
        meaningEs = "cuesta; pendiente; abreviación de Osaka",
        onyomi = listOf("ハン"),
        kunyomi = listOf("さか"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "候",
        meaningEs = "clima; tiempo; explorar",
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
        meaningEs = "súbito; repentino; empuje",
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
        meaningEs = "departamento; curso; materia",
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
        meaningEs = "existir; proteger; conocer",
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
        meaningEs = "perfección; conclusión; final",
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
        meaningEs = "condena; censura; obligación",
        onyomi = listOf("セキ"),
        kunyomi = listOf("せ.める"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "捕",
        meaningEs = "atrapar; capturar; coger",
        onyomi = listOf("ホ"),
        kunyomi = listOf("と.らえる", "と.らわれる", "と.る", "とら.える", "とら.われる", "つか.まえる", "つか.まる"),
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
        meaningEs = "sueldo; salario; distribuir",
        onyomi = listOf("キュウ"),
        kunyomi = listOf("たま.う", "たも.う", "-たま.え"),
        strokeCount = 12
    ),
    N3KanjiEntry(
        character = "苦",
        meaningEs = "sufrimiento; pena; doloroso",
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
        meaningEs = "renuncia; dimisión; palabra",
        onyomi = listOf("ジ"),
        kunyomi = listOf("や.める", "いな.む"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "因",
        meaningEs = "causa; origen; depender de",
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
        meaningEs = "eso; aquello; otra persona",
        onyomi = listOf("ヒ"),
        kunyomi = listOf("かれ", "かの", "か.の"),
        strokeCount = 8
    ),
    N3KanjiEntry(
        character = "未",
        meaningEs = "aún no; todavía; hasta ahora",
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
        meaningEs = "desaparecer; perecer; difunto",
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
        meaningEs = "adecuado; apropiado; adaptarse",
        onyomi = listOf("テキ"),
        kunyomi = listOf("かな.う"),
        strokeCount = 14
    ),
    N3KanjiEntry(
        character = "婦",
        meaningEs = "señora; mujer; esposa",
        onyomi = listOf("フ"),
        kunyomi = listOf("よめ"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "寄",
        meaningEs = "colaborar; apoyarse en; acercarse",
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
        meaningEs = "rey; regla; magnate",
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
        meaningEs = "espalda; detrás; estatura",
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
        meaningEs = "escarpado; inaccesible; peligroso",
        onyomi = listOf("ケン"),
        kunyomi = listOf("けわ.しい"),
        strokeCount = 11
    ),
    N3KanjiEntry(
        character = "頼",
        meaningEs = "solicitud; petición; solicitar",
        onyomi = listOf("ライ"),
        kunyomi = listOf("たの.む", "たの.もしい", "たよ.る"),
        strokeCount = 16
    ),
    N3KanjiEntry(
        character = "覚",
        meaningEs = "memorizar; recordar; despertarse",
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
        meaningEs = "omitir; echar; extraer",
        onyomi = listOf("バツ", "ハツ", "ハイ"),
        kunyomi = listOf("ぬ.く", "-ぬ.く", "ぬ.き", "ぬ.ける", "ぬ.かす", "ぬ.かる"),
        strokeCount = 7
    ),
    N3KanjiEntry(
        character = "留",
        meaningEs = "quedarse; permanecer; parar",
        onyomi = listOf("リュウ", "ル"),
        kunyomi = listOf("と.める", "と.まる", "とど.める", "とど.まる", "るうぶる"),
        strokeCount = 10
    ),
    N3KanjiEntry(
        character = "罪",
        meaningEs = "culpabilidad; pecado; delito",
        onyomi = listOf("ザイ"),
        kunyomi = listOf("つみ"),
        strokeCount = 13
    ),
    N3KanjiEntry(
        character = "努",
        meaningEs = "esfuerzo; esforzarse; hacer un esfuerzo",
        onyomi = listOf("ド"),
        kunyomi = listOf("つと.める"),
        strokeCount = 7
    )
)
