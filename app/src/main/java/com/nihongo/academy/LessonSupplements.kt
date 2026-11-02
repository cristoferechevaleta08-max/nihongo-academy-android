package com.nihongo.academy

internal data class LessonSupplement(
    val objective: String,
    val grammar: String,
    val vocabulary: List<Triple<String, String, String>>,
    val examples: List<String>,
    val unitAssessment: String,
    val kanaReviewed: Boolean = false
)

internal val lessonSupplements = mapOf(
 "n5-u01" to LessonSupplement(
  "Decir quién sos y saludar con cortesía.",
  "watashi wa [nombre] desu (soy [nombre]); anata (tú); sufijo ~san.",
  listOf(Triple("watashi","わたし","yo"),Triple("anata","あなた","tú"),Triple("sensei","せんせい","maestro/a"),Triple("gakusei","がくせい","estudiante"),Triple("hajimemashite","はじめまして","encantado de conocerte"),Triple("arigatou","ありがとう","gracias"),Triple("sumimasen","すみません","disculpe / perdón"),Triple("onegaishimasu","おねがいします","por favor al pedir algo")),
  listOf("Watashi wa Sakura desu. — Soy Sakura.","Anata wa gakusei desu ka. — ¿Eres estudiante?"),
  "Presentarse en dos frases y responder una pregunta sencilla sobre la persona."
 ),
 "n5-u02" to LessonSupplement(
  "Usar wa, ga, o, ni, de y mo para unir palabras en una frase.",
  "wa: tema; ga: sujeto; o: objeto; ni: lugar o destino; de: lugar de la acción; mo: también.",
  listOf(Triple("neko","ねこ","gato"),Triple("inu","いぬ","perro"),Triple("hon","ほん","libro"),Triple("gakkou","がっこう","escuela"),Triple("uchi","うち","casa"),Triple("tomodachi","ともだち","amigo"),Triple("mizu","みず","agua"),Triple("kouhii","コーヒー","café")),
  listOf("Neko ga imasu. — Hay un gato.","Watashi mo gakusei desu. — Yo también soy estudiante."),
  "Elegir la partícula correcta en cinco frases de práctica."
 ),
 "n5-u03" to LessonSupplement(
  "Conjugar verbos comunes en presente afirmativo cortés.",
  "taberu → tabemasu; nomu → nomimasu; iku → ikimasu; kuru → kimasu; suru → shimasu; miru → mimasu.",
  listOf(Triple("taberu","たべる","comer"),Triple("nomu","のむ","beber"),Triple("iku","いく","ir"),Triple("kuru","くる","venir"),Triple("suru","する","hacer"),Triple("miru","みる","ver")),
  listOf("Mainichi gohan o tabemasu. — Como arroz todos los días.","Gakkou ni ikimasu. — Voy a la escuela."),
  "Conjugar seis verbos en forma masu sin error."
 ),
 "n5-u04" to LessonSupplement(
  "Expresar acciones pasadas y negativas.",
  "Pasado cortés: tabemashita (comí), nomimashita (bebí). Negativo cortés: tabemasen (no como), ikimasen (no voy). Las formas se construyen con ~mashita y ~masen.",
  listOf(Triple("kinou","きのう","ayer"),Triple("kyou","きょう","hoy"),Triple("ashita","あした","mañana"),Triple("tabemasen","たべません","no como"),Triple("ikimashita","いきました","fui"),Triple("nomimasen","のみません","no bebo")),
  listOf("Kinou eiga o mimashita. — Ayer vi una película.","Kyou wa benkyou shimasen. — Hoy no estudio."),
  "Transformar cinco frases del presente al pasado y al negativo."
 ),
 "n5-u05" to LessonSupplement(
  "Describir cosas con adjetivos que terminan en i.",
  "Adjetivos i: ookii, chiisai, takai, yasui, atsui, samui. Negativo: ~kunai desu.",
  listOf(Triple("ookii","おおきい","grande"),Triple("chiisai","ちいさい","pequeño"),Triple("takai","たかい","caro / alto"),Triple("yasui","やすい","barato"),Triple("atsui","あつい","caliente"),Triple("samui","さむい","frío")),
  listOf("Kono hon wa takai desu. — Este libro es caro.","Kyou wa samui desu. — Hoy hace frío."),
  "Usar cuatro adjetivos i con su forma negativa."
 ),
 "n5-u06" to LessonSupplement(
  "Describir cosas con adjetivos que van seguidos de na.",
  "kirei, shizuka, genki, benri, hima, suki. Formas: ~desu / ~ja arimasen.",
  listOf(Triple("kirei","きれい","bonito"),Triple("shizuka","しずか","tranquilo"),Triple("genki","げんき","sano / animado"),Triple("benri","べんり","práctico"),Triple("hima","ひま","libre"),Triple("suki","すき","gustar")),
  listOf("Kono machi wa shizuka desu. — Esta ciudad es tranquila.","Sono heya wa kirei ja arimasen. — Esa habitación no es bonita."),
  "Escribir tres frases con adjetivos na en afirmativo y negativo."
 ),
 "n5-u07" to LessonSupplement(
  "Decir números, horas y días de la semana.",
  "Números ichi, ni, san, yon, go, roku, nana, hachi, kyuu, juu; ji (hora), fun (minuto), getsuyoubi (lunes).",
  listOf(Triple("ichi","いち","uno"),Triple("ni","に","dos"),Triple("san","さん","tres"),Triple("go","ご","cinco"),Triple("juu","じゅう","diez"),Triple("ji","じ","hora"),Triple("fun","ふん","minuto"),Triple("nichiyoubi","にちようび","domingo")),
  listOf("Ima san ji desu. — Son las tres.","Kaigi wa getsuyoubi desu. — La reunión es el lunes."),
  "Leer tres horas y dos días de la semana en voz alta."
 ),
 "n5-u08" to LessonSupplement(
  "Nombrar lugares y preguntar dónde están.",
  "eki, byouin, toshokan, kouen, koko, soko, asoko; ~wa doko desu ka (¿dónde está?).",
  listOf(Triple("eki","えき","estación"),Triple("byouin","びょういん","hospital"),Triple("toshokan","としょかん","biblioteca"),Triple("kouen","こうえん","parque"),Triple("koko","ここ","aquí"),Triple("soko","そこ","ahí"),Triple("asoko","あそこ","allí")),
  listOf("Eki wa doko desu ka. — ¿Dónde está la estación?","Toshokan wa asoko desu. — La biblioteca está allí."),
  "Preguntar dónde está un lugar y responder con koko, soko o asoko."
 ),
 "n5-u09" to LessonSupplement(
  "Pedir a alguien que haga algo con ~te kudasai.",
  "~te kudasai (por favor, haga algo); ~te mo ii desu ka (¿puedo hacer algo?).",
  listOf(Triple("mite","みて","mire"),Triple("kiite","きいて","escuche"),Triple("matte","まって","espere"),Triple("hayaku","はやく","rápido"),Triple("yukkuri","ゆっくり","despacio"),Triple("shizuka ni","しずかに","en silencio")),
  listOf("Mite kudasai. — Mire, por favor.","Yukkuri hanashite kudasai. — Hable despacio, por favor."),
  "Dar cuatro instrucciones con ~te kudasai."
 ),
 "n5-u10" to LessonSupplement(
  "Decir qué hay y dónde está algo o alguien.",
  "~ga arimasu (hay algo, para cosas); ~ga imasu (hay alguien, para seres vivos); ~ga arimasen (no hay).",
  listOf(Triple("hito","ひと","persona"),Triple("kuruma","くるま","coche"),Triple("tsukue","つくえ","escritorio"),Triple("kippu","きっぷ","boleto"),Triple("aru","ある","estar / haber, para cosas"),Triple("iru","いる","estar / haber, para seres vivos")),
  listOf("Tsukue no ue ni hon ga arimasu. — Hay un libro encima del escritorio.","Heya ni hito ga imasu. — Hay una persona en la habitación."),
  "Describir una habitación con cuatro frases correctas."
 ),
 "n4-u01" to LessonSupplement(
  "Unir acciones y expresar una acción en curso.",
  "Forma te: tabete; ~te imasu (estar haciendo algo); ~te kudasai (por favor, haga algo).",
  listOf(Triple("tabete","たべて","comiendo / y comer"),Triple("yonde","よんで","leyendo / y leer"),Triple("matte","まって","esperando / y esperar"),Triple("kaite","かいて","escribiendo / y escribir")),
  listOf("いまほんをよんでいます。 — Ahora estoy leyendo un libro.","あさごはんをたべて、がっこうにいきます。 — Desayuno y voy a la escuela."),
  "Construir la forma te de diez verbos y usar ~te imasu en cinco frases."
 ),
 "n4-u02" to LessonSupplement(
  "Expresar negación y decir que algo es necesario.",
  "tabenai (no comer); ~nakereba narimasen (hay que); ~nakute mo ii desu (no hace falta).",
  listOf(Triple("tabenai","たべない","no comer"),Triple("ikanai","いかない","no ir"),Triple("hayaku","はやく","pronto"),Triple("kusuri","くすり","medicina"),Triple("yasumu","やすむ","descansar"),Triple("benkyou suru","べんきょうする","estudiar")),
  listOf("はやくねなければなりません。 — Hay que dormir pronto.","あしたいかなくてもいいです。 — No hace falta ir mañana."),
  "Escribir cuatro frases de obligación y dos de no obligación."
 ),
 "n4-u03" to LessonSupplement(
  "Expresar la capacidad de hacer algo.",
  "taberareru (poder comer); ~eru (poder, para verbos de grupo 1); ~koto ga dekimasu (poder hacer).",
  listOf(Triple("oyogeru","およげる","poder nadar"),Triple("hanasu","はなす","hablar"),Triple("dekiru","できる","poder hacer"),Triple("tsukuru","つくる","hacer / crear")),
  listOf("にほんごをはなせます。 — Puedo hablar japonés.","すしをたべてもいいです。 — Puedes comer sushi, sin problema."),
  "Expresar en cinco frases lo que la persona puede y no puede hacer."
 ),
 "n4-u04" to LessonSupplement(
  "Decir lo que se quiere hacer con ~tai.",
  "tabetai desu (quiero comer); ~tai desu ka (¿querés?); ~takunai desu (no quiero).",
  listOf(Triple("nomitai","のみたい","quiero beber"),Triple("ikitai","いきたい","quiero ir"),Triple("aitai","あいたい","quiero ver a alguien"),Triple("yasumitai","やすみたい","quiero descansar")),
  listOf("にほんにいきたいです。 — Quiero ir a Japón.","なにかたべたいですか。 — ¿Querés comer algo?"),
  "Hablar de cuatro deseos propios y preguntar uno a otra persona."
 ),
 "n4-u05" to LessonSupplement(
  "Comparar dos cosas y decir cuál es la mejor.",
  "~yori (más que); ~no naka de ichiban (lo más de entre); onaji (igual).",
  listOf(Triple("hayai","はやい","rápido"),Triple("nagai","ながい","largo"),Triple("tanoshii","たのしい","divertido"),Triple("sukunai","すくない","poco / escaso"),Triple("ichiban","いちばん","lo más")),
  listOf("このでんしゃはあのでんしゃよりはやいです。 — Este tren es más rápido que aquel.","にほんごのなかでいちばんむずかしいです。 — Es lo más difícil entre los idiomas."),
  "Comparar tres pares de cosas y escoger un superlativo."
 ),
 "n4-u06" to LessonSupplement(
  "Diferenciar quién da y quién recibe.",
  "ageru (dar); morau (recibir); kureru (dar a mí); ~te agemasu / ~te moraimasu / ~te kuremasu.",
  listOf(Triple("ageru","あげる","dar"),Triple("morau","もらう","recibir"),Triple("kureru","くれる","dar a mí"),Triple("kasu","かす","prestar"),Triple("kariru","かりる","pedir prestado")),
  listOf("ともだちにほんをもらいました。 — Recibí un libro de un amigo.","たなかさんがしごとをてつだってくれました。 — Tanaka me ayudó con el trabajo."),
  "Explicar en tres frases quién dio y quién recibió algo."
 ),
 "n4-u07" to LessonSupplement(
  "Dar una opinión y expresar algo probable.",
  "~to omoimasu (pienso que); ~deshou (probablemente); ~kamo shiremasen (quizá).",
  listOf(Triple("omoimasu","おもいます","pienso"),Triple("tabun","たぶん","probablemente"),Triple("kitto","きっと","seguramente"),Triple("shitte imasu","しっています","sé / conozco")),
  listOf("あしたはあめでしょう。 — Probablemente mañana llueva.","かのじょはくるかもしれません。 — Quizá ella venga."),
  "Dar una opinión y una predicción en cuatro frases."
 ),
 "n4-u08" to LessonSupplement(
  "Expresar condiciones con ~tara y ~to.",
  "~tara (si / cuando); ~to (cuando ocurre algo siempre o como resultado natural).",
  listOf(Triple("ame","あめ","lluvia"),Triple("hare","はれ","buen tiempo"),Triple("tsukarete iru","つかれている","estar cansado"),Triple("kaeru","かえる","volver")),
  listOf("あめがふったら、うちにいます。 — Si llueve, me quedo en casa.","はるになると、さくらがさきます。 — Cuando llega la primavera, florecen los cerezos."),
  "Escribir tres frases condicionales con situaciones de la vida diaria."
 )
)
