# Arquitectura

## Capa de presentacion
- `ui`: pantallas, tema, componentes, navegación.
- `viewmodel`: lógica de UI y orquestación de casos de uso.

## Capa de dominio
- `domain`: modelos, validadores, motor de repaso y lógica de progreso.
- `repository`: acceso a datos y caché local.

## Capa de datos
- `data.local`: Room, DAO, entitites y migraciones.
- `data.preferences`: DataStore y Keystore.
- `data.assets`: carga del catálogo de idiomas desde JSON.

## Flujo de autenticación
1. Registro con validación de contraseña y generación de frase de recuperación.
2. Hash con PBKDF2 y guardado de salt.
3. Persistencia de sesión en Datastore cifrado con Android Keystore.
4. Redirección a rutas protegidas si no existe sesión.

## Modelo del catálogo
Cada idioma se define en `assets/languages.json` con:
- código
- nombre español
- nombre nativo
- sistema de escritura
- dirección de texto
- módulos disponibles

## Motor de repaso
El motor repasa ejercicios fallidos antes que los dominados. Su algoritmo usa un score por ejercicio, reprograma en ventana corta para fallos y suaviza la dificultad cuando hay consistencia positiva.

## Seguridad
- No se registran contraseñas ni frases de recuperación.
- Los tokens se cifran en Android Keystore con AES-GCM.
- Los bloqueos por intentos fallidos viven en Room.
