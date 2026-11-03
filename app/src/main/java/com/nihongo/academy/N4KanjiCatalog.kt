package com.nihongo.academy

/** Selección pedagógica propia de kanji frecuentes para N4; no es una lista oficial del JLPT. */
internal data class N4KanjiWord(val word:String,val reading:String,val meaningEs:String)
internal data class N4KanjiEntry(
    val character:String,val meaningEs:String,val onyomi:List<String>,val kunyomi:List<String>,
    val strokeCount:Int,val vocabulary:List<N4KanjiWord>,
    val exampleJa:String,val exampleReading:String,val exampleEs:String
)
private fun n4k(c:String,m:String,on:String,kun:String,s:Int,w:String,wr:String,wm:String,ja:String,jr:String,es:String)=
    N4KanjiEntry(c,m,on.split(" ").filter{it.isNotBlank()},kun.split(" ").filter{it.isNotBlank()},s,
        listOf(N4KanjiWord(w,wr,wm)),ja,jr,es)

internal val n4KanjiCatalog = listOf(
 n4k("会","reunión; encontrarse","カイ","あ.う",6,"会う","あう","encontrarse","駅で友だちに会います。","えきでともだちにあいます。","Me encuentro con un amigo en la estación."),
 n4k("社","empresa; santuario","シャ","やしろ",7,"会社","かいしゃ","empresa","父は会社で働いています。","ちちはかいしゃではたらいています。","Mi padre trabaja en una empresa."),
 n4k("員","miembro; empleado","イン","",10,"会社員","かいしゃいん","empleado de empresa","兄は会社員です。","あにはかいしゃいんです。","Mi hermano mayor es empleado."),
 n4k("者","persona; alguien","シャ","もの",8,"医者","いしゃ","médico","あの人は医者です。","あのひとはいしゃです。","Esa persona es médica."),
 n4k("事","asunto; cosa","ジ ズ","こと",8,"仕事","しごと","trabajo","毎日仕事をします。","まいにちしごとをします。","Trabajo todos los días."),
 n4k("自","uno mismo","ジ シ","みずか.ら",6,"自分","じぶん","uno mismo","自分の名前を書いてください。","じぶんのなまえをかいてください。","Escribí tu propio nombre, por favor."),
 n4k("同","igual; mismo","ドウ","おな.じ",6,"同じ","おなじ","igual; el mismo","私たちは同じ学校です。","わたしたちはおなじがっこうです。","Vamos a la misma escuela."),
 n4k("多","mucho; muchos","タ","おお.い",6,"多い","おおい","muchos","この町は人が多いです。","このまちはひとがおおいです。","Hay mucha gente en esta ciudad."),
 n4k("少","poco; pocos","ショウ","すく.ない すこ.し",4,"少ない","すくない","pocos","今日は時間が少ないです。","きょうはじかんがすくないです。","Hoy tengo poco tiempo."),
 n4k("強","fuerte","キョウ ゴウ","つよ.い つよ.まる",11,"強い","つよい","fuerte","このチームは強いです。","このチームはつよいです。","Este equipo es fuerte."),
 n4k("弱","débil","ジャク","よわ.い",10,"弱い","よわい","débil","私は寒さに弱いです。","わたしはさむさによわいです。","Soy sensible al frío."),
 n4k("早","temprano; rápido","ソウ サッ","はや.い はや.まる",6,"早い","はやい","temprano","毎朝早く起きます。","まいあさはやくおきます。","Me levanto temprano cada mañana."),
 n4k("近","cerca","キン","ちか.い",7,"近い","ちかい","cerca","駅はここから近いです。","えきはここからちかいです。","La estación está cerca de aquí."),
 n4k("遠","lejos","エン オン","とお.い",13,"遠い","とおい","lejos","学校は家から遠いです。","がっこうはいえからとおいです。","La escuela está lejos de casa."),
 n4k("明","claro; brillante","メイ ミョウ","あ.かるい あ.ける あ.くる",8,"明るい","あかるい","luminoso","この部屋は明るいです。","このへやはあかるいです。","Esta habitación es luminosa."),
 n4k("暗","oscuro","アン","くら.い",13,"暗い","くらい","oscuro","夜は道が暗いです。","よるはみちがくらいです。","De noche el camino está oscuro."),
 n4k("新","nuevo","シン","あたら.しい あら.た にい",13,"新しい","あたらしい","nuevo","新しい本を買いました。","あたらしいほんをかいました。","Compré un libro nuevo."),
 n4k("古","antiguo; viejo","コ","ふる.い ふる.す",5,"古い","ふるい","viejo","これは古い写真です。","これはふるいしゃしんです。","Esta es una foto antigua."),
 n4k("長","largo; jefe","チョウ","なが.い",8,"長い","ながい","largo","この道は長いです。","このみちはながいです。","Este camino es largo."),
 n4k("短","corto","タン","みじか.い",12,"短い","みじかい","corto","この映画は短いです。","このえいがはみじかいです。","Esta película es corta."),
 n4k("広","amplio","コウ","ひろ.い ひろ.まる",5,"広い","ひろい","amplio","この公園は広いです。","このこうえんはひろいです。","Este parque es amplio."),
 n4k("重","pesado; importante","ジュウ チョウ","おも.い かさ.ねる",9,"重い","おもい","pesado","このかばんは重いです。","このかばんはおもいです。","Esta mochila es pesada."),
 n4k("軽","ligero","ケイ","かる.い かろ.やか",12,"軽い","かるい","ligero","この箱は軽いです。","このはこはかるいです。","Esta caja es ligera."),
 n4k("低","bajo","テイ","ひく.い ひく.める",7,"低い","ひくい","bajo","このテーブルは低いです。","このテーブルはひくいです。","Esta mesa es baja."),
 n4k("正","correcto; justo","セイ ショウ","ただ.しい まさ",5,"正しい","ただしい","correcto","正しい答えを選んでください。","ただしいこたえをえらんでください。","Elegí la respuesta correcta."),
 n4k("有","tener; existir","ユウ ウ","あ.る",6,"有名","ゆうめい","famoso","この町は有名です。","このまちはゆうめいです。","Esta ciudad es famosa."),
 n4k("無","no tener; nada","ム ブ","な.い",12,"無理","むり","imposible; excesivo","無理をしないでください。","むりをしないでください。","No te esfuerces demasiado."),
 n4k("開","abrir","カイ","ひら.く あ.く あ.ける",12,"開ける","あける","abrir","窓を開けてください。","まどをあけてください。","Abrí la ventana, por favor."),
 n4k("閉","cerrar","ヘイ","し.める し.まる と.じる",11,"閉める","しめる","cerrar","ドアを閉めてください。","ドアをしめてください。","Cerrá la puerta, por favor."),
 n4k("始","comenzar","シ","はじ.める はじ.まる",8,"始まる","はじまる","comenzar","授業は九時に始まります。","じゅぎょうはくじにはじまります。","La clase comienza a las nueve."),
 n4k("終","terminar","シュウ","お.わる お.える",11,"終わる","おわる","terminar","仕事は五時に終わります。","しごとはごじにおわります。","El trabajo termina a las cinco."),
 n4k("送","enviar; acompañar","ソウ","おく.る",9,"送る","おくる","enviar","友だちにメールを送ります。","ともだちにメールをおくります。","Le envío un correo a un amigo."),
 n4k("持","tener; sostener","ジ","も.つ",9,"持つ","もつ","sostener","この本を持ってください。","このほんをもってください。","Sostené este libro, por favor."),
 n4k("待","esperar","タイ","ま.つ",9,"待つ","まつ","esperar","駅で少し待ちます。","えきですこしまちます。","Espero un poco en la estación."),
 n4k("貸","prestar","タイ","か.す",12,"貸す","かす","prestar","友だちに傘を貸しました。","ともだちにかさをかしました。","Le presté un paraguas a un amigo."),
 n4k("借","pedir prestado","シャク","か.りる",10,"借りる","かりる","pedir prestado","図書館で本を借ります。","としょかんでほんをかります。","Pido libros prestados en la biblioteca."),
 n4k("使","usar","シ","つか.う",8,"使う","つかう","usar","毎日この辞書を使います。","まいにちこのじしょをつかいます。","Uso este diccionario todos los días."),
 n4k("作","hacer; fabricar","サク サ","つく.る",7,"作る","つくる","hacer","母は夕食を作ります。","はははゆうしょくをつくります。","Mi madre prepara la cena."),
 n4k("働","trabajar","ドウ","はたら.く",13,"働く","はたらく","trabajar","兄は銀行で働いています。","あにはぎんこうではたらいています。","Mi hermano trabaja en un banco."),
 n4k("考","pensar; considerar","コウ","かんが.える",6,"考える","かんがえる","pensar","答えをよく考えてください。","こたえをよくかんがえてください。","Pensá bien la respuesta, por favor."),
 n4k("知","saber; conocer","チ","し.る",8,"知る","しる","saber; conocer","そのことを知っています。","そのことをしっています。","Sé eso."),
 n4k("思","pensar; sentir","シ","おも.う",9,"思う","おもう","pensar","日本は美しいと思います。","にほんはうつくしいとおもいます。","Pienso que Japón es hermoso."),
 n4k("言","decir; palabra","ゲン ゴン","い.う こと",7,"言う","いう","decir","本当のことを言ってください。","ほんとうのことをいってください。","Decí la verdad, por favor."),
 n4k("教","enseñar","キョウ","おし.える おそ.わる",11,"教える","おしえる","enseñar","先生は日本語を教えます。","せんせいはにほんごをおしえます。","El profesor enseña japonés."),
 n4k("習","aprender; practicar","シュウ","なら.う",11,"習う","ならう","aprender","週末にピアノを習います。","しゅうまつにピアノをならいます。","Estudio piano los fines de semana."),
 n4k("試","probar; examen","シ","こころ.みる ため.す",13,"試験","しけん","examen","明日は日本語の試験があります。","あしたはにほんごのしけんがあります。","Mañana tengo un examen de japonés."),
 n4k("験","prueba; experiencia","ケン ゲン","",18,"試験","しけん","examen","試験の前に復習します。","しけんのまえにふくしゅうします。","Repaso antes del examen."),
 n4k("答","respuesta","トウ","こた.える こた.え",12,"答える","こたえる","responder","質問に答えてください。","しつもんにこたえてください。","Respondé la pregunta, por favor."),
 n4k("問","pregunta; problema","モン","と.う と.い",11,"質問","しつもん","pregunta","先生に質問があります。","せんせいにしつもんがあります。","Tengo una pregunta para el profesor."),
 n4k("題","tema; asunto","ダイ","",18,"問題","もんだい","problema; pregunta","この問題は難しいです。","このもんだいはむずかしいです。","Este problema es difícil.")

 ,n4k("止","detener; parar","シ","と.まる と.める",4,"止まる","とまる","detenerse","ここで止まってください。","ここでとまってください。","Detente aquí, por favor.")
 ,n4k("好","gustar; agradable","コウ","この.む す.く",6,"好き","すき","gustar","日本語が好きです。","にほんごがすきです。","Me gusta el japonés.")
 ,n4k("牛","vaca; ganado","ギュウ","うし",4,"牛肉","ぎゅうにく","carne de vaca","牛肉を買いました。","ぎゅうにくをかいました。","Compré carne de vaca.")
 ,n4k("夕","tarde; atardecer","セキ","ゆう",3,"夕方","ゆうがた","atardecer","夕方に散歩します。","ゆうがたにさんぽします。","Paseo al atardecer.")
 ,n4k("文","texto; escritura","ブン モン","ふみ あや",4,"作文","さくぶん","redacción","日本語で作文を書きます。","にほんごでさくぶんをかきます。","Escribo una redacción en japonés.")
 ,n4k("村","pueblo; aldea","ソン","むら",7,"村","むら","pueblo","祖父は小さい村に住んでいます。","そふはちいさいむらにすんでいます。","Mi abuelo vive en un pueblo pequeño.")
 ,n4k("町","ciudad; barrio","チョウ","まち",7,"町","まち","ciudad","この町は静かです。","このまちはしずかです。","Esta ciudad es tranquila.")
 ,n4k("工","construcción; industria","コウ ク","",3,"工場","こうじょう","fábrica","父は工場で働いています。","ちちはこうじょうではたらいています。","Mi padre trabaja en una fábrica.")
 ,n4k("字","letra; carácter","ジ","あざ あざな",6,"漢字","かんじ","kanji","この漢字を書いてください。","このかんじをかいてください。","Escribe este kanji, por favor.")
 ,n4k("音","sonido","オン イン","おと ね",9,"音楽","おんがく","música","音楽を聞くのが好きです。","おんがくをきくのがすきです。","Me gusta escuchar música.")
 ,n4k("林","bosquecillo","リン","はやし",8,"林","はやし","bosquecillo","林の中を歩きました。","はやしのなかをあるきました。","Caminé por el bosquecillo.")
 ,n4k("森","bosque","シン","もり",12,"森","もり","bosque","森には鳥がたくさんいます。","もりにはとりがたくさんいます。","Hay muchos pájaros en el bosque.")
 ,n4k("門","puerta; portón","モン","かど",8,"専門","せんもん","especialidad","私の専門は日本語です。","わたしのせんもんはにほんごです。","Mi especialidad es el japonés.")
 ,n4k("地","tierra; suelo","チ ジ","",6,"地下","ちか","subterráneo","地下に店があります。","ちかにみせがあります。","Hay una tienda en el subsuelo.")
 ,n4k("合","encajar; coincidir","ゴウ ガッ","あ.う あ.わせる",6,"合う","あう","encajar; coincidir","この靴は私の足に合います。","このくつはわたしのあしにあいます。","Estos zapatos me quedan bien.")
 ,n4k("市","ciudad; mercado","シ","いち",5,"市役所","しやくしょ","ayuntamiento","市役所へ行きます。","しやくしょへいきます。","Voy al ayuntamiento.")
 ,n4k("方","dirección; manera","ホウ","かた",4,"方法","ほうほう","método","この方法を使ってください。","このほうほうをつかってください。","Usa este método, por favor.")
 ,n4k("回","vez; vuelta","カイ エ","まわ.る まわ.す",6,"一回","いっかい","una vez","この本を三回読みました。","このほんをさんかいよみました。","Leí este libro tres veces.")
 ,n4k("場","lugar; espacio","ジョウ チョウ","ば",12,"場所","ばしょ","lugar","待ち合わせの場所を決めましょう。","まちあわせのばしょをきめましょう。","Decidamos dónde encontrarnos.")
 ,n4k("京","capital","キョウ ケイ","みやこ",8,"東京","とうきょう","Tokio","東京に住んでいます。","とうきょうにすんでいます。","Vivo en Tokio.")
 ,n4k("通","pasar; atravesar","ツウ ツ","とお.る とお.す かよ.う",10,"通学","つうがく","ir a la escuela","毎日電車で通学します。","まいにちでんしゃでつうがくします。","Voy a la escuela en tren todos los días.")
 ,n4k("理","razón; lógica","リ","ことわり",11,"理由","りゆう","razón","遅れた理由を話してください。","おくれたりゆうをはなしてください。","Explica por qué llegaste tarde.")
 ,n4k("体","cuerpo","タイ テイ","からだ",7,"体","からだ","cuerpo","毎日体を動かします。","まいにちからだをうごかします。","Muevo el cuerpo todos los días.")
 ,n4k("首","cuello","シュ","くび",9,"首","くび","cuello","首が少し痛いです。","くびがすこしいたいです。","Me duele un poco el cuello.")
 ,n4k("用","uso; asunto","ヨウ","もち.いる",5,"用事","ようじ","asunto","今日は大切な用事があります。","きょうはたいせつなようじがあります。","Hoy tengo un asunto importante.")
 ,n4k("野","campo; llanura","ヤ ショ","の",11,"野菜","やさい","verduras","毎日野菜を食べます。","まいにちやさいをたべます。","Como verduras todos los días.")
 ,n4k("心","corazón; mente","シン","こころ",4,"安心","あんしん","tranquilidad","母の声を聞いて安心しました。","ははのこえをきいてあんしんしました。","Me tranquilicé al escuchar la voz de mi madre.")
 ,n4k("家","casa; familia","カ ケ","いえ や",10,"家族","かぞく","familia","家族と旅行します。","かぞくとりょこうします。","Viajo con mi familia.")
 ,n4k("元","origen; comienzo","ゲン ガン","もと",4,"元気","げんき","salud; energía","今日は元気です。","きょうはげんきです。","Hoy estoy bien.")
 ,n4k("画","dibujo; trazo","ガ カク","",8,"映画","えいが","película","週末に映画を見ます。","しゅうまつにえいがをみます。","Veo una película el fin de semana.")
 ,n4k("売","vender","バイ","う.る う.れる",7,"売り場","うりば","sector de ventas","この店は本を売っています。","このみせはほんをうっています。","Esta tienda vende libros.")
 ,n4k("引","tirar; atraer","イン","ひ.く ひ.ける",4,"引く","ひく","tirar","ドアを引いてください。","ドアをひいてください。","Tira de la puerta, por favor.")
 ,n4k("計","medir; contar","ケイ","はか.る はか.らう",9,"時計","とけい","reloj","時計を見てください。","とけいをみてください。","Mira el reloj, por favor.")
 ,n4k("台","soporte; contador","ダイ タイ","",5,"台所","だいどころ","cocina","母は台所にいます。","はははだいどころにいます。","Mi madre está en la cocina.")
 ,n4k("切","cortar; importante","セツ サイ","き.る き.れる",4,"切符","きっぷ","boleto","駅で切符を買いました。","えきできっぷをかいました。","Compré un boleto en la estación.")
 ,n4k("楽","divertido; música","ガク ラク","たの.しい たの.しむ",13,"音楽","おんがく","música","音楽を聞くと楽しいです。","おんがくをきくとたのしいです。","Escuchar música es divertido.")
 ,n4k("声","voz","セイ ショウ","こえ",7,"声","こえ","voz","先生の声が聞こえます。","せんせいのこえがきこえます。","Se escucha la voz del profesor.")
 ,n4k("親","padre; cercano","シン","おや した.しい",16,"両親","りょうしん","padres","両親に電話をしました。","りょうしんにでんわをしました。","Llamé por teléfono a mis padres.")
 ,n4k("頭","cabeza","トウ ズ","あたま かしら",16,"頭","あたま","cabeza","頭が痛いです。","あたまがいたいです。","Me duele la cabeza.")
 ,n4k("歌","canción; cantar","カ","うた うた.う",14,"歌う","うたう","cantar","友だちと歌を歌います。","ともだちとうたをうたいます。","Canto una canción con un amigo.")
 ,n4k("光","luz; brillar","コウ","ひか.る ひかり",6,"光","ひかり","luz","窓から光が入ります。","まどからひかりがはいります。","La luz entra por la ventana.")
 ,n4k("図","dibujo; plano","ズ ト","え はか.る",7,"地図","ちず","mapa","地図を見て駅を探します。","ちずをみてえきをさがします。","Busco la estación mirando el mapa.")
 ,n4k("室","habitación; sala","シツ","むろ",9,"教室","きょうしつ","aula","教室で日本語を勉強します。","きょうしつでにほんごをべんきょうします。","Estudio japonés en el aula.")
 ,n4k("太","grueso; gordo","タイ タ","ふと.い ふと.る",4,"太い","ふとい","grueso","この木はとても太いです。","このきはとてもふといです。","Este árbol es muy grueso.")
 ,n4k("歩","caminar; paso","ホ ブ フ","ある.く あゆ.む",8,"歩く","あるく","caminar","駅まで歩きます。","えきまであるきます。","Camino hasta la estación.")
 ,n4k("風","viento","フウ フ","かぜ かざ",9,"風","かぜ","viento","今日は風が強いです。","きょうはかぜがつよいです。","Hoy el viento es fuerte.")
 ,n4k("紙","papel","シ","かみ",10,"手紙","てがみ","carta","友だちに手紙を書きます。","ともだちにてがみをかきます。","Escribo una carta a un amigo.")
 ,n4k("春","primavera","シュン","はる",9,"春","はる","primavera","春に桜が咲きます。","はるにさくらがさきます。","En primavera florecen los cerezos.")
 ,n4k("色","color","ショク シキ","いろ",6,"色","いろ","color","好きな色は何ですか。","すきないろはなんですか。","¿Cuál es tu color favorito?")
 ,n4k("走","correr","ソウ","はし.る",7,"走る","はしる","correr","毎朝公園を走ります。","まいあさこうえんをはしります。","Corro por el parque todas las mañanas.")

)
