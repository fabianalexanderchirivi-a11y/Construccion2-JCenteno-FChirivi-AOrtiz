# Resumen de avance

## Autenticación y seguridad
- **Login JWT**: endpoint `POST /auth/login` autentica con `AuthenticationManager`, busca el usuario en la tabla `account` y genera un token JWT con rol e id del sujeto usando `JwtService`. Responde con token, rol e id, más fecha de expiración.
- **Filtro de seguridad**: configuración global deshabilita CSRF, usa sesiones sin estado, permite libre acceso solo a `/actuator/health` y `/auth/login`, y protege el resto con filtro JWT antes del `UsernamePasswordAuthenticationFilter`.

## Gestión de pacientes
- **CRUD restringido**: `PatientController` expone creación y actualización solo para `ADMIN_STAFF`, mientras que las consultas de pacientes individuales o listados están habilitadas para `ADMIN_STAFF`, `DOCTOR` y `NURSE`.
- **Mapeo de DTOs a dominio**: las peticiones construyen comandos para registrar/actualizar, incluyendo datos de contacto de emergencia y póliza; las respuestas devuelven el paciente con dichos subobjetos serializados.
- **Servicio de registro**: valida duplicidad de documento, levanta `IllegalArgumentException` y persiste el paciente con su contacto de emergencia y póliza de seguro mediante el repositorio de pacientes.

## Gestión de personal
- **Usuarios de RR.HH.**: `UserController` queda bajo rol `HUMAN_RESOURCES` e incluye endpoints para crear usuarios, consultarlos, listarlos y eliminarlos con los casos de uso correspondientes.

## Próximos pasos sugeridos
- Se documentó el modelo de datos persistente en `DATA_MODEL.md`, cubriendo entidades y repositorios JPA clave.
- Se implementaron los endpoints REST de signos vitales (`POST/GET /api/patients/{id}/vitals`) con servicios reales, validación de pacientes/enfermeras y persistencia JPA.
- Quedan pendientes las pruebas automatizadas; se priorizó completar los controladores y la persistencia solicitada sin añadir archivos de prueba.
