# Nihongo Academy — Android

Aplicación Android nativa en desarrollo, con interfaz en español.

## Base técnica
- Kotlin + Jetpack Compose.
- Tema oscuro y claro.
- Persistencia local con DataStore.
- Compatible con dispositivos Android sin Google Play Services.
- Integración continua para compilar el APK y ejecutar pruebas unitarias.

## Estado del proyecto
- Ruta de japonés N5 organizada en diez unidades.
- Contenidos de las unidades: objetivo, gramática, vocabulario, ejemplos y ejercicios.
- Preferencia para mostrar romaji o kana.
- Corrección de ejercicios y cuaderno de errores.
- Plan de estudio N5 de doce semanas, con seguimiento del progreso.
- La biblioteca inicial de kanji N5 está integrada en la navegación, con 100 fichas locales, lecturas, vocabulario, ejemplos y seguimiento local.
- El repaso de reconocimiento y la programación espaciada están implementados con DataStore local.
- La práctica de escritura libre con el dedo está disponible; la animación de orden de trazos, la validación automática y el examen completo de kanji siguen pendientes de implementación y revisión.
- Las semanas de escucha y simulacro final permanecen pendientes de materiales originales y revisión; no se presentan como terminadas.
- N4 no se habilita como siguiente etapa hasta cerrar y validar N5.
- El selector de idiomas no significa que todos esos cursos estén disponibles.
- Las funciones que dependan de cuentas, sincronización o comunidad requieren una fase posterior.

## Contenido y propiedad intelectual
- El PDF de planificación es una referencia interna de desarrollo; no se distribuye desde la aplicación ni se ofrece como descarga.
- Los documentos de `docs/` son notas técnicas internas para el equipo, no materiales de estudio para el usuario final.
- Las lecciones, explicaciones, ejemplos, preguntas y audios publicados deben ser originales o contar con una licencia o permiso que autorice su uso.
- No se copiarán páginas de libros, cursos comerciales, bancos de preguntas ni audios protegidos.
- Las referencias al JLPT se usan para describir objetivos y formatos de práctica; Nihongo Academy no está afiliada, aprobada ni certificada por JLPT.
- Antes de publicar, se revisarán las licencias de recursos externos, las atribuciones necesarias y el contenido japonés.

## Reglas de calidad
- No declarar terminada una función que todavía esté pendiente.
- No presentar una selección curricular propia como una lista oficial del JLPT.
- No desbloquear kanji, escucha ni simulacro final hasta que sus materiales estén preparados y revisados.

Requiere Android Studio, JDK 17 y Android SDK 35 para compilar localmente.
