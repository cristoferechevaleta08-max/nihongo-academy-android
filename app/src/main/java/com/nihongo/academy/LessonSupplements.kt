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
  "Conectar acciones y describir acciones en curso o estados resultantes.",
  "Forma te: verbos grupo 1 (kaku → kaite; yomu → yonde; matsu → matte), grupo 2 (taberu → tabete) e irregulares (suru → shite; kuru → kite). ~te imasu puede indicar acción en curso (hon o yonde imasu) y, con ciertos verbos, un estado resultante (kekkon shite imasu = está casado). No confundas ~te imasu con el presente habitual. La forma te también conecta acciones en secuencia.",
  listOf(Triple("tabete","たべて","comiendo / y comer"),Triple("yonde","よんで","leyendo / y leer"),Triple("matte","まって","esperando / y esperar"),Triple("kaite","かいて","escribiendo / y escribir"),Triple("shite","して","haciendo"),Triple("kite","きて","viniendo"),Triple("aruite","あるいて","caminando"),Triple("nomi","のみ","bebiendo; forma de enlace")),
  listOf("いまほんをよんでいます。 — Ahora estoy leyendo un libro.","あさごはんをたべて、がっこうにいきます。 — Desayuno y después voy a la escuela.","まどがあいています。 — La ventana está abierta (estado resultante)."),
  "Conjugar diez verbos en forma te, distinguir acción en curso de estado resultante y construir cinco frases con ~te imasu."
 ),
 "n4-u02" to LessonSupplement(
  "Formar el negativo informal y expresar obligación o ausencia de obligación.",
  "Forma nai: verbos grupo 1 cambian la vocal final a la columna a + nai (kaku → kakanai; nomu → nomanai; iku → ikanai; excepción: au → awanai). Grupo 2: quitar ru + nai (taberu → tabenai). Irregulares: suru → shinai; kuru → konai. Obligación: nai-forma + nakereba narimasen (ikanakereba narimasen). Ausencia de obligación: nai-forma sin nai + nakute mo ii desu (ikanakute mo ii desu).",
  listOf(Triple("tabenai","たべない","no comer"),Triple("ikanai","いかない","no ir"),Triple("kakanai","かかない","no escribir"),Triple("nomanai","のまない","no beber"),Triple("shinai","しない","no hacer"),Triple("konai","こない","no venir"),Triple("kusuri","くすり","medicina"),Triple("yasumu","やすむ","descansar")),
  listOf("はやくねなければなりません。 — Tengo que dormir temprano.","あしたいかなくてもいいです。 — No hace falta ir mañana.","ここでくすりをのまなければなりません。 — Tengo que tomar el medicamento aquí."),
  "Transformar diez verbos a la forma nai y escribir cuatro frases de obligación y dos de ausencia de obligación."
 ),
 "n4-u03" to LessonSupplement(
  "Expresar capacidad o posibilidad, sin confundirla con permiso.",
  "Verbos grupo 2: quitar ru + rareru (taberu → taberareru). Grupo 1: cambiar la vocal final a la columna e + ru (yomu → yomeru; hanasu → hanaseru; oyogu → oyogeru). Irregulares: suru → dekiru; kuru → korareru. Alternativa: verbo diccionario + koto ga dekimasu. Atención: ~te mo ii desu significa que algo está permitido, no que la persona sea capaz de hacerlo.",
  listOf(Triple("taberareru","たべられる","poder comer"),Triple("yomeru","よめる","poder leer"),Triple("hanaseru","はなせる","poder hablar"),Triple("oyogeru","およげる","poder nadar"),Triple("dekiru","できる","poder hacer"),Triple("tsukuru","つくる","hacer / crear"),Triple("koto","こと","hecho / cosa abstracta")),
  listOf("にほんごをはなせます。 — Puedo hablar japonés.","ひらがながよめます。 — Puedo leer hiragana.","このプールでおよいでもいいです。 — Está permitido nadar en esta piscina (permiso, no capacidad)."),
  "Transformar diez verbos a la forma potencial y escribir cinco frases de capacidad, incluyendo una negativa."
 ),
 "n4-u04" to LessonSupplement(
  "Expresar deseos propios y preguntar qué desea otra persona.",
  "Añadí ~tai a la raíz en forma masu (tabemasu → tabetai; nomimasu → nomitai). Se conjuga como adjetivo i: tabetakunai (no quiero comer), tabetakatta (quería comer), tabetaku arimasen (forma cortés negativa). Para deseos de otra persona suele usarse ~tagatte imasu en observaciones, o preguntar directamente con ~tai desu ka. Para querer un objeto se usa sustantivo + ga hoshii desu, no ~tai.",
  listOf(Triple("nomitai","のみたい","quiero beber"),Triple("ikitai","いきたい","quiero ir"),Triple("aitai","あいたい","quiero ver a alguien"),Triple("yasumitai","やすみたい","quiero descansar"),Triple("hoshii","ほしい","querer / desear un objeto"),Triple("tabetakunai","たべたくない","no querer comer")),
  listOf("にほんにいきたいです。 — Quiero ir a Japón.","なにかたべたいですか。 — ¿Querés comer algo?","あたらしいくるまがほしいです。 — Quiero un coche nuevo."),
  "Hablar de cuatro deseos propios, formular dos preguntas y distinguir deseo de acción (~tai) de deseo de objeto (hoshii)."
 ),
 "n4-u05" to LessonSupplement(
  "Comparar personas y objetos, y expresar el grado máximo dentro de un grupo.",
  "Comparación: A は B より adjetivo です (A es más ... que B). Pregunta: A と B と どちらが adjetivo ですか (¿cuál de los dos ...?). Respuesta: A のほうが ... (A es más ...). Superlativo: grupo の中で X が一番 adjetivo です. Para igualdad: A と B は同じくらいです (son aproximadamente iguales).",
  listOf(Triple("hayai","はやい","rápido / temprano"),Triple("nagai","ながい","largo"),Triple("tanoshii","たのしい","divertido"),Triple("sukunai","すくない","poco / escaso"),Triple("ichiban","いちばん","el más"),Triple("dochira","どちら","cuál de los dos"),Triple("hou","ほう","lado / opción"),Triple("onaji","おなじ","igual")),
  listOf("このでんしゃはあのでんしゃよりはやいです。 — Este tren es más rápido que aquel.","コーヒーとおちゃとどちらがすきですか。 — ¿Cuál te gusta más, el café o el té?","スポーツの中でサッカーが一番好きです。 — Entre los deportes, el fútbol es el que más me gusta."),
  "Comparar tres pares de cosas, responder dos preguntas con ~no hou ga y construir un superlativo correcto."
 ),
 "n4-u06" to LessonSupplement(
  "Expresar la dirección del intercambio y los favores recibidos o realizados.",
  "あげる: el sujeto da algo a otra persona (A は B に本をあげます). くれる: otra persona da algo al hablante o a su grupo (A が私に本をくれます). もらう: el sujeto recibe algo de alguien (A は B に／から本をもらいます). Con la forma te: ~てあげる (hacer un favor a alguien), ~てくれる (alguien hace algo por mí) y ~てもらう (recibo el favor). Usá estas formas con cuidado: pueden sonar demasiado directas si se aplican a superiores sin contexto.",
  listOf(Triple("ageru","あげる","dar a otra persona"),Triple("morau","もらう","recibir"),Triple("kureru","くれる","dar al hablante"),Triple("kasu","かす","prestar"),Triple("kariru","かりる","pedir prestado"),Triple("tetsudau","てつだう","ayudar"),Triple("oshieru","おしえる","enseñar")),
  listOf("ともだちにほんをあげました。 — Le di un libro a un amigo.","ともだちがわたしにほんをくれました。 — Un amigo me dio un libro.","ともだちににほんごをおしえてもらいました。 — Recibí de un amigo la ayuda de que me enseñara japonés."),
  "Escribir tres frases que distingan dar, recibir y dar al hablante, y otras tres con ~te ageru / ~te kureru / ~te morau."
 ),
 "n4-u07" to LessonSupplement(
  "Expresar opiniones, suposiciones y distintos grados de posibilidad.",
  "Opinión: oración en forma simple + とおもいます (pienso que...). En adjetivos na y sustantivos se conserva だ antes de とおもいます en estilo informal (しずかだとおもいます). ~でしょう expresa una suposición o confirmación probable; ~かもしれません indica posibilidad menos segura (quizá). たぶん suele acompañar una suposición; きっと expresa una expectativa fuerte. Para decir que conocés a alguien o algo: しっています; para decir que no lo sabés: しりません.",
  listOf(Triple("omoimasu","おもいます","pienso"),Triple("tabun","たぶん","probablemente"),Triple("kitto","きっと","seguramente"),Triple("kamo shiremasen","かもしれません","quizá"),Triple("deshou","でしょう","probablemente / ¿verdad?"),Triple("shitte imasu","しっています","sé / conozco"),Triple("shirimasen","しりません","no sé")),
  listOf("あしたはあめでしょう。 — Probablemente mañana llueva.","かのじょはくるかもしれません。 — Quizá ella venga.","このまちはしずかだとおもいます。 — Pienso que esta ciudad es tranquila."),
  "Dar dos opiniones y dos predicciones; diferenciar una posibilidad con ~kamo shiremasen de una suposición con ~deshou."
 ),
 "n4-u08" to LessonSupplement(
  "Distinguir condiciones abiertas con ~tara de resultados habituales o naturales con ~to.",
  "~たら se forma con el pasado informal + ら (雨が降ったら). Puede expresar si/cuando y permite una decisión, petición o invitación en la oración principal. ~と se usa para resultados automáticos, naturales o habituales (春になると桜が咲きます); normalmente no se usa cuando la oración principal expresa una orden, invitación o voluntad del hablante. No son intercambiables en todos los contextos.",
  listOf(Triple("ame","あめ","lluvia"),Triple("hare","はれ","buen tiempo"),Triple("tsukarete iru","つかれている","estar cansado"),Triple("kaeru","かえる","volver"),Triple("furu","ふる","caer (lluvia / nieve)"),Triple("haru","はる","primavera"),Triple("saku","さく","florecer"),Triple("moshi","もし","si (refuerzo de condición)")),
  listOf("あめがふったら、うちにいます。 — Si llueve, me quedo en casa.","はるになると、さくらがさきます。 — Cuando llega la primavera, florecen los cerezos.","ひまだったら、えいがをみましょう。 — Si tenés tiempo libre, veamos una película."),
  "Escribir tres frases con ~tara que incluyan una invitación o decisión y tres frases con ~to para resultados naturales o habituales."
 )
)
