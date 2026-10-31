# Privacidad y protección de datos

- Todo el contenido se queda en el dispositivo. No hay servicios externos ni analítica.
- Las contraseñas se almacenan como hash PBKDF2 con salt aleatorio.
- Las sesiones y tokens se cifran localmente con Android Keystore.
- `allowBackup` está desactivado en el manifiesto.
- La pantalla de login y de recuperación usa `FLAG_SECURE`.
- La eliminación de cuenta borra el usuario, progreso y token asociado.
- La exportación de progreso se genera como JSON local para el usuario.
