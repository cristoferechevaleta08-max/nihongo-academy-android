# N5: contenido implementado y alcance real

Este documento describe lo que está integrado en la rama de trabajo. No se presenta como certificación oficial JLPT.

## Kanji (semanas 9–10)

- Catálogo local de 100 kanji con significado, lecturas, vocabulario y ejemplos.
- Fichas de estudio, repaso espaciado local, práctica de escritura libre y animación del orden de trazos cuando existe el dato correspondiente.
- Dos evaluaciones semanales de 10 preguntas generadas desde el catálogo integrado.
- La selección es curricular de Nihongo Academy; no se denomina lista oficial JLPT. El JLPT dejó de publicar listas fijas de kanji, vocabulario y gramática tras la revisión de 2010.
- Los datos de orden de trazos derivados de KanjiVG conservan su atribución y licencia en THIRD_PARTY_NOTICES.md.

## Comprensión auditiva (semana 11)

- Seis diálogos originales: cafetería, estación, escuela, planes y clima, tienda e invitación.
- Cada diálogo contiene cuatro intervenciones, texto japonés, lectura en romaji, traducción y tres preguntas de comprensión.
- Total: 24 intervenciones y 18 preguntas originales.
- La aplicación utiliza Android TextToSpeech con Locale.JAPANESE para generar voz en el propio dispositivo. No descarga audios ni incorpora grabaciones protegidas.
- La reproducción depende de que el dispositivo tenga un motor TTS y una voz japonesa instalada. Si no la tiene, los guiones, lecturas y preguntas siguen disponibles; la app informa de la limitación y no finge reproducir audio.
- La pestaña Escucha permite reproducir el diálogo completo o cada intervención, ocultar/mostrar romaji y traducción, y comprobar la comprensión.

## Evaluación final de práctica (semana 12)

- Evaluación integrada de 30 preguntas: 15 preguntas extraídas de las unidades N5, tres preguntas sobre lecturas breves originales y 12 preguntas de comprensión auditiva.
- El banco es original y local. La evaluación guarda resultado y errores mediante el mecanismo del plan semanal.
- Es un simulacro breve de práctica de Nihongo Academy; no reproduce la duración ni toda la estructura del examen oficial JLPT y no debe presentarse como examen oficial.

## Contenido N5 y criterios

- Diez unidades N5 en el orden del plan, con ejercicios y tareas prácticas.
- Las respuestas libres se autoevalúan: la app no afirma corregir texto libre automáticamente.
- La práctica de pronunciación mediante reconocimiento de voz devuelve texto reconocido; no equivale a una evaluación fonética profesional.
- El progreso, las tarjetas y el repaso se guardan localmente.

## Derechos y privacidad del material

- Los diálogos y preguntas de escucha son originales de Nihongo Academy.
- No se copian páginas de libros, cursos comerciales, bancos de preguntas ni audios oficiales.
- No se generan documentos o cuadernillos descargables como parte de estas semanas: el material se presenta dentro de la app.
- La referencia al JLPT describe el nivel y sirve como orientación de diseño; no implica afiliación, aprobación ni certificación por parte del JLPT.

## Verificaciones antes de publicar

- Ejecutar `:app:assembleDebug` y `:app:testDebugUnitTest` en CI.
- Probar la reproducción TTS en un dispositivo real Huawei/Android sin Google Play Services, confirmar si tiene voz japonesa y documentar la ruta de instalación de datos de voz si fuera necesario.
- Revisar los guiones y lecturas japonesas con una persona competente/nativa antes de considerar el contenido lingüísticamente definitivo.
- Comprobar que las semanas 9–12 se pueden aprobar y que N4 se habilita únicamente al completar las doce semanas.
