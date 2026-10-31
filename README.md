# Nihongo Academy

Aplicación Android nativa para aprender idiomas, con identidad editorial en blanco y negro, progreso por idioma y motor de repaso adaptativo.

## Requisitos
- Android Studio Ladybug o superior
- JDK 17
- Android SDK 35
- Dispositivo recomendado: Pixel 8 / simulador API 34+

## Compilación
```bash
./gradlew clean assembleDebug
./gradlew assembleRelease
```

## Firma con keystore
```bash
keytool -genkeypair -v -keystore release-keystore.jks -alias nihongo -keyalg RSA -keysize 2048 -validity 10000 -storepass <PASSWORD> -keypass <PASSWORD> -dname "CN=Nihongo Academy, OU=Mobile, O=Nihongo Academy, L=Remote, S=Remote, C=ES"
./gradlew assembleRelease
```

## Tests
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## Fuentes locales y cobertura
- `Noto Sans`: latín, cirílico, vietnamita y compatibilidad general.
- `Noto Serif`/`Noto Sans JP`: japonés (hiragana, katakana, kanji).
- `Noto Sans SC`: hanzi simplificados.
- `Noto Sans Devanagari`: hindi y sánscrito.
- `Noto Sans Bengali`: bengalí.
- `Noto Naskh Arabic`: árabe.
- `Noto Sans Kannada`/`Noto Sans Arabic`/`Noto Sans Symbols`: soporte ampliado para escrituras y signos.

## Añadir un idioma nuevo
1. Añade la entrada en `assets/languages.json`.
2. Crea el JSON de palabras o frases para ese idioma bajo `assets/content/<code>.json`.
3. Si el sistema de escritura usa RTL, establece `textDirection = "RTL"`.
4. Recompila; la app carga las lecciones sin tocar pantallas.

## Arquitectura
- UI: Jetpack Compose + Material 3
- Estado: ViewModel + StateFlow
- Persistencia: Room + DataStore + Keystore
- DI: inyección manual para reducir complejidad y mantener trazabilidad sin SDK externo
