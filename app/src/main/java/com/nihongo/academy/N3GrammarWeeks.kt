package com.nihongo.academy

/**
 * Primera unidad gramatical N3: voz pasiva.
 * El contenido es material didáctico original organizado para el curso.
 */
internal val n3PassiveVoiceDays = listOf(
    KanaDay(1, "Qué expresa la voz pasiva", "Identificar quién realiza la acción y quién la recibe.",
        listOf(
            "Reconocer la estructura: persona afectada は agente に verbo en pasiva.",
            "Distinguir la persona que realiza la acción de la persona o cosa afectada.",
            "Comparar una frase activa con su equivalente pasiva.",
            "Leer en voz alta わたしは先生にほめられました。"
        )),
    KanaDay(2, "Pasiva de verbos del grupo 1", "Transformar verbos godan cambiando la última sílaba de la fila u a la fila a y añadiendo れる.",
        listOf(
            "Practicar 書く → 書かれる (kaku → kakareru).",
            "Practicar 読む → 読まれる (yomu → yomareru).",
            "Practicar 話す → 話される (hanasu → hanasareru).",
            "Recordar que う cambia a わ antes de れる: 買う → 買われる."
        )),
    KanaDay(3, "Pasiva de verbos del grupo 2", "Formar la pasiva de verbos ichidan sustituyendo る por られる.",
        listOf(
            "Practicar 食べる → 食べられる (taberu → taberareru).",
            "Practicar 見る → 見られる (miru → mirareru).",
            "Identificar la terminación られる en contexto pasivo.",
            "No confundir la forma pasiva con otros usos posibles de られる."
        )),
    KanaDay(4, "Pasiva de verbos irregulares", "Dominar las dos conjugaciones irregulares principales.",
        listOf(
            "Aprender する → される (suru → sareru).",
            "Aprender 来る → 来られる (kuru → korareru).",
            "Practicar las dos formas dentro de frases completas.",
            "Recordar que estas formas no siguen las reglas regulares."
        )),
    KanaDay(5, "Partícula に en la pasiva", "Marcar con に a la persona o entidad que realiza la acción.",
        listOf(
            "Leer わたしは先生にほめられました。 (El profesor me elogió).",
            "Identificar quién recibe la acción y quién la realiza.",
            "Practicar frases con 友だちに y 母に.",
            "Evitar traducir に siempre como una única preposición española."
        )),
    KanaDay(6, "Pasiva de perjuicio y experiencia", "Comprender situaciones en las que el hablante expresa que una acción ajena le afectó.",
        listOf(
            "Reconocer 雨に降られました como una experiencia desagradable causada por la lluvia.",
            "Distinguir una pasiva directa de una pasiva de perjuicio.",
            "Identificar a la persona afectada aunque no sea el objeto directo de la frase activa.",
            "Practicar ejemplos breves sin asumir que toda pasiva expresa algo negativo."
        )),
    KanaDay(7, "Repaso y evaluación de voz pasiva", "Consolidar formación, partículas y comprensión del significado.",
        listOf(
            "Conjugar verbos de los tres grupos y los dos irregulares.",
            "Transformar frases activas sencillas a pasivas.",
            "Identificar agente y persona afectada.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3PassiveVoiceAssessment = listOf(
    KanaQuizQuestion("¿Cuál es la forma pasiva de 書く (kaku)?", listOf("書かれる (kakareru)", "書ける (kakeru)", "書かせる (kakaseru)", "書いた (kaita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 読む (yomu)?", listOf("読まれる (yomareru)", "読める (yomeru)", "読ませる (yomaseru)", "読んだ (yonda)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 買う (kau)?", listOf("買われる (kawareru)", "買える (kaeru)", "買わせる (kawaseru)", "買った (katta)"), 0),
    KanaQuizQuestion("¿Cómo se forma normalmente la pasiva de 食べる (taberu)?", listOf("食べられる (taberareru)", "食べれる (taberu)", "食べさせる (tabesaseru)", "食べた (tabeta)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 見る (miru)?", listOf("見られる (mirareru)", "見させる (misaseru)", "見える (mieru)", "見た (mita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de する (suru)?", listOf("される (sareru)", "させる (saseru)", "できる (dekiru)", "した (shita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma pasiva de 来る (kuru)?", listOf("来られる (korareru)", "来させる (kosaseru)", "来れる (koreru)", "来た (kita)"), 0),
    KanaQuizQuestion("En わたしは先生にほめられました, ¿quién realizó la acción?", listOf("El profesor", "Yo", "Un amigo", "Nadie"), 0),
    KanaQuizQuestion("En una pasiva típica, ¿qué función suele marcar に?", listOf("El agente que realiza la acción", "El objeto directo", "El lugar de destino", "El tiempo"), 0),
    KanaQuizQuestion("¿Qué expresa 雨に降られました en un contexto de pasiva de perjuicio?", listOf("Me afectó que lloviera / me agarró la lluvia", "Hice que lloviera", "Dejé de llover", "Voy a hacer que llueva"), 0),
    KanaQuizQuestion("¿Toda oración pasiva japonesa expresa necesariamente algo negativo?", listOf("No, depende del contexto", "Sí, siempre", "Solo si aparece に", "Solo en pasado"), 0),
    KanaQuizQuestion("¿Qué elemento identifica a la persona afectada en わたしは先生にほめられました?", listOf("わたしは", "先生に", "ほめ", "ました"), 0)
)


// Segunda unidad gramatical N3: causativa y causativa-pasiva.
internal val n3CausativeDays = listOf(
    KanaDay(1, "Significado de la causativa", "Expresar que alguien hace que otra persona realice una acción o le permite hacerlo.",
        listOf(
            "Distinguir causativa de una orden directa.",
            "Reconocer los matices de obligación y permiso según el contexto.",
            "Identificar quién causa la acción y quién la realiza.",
            "Leer 母は子どもに野菜を食べさせました。"
        )),
    KanaDay(2, "Causativa de verbos del grupo 1", "Cambiar la última sílaba de la fila u a la fila a y añadir せる.",
        listOf(
            "Practicar 書く → 書かせる (kaku → kakaseru).",
            "Practicar 読む → 読ませる (yomu → yomaseru).",
            "Practicar 話す → 話させる (hanasu → hanasaseru).",
            "Recordar う → わせる: 買う → 買わせる."
        )),
    KanaDay(3, "Causativa de verbos del grupo 2", "Sustituir る por させる en los verbos ichidan.",
        listOf(
            "Practicar 食べる → 食べさせる (taberu → tabesaseru).",
            "Practicar 見る → 見させる (miru → misaseru).",
            "Crear frases de permiso y obligación con verbos del grupo 2.",
            "Comprobar quién realiza la acción en cada ejemplo."
        )),
    KanaDay(4, "Causativa de verbos irregulares", "Aprender las formas causativas de する y 来る.",
        listOf(
            "Estudiar する → させる (suru → saseru).",
            "Estudiar 来る → 来させる (kuru → kosaseru).",
            "Practicar las formas en frases completas.",
            "No aplicar mecánicamente las reglas de los grupos regulares."
        )),
    KanaDay(5, "Quién causa y quién actúa", "Interpretar las funciones de las personas en una oración causativa.",
        listOf(
            "Separar la persona que da lugar a la acción de quien la ejecuta.",
            "Observar el uso de に y を según el verbo y la construcción.",
            "Comparar ejemplos con verbos transitivos e intransitivos.",
            "Evitar traducir la causativa siempre como una orden: el contexto puede indicar permiso."
        )),
    KanaDay(6, "Forma causativa-pasiva", "Expresar que alguien se ve obligado o queda en la situación de realizar una acción.",
        listOf(
            "Practicar 書く → 書かせられる (kakaserareru).",
            "Practicar 食べる → 食べさせられる (tabesaserareru).",
            "Aprender する → させられる y 来る → 来させられる.",
            "Distinguir causativa, pasiva y causativa-pasiva por su significado."
        )),
    KanaDay(7, "Repaso y evaluación de causativa", "Consolidar conjugación, perspectiva y diferencias de significado.",
        listOf(
            "Conjugar verbos de los tres grupos y los irregulares.",
            "Transformar frases sencillas a causativa.",
            "Reconocer situaciones expresadas con causativa-pasiva.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3CausativeAssessment = listOf(
    KanaQuizQuestion("¿Cuál es la forma causativa de 書く (kaku)?", listOf("書かせる (kakaseru)", "書かれる (kakareru)", "書ける (kakeru)", "書いた (kaita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de 読む (yomu)?", listOf("読ませる (yomaseru)", "読まれる (yomareru)", "読める (yomeru)", "読んだ (yonda)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de 買う (kau)?", listOf("買わせる (kawaseru)", "買われる (kawareru)", "買える (kaeru)", "買った (katta)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de 食べる (taberu)?", listOf("食べさせる (tabesaseru)", "食べられる (taberareru)", "食べる (taberu)", "食べた (tabeta)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de 見る (miru)?", listOf("見させる (misaseru)", "見られる (mirareru)", "見える (mieru)", "見た (mita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de する (suru)?", listOf("させる (saseru)", "される (sareru)", "できる (dekiru)", "した (shita)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa de 来る (kuru)?", listOf("来させる (kosaseru)", "来られる (korareru)", "来れる (koreru)", "来た (kita)"), 0),
    KanaQuizQuestion("¿Qué puede expresar una causativa según el contexto?", listOf("Hacer que alguien actúe o permitirle actuar", "Solo una acción pasada", "Una pregunta indirecta", "Una comparación"), 0),
    KanaQuizQuestion("¿Quién realiza la acción en una oración causativa?", listOf("La persona a quien se hace actuar o se permite actuar", "Siempre quien habla", "Siempre el objeto", "Nadie"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa-pasiva de 食べる?", listOf("食べさせられる (tabesaserareru)", "食べられる (taberareru)", "食べさせる (tabesaseru)", "食べた (tabeta)"), 0),
    KanaQuizQuestion("¿Cuál es la forma causativa-pasiva de する?", listOf("させられる (saserareru)", "される (sareru)", "させる (saseru)", "できる (dekiru)"), 0),
    KanaQuizQuestion("¿La causativa-pasiva suele expresar qué matiz?", listOf("Que alguien se ve obligado o puesto en situación de hacer algo", "Que alguien recibe un elogio", "Que algo ocurrió espontáneamente", "Que una acción es posible"), 0)
)


// Tercera unidad gramatical N3: dar, recibir y expresar consideración.
internal val n3GivingReceivingDays = listOf(
    KanaDay(1, "Perspectiva de dar y recibir", "Elegir la expresión según quién ofrece, quién recibe y desde qué punto de vista se habla.",
        listOf(
            "Distinguir あげる (ageru), くれる (kureru) y もらう (morau).",
            "Identificar al dador y al receptor antes de elegir el verbo.",
            "Observar cómo cambia la perspectiva cuando el receptor es el hablante o alguien cercano.",
            "Comparar 友だちに本をあげました y 友だちが本をくれました."
        )),
    KanaDay(2, "あげる y くれる", "Expresar que alguien da algo, con especial atención a la perspectiva del hablante.",
        listOf(
            "Usar あげる cuando el hablante describe dar algo a otra persona.",
            "Usar くれる cuando otra persona da algo al hablante o a alguien de su entorno.",
            "Practicar el orden persona + に + objeto + を + verbo.",
            "Evitar intercambiar あげる y くれる sin revisar quién recibe."
        )),
    KanaDay(3, "もらう y la persona de quien se recibe", "Describir la recepción de algo y señalar su origen.",
        listOf(
            "Practicar 友だちにプレゼントをもらいました.",
            "Reconocer に y から como marcadores posibles del origen según el contexto.",
            "Comparar la misma situación desde la perspectiva de quien da y quien recibe.",
            "Crear ejemplos con objetos cotidianos."
        )),
    KanaDay(4, "Acciones realizadas por otros: てあげる", "Expresar que uno hace algo por otra persona.",
        listOf(
            "Formar verbo en て + あげる.",
            "Practicar 妹に宿題を教えてあげました.",
            "Reconocer que puede sonar paternalista si se usa sin cuidado con superiores o desconocidos.",
            "Elegir expresiones más neutrales o respetuosas cuando la relación lo requiera."
        )),
    KanaDay(5, "Acciones recibidas: てくれる y てもらう", "Expresar ayuda o beneficio recibido desde dos perspectivas.",
        listOf(
            "Usar てくれる para destacar que alguien hace algo por el hablante o su grupo.",
            "Usar てもらう para destacar que el hablante recibe el favor o consigue que se haga algo.",
            "Comparar 友だちが手伝ってくれた y 友だちに手伝ってもらった.",
            "Identificar el matiz de gratitud y beneficio en el contexto."
        )),
    KanaDay(6, "Peticiones, favores y consideración", "Usar las construcciones de beneficio con tacto y adecuación interpersonal.",
        listOf(
            "Distinguir una descripción de un favor de una petición directa.",
            "Reconocer てくれませんか y てもらえませんか como formas de pedir ayuda.",
            "Practicar respuestas de aceptación y agradecimiento.",
            "Evitar usar てあげる para ofrecer ayuda a un superior si puede resultar condescendiente."
        )),
    KanaDay(7, "Repaso y evaluación de dar y recibir", "Integrar perspectiva, partículas y expresiones de beneficio.",
        listOf(
            "Identificar dador, receptor y beneficiario en cada oración.",
            "Transformar ejemplos entre あげる, くれる y もらう.",
            "Distinguir てあげる, てくれる y てもらう.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3GivingReceivingAssessment = listOf(
    KanaQuizQuestion("¿Qué verbo suele usarse cuando el hablante da algo a otra persona?", listOf("あげる (ageru)", "くれる (kureru)", "もらう (morau)", "なる (naru)"), 0),
    KanaQuizQuestion("¿Qué verbo suele usarse cuando alguien da algo al hablante?", listOf("くれる (kureru)", "あげる (ageru)", "もらう (morau)", "する (suru)"), 0),
    KanaQuizQuestion("¿Qué verbo expresa recibir algo?", listOf("もらう (morau)", "あげる (ageru)", "くれる (kureru)", "行く (iku)"), 0),
    KanaQuizQuestion("En 友だちが本をくれました, ¿quién recibe el libro?", listOf("El hablante o alguien de su entorno", "El amigo", "Una tercera persona desconocida", "Nadie"), 0),
    KanaQuizQuestion("¿Qué construcción expresa hacer algo por otra persona?", listOf("てあげる", "てくれる", "てもらう", "てしまう"), 0),
    KanaQuizQuestion("¿Qué construcción destaca que alguien hace algo por el hablante?", listOf("てくれる", "てあげる", "てしまう", "ておく"), 0),
    KanaQuizQuestion("¿Qué construcción destaca que el hablante recibe el favor?", listOf("てもらう", "てあげる", "てしまう", "てみる"), 0),
    KanaQuizQuestion("¿Qué partícula puede marcar a la persona de quien se recibe algo?", listOf("に", "を", "で", "へ"), 0),
    KanaQuizQuestion("¿Cuál es una forma de pedir ayuda con respeto?", listOf("手伝ってもらえませんか", "手伝ってあげました", "手伝ってしまいました", "手伝っておきます"), 0),
    KanaQuizQuestion("¿Por qué conviene evitar てあげる con superiores en ciertos contextos?", listOf("Puede sonar condescendiente", "Siempre es gramaticalmente incorrecto", "Solo se usa en pasado", "Indica obligación"), 0),
    KanaQuizQuestion("¿Qué debes identificar antes de elegir あげる, くれる o もらう?", listOf("Quién da, quién recibe y la perspectiva", "Solo el tiempo verbal", "El número de sílabas", "El lugar de la acción"), 0),
    KanaQuizQuestion("¿Qué expresa 手伝ってくれてありがとう?", listOf("Gracias por ayudarme", "Voy a ayudarte", "Te obligué a ayudar", "Recibí un objeto"), 0)
)


// Cuarta unidad gramatical N3: condiciones y supuestos.
internal val n3ConditionalDays = listOf(
    KanaDay(1, "Panorama de las cuatro condiciones", "Distinguir ば, たら, なら y と según el tipo de relación entre condición y resultado.",
        listOf(
            "Identificar la condición y el resultado en una oración.",
            "Reconocer ば como condición lógica o general en muchos contextos.",
            "Reconocer たら como condición o situación que se cumple primero.",
            "Relacionar なら con un tema o supuesto ya planteado y と con resultados naturales o habituales."
        )),
    KanaDay(2, "Formación de ば", "Conjugar verbos y adjetivos para expresar una condición.",
        listOf(
            "Practicar verbos godan: 書く → 書けば y 読む → 読めば.",
            "Practicar verbos ichidan: 食べる → 食べれば y 見る → 見れば.",
            "Aprender las formas irregulares する → すれば y 来る → くれば.",
            "Reconocer adjetivos い: 安い → 安ければ; y sustantivos/adjetivos な con なら."
        )),
    KanaDay(3, "Formación y uso de たら", "Usar la forma pasada informal más ら para condiciones y acciones previas.",
        listOf(
            "Formar 書いたら, 読んだら y 買ったら.",
            "Formar 食べたら, したら y 来たら.",
            "Practicar instrucciones y propuestas después de una condición cumplida.",
            "Distinguir たら condicional de una secuencia temporal según el contexto."
        )),
    KanaDay(4, "Uso de なら", "Presentar una condición basada en información, tema o supuesto previo.",
        listOf(
            "Practicar 日本へ行くなら、京都もおすすめです.",
            "Usar sustantivo + なら y adjetivo な + なら.",
            "Observar que なら suele tomar como punto de partida algo mencionado o supuesto.",
            "Comparar consejos con なら frente a condiciones generales con ば."
        )),
    KanaDay(5, "Uso de と", "Expresar resultados naturales, automáticos o habituales cuando se cumple una condición.",
        listOf(
            "Practicar 春になると、花が咲きます.",
            "Usar la forma diccionario o negativa antes de と.",
            "Reconocer instrucciones de uso y relaciones causa-resultado habituales.",
            "Evitar usar と normalmente para órdenes, invitaciones o decisiones voluntarias del hablante en la cláusula principal."
        )),
    KanaDay(6, "Comparación en diálogos", "Elegir la condición adecuada según intención, contexto y tipo de resultado.",
        listOf(
            "Comparar 雨が降れば, 雨が降ったら, 雨なら y 雨が降ると.",
            "Identificar cuándo se da un consejo, una secuencia o un resultado natural.",
            "Completar diálogos breves con la forma condicional adecuada.",
            "Explicar por qué una opción no encaja con la intención comunicativa."
        )),
    KanaDay(7, "Repaso y evaluación de condiciones", "Consolidar la formación y los matices de las cuatro estructuras.",
        listOf(
            "Conjugar verbos en ば y たら.",
            "Distinguir el supuesto contextual de なら y el resultado natural de と.",
            "Revisar restricciones frecuentes en la cláusula principal.",
            "Completar la evaluación semanal y corregir los errores."
        ))
)

internal val n3ConditionalAssessment = listOf(
    KanaQuizQuestion("¿Cuál es la forma ば de 書く?", listOf("書けば", "書いたら", "書くと", "書いて"), 0),
    KanaQuizQuestion("¿Cuál es la forma ば de 食べる?", listOf("食べれば", "食べたら", "食べると", "食べて"), 0),
    KanaQuizQuestion("¿Cuál es la forma ば de する?", listOf("すれば", "したら", "すると", "して"), 0),
    KanaQuizQuestion("¿Cómo se forma normalmente たら a partir de 読む?", listOf("読んだら", "読めば", "読むと", "読んで"), 0),
    KanaQuizQuestion("¿Qué matiz suele tener なら?", listOf("Parte de un tema o supuesto planteado", "Siempre indica una consecuencia automática", "Solo expresa pasado", "Siempre indica una orden"), 0),
    KanaQuizQuestion("¿Qué relación suele expresar と?", listOf("Un resultado natural o habitual", "Una petición cortés", "Una acción recibida como favor", "Una experiencia pasada aislada"), 0),
    KanaQuizQuestion("¿Cuál es una frase natural para un resultado habitual?", listOf("春になると、花が咲きます", "春になると、いっしょに行きませんか", "春になると、手伝ってください", "春になると、行きましょう"), 0),
    KanaQuizQuestion("¿Qué forma condicional se usa en 日本へ行くなら、京都もおすすめです?", listOf("なら", "ば", "と", "たら"), 0),
    KanaQuizQuestion("¿Cuál es la forma たら de 来る?", listOf("来たら", "くれば", "来ると", "来て"), 0),
    KanaQuizQuestion("¿Qué estructura suele evitarse con と en la cláusula principal?", listOf("Una orden o invitación del hablante", "Un resultado natural", "Un hecho habitual", "Una consecuencia automática"), 0),
    KanaQuizQuestion("¿Cuál es la forma ば de 安い?", listOf("安ければ", "安かったら", "安いと", "安くて"), 0),
    KanaQuizQuestion("¿Qué debes considerar para elegir entre ば, たら, なら y と?", listOf("El contexto y la relación entre condición y resultado", "Solo la longitud de la frase", "Únicamente el tiempo pasado", "El número de kanji"), 0)
)


 
// Quinta unidad gramatical N3: tiempo, simultaneidad y oportunidades.
internal val n3TimeExpressionsDays = listOf(
    KanaDay(1, "間: durante todo un periodo", "Usar 間 para indicar que una situación continúa durante el intervalo completo.",
        listOf(
            "Reconocer la estructura: verbo en forma diccionario o ている + 間.",
            "Practicar 母が料理をしている間、私は勉強しました.",
            "Entender que la acción principal ocurre a lo largo del periodo indicado.",
            "Comparar 間 con 間に: duración completa frente a un hecho dentro del intervalo."
        )),
    KanaDay(2, "間に: algo sucede dentro del intervalo", "Usar 間に cuando una acción o cambio ocurre en algún momento del periodo.",
        listOf(
            "Practicar 母が出かけている間に、部屋を掃除しました.",
            "Identificar el evento delimitado que sucede dentro del intervalo.",
            "Distinguir 間に de 間: no se afirma que el evento dure todo el periodo.",
            "Crear ejemplos con vacaciones, ausencia y tiempo libre."
        )),
    KanaDay(3, "うちに: mientras aún se puede", "Expresar que conviene actuar antes de que cambie una condición o se pierda una oportunidad.",
        listOf(
            "Practicar 若いうちに、いろいろな経験をしたいです.",
            "Usar verbo en forma diccionario, ない o ている + うちに según el sentido.",
            "Reconocer cambios graduales o una ventana de oportunidad.",
            "Comparar うちに con 間に: うちに destaca actuar antes de que la situación cambie."
        )),
    KanaDay(4, "たびに: cada vez que", "Expresar que un hecho se repite cada vez que ocurre otro.",
        listOf(
            "Practicar この写真を見るたびに、旅行を思い出します.",
            "Usar verbo en forma diccionario + たびに.",
            "Practicar sustantivo + の + たびに.",
            "Distinguir una repetición sistemática de un solo evento ocurrido durante un intervalo."
        )),
    KanaDay(5, "途中で y 途中に: en medio de", "Situar un evento durante el desarrollo de una actividad, trayecto o proceso.",
        listOf(
            "Practicar 学校へ行く途中で、友だちに会いました.",
            "Usar verbo en forma diccionario + 途中で para un proceso en curso.",
            "Reconocer sustantivo + の + 途中で.",
            "Observar que 途中に puede señalar un punto o momento intermedio, según el contexto."
        )),
    KanaDay(6, "Comparar expresiones temporales", "Elegir la estructura según duración, evento interno, oportunidad, repetición o proceso interrumpido.",
        listOf(
            "Contrastar 間 y 間に con ejemplos de una misma situación.",
            "Elegir うちに cuando importa actuar antes de un cambio.",
            "Elegir たびに para una relación repetida cada vez.",
            "Usar 途中で para un encuentro o suceso durante un trayecto o actividad."
        )),
    KanaDay(7, "Repaso y evaluación de expresiones temporales", "Integrar las cinco construcciones y explicar sus diferencias.",
        listOf(
            "Identificar el intervalo completo expresado por 間.",
            "Localizar un evento dentro de un intervalo con 間に.",
            "Distinguir oportunidad con うちに y repetición con たびに.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3TimeExpressionsAssessment = listOf(
    KanaQuizQuestion("¿Qué expresa 間 en 母が料理をしている間?", listOf("Durante todo el periodo en que cocina", "Cada vez que cocina", "Antes de que cocine", "En medio del camino"), 0),
    KanaQuizQuestion("¿Qué matiz aporta 間に?", listOf("Un hecho ocurre en algún momento del intervalo", "La acción dura necesariamente todo el intervalo", "Una acción se repite cada vez", "Una condición ya terminó"), 0),
    KanaQuizQuestion("¿Qué expresa うちに en 若いうちに?", listOf("Mientras aún se es joven, antes de que cambie la situación", "Cada vez que se es joven", "En medio de un viaje", "Durante todo el día sin excepción"), 0),
    KanaQuizQuestion("¿Qué estructura significa cada vez que?", listOf("たびに", "間", "途中で", "うちに"), 0),
    KanaQuizQuestion("¿Cuál es una forma correcta con たびに?", listOf("見るたびに", "見た間", "見て途中", "見るうち"), 0),
    KanaQuizQuestion("¿Qué expresa 学校へ行く途中で友だちに会いました?", listOf("Me encontré con un amigo de camino a la escuela", "Vi a un amigo cada vez que fui", "Estuve con un amigo durante todo el día", "Vi a un amigo antes de salir de casa"), 0),
    KanaQuizQuestion("¿Qué construcción destaca actuar antes de que una condición cambie?", listOf("うちに", "たびに", "途中で", "間"), 0),
    KanaQuizQuestion("¿Qué opción expresa un evento puntual dentro del tiempo en que alguien está fuera?", listOf("出かけている間に", "出かけているたびに", "出かけている途中", "出かけている間から"), 0),
    KanaQuizQuestion("¿Cómo se conecta un sustantivo con たびに?", listOf("Sustantivo + の + たびに", "Sustantivo + を + たびに", "Sustantivo + が + たびに", "Sustantivo + で + たびに"), 0),
    KanaQuizQuestion("¿Qué diferencia principal hay entre 間 y 間に?", listOf("間 indica el periodo; 間に sitúa un hecho dentro de él", "Son siempre intercambiables", "間 solo se usa en futuro", "間に solo se usa con sustantivos"), 0),
    KanaQuizQuestion("¿Qué expresión encaja con 'cada vez que veo esta foto'?", listOf("この写真を見るたびに", "この写真を見る間", "この写真を見る途中で", "この写真を見る間に"), 0),
    KanaQuizQuestion("¿Qué expresión se usa para un suceso en medio de un trayecto?", listOf("途中で", "たびに", "うちに", "間に"), 0)
)


 
// Sexta unidad gramatical N3: causa, razón y resultado.
internal val n3CauseReasonResultDays = listOf(
    KanaDay(1, "から y ので: expresar una razón", "Distinguir una explicación más directa o subjetiva de una razón presentada de forma más suave.",
        listOf(
            "Usar forma simple + から para explicar una razón desde la perspectiva del hablante.",
            "Practicar forma simple + ので y sustantivo/adjetivo な + なので.",
            "Comparar 雨が降っているから、行きません y 雨が降っているので、行きません.",
            "Observar que ので suele sonar más suave y explicativo, aunque el contexto y el registro importan."
        )),
    KanaDay(2, "ために: causa y motivo", "Reconocer ために como causa o motivo en contextos donde se explica una situación o resultado.",
        listOf(
            "Practicar 大雪のために、電車が遅れました.",
            "Usar sustantivo + の + ために para indicar causa.",
            "Distinguir el uso causal de ために del uso de propósito: 日本へ行くために勉強します.",
            "Leer la oración completa para decidir si expresa motivo o finalidad."
        )),
    KanaDay(3, "おかげで: resultado favorable", "Atribuir un resultado positivo a una persona, circunstancia o ayuda.",
        listOf(
            "Practicar 先生のおかげで、合格できました.",
            "Usar sustantivo + の + おかげで.",
            "Reconocer el matiz de gratitud o valoración positiva.",
            "Observar que puede usarse irónicamente en ciertos contextos, pero el sentido habitual es favorable."
        )),
    KanaDay(4, "せいで: resultado desfavorable", "Expresar que una causa se considera responsable de un resultado negativo.",
        listOf(
            "Practicar 雨のせいで、試合が中止になりました.",
            "Usar sustantivo + の + せいで y formas simples + せいで.",
            "Distinguir la atribución negativa de la valoración positiva de おかげで.",
            "Evitar usar せいで para atribuir culpa sin considerar el impacto interpersonal."
        )),
    KanaDay(5, "そのため y その結果: conectar causa y consecuencia", "Organizar explicaciones y textos breves mostrando con claridad qué ocurrió como consecuencia.",
        listOf(
            "Usar そのため para introducir una consecuencia relacionada con lo anterior.",
            "Usar その結果 para destacar el resultado obtenido.",
            "Practicar 事故がありました。そのため、道が混んでいます.",
            "Mantener clara la relación lógica entre la causa y la consecuencia."
        )),
    KanaDay(6, "により y によって: causa, medio y agente", "Reconocer usos frecuentes de により y によって en explicaciones más formales y textos informativos.",
        listOf(
            "Practicar 台風によって、多くの木が倒れました.",
            "Reconocer によって para indicar causa o medio según el contexto.",
            "Observar により como variante formal frecuente en avisos y textos escritos.",
            "No asumir que によって siempre significa causa: también puede indicar medio o agente."
        )),
    KanaDay(7, "Repaso y evaluación de causa y resultado", "Elegir conectores y expresiones causales según el matiz y el registro.",
        listOf(
            "Comparar から y ので en explicaciones cotidianas.",
            "Distinguir causa con ために, valoración positiva con おかげで y negativa con せいで.",
            "Conectar ideas con そのため y その結果.",
            "Completar la evaluación semanal y revisar los errores."
        ))
)

internal val n3CauseReasonResultAssessment = listOf(
    KanaQuizQuestion("¿Qué expresión suele presentar una razón de forma más suave?", listOf("ので", "せいで", "たびに", "途中で"), 0),
    KanaQuizQuestion("¿Qué matiz suele tener から?", listOf("Explicación directa desde la perspectiva del hablante", "Resultado siempre positivo", "Repetición de una acción", "Acción en medio de un trayecto"), 0),
    KanaQuizQuestion("En 大雪のために、電車が遅れました, ¿qué expresa ために?", listOf("La causa del retraso", "La finalidad de estudiar", "Una petición", "Una comparación"), 0),
    KanaQuizQuestion("¿Qué expresión atribuye normalmente un resultado favorable a una causa?", listOf("おかげで", "せいで", "のに", "ところで"), 0),
    KanaQuizQuestion("¿Qué expresión atribuye normalmente un resultado desfavorable?", listOf("せいで", "おかげで", "たびに", "うちに"), 0),
    KanaQuizQuestion("¿Cuál es una frase natural para agradecer una ayuda?", listOf("先生のおかげで、合格できました", "先生のせいで、合格できました", "先生の途中で、合格できました", "先生のたびに、合格できました"), 0),
    KanaQuizQuestion("¿Qué conector puede introducir una consecuencia de lo mencionado antes?", listOf("そのため", "あげる", "なら", "ながら"), 0),
    KanaQuizQuestion("¿Qué expresión destaca el resultado que se obtuvo?", listOf("その結果", "その間", "そのたび", "そのうち"), 0),
    KanaQuizQuestion("¿Qué puede indicar によって según el contexto?", listOf("Causa, medio o agente", "Solo una causa positiva", "Solo tiempo futuro", "Una condición hipotética"), 0),
    KanaQuizQuestion("¿Cuál es una variante formal frecuente de によって?", listOf("により", "にくい", "にたい", "にても"), 0),
    KanaQuizQuestion("¿Qué debes comprobar para distinguir el ために causal del de propósito?", listOf("La relación lógica y el contexto de la oración", "Solo si aparece un kanji", "El número de sílabas", "Si la frase está en pasado"), 0),
    KanaQuizQuestion("¿Cuál es la diferencia habitual entre おかげで y せいで?", listOf("おかげで valora favorablemente; せいで atribuye algo desfavorable", "Ambos expresan siempre gratitud", "おかげで indica tiempo y せいで lugar", "No existe diferencia de matiz"), 0)
)

internal fun n3GrammarDaysForWeek(week: Int): List<KanaDay> = when (week) {
    1 -> n3PassiveVoiceDays
    2 -> n3CausativeDays
    3 -> n3GivingReceivingDays
    4 -> n3ConditionalDays
    5 -> n3TimeExpressionsDays
    6 -> n3CauseReasonResultDays
    7 -> n3ChangeDecisionHabitsDays
    8 -> n3AppearanceInformationProbabilityDays
    else -> emptyList()
}

internal fun n3GrammarAssessmentForWeek(week: Int): List<KanaQuizQuestion> = when (week) {
    1 -> n3PassiveVoiceAssessment
    2 -> n3CausativeAssessment
    3 -> n3GivingReceivingAssessment
    4 -> n3ConditionalAssessment
    5 -> n3TimeExpressionsAssessment
    6 -> n3CauseReasonResultAssessment
    7 -> n3ChangeDecisionHabitsAssessment
    8 -> n3AppearanceInformationProbabilityAssessment
    else -> emptyList()
}
