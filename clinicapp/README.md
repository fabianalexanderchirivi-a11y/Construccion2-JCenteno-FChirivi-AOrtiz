# 🏥 ClinicApp

<table>
  <tr>
    <th>📚 Subject</th>
    <td>Construcción de Software 2</td>
  </tr>
  <tr>
    <th>👥 Team</th>
    <td>
      Jesús Adrián Centeno Montiel <br>
      Fabián Alexander Chirivi Pinzón <br>
      Alejandro Ortiz Acevedo
    </td>
  </tr>
  <tr>
    <th>🛠️ Technology</th>
    <td>Java 17 · Spring Boot 3.5.5 · Maven</td>
  </tr>
  <tr>
    <th>📖 Description</th>
    <td>
      Information Management System for a Clinic IPS.  
      Manages patients, staff, roles, appointments,  
      billing, and clinical records.
    </td>
  </tr>
</table>

## Endpoints clave

Los controladores REST expuestos incluyen:

- `/api/patients`: registrar, actualizar y consultar pacientes (rol administrativo).
- `/api/users`: alta, baja y consulta de cuentas del personal (rol de RR.HH.).
- `/api/catalog`: mantenimiento de catálogos de medicamentos, procedimientos y ayudas diagnósticas (rol de soporte).
- `/api/orders`: prescripción de órdenes médicas y consulta por paciente o médico (rol médico).
- `/api/medical-history`: registro y consulta de notas de historia clínica (rol médico).
- `/api/nursing`: registro de signos vitales, medicamentos administrados y procedimientos de enfermería (rol enfermería).
- `/api/appointments`: agendamiento y consulta de citas (rol administrativo).
- `/api/billing`: generación y consulta de facturas y acumulado de copagos (rol facturación).

Todos los endpoints se protegen con JWT y autorización basada en roles, según la configuración de seguridad incluida en el proyecto.

## Pruebas rápidas en Postman/cURL (rol HUMAN_RESOURCES)

1. **Obtener token JWT**
   ```bash
   curl -X POST http://localhost:8081/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"rh1","password":"<TU_CLAVE>"}'
   ```
   Copia el valor de `token` de la respuesta.

2. **Usar el token en llamadas permitidas** (RR.HH.):
   - Listar usuarios
     ```bash
     curl -X GET http://localhost:8081/api/users \
       -H "Authorization: Bearer <TOKEN>"
     ```
   - Crear usuario
     ```bash
     curl -X POST http://localhost:8081/api/users \
       -H "Authorization: Bearer <TOKEN>" \
       -H "Content-Type: application/json" \
       -d '{"document":"123456789","fullName":"Nuevo Usuario","email":"nuevo@demo.com","role":"DOCTOR","address":"Calle 1","phone":"3001234567","birthDate":"1990-01-01"}'
     ```
   - Actualizar usuario
     ```bash
     curl -X PUT http://localhost:8081/api/users/123456789 \
       -H "Authorization: Bearer <TOKEN>" \
       -H "Content-Type: application/json" \
       -d '{"fullName":"Usuario Actualizado","email":"actualizado@demo.com","role":"DOCTOR","address":"Calle 2","phone":"3007654321","birthDate":"1990-01-01"}'
     ```
   - Eliminar usuario
     ```bash
     curl -X DELETE http://localhost:8081/api/users/123456789 \
       -H "Authorization: Bearer <TOKEN>"
     ```

3. **Comprobar operaciones denegadas** (deben responder 403):
   - Pacientes/administrativo: `curl -i -X GET http://localhost:8081/api/patients -H "Authorization: Bearer <TOKEN>"`
   - Órdenes médicas: `curl -i -X GET http://localhost:8081/api/orders -H "Authorization: Bearer <TOKEN>"`
   - Catálogos/facturación: `curl -i -X GET http://localhost:8081/api/catalog/medications -H "Authorization: Bearer <TOKEN>"`
   - Signos vitales/enfermería: `curl -i -X GET http://localhost:8081/api/vital-signs -H "Authorization: Bearer <TOKEN>"`

Si alguna operación permitida retorna 401, verifica que el header `Authorization` incluya el JWT (`Bearer <token>`). Si una operación debería permitirse y devuelve 403, revisa el rol configurado en la tabla `accounts` para ese usuario.

## Uso en Postman paso a paso

1. **Login** (pestaña *Body* → *raw* → *JSON*):
   ```json
   {
     "username": "rh1",
     "password": "<TU_CLAVE>"
   }
   ```
   - Endpoint: `POST http://localhost:8081/api/auth/login`
   - Copia `token` de la respuesta.

2. **Configurar el header para todas las peticiones protegidas**:
   - En *Headers*: `Authorization` = `Bearer <token>` (sin espacios extra).
   - Marca el header como “Active” para que se envíe en cada request.

3. **Crear usuario (solo rol HUMAN_RESOURCES)**:
   - Endpoint: `POST http://localhost:8081/api/users`
   - *Headers*: `Authorization: Bearer <token>`, `Content-Type: application/json`
   - *Body* (*raw*, *JSON*):
     ```json
     {
       "document": "123456789",
       "fullName": "Nuevo Usuario",
       "email": "nuevo@demo.com",
       "role": "DOCTOR",
       "address": "Calle 1",
       "phone": "3001234567",
       "birthDate": "1990-01-01"
     }
     ```
   - Restricción de dominio: `document` (cédula) debe tener entre 1 y 10 dígitos; si envías vacío o más de 10 dígitos, la API responde con `La cédula debe tener entre 1 y 10 dígitos.`

4. **Diagnóstico de 403 en Postman**:
   - Revisa que el header `Authorization` esté presente con el token vigente.
   - Confirma que la cuenta que usas tiene `role = HUMAN_RESOURCES` en la tabla `accounts`.
   - Si el rol es correcto y persiste el 403, el endpoint podría requerir otro rol (ejemplo: `/api/patients` es ADMINISTRATIVE/ADMIN_STAFF). Consulta la matriz de roles de la sección “Endpoints clave”.

5. **Reutilizar el token**:
   - Guarda el token en una variable de entorno de Postman (p. ej. `token`) y referencia `{{token}}` en el header para no copiarlo manualmente en cada request.

### Comando rápido de verificación (RR.HH.)
Para validar que el token de un usuario con rol **HUMAN_RESOURCES** funciona, ejecuta desde consola o Postman (en la pestaña *Terminal* de la colección) el siguiente `curl` sustituyendo `<TOKEN>` por tu JWT:

```bash
curl -i -X GET http://localhost:8081/api/users \
  -H "Authorization: Bearer <TOKEN>"
```

Si el rol es correcto, la respuesta será **200 OK** con el listado de usuarios; si recibes **403 Forbidden**, revisa que el `Authorization` esté presente y que la cuenta realmente tenga el rol de RR.HH.
