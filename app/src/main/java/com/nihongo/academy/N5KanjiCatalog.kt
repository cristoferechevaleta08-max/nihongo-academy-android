package com.nihongo.academy

/** Contenido pedagógico inicial de kanji N5. Selección propia, no lista oficial del JLPT. */
internal data class N5KanjiWord(
    val word: String,
    val reading: String,
    val meaningEs: String
)

internal data class N5KanjiEntry(
    val character: String,
    val meaningEs: String,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val strokeCount: Int,
    val vocabulary: List<N5KanjiWord>,
    val exampleJa: String,
    val exampleReading: String,
    val exampleEs: String
)

internal val n5KanjiCatalog = listOf(
    N5KanjiEntry("一","uno",listOf("イチ","イツ"),listOf("ひと","ひとつ"),1,listOf(N5KanjiWord("一つ","ひとつ","uno (objeto)"),N5KanjiWord("一月","いちがつ","enero")),"りんごを一つください。","りんごをひとつください。","Una manzana, por favor."),
    N5KanjiEntry("二","dos",listOf("ニ"),listOf("ふた","ふたつ"),2,listOf(N5KanjiWord("二つ","ふたつ","dos (objetos)"),N5KanjiWord("二月","にがつ","febrero")),"本を二つ買いました。","ほんをふたつかいました。","Compré dos libros."),
    N5KanjiEntry("三","tres",listOf("サン"),listOf("み","みっつ"),3,listOf(N5KanjiWord("三つ","みっつ","tres (objetos)"),N5KanjiWord("三月","さんがつ","marzo")),"りんごが三つあります。","りんごがみっつあります。","Hay tres manzanas."),
    N5KanjiEntry("四","cuatro",listOf("シ"),listOf("よ","よん","よっつ"),5,listOf(N5KanjiWord("四つ","よっつ","cuatro (objetos)"),N5KanjiWord("四月","しがつ","abril")),"四月に日本へ行きます。","しがつににほんへいきます。","Voy a Japón en abril."),
    N5KanjiEntry("五","cinco",listOf("ゴ"),listOf("いつ","いつつ"),4,listOf(N5KanjiWord("五つ","いつつ","cinco (objetos)"),N5KanjiWord("五月","ごがつ","mayo")),"五時に起きます。","ごじにおきます。","Me levanto a las cinco."),
    N5KanjiEntry("六","seis",listOf("ロク"),listOf("む","むっつ","むい"),4,listOf(N5KanjiWord("六つ","むっつ","seis (objetos)"),N5KanjiWord("六月","ろくがつ","junio")),"六時から勉強します。","ろくじからべんきょうします。","Estudio desde las seis."),
    N5KanjiEntry("七","siete",listOf("シチ"),listOf("なな","なの"),2,listOf(N5KanjiWord("七つ","ななつ","siete (objetos)"),N5KanjiWord("七月","しちがつ","julio")),"七時に学校へ行きます。","しちじにがっこうへいきます。","Voy a la escuela a las siete."),
    N5KanjiEntry("八","ocho",listOf("ハチ"),listOf("や","やっつ","よう"),2,listOf(N5KanjiWord("八つ","やっつ","ocho (objetos)"),N5KanjiWord("八月","はちがつ","agosto")),"八人の学生がいます。","はちにんのがくせいがいます。","Hay ocho estudiantes."),
    N5KanjiEntry("九","nueve",listOf("キュウ","ク"),listOf("ここの","ここのつ"),2,listOf(N5KanjiWord("九つ","ここのつ","nueve (objetos)"),N5KanjiWord("九月","くがつ","septiembre")),"九時に寝ます。","くじにねます。","Me acuesto a las nueve."),
    N5KanjiEntry("十","diez",listOf("ジュウ","ジッ"),listOf("とお","と"),2,listOf(N5KanjiWord("十","じゅう","diez"),N5KanjiWord("十月","じゅうがつ","octubre")),"十人います。","じゅうにんいます。","Hay diez personas."),
    N5KanjiEntry("百","cien",listOf("ヒャク"),emptyList(),6,listOf(N5KanjiWord("百","ひゃく","cien"),N5KanjiWord("三百","さんびゃく","trescientos")),"これは百円です。","これはひゃくえんです。","Esto cuesta cien yenes."),
    N5KanjiEntry("千","mil",listOf("セン"),listOf("ち"),3,listOf(N5KanjiWord("千","せん","mil"),N5KanjiWord("千円","せんえん","mil yenes")),"千円あります。","せんえんあります。","Tengo mil yenes."),
    N5KanjiEntry("万","diez mil",listOf("マン","バン"),emptyList(),3,listOf(N5KanjiWord("一万","いちまん","diez mil"),N5KanjiWord("万円","まんえん","diez mil yenes")),"一万円です。","いちまんえんです。","Son diez mil yenes."),
    N5KanjiEntry("円","yen; círculo",listOf("エン"),emptyList(),4,listOf(N5KanjiWord("百円","ひゃくえん","cien yenes"),N5KanjiWord("千円","せんえん","mil yenes")),"これは五百円です。","これはごひゃくえんです。","Esto cuesta quinientos yenes."),
    N5KanjiEntry("日","día; sol",listOf("ニチ","ジツ"),listOf("ひ","か"),4,listOf(N5KanjiWord("今日","きょう","hoy"),N5KanjiWord("日本","にほん","Japón"),N5KanjiWord("日曜日","にちようび","domingo")),"今日はいい日です。","きょうはいいひです。","Hoy es un buen día."),
    N5KanjiEntry("月","luna; mes",listOf("ゲツ","ガツ"),listOf("つき"),4,listOf(N5KanjiWord("月曜日","げつようび","lunes"),N5KanjiWord("一月","いちがつ","enero")),"月がきれいです。","つきがきれいです。","La luna es bonita."),
    N5KanjiEntry("火","fuego",listOf("カ"),listOf("ひ"),4,listOf(N5KanjiWord("火曜日","かようび","martes"),N5KanjiWord("火","ひ","fuego")),"火曜日に勉強します。","かようびにべんきょうします。","Estudio el martes."),
    N5KanjiEntry("水","agua",listOf("スイ"),listOf("みず"),4,listOf(N5KanjiWord("水","みず","agua"),N5KanjiWord("水曜日","すいようび","miércoles")),"水を飲みます。","みずをのみます。","Bebo agua."),
    N5KanjiEntry("木","árbol; madera",listOf("モク","ボク"),listOf("き","こ"),4,listOf(N5KanjiWord("木","き","árbol"),N5KanjiWord("木曜日","もくようび","jueves")),"大きい木があります。","おおきいきがあります。","Hay un árbol grande."),
    N5KanjiEntry("金","oro; dinero",listOf("キン","コン"),listOf("かね","かな"),8,listOf(N5KanjiWord("お金","おかね","dinero"),N5KanjiWord("金曜日","きんようび","viernes")),"お金がありません。","おかねがありません。","No tengo dinero."),
    N5KanjiEntry("土","tierra; suelo",listOf("ド","ト"),listOf("つち"),3,listOf(N5KanjiWord("土曜日","どようび","sábado"),N5KanjiWord("土","つち","tierra")),"土曜日は休みです。","どようびはやすみです。","El sábado descanso."),
    N5KanjiEntry("年","año",listOf("ネン"),listOf("とし"),6,listOf(N5KanjiWord("今年","ことし","este año"),N5KanjiWord("去年","きょねん","el año pasado"),N5KanjiWord("来年","らいねん","el año que viene")),"今年、日本語を勉強します。","ことし、にほんごをべんきょうします。","Este año estudio japonés."),
    N5KanjiEntry("時","hora; tiempo",listOf("ジ"),listOf("とき"),10,listOf(N5KanjiWord("時間","じかん","tiempo; duración"),N5KanjiWord("三時","さんじ","las tres")),"今は三時です。","いまはさんじです。","Ahora son las tres."),
    N5KanjiEntry("分","minuto; parte",listOf("ブン","フン","ブ"),listOf("わ"),4,listOf(N5KanjiWord("五分","ごふん","cinco minutos"),N5KanjiWord("十分","じゅっぷん","diez minutos")),"十分休みます。","じゅっぷんやすみます。","Descanso diez minutos."),
    N5KanjiEntry("半","mitad",listOf("ハン"),emptyList(),5,listOf(N5KanjiWord("半分","はんぶん","mitad"),N5KanjiWord("三時半","さんじはん","las tres y media")),"三時半に会いましょう。","さんじはんにあいましょう。","Encontrémonos a las tres y media."),
    N5KanjiEntry("今","ahora",listOf("コン","キン"),listOf("いま"),4,listOf(N5KanjiWord("今","いま","ahora"),N5KanjiWord("今日","きょう","hoy"),N5KanjiWord("今月","こんげつ","este mes")),"今、何時ですか。","いま、なんじですか。","¿Qué hora es ahora?"),
    N5KanjiEntry("人","persona",listOf("ジン","ニン"),listOf("ひと"),2,listOf(N5KanjiWord("人","ひと","persona"),N5KanjiWord("日本人","にほんじん","japonés/a"),N5KanjiWord("三人","さんにん","tres personas")),"あの人は先生です。","あのひとはせんせいです。","Aquella persona es docente."),
    N5KanjiEntry("大","grande",listOf("ダイ","タイ"),listOf("おお"),3,listOf(N5KanjiWord("大学","だいがく","universidad"),N5KanjiWord("大きい","おおきい","grande")),"大学で日本語を勉強します。","だいがくでにほんごをべんきょうします。","Estudio japonés en la universidad."),
    N5KanjiEntry("小","pequeño",listOf("ショウ"),listOf("ちい","こ","お"),3,listOf(N5KanjiWord("小さい","ちいさい","pequeño"),N5KanjiWord("小学校","しょうがっこう","escuela primaria")),"小さい犬がいます。","ちいさいいぬがいます。","Hay un perro pequeño."),
    N5KanjiEntry("中","dentro; medio",listOf("チュウ"),listOf("なか"),4,listOf(N5KanjiWord("中","なか","dentro"),N5KanjiWord("中国","ちゅうごく","China"),N5KanjiWord("一年中","いちねんじゅう","todo el año")),"かばんの中に本があります。","かばんのなかにほんがあります。","Hay un libro dentro del bolso."),
    N5KanjiEntry("上","arriba; encima",listOf("ジョウ"),listOf("うえ","あ","のぼ"),3,listOf(N5KanjiWord("上","うえ","encima"),N5KanjiWord("上手","じょうず","hábil")),"机の上に本があります。","つくえのうえにほんがあります。","Hay un libro encima del escritorio."),
    N5KanjiEntry("下","abajo; debajo",listOf("カ","ゲ"),listOf("した","さ","くだ","お"),3,listOf(N5KanjiWord("下","した","debajo"),N5KanjiWord("地下鉄","ちかてつ","metro")),"いすの下にねこがいます。","いすのしたにねこがいます。","Hay un gato debajo de la silla."),
    N5KanjiEntry("山","montaña",listOf("サン"),listOf("やま"),3,listOf(N5KanjiWord("山","やま","montaña"),N5KanjiWord("富士山","ふじさん","monte Fuji")),"あの山は高いです。","あのやまはたかいです。","Aquella montaña es alta."),
    N5KanjiEntry("川","río",listOf("セン"),listOf("かわ"),3,listOf(N5KanjiWord("川","かわ","río"),N5KanjiWord("小川","おがわ","arroyo")),"川の水はきれいです。","かわのみずはきれいです。","El agua del río está limpia."),
    N5KanjiEntry("田","arrozal",listOf("デン"),listOf("た"),5,listOf(N5KanjiWord("田んぼ","たんぼ","arrozal"),N5KanjiWord("山田","やまだ","Yamada (apellido)")),"田んぼに水があります。","たんぼにみずがあります。","Hay agua en el arrozal."),
    N5KanjiEntry("天","cielo",listOf("テン"),listOf("あめ","あま"),4,listOf(N5KanjiWord("天気","てんき","clima"),N5KanjiWord("天国","てんごく","paraíso")),"今日は天気がいいです。","きょうはてんきがいいです。","Hoy hace buen tiempo."),
    N5KanjiEntry("気","energía; ánimo",listOf("キ","ケ"),emptyList(),6,listOf(N5KanjiWord("天気","てんき","clima"),N5KanjiWord("元気","げんき","salud; energía"),N5KanjiWord("人気","にんき","popularidad")),"元気ですか。","げんきですか。","¿Cómo estás?"),
    N5KanjiEntry("雨","lluvia",listOf("ウ"),listOf("あめ","あま"),8,listOf(N5KanjiWord("雨","あめ","lluvia"),N5KanjiWord("大雨","おおあめ","lluvia fuerte")),"今日は雨です。","きょうはあめです。","Hoy llueve."),
    N5KanjiEntry("生","vida; nacer",listOf("セイ","ショウ"),listOf("い","う","なま"),5,listOf(N5KanjiWord("学生","がくせい","estudiante"),N5KanjiWord("先生","せんせい","docente"),N5KanjiWord("生まれる","うまれる","nacer")),"私は学生です。","わたしはがくせいです。","Soy estudiante."),
    N5KanjiEntry("学","estudio; aprender",listOf("ガク"),listOf("まな"),8,listOf(N5KanjiWord("学生","がくせい","estudiante"),N5KanjiWord("学校","がっこう","escuela"),N5KanjiWord("大学","だいがく","universidad")),"学校で日本語を学びます。","がっこうでにほんごをまなびます。","Aprendo japonés en la escuela."),
    N5KanjiEntry("校","escuela",listOf("コウ"),emptyList(),10,listOf(N5KanjiWord("学校","がっこう","escuela"),N5KanjiWord("高校","こうこう","bachillerato")),"学校は八時に始まります。","がっこうははちじにはじまります。","La escuela empieza a las ocho."),
    N5KanjiEntry("先","anterior; delante",listOf("セン"),listOf("さき"),6,listOf(N5KanjiWord("先生","せんせい","docente"),N5KanjiWord("先週","せんしゅう","la semana pasada"),N5KanjiWord("先月","せんげつ","el mes pasado")),"先生は日本人です。","せんせいはにほんじんです。","El docente es japonés."),
    N5KanjiEntry("何","qué; cuál",listOf("カ"),listOf("なに","なん"),7,listOf(N5KanjiWord("何","なに","qué"),N5KanjiWord("何時","なんじ","qué hora"),N5KanjiWord("何人","なんにん","cuántas personas")),"これは何ですか。","これはなんですか。","¿Qué es esto?")

    N5KanjiEntry("女","mujer",listOf("ジョ","ニョ"),listOf("おんな","め"),3,listOf(N5KanjiWord("女","おんな","mujer"),N5KanjiWord("女の人","おんなのひと","mujer"),N5KanjiWord("女の子","おんなのこ","niña")),"女の人は先生です。","おんなのひとはせんせいです。","La mujer es docente."),
    N5KanjiEntry("男","hombre",listOf("ダン","ナン"),listOf("おとこ"),7,listOf(N5KanjiWord("男","おとこ","hombre"),N5KanjiWord("男の人","おとこのひと","hombre"),N5KanjiWord("男の子","おとこのこ","niño")),"男の子がいます。","おとこのこがいます。","Hay un niño."),
    N5KanjiEntry("子","niño; hijo",listOf("シ","ス"),listOf("こ"),3,listOf(N5KanjiWord("子ども","こども","niño; niña"),N5KanjiWord("子どもたち","こどもたち","niños"),N5KanjiWord("女の子","おんなのこ","niña")),"子どもが三人います。","こどもがさんにんいます。","Hay tres niños."),
    N5KanjiEntry("父","padre",listOf("フ"),listOf("ちち"),4,listOf(N5KanjiWord("父","ちち","mi padre"),N5KanjiWord("お父さん","おとうさん","padre (forma respetuosa)")),"父は会社員です。","ちちはかいしゃいんです。","Mi padre trabaja en una empresa."),
    N5KanjiEntry("母","madre",listOf("ボ"),listOf("はは"),5,listOf(N5KanjiWord("母","はは","mi madre"),N5KanjiWord("お母さん","おかあさん","madre (forma respetuosa)")),"母は料理をします。","はははりょうりをします。","Mi madre cocina."),
    N5KanjiEntry("友","amigo",listOf("ユウ"),listOf("とも"),4,listOf(N5KanjiWord("友だち","ともだち","amigo/a"),N5KanjiWord("友人","ゆうじん","amigo/a")),"友だちと学校へ行きます。","ともだちとがっこうへいきます。","Voy a la escuela con un amigo."),
    N5KanjiEntry("名","nombre; fama",listOf("メイ","ミョウ"),listOf("な"),6,listOf(N5KanjiWord("名前","なまえ","nombre"),N5KanjiWord("有名","ゆうめい","famoso/a")),"名前を書いてください。","なまえをかいてください。","Escribe tu nombre, por favor."),
    N5KanjiEntry("本","libro; origen",listOf("ホン"),listOf("もと"),5,listOf(N5KanjiWord("本","ほん","libro"),N5KanjiWord("日本","にほん","Japón"),N5KanjiWord("本当","ほんとう","verdad")),"これは日本語の本です。","これはにほんごのほんです。","Este es un libro de japonés."),
    N5KanjiEntry("語","idioma; palabra",listOf("ゴ"),listOf("かた"),14,listOf(N5KanjiWord("日本語","にほんご","idioma japonés"),N5KanjiWord("英語","えいご","idioma inglés"),N5KanjiWord("外国語","がいこくご","idioma extranjero")),"日本語を勉強します。","にほんごをべんきょうします。","Estudio japonés."),
    N5KanjiEntry("国","país",listOf("コク"),listOf("くに"),8,listOf(N5KanjiWord("国","くに","país"),N5KanjiWord("外国","がいこく","país extranjero"),N5KanjiWord("中国","ちゅうごく","China")),"この国は広いです。","このくにはひろいです。","Este país es grande."),
    N5KanjiEntry("読","leer",listOf("ドク","トク","トウ"),listOf("よ"),14,listOf(N5KanjiWord("読む","よむ","leer"),N5KanjiWord("読書","どくしょ","lectura"),N5KanjiWord("読み方","よみかた","forma de leer")),"本を読みます。","ほんをよみます。","Leo un libro."),
    N5KanjiEntry("書","escribir",listOf("ショ"),listOf("か"),10,listOf(N5KanjiWord("書く","かく","escribir"),N5KanjiWord("辞書","じしょ","diccionario"),N5KanjiWord("読書","どくしょ","lectura")),"手紙を書きます。","てがみをかきます。","Escribo una carta."),
    N5KanjiEntry("見","ver; mirar",listOf("ケン"),listOf("み"),7,listOf(N5KanjiWord("見る","みる","ver"),N5KanjiWord("見せる","みせる","mostrar"),N5KanjiWord("花見","はなみ","contemplación de flores")),"映画を見ます。","えいがをみます。","Veo una película."),
    N5KanjiEntry("聞","oír; escuchar; preguntar",listOf("ブン","モン"),listOf("き"),14,listOf(N5KanjiWord("聞く","きく","escuchar; preguntar"),N5KanjiWord("新聞","しんぶん","periódico"),N5KanjiWord("聞こえる","きこえる","oírse")),"先生の話を聞きます。","せんせいのはなしをききます。","Escucho al docente."),
    N5KanjiEntry("話","hablar; conversación",listOf("ワ"),listOf("はな"),13,listOf(N5KanjiWord("話す","はなす","hablar"),N5KanjiWord("電話","でんわ","teléfono"),N5KanjiWord("会話","かいわ","conversación")),"友だちと話します。","ともだちとはなします。","Hablo con un amigo."),
    N5KanjiEntry("食","comer; comida",listOf("ショク","ジキ"),listOf("た"),9,listOf(N5KanjiWord("食べる","たべる","comer"),N5KanjiWord("食事","しょくじ","comida; comida formal"),N5KanjiWord("食べ物","たべもの","alimento")),"朝ご飯を食べます。","あさごはんをたべます。","Desayuno."),
    N5KanjiEntry("飲","beber",listOf("イン"),listOf("の"),12,listOf(N5KanjiWord("飲む","のむ","beber"),N5KanjiWord("飲み物","のみもの","bebida"),N5KanjiWord("飲食","いんしょく","comida y bebida")),"水を飲みます。","みずをのみます。","Bebo agua."),
    N5KanjiEntry("行","ir; realizar",listOf("コウ","ギョウ","アン"),listOf("い","ゆ","おこな"),6,listOf(N5KanjiWord("行く","いく","ir"),N5KanjiWord("銀行","ぎんこう","banco"),N5KanjiWord("旅行","りょこう","viaje")),"学校へ行きます。","がっこうへいきます。","Voy a la escuela."),
    N5KanjiEntry("来","venir; próximo",listOf("ライ"),listOf("く","き","こ"),7,listOf(N5KanjiWord("来る","くる","venir"),N5KanjiWord("来週","らいしゅう","la semana que viene"),N5KanjiWord("来年","らいねん","el año que viene")),"友だちが家に来ます。","ともだちがいえにきます。","Un amigo viene a casa."),
    N5KanjiEntry("帰","volver; regresar",listOf("キ"),listOf("かえ"),10,listOf(N5KanjiWord("帰る","かえる","volver a casa"),N5KanjiWord("帰国","きこく","regreso al país")),"家に帰ります。","いえにかえります。","Vuelvo a casa."),
    N5KanjiEntry("休","descansar; día libre",listOf("キュウ"),listOf("やす"),6,listOf(N5KanjiWord("休む","やすむ","descansar"),N5KanjiWord("休日","きゅうじつ","día libre"),N5KanjiWord("夏休み","なつやすみ","vacaciones de verano")),"日曜日は休みます。","にちようびはやすみます。","Descanso los domingos.")
)

internal fun n5KanjiById(character: String): N5KanjiEntry? =
    n5KanjiCatalog.firstOrNull { it.character == character }
