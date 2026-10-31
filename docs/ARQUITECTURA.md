# Arquitectura

- Presentación: Activity + Compose + Navigation
- Dominio: validación de contraseña y repaso adaptativo
- Datos: Room, DataStore y token cifrado con Keystore
- Configuración: catálogo de idiomas en JSON

## Flujos principales
- Bienvenida → registro / login → selección de idioma → home
- Ajustes y progreso por idioma
- Ruta de estudio con lección por sesión

## Privacidad
- Todo queda en el dispositivo.
- Sin servicios externos ni Firebase.
- `allowBackup=false`.
