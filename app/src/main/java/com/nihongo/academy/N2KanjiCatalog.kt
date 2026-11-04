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
    N2KanjiEntry("曖", "ambiguo; poco claro", listOf("アイ"), listOf(), 17, listOf(N2KanjiWord("曖昧", "あいまい", "ambiguo; poco claro"), N2KanjiWord("曖昧さ", "あいまいさ", "ambigüedad")), "彼の説明は曖昧です。", "かれのせつめいはあいまいです。", "Su explicación es ambigua."),
    N2KanjiEntry("握", "agarrar; sujetar; empuñar", listOf("アク"), listOf("にぎ.る"), 12, listOf(N2KanjiWord("握手", "あくしゅ", "apretón de manos"), N2KanjiWord("握る", "にぎる", "agarrar; empuñar")), "私たちは握手をしました。", "わたしたちはあくしゅをしました。", "Nos dimos la mano."),
    N2KanjiEntry("扱", "manejar; tratar; ocuparse de", listOf("ソウ"), listOf("あつか.う"), 6, listOf(N2KanjiWord("扱う", "あつかう", "manejar; tratar"), N2KanjiWord("扱い", "あつかい", "trato; manejo")), "この店は古い本を扱っています。", "このみせはふるいほんをあつかっています。", "Esta tienda trabaja con libros antiguos."),
    N2KanjiEntry("依", "depender de; confiar en", listOf("イ", "エ"), listOf(), 8, listOf(N2KanjiWord("依頼", "いらい", "solicitud; encargo"), N2KanjiWord("依存", "いぞん", "dependencia")), "友人に仕事を依頼しました。", "ゆうじんにしごとをいらいしました。", "Le encargué un trabajo a un amigo."),
    N2KanjiEntry("威", "autoridad; intimidar; imponerse", listOf("イ"), listOf("おど.す"), 9, listOf(N2KanjiWord("威力", "いりょく", "potencia; fuerza"), N2KanjiWord("威圧", "いあつ", "intimidación")), "その道具は強い威力があります。", "そのどうぐはつよいいりょくがあります。", "Esa herramienta tiene mucha potencia."),
    N2KanjiEntry("為", "hacer; realizar; beneficio", listOf("イ"), listOf("ため", "な.る", "な.す"), 9, listOf(N2KanjiWord("為替", "かわせ", "tipo de cambio"), N2KanjiWord("行為", "こうい", "acto; conducta")), "為替レートを確認します。", "かわせレートをかくにんします。", "Voy a comprobar el tipo de cambio."),
    N2KanjiEntry("維", "mantener; conservar; fibra", listOf("イ"), listOf(), 14, listOf(N2KanjiWord("維持", "いじ", "mantenimiento; conservación"), N2KanjiWord("維新", "いしん", "restauración; renovación")), "健康を維持したいです。", "けんこうをいじしたいです。", "Quiero mantener mi salud."),
    N2KanjiEntry("緯", "latitud; trama; dirección horizontal", listOf("イ"), listOf("よこいと"), 16, listOf(N2KanjiWord("緯度", "いど", "latitud"), N2KanjiWord("経緯", "けいい", "circunstancias; proceso")), "地図で緯度を調べました。", "ちずでいどをしらべました。", "Busqué la latitud en el mapa."),
    N2KanjiEntry("遺", "dejar atrás; legado; heredar", listOf("イ", "ユイ"), listOf(), 15, listOf(N2KanjiWord("遺産", "いさん", "herencia; patrimonio"), N2KanjiWord("遺伝", "いでん", "herencia genética")), "祖父の遺産を受け継ぎました。", "そふのいさんをうけつぎました。", "Heredé el patrimonio de mi abuelo."),
    N2KanjiEntry("域", "región; territorio; zona", listOf("イキ"), listOf(), 11, listOf(N2KanjiWord("地域", "ちいき", "región; zona"), N2KanjiWord("区域", "くいき", "área; sector")), "この地域はとても静かです。", "このちいきはとてもしずかです。", "Esta región es muy tranquila."),
    N2KanjiEntry("宇", "espacio; universo; alero", listOf("ウ"), listOf(), 6, listOf(N2KanjiWord("宇宙", "うちゅう", "universo; espacio"), N2KanjiWord("宇宙船", "うちゅうせん", "nave espacial")), "私は宇宙に興味があります。", "わたしはうちゅうにきょうみがあります。", "Me interesa el espacio."),
    N2KanjiEntry("浦", "bahía; ensenada; costa", listOf("ホ"), listOf("うら"), 10, listOf(N2KanjiWord("浦", "うら", "ensenada; pequeña bahía"), N2KanjiWord("浦島太郎", "うらしまたろう", "Urashima Tarō; personaje folclórico")), "小さな浦に船が集まっています。", "ちいさなうらにふねがあつまっています。", "Los barcos se reúnen en una pequeña ensenada."),
    N2KanjiEntry("影", "sombra; silueta; reflejo", listOf("エイ"), listOf("かげ"), 15, listOf(N2KanjiWord("影響", "えいきょう", "influencia; impacto"), N2KanjiWord("面影", "おもかげ", "recuerdo; rasgos que permanecen")), "そのニュースは社会に大きな影響を与えました。", "そのニュースはしゃかいにおおきなえいきょうをあたえました。", "Esa noticia tuvo un gran impacto en la sociedad."),
    N2KanjiEntry("鋭", "afilado; agudo; perspicaz", listOf("エイ"), listOf("するど.い"), 15, listOf(N2KanjiWord("鋭い", "するどい", "agudo; perspicaz"), N2KanjiWord("鋭角", "えいかく", "ángulo agudo")), "彼は鋭い質問をしました。", "かれはするどいしつもんをしました。", "Él hizo una pregunta incisiva."),
    N2KanjiEntry("疫", "epidemia; enfermedad contagiosa", listOf("エキ", "ヤク"), listOf(), 9, listOf(N2KanjiWord("疫病", "えきびょう", "epidemia"), N2KanjiWord("疫学", "えきがく", "epidemiología")), "疫病の予防は大切です。", "えきびょうのよぼうはたいせつです。", "La prevención de epidemias es importante."),
    N2KanjiEntry("閲", "examinar; revisar; inspeccionar", listOf("エツ"), listOf(), 15, listOf(N2KanjiWord("閲覧", "えつらん", "consulta; visualización"), N2KanjiWord("閲読", "えつどく", "lectura y revisión")), "資料を閲覧してください。", "しりょうをえつらんしてください。", "Por favor, consulta el material."),
    N2KanjiEntry("延", "prolongar; extender; aplazar", listOf("エン"), listOf("の.びる", "の.べる", "の.ばす"), 8, listOf(N2KanjiWord("延期", "えんき", "aplazamiento"), N2KanjiWord("延長", "えんちょう", "extensión; prórroga")), "会議は来週に延期されました。", "かいぎはらいしゅうにえんきされました。", "La reunión se aplazó hasta la próxima semana."),
    N2KanjiEntry("沿", "bordear; seguir; a lo largo de", listOf("エン"), listOf("そ.う"), 8, listOf(N2KanjiWord("沿線", "えんせん", "a lo largo de una línea ferroviaria"), N2KanjiWord("沿岸", "えんがん", "costa; litoral")), "この沿線には駅が多いです。", "このえんせんにはえきがおおいです。", "Hay muchas estaciones a lo largo de esta línea."),
    N2KanjiEntry("縁", "vínculo; relación; borde; destino", listOf("エン"), listOf("ふち", "ゆかり", "よすが"), 15, listOf(N2KanjiWord("縁側", "えんがわ", "galería de madera junto al jardín"), N2KanjiWord("縁結び", "えんむすび", "unión de destinos; emparejamiento")), "友人との縁を大切にします。", "ゆうじんとのえんをたいせつにします。", "Valoro el vínculo con mis amigos."),
    N2KanjiEntry("汚", "sucio; ensuciar; contaminar", listOf("オ"), listOf("けが.す", "けが.れる", "よご.す", "よご.れる", "きたな.い"), 6, listOf(N2KanjiWord("汚染", "おせん", "contaminación"), N2KanjiWord("汚れ", "よごれ", "suciedad; mancha")), "川の汚染を防ぎましょう。", "かわのおせんをふせぎましょう。", "Evitemos la contaminación del río."),
    N2KanjiEntry("奥", "interior; fondo; profundo", listOf("オウ"), listOf("おく"), 12, listOf(N2KanjiWord("奥様", "おくさま", "esposa; tratamiento cortés"), N2KanjiWord("奥深い", "おくぶかい", "profundo; recóndito")), "家の奥に部屋があります。", "いえのおくにへやがあります。", "Hay una habitación al fondo de la casa."),
    N2KanjiEntry("憶", "recordar; memoria; recuerdo", listOf("オク"), listOf(), 16, listOf(N2KanjiWord("記憶", "きおく", "memoria; recuerdo"), N2KanjiWord("憶測", "おくそく", "conjetura; especulación")), "その日のことをよく記憶しています。", "そのひのことをよくきおくしています。", "Recuerdo bien lo que pasó ese día."),
    N2KanjiEntry("乙", "segundo; extraño; elegante", listOf("オツ", "イツ"), listOf("おと", "きのと"), 1, listOf(N2KanjiWord("乙女", "おとめ", "doncella; joven mujer"), N2KanjiWord("乙女座", "おとめざ", "constelación de Virgo")), "妹は乙女座です。", "いもうとはおとめざです。", "Mi hermana menor es Virgo."),
    N2KanjiEntry("卸", "venta al por mayor; descargar", listOf("シャ"), listOf("おろ.す", "おろし"), 9, listOf(N2KanjiWord("卸売", "おろしうり", "venta al por mayor"), N2KanjiWord("卸す", "おろす", "vender al por mayor; descargar")), "この会社は食品を卸しています。", "このかいしゃはしょくひんをおろしています。", "Esta empresa distribuye alimentos al por mayor."),
    N2KanjiEntry("穏", "tranquilo; apacible; sereno", listOf("オン"), listOf("おだ.やか"), 16, listOf(N2KanjiWord("穏やか", "おだやか", "tranquilo; apacible"), N2KanjiWord("平穏", "へいおん", "paz; tranquilidad")), "今日は穏やかな天気です。", "きょうはおだやかなてんきです。", "Hoy hace un tiempo tranquilo."),
    N2KanjiEntry("佳", "excelente; hermoso; bueno", listOf("カ"), listOf(), 8, listOf(N2KanjiWord("佳作", "かさく", "obra meritoria; mención honorífica"), N2KanjiWord("佳人", "かじん", "persona hermosa")), "彼の作品は佳作に選ばれました。", "かれのさくひんはかさくにえらばれました。", "Su obra fue seleccionada para una mención honorífica."),
    N2KanjiEntry("架", "construir; tender; estantería", listOf("カ"), listOf("か.ける", "か.かる"), 9, listOf(N2KanjiWord("架空", "かくう", "ficticio; imaginario"), N2KanjiWord("架ける", "かける", "tender; construir un puente")), "これは架空の話です。", "これはかくうのはなしです。", "Esta es una historia ficticia."),
    N2KanjiEntry("菓", "dulce; golosina; confitería", listOf("カ"), listOf(), 11, listOf(N2KanjiWord("菓子", "かし", "dulces; golosinas"), N2KanjiWord("和菓子", "わがし", "dulces tradicionales japoneses")), "日本のお菓子が好きです。", "にほんのおかしがすきです。", "Me gustan los dulces japoneses."),
    N2KanjiEntry("暇", "tiempo libre; ocio; descanso", listOf("カ"), listOf("ひま"), 13, listOf(N2KanjiWord("暇", "ひま", "tiempo libre"), N2KanjiWord("暇つぶし", "ひまつぶし", "pasatiempo; forma de matar el tiempo")), "暇なときに本を読みます。", "ひまなときにほんをよみます。", "Leo libros cuando tengo tiempo libre."),
    N2KanjiEntry("禍", "desgracia; calamidad; desastre", listOf("カ"), listOf("わざわい"), 13, listOf(N2KanjiWord("災禍", "さいか", "calamidad; desastre"), N2KanjiWord("禍根", "かこん", "causa de un problema futuro; semilla de discordia")), "小さな誤解が大きな禍根を残しました。", "ちいさなごかいがおおきなかこんをのこしました。", "Un pequeño malentendido dejó un gran problema para el futuro."),
,

    N2KanjiEntry("壊", "romper; destruir; deteriorarse", listOf("カイ"), listOf("こわ.す", "こわ.れる"), 16, listOf(N2KanjiWord("破壊", "はかい", "destrucción"), N2KanjiWord("壊れる", "こわれる", "romperse; estropearse")), "この時計は壊れています。", "このとけいはこわれています。", "Este reloj está roto."),
    N2KanjiEntry("懐", "nostalgia; bolsillo; añorar", listOf("カイ"), listOf("ふところ", "なつ.かしい", "なつ.く"), 16, listOf(N2KanjiWord("懐かしい", "なつかしい", "nostálgico; añorado"), N2KanjiWord("懐中電灯", "かいちゅうでんとう", "linterna")), "子供のころが懐かしいです。", "こどものころがなつかしいです。", "Extraño los tiempos de mi infancia."),
    N2KanjiEntry("慨", "lamentar; indignación; pesar", listOf("ガイ"), listOf(), 13, listOf(N2KanjiWord("感慨", "かんがい", "emoción profunda"), N2KanjiWord("憤慨", "ふんがい", "indignación")), "その知らせを聞いて深い感慨を覚えました。", "そのしらせをきいてふかいかんがいをおぼえました。", "Al escuchar la noticia, sentí una profunda emoción."),
    N2KanjiEntry("該", "correspondiente; dicho; mencionado", listOf("ガイ"), listOf(), 13, listOf(N2KanjiWord("該当", "がいとう", "correspondiente; aplicable"), N2KanjiWord("該当者", "がいとうしゃ", "persona a quien corresponde")), "この条件に該当する人はいますか。", "このじょうけんにがいとうするひとはいますか。", "¿Hay alguien que cumpla esta condición?"),
    N2KanjiEntry("概", "general; aproximadamente; resumen", listOf("ガイ"), listOf(), 14, listOf(N2KanjiWord("概要", "がいよう", "resumen; descripción general"), N2KanjiWord("概念", "がいねん", "concepto")), "会議の概要を説明します。", "かいぎのがいようをせつめいします。", "Voy a explicar el resumen de la reunión."),
    N2KanjiEntry("垣", "cerca; valla; seto", listOf("エン"), listOf("かき"), 9, listOf(N2KanjiWord("垣根", "かきね", "seto; cerca"), N2KanjiWord("石垣", "いしがき", "muro de piedra")), "家の周りに垣根があります。", "いえのまわりにかきねがあります。", "Hay un seto alrededor de la casa."),
    N2KanjiEntry("核", "núcleo; central; nuclear", listOf("カク"), listOf(), 10, listOf(N2KanjiWord("核心", "かくしん", "núcleo; punto central"), N2KanjiWord("核兵器", "かくへいき", "arma nuclear")), "話の核心を理解しました。", "はなしのかくしんをりかいしました。", "Comprendí el punto central de la conversación."),
    N2KanjiEntry("殻", "cáscara; caparazón; vaina", listOf("カク"), listOf("から"), 11, listOf(N2KanjiWord("貝殻", "かいがら", "concha"), N2KanjiWord("卵の殻", "たまごのから", "cáscara de huevo")), "卵の殻をきれいにむきました。", "たまごのからをきれいにむきました。", "Pelé cuidadosamente la cáscara del huevo."),
    N2KanjiEntry("獲", "capturar; obtener; adquirir", listOf("カク"), listOf("え.る"), 16, listOf(N2KanjiWord("獲得", "かくとく", "adquisición; obtención"), N2KanjiWord("捕獲", "ほかく", "captura")), "チームは優勝を獲得しました。", "チームはゆうしょうをかくとくしました。", "El equipo consiguió el campeonato."),
    N2KanjiEntry("嚇", "amenazar; intimidar", listOf("カク"), listOf(), 17, listOf(N2KanjiWord("威嚇", "いかく", "intimidación; amenaza"), N2KanjiWord("嚇かす", "おどかす", "asustar; intimidar")), "大きな音で鳥を嚇かしてしまいました。", "おおきなおとでとりをおどかしてしまいました。", "Asusté a los pájaros con un ruido fuerte."),
    N2KanjiEntry("較", "comparar; contraste", listOf("カク"), listOf(), 13, listOf(N2KanjiWord("比較", "ひかく", "comparación"), N2KanjiWord("比較的", "ひかくてき", "comparativamente; relativamente")), "二つの案を比較しました。", "ふたつのあんをひかくしました。", "Comparé las dos propuestas."),
    N2KanjiEntry("隔", "separar; intervalo; distancia", listOf("カク"), listOf("へだ.てる", "へだ.たる"), 13, listOf(N2KanjiWord("間隔", "かんかく", "intervalo; distancia"), N2KanjiWord("隔週", "かくしゅう", "cada dos semanas")), "席の間隔を空けてください。", "せきのかんかくをあけてください。", "Por favor, deja espacio entre los asientos."),
    N2KanjiEntry("岳", "montaña alta; pico", listOf("ガク"), listOf("たけ"), 8, listOf(N2KanjiWord("山岳", "さんがく", "montañas; región montañosa"), N2KanjiWord("岳父", "がくふ", "suegro")), "夏に山岳地帯を歩きました。", "なつにさんがくちたいをあるきました。", "En verano caminé por una región montañosa."),
    N2KanjiEntry("潟", "laguna; ensenada", listOf("セキ"), listOf("かた"), 15, listOf(N2KanjiWord("干潟", "ひがた", "marisma; zona intermareal"), N2KanjiWord("新潟", "にいがた", "Niigata")), "新潟県を旅行しました。", "にいがたけんをりょこうしました。", "Viajé por la prefectura de Niigata."),
    N2KanjiEntry("括", "agrupar; resumir; abarcar", listOf("カツ"), listOf("くく.る"), 9, listOf(N2KanjiWord("一括", "いっかつ", "en conjunto; de una vez"), N2KanjiWord("括弧", "かっこ", "paréntesis")), "必要な情報を一括で送ります。", "ひつようなじょうほうをいっかつでおくります。", "Enviaré toda la información necesaria de una sola vez."),
    N2KanjiEntry("喝", "gritar; reprender", listOf("カツ"), listOf(), 11, listOf(N2KanjiWord("喝采", "かっさい", "ovación; aplausos"), N2KanjiWord("喝破", "かっぱ", "denuncia enérgica; afirmar con fuerza")), "観客から大きな喝采が起こりました。", "かんきゃくからおおきなかっさいがおこりました。", "El público estalló en una gran ovación."),
    N2KanjiEntry("渇", "tener sed; secarse", listOf("カツ"), listOf("かわ.く"), 11, listOf(N2KanjiWord("渇く", "かわく", "tener sed; secarse"), N2KanjiWord("渇望", "かつぼう", "anhelo intenso; ansia")), "運動の後で喉が渇きました。", "うんどうのあとでのどがかわきました。", "Después de hacer ejercicio tuve sed."),
    N2KanjiEntry("滑", "resbalar; deslizarse; fluido", listOf("カツ", "コツ"), listOf("すべ.る", "なめ.らか"), 13, listOf(N2KanjiWord("滑る", "すべる", "resbalar; deslizarse"), N2KanjiWord("滑らか", "なめらか", "suave; fluido")), "道が凍って滑りやすいです。", "みちがこおってすべりやすいです。", "El camino está congelado y es fácil resbalar."),
    N2KanjiEntry("褐", "marrón; pardo", listOf("カツ"), listOf(), 13, listOf(N2KanjiWord("褐色", "かっしょく", "marrón; pardo"), N2KanjiWord("褐炭", "かったん", "lignito")), "この鳥は褐色の羽を持っています。", "このとりはかっしょくのはねをもっています。", "Este pájaro tiene plumas marrones."),
    N2KanjiEntry("轄", "control; jurisdicción; supervisar", listOf("カツ"), listOf(), 17, listOf(N2KanjiWord("管轄", "かんかつ", "jurisdicción; competencia"), N2KanjiWord("所轄", "しょかつ", "jurisdicción correspondiente")), "この地域は警察署の管轄です。", "このちいきはけいさつしょのかんかつです。", "Esta zona está bajo la jurisdicción de la comisaría."),
    N2KanjiEntry("且", "además; y también; por ahora", listOf("ショ"), listOf("か.つ"), 5, listOf(N2KanjiWord("且つ", "かつ", "y además; al mismo tiempo"), N2KanjiWord("尚且つ", "なおかつ", "además; todavía más")), "彼は親切で、且つ仕事も速いです。", "かれはしんせつで、かつしごともはやいです。", "Él es amable y, además, trabaja rápido."),
    N2KanjiEntry("釜", "olla; caldero", listOf("フ"), listOf("かま"), 10, listOf(N2KanjiWord("釜飯", "かまめし", "arroz cocido en una olla"), N2KanjiWord("釜", "かま", "olla; caldero")), "釜でご飯を炊きます。", "かまでごはんをたきます。", "Cocino arroz en una olla."),
    N2KanjiEntry("鎌", "hoz; guadaña", listOf("レン"), listOf("かま"), 18, listOf(N2KanjiWord("鎌倉", "かまくら", "Kamakura"), N2KanjiWord("鎌", "かま", "hoz")), "鎌倉は歴史のある町です。", "かまくらはれきしのあるまちです。", "Kamakura es una ciudad con historia."),
    N2KanjiEntry("刈", "segar; cortar hierba", listOf("ガイ"), listOf("か.る"), 4, listOf(N2KanjiWord("草刈り", "くさかり", "corte de hierba"), N2KanjiWord("刈る", "かる", "segar; cortar")), "庭の草を刈りました。", "にわのくさをかりました。", "Corté la hierba del jardín."),
    N2KanjiEntry("甘", "dulce; indulgente; ingenuo", listOf("カン"), listOf("あま.い", "あま.える", "あま.やかす"), 5, listOf(N2KanjiWord("甘い", "あまい", "dulce"), N2KanjiWord("甘酒", "あまざけ", "bebida dulce de arroz fermentado")), "この果物はとても甘いです。", "このくだものはとてもあまいです。", "Esta fruta es muy dulce."),
    N2KanjiEntry("監", "supervisar; vigilar; inspeccionar", listOf("カン"), listOf(), 15, listOf(N2KanjiWord("監督", "かんとく", "director; entrenador"), N2KanjiWord("監視", "かんし", "vigilancia; supervisión")), "監督が選手に指示を出しました。", "かんとくがせんしゅにしじをだしました。", "El entrenador dio instrucciones a los jugadores."),
    N2KanjiEntry("冠", "corona; coronar; primero", listOf("カン"), listOf("かんむり"), 9, listOf(N2KanjiWord("王冠", "おうかん", "corona"), N2KanjiWord("冠婚葬祭", "かんこんそうさい", "ceremonias importantes de la vida")), "博物館に古い王冠が展示されています。", "はくぶつかんにふるいおうかんがてんじされています。", "En el museo se exhibe una corona antigua."),
    N2KanjiEntry("勘", "intuición; percepción; presentimiento", listOf("カン"), listOf(), 11, listOf(N2KanjiWord("勘違い", "かんちがい", "malentendido; equivocación"), N2KanjiWord("勘定", "かんじょう", "cuenta; cálculo")), "私は時間を勘違いしていました。", "わたしはじかんをかんちがいしていました。", "Me había equivocado con la hora."),
    N2KanjiEntry("堪", "soportar; resistir; aguantar", listOf("カン", "タン"), listOf("た.える", "こら.える", "こた.える"), 12, listOf(N2KanjiWord("堪える", "たえる", "soportar; resistir"), N2KanjiWord("堪忍", "かんにん", "paciencia; tolerancia")), "この暑さには堪えられません。", "このあつさにはたえられません。", "No puedo soportar este calor."),
    N2KanjiEntry("喚", "llamar; convocar; gritar", listOf("カン"), listOf("わめ.く"), 12, listOf(N2KanjiWord("召喚", "しょうかん", "convocatoria; citación"), N2KanjiWord("喚起", "かんき", "despertar; suscitar; llamar la atención")), "その事件は注意を喚起しました。", "そのじけんはちゅういをかんきしました。", "Ese incidente llamó la atención."),
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
,
    N2KanjiEntry("担", "asumir; cargar; responsabilizarse", listOf("タン"), listOf("かつ.ぐ", "にな.う"), 8, listOf(N2KanjiWord("担当", "たんとう", "encargarse; estar a cargo"), N2KanjiWord("担う", "になう", "asumir; llevar sobre los hombros")), "私がこの仕事を担当します。", "わたしがこのしごとをたんとうします。", "Yo me encargo de este trabajo."),
    N2KanjiEntry("準", "preparación; estándar; corresponder", listOf("ジュン"), listOf("じゅん.じる", "じゅん.ずる", "なぞら.える", "のり", "ひと.しい", "みずもり"), 13, listOf(N2KanjiWord("準備", "じゅんび", "preparación"), N2KanjiWord("基準", "きじゅん", "criterio; estándar")), "試験の準備をしています。", "しけんのじゅんびをしています。", "Estoy preparando el examen."),
    N2KanjiEntry("賞", "premio; recompensa; elogio", listOf("ショウ"), listOf("ほ.める"), 15, listOf(N2KanjiWord("賞金", "しょうきん", "premio en dinero"), N2KanjiWord("賞品", "しょうひん", "premio; artículo premiado")), "彼は作文で賞をもらいました。", "かれはさくぶんでしょうをもらいました。", "Él recibió un premio por su redacción."),
    N2KanjiEntry("辺", "alrededores; zona; límite", listOf("ヘン"), listOf("あた.り", "ほと.り", "-べ"), 5, listOf(N2KanjiWord("周辺", "しゅうへん", "alrededores"), N2KanjiWord("この辺", "このへん", "por aquí; esta zona")), "駅の周辺はにぎやかです。", "えきのしゅうへんはにぎやかです。", "Los alrededores de la estación son animados."),
    N2KanjiEntry("造", "fabricar; crear; estructura", listOf("ゾウ"), listOf("つく.る", "つく.り", "-づく.り"), 10, listOf(N2KanjiWord("製造", "せいぞう", "fabricación"), N2KanjiWord("構造", "こうぞう", "estructura")), "この工場では車を製造しています。", "このこうじょうではくるまをせいぞうしています。", "En esta fábrica se fabrican automóviles."),
    N2KanjiEntry("被", "sufrir; recibir; cubrir", listOf("ヒ"), listOf("こうむ.る", "おお.う", "かぶ.る", "かぶ.せる"), 10, listOf(N2KanjiWord("被害", "ひがい", "daños; perjuicios"), N2KanjiWord("被る", "こうむる", "sufrir; recibir (un daño)")), "台風で大きな被害が出ました。", "たいふうでおおきなひがいがでました。", "El tifón causó grandes daños."),
    N2KanjiEntry("復", "restaurar; recuperar; volver", listOf("フク"), listOf("また"), 12, listOf(N2KanjiWord("復習", "ふくしゅう", "repaso"), N2KanjiWord("回復", "かいふく", "recuperación")), "毎晩、授業を復習します。", "まいばん、じゅぎょうをふくしゅうします。", "Repaso las clases todas las noches."),
    N2KanjiEntry("課", "sección; lección; tarea", listOf("カ"), listOf(), 15, listOf(N2KanjiWord("課題", "かだい", "tarea; problema asignado"), N2KanjiWord("課長", "かちょう", "jefe de sección")), "今日の課題を提出しました。", "きょうのかだいをていしゅつしました。", "Entregué la tarea de hoy."),
    N2KanjiEntry("脳", "cerebro; mente; memoria", listOf("ノウ", "ドウ"), listOf("のうずる"), 11, listOf(N2KanjiWord("頭脳", "ずのう", "intelecto; cerebro"), N2KanjiWord("脳", "のう", "cerebro")), "脳は体の大切な器官です。", "のうはからだのたいせつなきかんです。", "El cerebro es un órgano importante del cuerpo."),
    N2KanjiEntry("極", "extremo; polo; llevar al máximo", listOf("キョク", "ゴク"), listOf("きわ.める", "きわ.まる", "きわ.まり", "きわ.み", "き.める", "-ぎ.め", "き.まる"), 12, listOf(N2KanjiWord("積極的", "せっきょくてき", "proactivo; positivo"), N2KanjiWord("南極", "なんきょく", "Polo Sur")), "彼女は積極的に質問します。", "かのじょはせっきょくてきにしつもんします。", "Ella hace preguntas activamente."),
    N2KanjiEntry("含", "contener; incluir; abarcar", listOf("ガン"), listOf("ふく.む", "ふく.める"), 7, listOf(N2KanjiWord("含む", "ふくむ", "contener; incluir"), N2KanjiWord("含める", "ふくめる", "incluir")), "この料理は卵を含みます。", "このりょうりはたまごをふくみます。", "Este plato contiene huevo."),
    N2KanjiEntry("蔵", "almacén; guardar; almacenar", listOf("ゾウ", "ソウ"), listOf("くら", "おさ.める", "かく.れる"), 15, listOf(N2KanjiWord("冷蔵庫", "れいぞうこ", "refrigerador"), N2KanjiWord("貯蔵", "ちょぞう", "almacenamiento")), "牛乳を冷蔵庫に入れます。", "ぎゅうにゅうをれいぞうこにいれます。", "Pongo la leche en el refrigerador."),
    N2KanjiEntry("量", "cantidad; medir; volumen", listOf("リョウ"), listOf("はか.る"), 12, listOf(N2KanjiWord("数量", "すうりょう", "cantidad"), N2KanjiWord("測量", "そくりょう", "medición; agrimensura")), "水の量を量ってください。", "みずのりょうをはかってください。", "Por favor, mide la cantidad de agua."),
    N2KanjiEntry("型", "tipo; modelo; molde", listOf("ケイ"), listOf("かた", "-がた"), 9, listOf(N2KanjiWord("新型", "しんがた", "nuevo modelo"), N2KanjiWord("模型", "もけい", "maqueta; modelo")), "これは新型の車です。", "これはしんがたのくるまです。", "Este es un automóvil de nuevo modelo."),
    N2KanjiEntry("況", "situación; condición; estado", listOf("キョウ"), listOf("まし.て", "いわ.んや", "おもむき"), 8, listOf(N2KanjiWord("状況", "じょうきょう", "situación"), N2KanjiWord("不況", "ふきょう", "recesión")), "現在の状況を説明してください。", "げんざいのじょうきょうをせつめいしてください。", "Por favor, explica la situación actual."),
    N2KanjiEntry("針", "aguja; orientación; directriz", listOf("シン"), listOf("はり"), 10, listOf(N2KanjiWord("針", "はり", "aguja"), N2KanjiWord("方針", "ほうしん", "política; directriz")), "時計の針が止まりました。", "とけいのはりがとまりました。", "Las agujas del reloj se detuvieron."),
    N2KanjiEntry("専", "especialidad; exclusivo; principalmente", listOf("セン"), listOf("もっぱ.ら"), 9, listOf(N2KanjiWord("専門", "せんもん", "especialidad"), N2KanjiWord("専用", "せんよう", "uso exclusivo")), "私は日本語を専門に勉強しています。", "わたしはにほんごをせんもんにべんきょうしています。", "Estudio japonés como especialidad."),
    N2KanjiEntry("谷", "valle; barranco", listOf("コク"), listOf("たに", "きわ.まる"), 7, listOf(N2KanjiWord("谷", "たに", "valle"), N2KanjiWord("渓谷", "けいこく", "desfiladero; valle profundo")), "深い谷に川が流れています。", "ふかいたににかわがながれています。", "Un río fluye por un valle profundo."),
    N2KanjiEntry("史", "historia; crónica", listOf("シ"), listOf(), 5, listOf(N2KanjiWord("歴史", "れきし", "historia"), N2KanjiWord("史上", "しじょう", "en la historia")), "日本の歴史を学んでいます。", "にほんのれきしをまなんでいます。", "Estoy estudiando la historia de Japón."),
    N2KanjiEntry("階", "piso; escalón; nivel", listOf("カイ"), listOf("きざはし"), 12, listOf(N2KanjiWord("階段", "かいだん", "escalera"), N2KanjiWord("二階", "にかい", "segundo piso")), "教室は三階にあります。", "きょうしつはさんがいにあります。", "El aula está en el tercer piso."),
    N2KanjiEntry("管", "tubo; administrar; gestionar", listOf("カン"), listOf("くだ"), 14, listOf(N2KanjiWord("管理", "かんり", "administración; gestión"), N2KanjiWord("血管", "けっかん", "vaso sanguíneo")), "この施設を管理しています。", "このしせつをかんりしています。", "Gestionamos estas instalaciones."),
    N2KanjiEntry("兵", "soldado; tropas; fuerzas militares", listOf("ヘイ", "ヒョウ"), listOf("つわもの"), 7, listOf(N2KanjiWord("兵士", "へいし", "soldado"), N2KanjiWord("兵隊", "へいたい", "tropa; soldado")), "兵士たちは町を守りました。", "へいしたちはまちをまもりました。", "Los soldados protegieron la ciudad."),
    N2KanjiEntry("接", "contactar; tocar; conectar", listOf("セツ", "ショウ"), listOf("つ.ぐ"), 11, listOf(N2KanjiWord("直接", "ちょくせつ", "directamente"), N2KanjiWord("接近", "せっきん", "aproximación")), "先生に直接質問しました。", "せんせいにちょくせつしつもんしました。", "Le hice una pregunta directamente al profesor."),
    N2KanjiEntry("細", "delgado; detallado; minucioso", listOf("サイ"), listOf("ほそ.い", "ほそ.る", "こま.か", "こま.かい"), 11, listOf(N2KanjiWord("細かい", "こまかい", "detallado; minucioso"), N2KanjiWord("詳細", "しょうさい", "detalle; información detallada")), "詳細を確認してください。", "しょうさいをかくにんしてください。", "Por favor, comprueba los detalles."),
    N2KanjiEntry("効", "ser eficaz; efecto; eficacia", listOf("コウ"), listOf("き.く", "ききめ", "なら.う"), 8, listOf(N2KanjiWord("効果", "こうか", "efecto"), N2KanjiWord("有効", "ゆうこう", "válido; eficaz")), "この方法はよく効きます。", "このほうほうはよくききます。", "Este método resulta eficaz."),
    N2KanjiEntry("丸", "redondo; círculo; entero", listOf("ガン"), listOf("まる", "まる.める", "まる.い"), 3, listOf(N2KanjiWord("丸い", "まるい", "redondo"), N2KanjiWord("丸ごと", "まるごと", "entero; completo")), "丸いテーブルを買いました。", "まるいテーブルをかいました。", "Compré una mesa redonda."),
    N2KanjiEntry("湾", "bahía; golfo; ensenada", listOf("ワン"), listOf("いりえ"), 12, listOf(N2KanjiWord("東京湾", "とうきょうわん", "bahía de Tokio"), N2KanjiWord("湾岸", "わんがん", "zona costera")), "船が湾に入ります。", "ふねがわんにはいります。", "El barco entra en la bahía."),
    N2KanjiEntry("録", "registrar; grabar; registro", listOf("ロク"), listOf("しる.す", "と.る"), 16, listOf(N2KanjiWord("記録", "きろく", "registro; récord"), N2KanjiWord("録音", "ろくおん", "grabación de audio")), "会議を録音しました。", "かいぎをろくおんしました。", "Grabé la reunión."),
    N2KanjiEntry("省", "ministerio; omitir; reflexionar", listOf("セイ", "ショウ"), listOf("かえり.みる", "はぶ.く"), 9, listOf(N2KanjiWord("反省", "はんせい", "reflexión; reconsideración"), N2KanjiWord("省略", "しょうりゃく", "omisión; abreviación")), "名前を省略しないでください。", "なまえをしょうりゃくしないでください。", "Por favor, no abrevies el nombre."),
    N2KanjiEntry("旧", "antiguo; anterior; viejo", listOf("キュウ"), listOf("ふる.い", "もと"), 5, listOf(N2KanjiWord("旧友", "きゅうゆう", "viejo amigo"), N2KanjiWord("旧式", "きゅうしき", "modelo antiguo")), "旧友に久しぶりに会いました。", "きゅうゆうにひさしぶりにあいました。", "Volví a encontrarme con un viejo amigo después de mucho tiempo.")
)

internal val n2KanjiCharacters: List<String> = n2KanjiCatalog.map { it.character }
