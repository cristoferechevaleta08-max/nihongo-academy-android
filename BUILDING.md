# Build de prueba

La compilación de Android se ejecuta mediante GitHub Actions en cada push.
El artefacto `nihongo-academy-debug-apk` se publica únicamente cuando
`:app:assembleDebug` y `:app:testDebugUnitTest` finalizan correctamente.
