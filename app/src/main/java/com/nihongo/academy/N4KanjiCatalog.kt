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


 ,n4k("秋","otoño","シュウ","あき",9,"秋","あき","otoño","秋に日本へ旅行します。","あきににほんへりょこうします。","Viajo a Japón en otoño.")
 ,n4k("夏","verano","カ ガ","なつ",10,"夏休み","なつやすみ","vacaciones de verano","夏休みに海へ行きます。","なつやすみにうみへいきます。","Voy al mar durante las vacaciones de verano.")
 ,n4k("顔","cara; rostro","ガン","かお",18,"顔","かお","cara","友だちの顔を覚えています。","ともだちのかおをおぼえています。","Recuerdo la cara de mi amigo.")
 ,n4k("池","estanque","チ","いけ",6,"池","いけ","estanque","公園に大きな池があります。","こうえんにおおきないけがあります。","Hay un estanque grande en el parque.")
 ,n4k("曜","día de la semana","ヨウ","",18,"曜日","ようび","día de la semana","今日は何曜日ですか。","きょうはなんようびですか。","¿Qué día de la semana es hoy?")
 ,n4k("肉","carne","ニク","しし",6,"肉","にく","carne","肉と野菜を買いました。","にくとやさいをかいました。","Compré carne y verduras.")
 ,n4k("鳥","pájaro; ave","チョウ","とり",11,"鳥","とり","pájaro","朝、鳥の声を聞きました。","あさ、とりのこえをききました。","Por la mañana escuché el canto de los pájaros.")
 ,n4k("冬","invierno","トウ","ふゆ",5,"冬","ふゆ","invierno","冬は寒いです。","ふゆはさむいです。","El invierno es frío.")
 ,n4k("茶","té","チャ サ","",9,"お茶","おちゃ","té","お茶を一杯飲みます。","おちゃをいっぱいのみます。","Bebo una taza de té.")
 ,n4k("弟","hermano menor","テイ ダイ","おとうと",7,"弟","おとうと","hermano menor","弟は高校生です。","おとうとはこうこうせいです。","Mi hermano menor es estudiante de secundaria.")
 ,n4k("兄","hermano mayor","ケイ キョウ","あに",5,"兄","あに","hermano mayor","兄は東京に住んでいます。","あにはとうきょうにすんでいます。","Mi hermano mayor vive en Tokio.")
 ,n4k("妹","hermana menor","マイ","いもうと",8,"妹","いもうと","hermana menor","妹は歌が好きです。","いもうとはうたがすきです。","A mi hermana menor le gusta cantar.")
 ,n4k("姉","hermana mayor","シ","あね",8,"姉","あね","hermana mayor","姉は毎日日本語を勉強します。","あねはまいにちにほんごをべんきょうします。","Mi hermana mayor estudia japonés todos los días.")
 ,n4k("発","salida; emitir; comenzar","ハツ ホツ","た.つ",9,"出発","しゅっぱつ","salida; partida","電車は八時に出発します。","でんしゃははちじにしゅっぱつします。","El tren sale a las ocho.")
 ,n4k("業","trabajo; industria","ギョウ ゴウ","わざ",13,"授業","じゅぎょう","clase","日本語の授業は楽しいです。","にほんごのじゅぎょうはたのしいです。","La clase de japonés es divertida.")
 ,n4k("動","mover; movimiento","ドウ","うご.く うご.かす",11,"動く","うごく","moverse","この時計は動いていません。","このとけいはうごいていません。","Este reloj no está funcionando.")
 ,n4k("主","principal; dueño","シュ ス","ぬし おも",5,"主人","しゅじん","esposo; dueño","主人は料理をしています。","しゅじんはりょうりをしています。","Mi esposo está cocinando.")
 ,n4k("意","idea; intención","イ","",13,"意味","いみ","significado","この言葉の意味が分かりますか。","このことばのいみがわかりますか。","¿Entiendes el significado de esta palabra?")
 ,n4k("度","grado; vez","ド ト","たび",9,"今度","こんど","esta vez; la próxima","今度、一緒に映画を見ましょう。","こんど、いっしょにえいがをみましょう。","La próxima vez veamos una película juntos.")
 ,n4k("都","capital; metrópolis","ト ツ","みやこ",11,"京都","きょうと","Kioto","京都には古いお寺があります。","きょうとにはふるいおてらがあります。","En Kioto hay templos antiguos.")
 ,n4k("世","mundo; generación","セイ セ","よ",5,"世界","せかい","mundo","世界のいろいろな国を知りたいです。","せかいのいろいろなくにをしりたいです。","Quiero conocer distintos países del mundo.")
 ,n4k("区","distrito; zona","ク オウ","",4,"区役所","くやくしょ","oficina del distrito","区役所で住所を登録しました。","くやくしょでじゅうしょをとうろくしました。","Registré mi dirección en la oficina del distrito.")
 ,n4k("県","prefectura","ケン","",9,"県","けん","prefectura","この県は海に近いです。","このけんはうみにちかいです。","Esta prefectura está cerca del mar.")
 ,n4k("進","avanzar; progresar","シン","すす.む すす.める",11,"進む","すすむ","avanzar","まっすぐ進んでください。","まっすぐすすんでください。","Sigue derecho, por favor.")
 ,n4k("院","institución; hospital","イン","",10,"病院","びょういん","hospital","母は病院で働いています。","はははびょういんではたらいています。","Mi madre trabaja en un hospital.")
 ,n4k("界","mundo; ámbito","カイ","",9,"世界","せかい","mundo","世界の文化を勉強しています。","せかいのぶんかをべんきょうしています。","Estudio las culturas del mundo.")
 ,n4k("集","reunir; coleccionar","シュウ","あつ.まる あつ.める",12,"集まる","あつまる","reunirse","友だちが駅に集まりました。","ともだちがえきにあつまりました。","Mis amigos se reunieron en la estación.")
 ,n4k("物","cosa; objeto","ブツ モツ","もの",8,"食べ物","たべもの","comida","好きな食べ物は何ですか。","すきなたべものはなんですか。","¿Cuál es tu comida favorita?")
 ,n4k("所","lugar","ショ","ところ",8,"場所","ばしょ","lugar","静かな所で勉強したいです。","しずかなところでべんきょうしたいです。","Quiero estudiar en un lugar tranquilo.")
 ,n4k("品","artículo; producto","ヒン ホン","しな",9,"品物","しなもの","artículo; mercancía","店に新しい品物が入りました。","みせにあたらしいしなものがはいりました。","Llegó mercancía nueva a la tienda.")
 ,n4k("死","morir; muerte","シ","し.ぬ",6,"死ぬ","しぬ","morir","植物は水がないと死にます。","しょくぶつはみずがないとしにます。","Las plantas mueren si no tienen agua.")
 ,n4k("運","transportar; suerte","ウン","はこ.ぶ",12,"運転","うんてん","conducir","父は毎日車を運転します。","ちちはまいにちくるまをうんてんします。","Mi padre conduce todos los días.")
 ,n4k("住","vivir; residir","ジュウ","す.む す.まう",7,"住所","じゅうしょ","dirección","住所を書いてください。","じゅうしょをかいてください。","Escribe tu dirección, por favor.")
 ,n4k("真","verdad; real","シン","ま まこと",10,"写真","しゃしん","fotografía","旅行の写真を見せてください。","りょこうのしゃしんをみせてください。","Muéstrame las fotos del viaje, por favor.")
 ,n4k("急","urgente; rápido","キュウ","いそ.ぐ",9,"急ぐ","いそぐ","apurarse","時間がないので急ぎます。","じかんがないのでいそぎます。","Me apuro porque no tengo tiempo.")
 ,n4k("転","girar; cambiar","テン","ころ.がる ころ.げる",11,"自転車","じてんしゃ","bicicleta","自転車で学校へ行きます。","じてんしゃでがっこうへいきます。","Voy a la escuela en bicicleta.")
 ,n4k("研","investigar; pulir","ケン","と.ぐ",9,"研究","けんきゅう","investigación","大学で日本語を研究しています。","だいがくでにほんごをけんきゅうしています。","Investigo japonés en la universidad.")
 ,n4k("究","investigar; profundizar","キュウ ク","きわ.める",7,"研究","けんきゅう","investigación","日本の歴史を研究します。","にほんのれきしをけんきゅうします。","Investigo la historia de Japón.")
 ,n4k("起","levantarse; ocurrir","キ","お.きる お.こる",10,"起きる","おきる","levantarse","毎朝六時に起きます。","まいあさろくじにおきます。","Me levanto a las seis cada mañana.")
 ,n4k("着","vestir; llegar","チャク ジャク","き.る つ.く",12,"着る","きる","vestir","今日は新しい服を着ます。","きょうはあたらしいふくをきます。","Hoy me pongo ropa nueva.")

)
