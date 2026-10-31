# Nihongo Academy

## Requisitos
- Android Studio Ladybug o superior
- JDK 17
- Android SDK 35

## Compilación
```bash
./gradlew clean assembleDebug
./gradlew assembleRelease
```

## Tests
```bash
./gradlew test
```

## Arquitectura
- Compose + Material 3
- MVVM + StateFlow
- Room + DataStore + Keystore
- Lógica de repaso adaptativa y validación de contraseña

## Añadir idiomas
1. Edita `app/src/main/assets/languages.json`.
2. Crea un JSON nuevo en `app/src/main/assets/content/<code>.json`.
3. Recompila.
