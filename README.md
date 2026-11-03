# Nihongo Academy — Android

Aplicación Android nativa en desarrollo, con interfaz en español.

## Base técnica
- Kotlin + Jetpack Compose.
- Tema oscuro y claro.
- Persistencia local con DataStore.
- Compatible con dispositivos Android sin Google Play Services.
- Integración continua para compilar el APK y ejecutar pruebas unitarias.

## Estado del proyecto

### Ruta N5
- Diez unidades con objetivos, gramática, vocabulario, ejemplos y ejercicios.
- Preferencia para mostrar romaji o kana.
- Corrección de ejercicios y cuaderno de errores.
- Plan de estudio de doce semanas con seguimiento del progreso y evaluaciones semanales.
- Biblioteca de 100 kanji N5 con lecturas, vocabulario y ejemplos.
- Repaso espaciado y seguimiento local con DataStore.
- Práctica de escritura libre con el dedo y examen de reconocimiento.
- Animación de orden de trazos basada en KanjiVG.
- Seis diálogos originales de escucha y práctica de comprensión.
- Simulacro final integrado con gramática, vocabulario, lectura y escucha.

### Ruta N4
- Ocho unidades de gramática con explicaciones ampliadas, vocabulario, ejemplos y ejercicios.
- Seis diálogos originales de escucha con preguntas de comprensión.
- Tres lecturas originales con ejercicios.
- Evaluación final integrada de 30 preguntas.
- Catálogo pedagógico de 181 kanji N4, sin duplicar los caracteres del catálogo N5.
- Fichas con lecturas, vocabulario, ejemplos y traducciones.
- Repaso espaciado, seguimiento local y examen de reconocimiento.
- Animación de orden de trazos para los 181 kanji, con datos vectoriales incluidos para uso sin conexión.
- Práctica de escritura libre con el dedo; no se afirma que exista corrección automática del orden o la dirección de los trazos.
- El acceso al bloque N4 se mantiene condicionado a completar el plan N5.

## Alcance y limitaciones
- Los 181 kanji N4 constituyen una selección pedagógica de la aplicación, no una lista oficial cerrada publicada por JLPT.
- El selector de idiomas no significa que todos esos cursos estén disponibles.
- Las funciones que dependan de cuentas, sincronización o comunidad requieren una fase posterior.
- El audio de escucha usa la voz japonesa instalada en Android mediante TextToSpeech; la disponibilidad y calidad de voz dependen del dispositivo.
- La aplicación no requiere Google Play Services para sus funciones locales descritas.

## Contenido y propiedad intelectual
- El PDF de planificación es una referencia interna de desarrollo; no se distribuye desde la aplicación ni se ofrece como descarga.
- Los documentos de `docs/` son notas técnicas internas para el equipo, no materiales de estudio para el usuario final.
- Las lecciones, explicaciones, ejemplos, preguntas y diálogos publicados deben ser originales o contar con una licencia o permiso que autorice su uso.
- No se copiarán páginas de libros, cursos comerciales, bancos de preguntas ni audios protegidos.
- Las referencias al JLPT se usan para describir objetivos y formatos de práctica; Nihongo Academy no está afiliada, aprobada ni certificada por JLPT.
- Los datos de orden de trazos derivados de KanjiVG conservan su atribución y licencia CC BY-SA 3.0; ver `THIRD_PARTY_NOTICES.md`.

## Reglas de calidad
- No declarar terminada una función que todavía esté pendiente.
- No presentar una selección curricular propia como una lista oficial del JLPT.
- No desbloquear etapas de estudio hasta que sus materiales estén preparados y revisados.

Requiere Android Studio, JDK 17 y Android SDK 35 para compilar localmente.
