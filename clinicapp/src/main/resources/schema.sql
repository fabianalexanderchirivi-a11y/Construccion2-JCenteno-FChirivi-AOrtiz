-- Seguridad / cuentas
CREATE TABLE IF NOT EXISTS accounts (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  username         VARCHAR(30)  NOT NULL UNIQUE,
  password_hash    VARCHAR(100) NOT NULL,
  role             VARCHAR(30)  NOT NULL,
  subject_type     VARCHAR(10)  NOT NULL,
  subject_document VARCHAR(15)  NOT NULL,
  CONSTRAINT uk_accounts_username UNIQUE (username)
) ENGINE=InnoDB;

-- Usuarios de negocio (RRHH)
CREATE TABLE IF NOT EXISTS users (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  document   VARCHAR(15) NOT NULL UNIQUE,
  full_name  VARCHAR(120) NOT NULL,
  email      VARCHAR(120) NOT NULL UNIQUE,
  phone      VARCHAR(30),
  birth_date DATE,
  address    VARCHAR(120),
  role       VARCHAR(30) NOT NULL
) ENGINE=InnoDB;

-- Pacientes
CREATE TABLE IF NOT EXISTS patients (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  document   VARCHAR(15) NOT NULL UNIQUE,
  full_name  VARCHAR(120) NOT NULL,
  email      VARCHAR(120),
  phone      VARCHAR(30),
  birth_date DATE,
  address    VARCHAR(120),
  gender     VARCHAR(10)
) ENGINE=InnoDB;

-- Citas
CREATE TABLE IF NOT EXISTS appointments (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_document  VARCHAR(15) NOT NULL,
  doctor_document   VARCHAR(15) NOT NULL,
  scheduled_at      DATETIME    NOT NULL,
  reason            VARCHAR(160),
  CONSTRAINT uk_doc_time UNIQUE (doctor_document, scheduled_at),
  CONSTRAINT uk_pat_time UNIQUE (patient_document, scheduled_at)
) ENGINE=InnoDB;

-- Catálogos
CREATE TABLE IF NOT EXISTS medications (
  id        VARCHAR(10)  NOT NULL PRIMARY KEY,
  name      VARCHAR(150) NOT NULL,
  unit_cost INT          NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS procedures (
  id        VARCHAR(10)  NOT NULL PRIMARY KEY,
  name      VARCHAR(150) NOT NULL,
  unit_cost INT          NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS diagnostic_aids (
  id        VARCHAR(10)  NOT NULL PRIMARY KEY,
  name      VARCHAR(150) NOT NULL,
  unit_cost INT          NOT NULL
) ENGINE=InnoDB;

-- Órdenes médicas
CREATE TABLE IF NOT EXISTS orders (
  order_number    BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_document VARCHAR(15) NOT NULL,
  doctor_document  VARCHAR(15) NOT NULL,
  created_at       DATETIME    NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS order_medications (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_number    BIGINT       NOT NULL,
  item_number     INT          NOT NULL,
  medication_name VARCHAR(120) NOT NULL,
  dose            VARCHAR(80)  NOT NULL,
  duration        VARCHAR(80),
  cost            DECIMAL(12,2) NOT NULL,
  CONSTRAINT fk_om_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS order_procedures (
  id                   BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_number         BIGINT       NOT NULL,
  item_number          INT          NOT NULL,
  procedure_name       VARCHAR(120) NOT NULL,
  times                INT          NOT NULL,
  frequency            VARCHAR(60)  NOT NULL,
  requires_specialist  BOOLEAN,
  specialist_type_id   VARCHAR(20),
  cost                 DECIMAL(12,2) NOT NULL,
  CONSTRAINT fk_op_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS order_diagnostics (
  id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_number        BIGINT       NOT NULL,
  item_number         INT          NOT NULL,
  diagnostic_name     VARCHAR(120) NOT NULL,
  quantity            INT          NOT NULL,
  requires_specialist BOOLEAN,
  specialist_type_id  VARCHAR(20),
  cost                DECIMAL(12,2) NOT NULL,
  CONSTRAINT fk_od_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;

-- Historia clínica y notas médicas
CREATE TABLE IF NOT EXISTS clinical_history (
  id                         BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_id_number          VARCHAR(10) NOT NULL,
  attended_at                DATETIME    NOT NULL,
  doctor_id_number           VARCHAR(10) NOT NULL,
  reason                     VARCHAR(255),
  symptoms                   VARCHAR(255),
  diagnosis                  VARCHAR(255) NOT NULL,
  diagnostic_aid_order_number VARCHAR(40),
  diagnostic_aid_item_number  INT,
  diagnostic_aid_result       VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS medical_history (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_document  VARCHAR(15) NOT NULL,
  date_time         DATETIME    NOT NULL,
  doctor_document   VARCHAR(15) NOT NULL,
  reason            VARCHAR(160),
  symptoms          TEXT,
  diagnosis         TEXT,
  payload           TEXT,
  INDEX idx_history_patient (patient_document),
  UNIQUE KEY uk_history_patient_date (patient_document, date_time)
) ENGINE=InnoDB;

-- Signos vitales y enfermería
CREATE TABLE IF NOT EXISTS vital_signs (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_id_number VARCHAR(30) NOT NULL,
  nurse_id_number   VARCHAR(30) NOT NULL,
  recorded_at       DATETIME    NOT NULL,
  systolic          INT,
  diastolic         INT,
  temperature       DOUBLE,
  pulse             INT,
  oxygen            INT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS nursing_records (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  action_type        VARCHAR(40) NOT NULL,
  performed_at       DATETIME    NOT NULL,
  patient_id_number  VARCHAR(30) NOT NULL,
  nurse_id_number    VARCHAR(30) NOT NULL,
  order_type         VARCHAR(40) NOT NULL,
  order_created_at   DATETIME    NOT NULL,
  item_number        INT         NOT NULL,
  catalog_id         VARCHAR(50) NOT NULL,
  quantity           INT         NOT NULL
) ENGINE=InnoDB;

-- Facturación
CREATE TABLE IF NOT EXISTS invoices (
  invoice_number BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_number   BIGINT       NOT NULL,
  total_amount   DECIMAL(12,2) NOT NULL,
  created_at     DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS invoice_items (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  invoice_number BIGINT       NOT NULL,
  item_number    INT          NOT NULL,
  description    VARCHAR(255) NOT NULL,
  amount         DECIMAL(12,2) NOT NULL,
  item_type      VARCHAR(30)  NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS billing_ledger (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  patient_id_number VARCHAR(10) NOT NULL,
  year              INT         NOT NULL,
  amount            INT         NOT NULL
) ENGINE=InnoDB;
