# Arquitectura

## Capas
- Presentación: Compose + Navigation + ViewModel
- Dominio: validación de contraseñas, repaso adaptativo y modelos del producto
- Datos: JSON de catálogo, Room y preferencias locales
- Seguridad: hash PBKDF2, Keystore y sesión local

## Flujo principal
1. Bienvenida
2. Registro o login
3. Selección de idioma
4. Pantalla principal y estudio
5. Ajustes y progreso

## Modelado
El catálogo de idiomas vive en `app/src/main/assets/languages.json` para permitir extensión sin tocar pantallas.
