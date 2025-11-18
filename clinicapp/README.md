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
