# Nihongo Academy Android

Aplicación Android nativa para estudiar idiomas, construida con Kotlin, Jetpack Compose y Material 3. La interfaz está en español y utiliza una identidad visual monocromática.

## Requisitos

- Android Studio Ladybug o superior
- JDK 17
- Android SDK 35
- Gradle 8.7 (incluido mediante el wrapper)

## Compilar y probar

En Windows:

```powershell
.gradlew.bat :app:assembleDebug
.gradlew.bat :app:testDebugUnitTest
```

En macOS o Linux:

```bash
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest
```

El APK de depuración se genera en:

`app/build/outputs/apk/debug/app-debug.apk`

## Funciones implementadas

- Catálogo de 15 idiomas y selección del idioma activo.
- Diez módulos por idioma con lecciones y ejercicios de opción múltiple.
- Evaluaciones intermedias y finales de dos preguntas, con puntuación y umbral de aprobación del 60 %.
- Progreso por idioma, XP, niveles, recompensas y rachas.
- Tarjetas de memoria con intervalos de repetición espaciada y almacenamiento local.
- Pronunciación mediante Android TextToSpeech cuando el dispositivo dispone de una voz compatible.
- Registro e inicio de sesión locales, contraseñas protegidas con PBKDF2 y código de recuperación.
- Restablecimiento de contraseña mediante el código de recuperación, que se renueva después de cada uso.
- Preferencias persistentes de apariencia (sistema, claro u oscuro), pronunciación y reducción de animaciones.

## Estado y límites importantes

La aplicación ya compila y las pruebas unitarias configuradas en GitHub Actions pasan. El flujo de CI también genera un artefacto APK de depuración.

Todavía no es una versión de producción ni una plataforma conectada a la nube:

- Las cuentas, sesiones y avances se guardan localmente en el dispositivo. No existe sincronización entre dispositivos.
- El código de recuperación se muestra al crear la cuenta y debe guardarse fuera del dispositivo. Si se pierde junto con los datos locales, no hay recuperación remota.
- La comunidad y las preguntas/respuestas en línea necesitan un backend y moderación; todavía no están conectadas.
- El inicio de sesión con Google no está configurado.
- La pronunciación usa las voces instaladas en Android; no incluye grabaciones de audio profesionales.
- El catálogo cubre 15 idiomas, pero los programas educativos necesitan ampliarse y revisarse antes de considerarlos cursos completos o preparación oficial JLPT/CEFR.

## GitHub Actions

El workflow `.github/workflows/android-ci.yml` compila el APK, ejecuta las pruebas unitarias y publica el artefacto `nihongo-academy-debug-apk`.

Consulta las ejecuciones y descarga el APK desde la pestaña **Actions** del repositorio.
