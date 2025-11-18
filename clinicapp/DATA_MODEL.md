# Modelo de datos persistente

Este documento resume las tablas creadas automáticamente por JPA y sus _adapters_ asociados.

## Autenticación y control de acceso
- **accounts** (`AccountEntity`, `SpringDataAccountRepository`): almacena las credenciales y el rol de la cuenta que usa JWT. Campos clave: `username` (único), `password_hash`, `role`, `subject_document`.
- **users** (`UserEntity`, `SpringDataUserRepository`, `UserJpaAdapter`): datos personales y rol de los colaboradores (RR.HH., médicos, enfermería, etc.). `document` es único y se usa para enlazar con historias clínicas y validaciones de rol.

## Pacientes
- **patients** (`PatientEntity`, `SpringDataPatientRepository`, `PatientJpaAdapter`): datos demográficos básicos del paciente (`document`, `full_name`, `birth_date`, `gender`, `address`, `phone`, `email`).

## Signos vitales y registros clínicos
- **vital_signs** (`VitalSignsEntity`, `SpringDataVitalSignsRepository`, `VitalSignsJpaAdapter`): historial de signos vitales por paciente. Incluye `patient_id_number`, `nurse_id_number`, `recorded_at`, tensión arterial, temperatura, pulso y oxigenación.
- **medical_history_entries / nursing_records / clinical_history_entries**: tablas para entradas clínicas y registros de enfermería (ver adaptadores en `adapter/out/persistence/jpa/history`, `.../nursing` y `.../clinical`). Se usan para episodios, evoluciones y órdenes médicas.

## Órdenes y procedimientos
- **orders**, **order_medications**, **order_procedures**, **order_diagnostics** (`OrderEntity`, `OrderMedicationEntity`, `OrderProcedureEntity`, `OrderDiagnosticEntity`): modelan órdenes médicas y sus ítems especializados. Repositorios en `adapter/out/persistence/jpa/order`.
- **procedures** y **medications** (`ProcedureEntity`, `MedicationEntity`): catálogos de procedimientos y medicamentos asociados a las órdenes.

> Para pruebas locales se usa H2 en memoria (perfil `test`) con `ddl-auto=create-drop`, mientras que en entornos reales el `application.yml` apunta a MySQL.
