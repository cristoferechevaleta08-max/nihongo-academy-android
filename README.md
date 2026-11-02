# Nihongo Academy — reconstrucción Android

Proyecto Android nativo reconstruido en una rama separada a partir de `Nihongo_Academy_Ideas_App.pdf`.

## Base técnica
- Kotlin + Jetpack Compose.
- Interfaz en español; tema oscuro y claro.
- Datos locales persistentes con DataStore.
- Sin dependencia de Google Play Services.
- CI con Java 17, Gradle 8.9, compilación APK y pruebas unitarias.

## Alcance incorporado
- Selector de 15 idiomas de estudio, con japonés como curso publicado en esta versión.
- Ruta curricular japonesa N5 (10 unidades) y N4 (8 unidades).
- 111 ejercicios; cada unidad tiene entre 5 y 7 ejercicios.
- Lecciones con corrección inmediata y aprobación mínima del 70%.
- Cuaderno de errores persistente con explicación y respuesta correcta.
- Plan visual N5 de 12 semanas y resumen de transición a N4 de 16 semanas.
- Simulacro de práctica cronometrado.
- Sobres diarios de tres cartas y colección persistente.
- Perfil local, nombre y preferencia de tema.

## Pendiente antes de considerar el producto completo
- Evaluaciones semanales del plan con repetición automática si se falla más del 30%, desbloqueo secuencial y pausa/reanudación persistente.
- Completar la práctica específica de hiragana/katakana, kanji y escucha descrita en el plan.
- Completar los contenidos de inglés y los otros idiomas; el selector no representa cursos ya terminados.
- Cuentas seguras, sincronización y comunidad online mediante backend.
- Evaluación fonética de pronunciación; el reconocedor Android actual solo devuelve texto reconocido.
- Revisión nativa del contenido japonés: el PDF advierte que los textos fuente están en romaji y deben revisarse antes de publicarse.

## Reglas del documento fuente
- No incorporar flashcards: el PDF indica que el repaso se basa en el cuaderno de errores.
- No marcar una lección como completada si no alcanza el 70%.
- No presentar las funciones pendientes como si ya estuvieran implementadas.

Requiere Android Studio, JDK 17 y Android SDK 35.
