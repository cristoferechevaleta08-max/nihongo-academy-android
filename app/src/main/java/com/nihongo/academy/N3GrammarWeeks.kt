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

internal fun n3GrammarDaysForWeek(week: Int): List<KanaDay> = when (week) {
    1 -> n3PassiveVoiceDays
    2 -> n3CausativeDays
    3 -> n3GivingReceivingDays
    else -> emptyList()
}

internal fun n3GrammarAssessmentForWeek(week: Int): List<KanaQuizQuestion> = when (week) {
    1 -> n3PassiveVoiceAssessment
    2 -> n3CausativeAssessment
    3 -> n3GivingReceivingAssessment
    else -> emptyList()
}
