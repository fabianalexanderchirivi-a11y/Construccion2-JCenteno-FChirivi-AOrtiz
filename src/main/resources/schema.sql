-- Crear BD (MariaDB 10.4)
CREATE DATABASE IF NOT EXISTS clinicapp
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE clinicapp;

-- (el resto de tablas igual; no uses la colación 0900_ai_ci)

CREATE USER IF NOT EXISTS 'clinic'@'localhost' IDENTIFIED BY 'clinic123';
GRANT ALL PRIVILEGES ON clinicapp.* TO 'clinic'@'localhost';
FLUSH PRIVILEGES;


-- Tabla de ÓRDENES (SQL, estructurada)
-- Campos exigidos: número de orden, cédula paciente, cédula médico, fecha creación. :contentReference[oaicite:3]{index=3}
CREATE TABLE IF NOT EXISTS orders (
  order_number INT NOT NULL,            -- máx. 6 dígitos (control en app) :contentReference[oaicite:4]{index=4}
  patient_id   VARCHAR(10) NOT NULL,    -- cédula paciente
  doctor_id    VARCHAR(10) NOT NULL,    -- cédula médico (máx. 10 dígitos) :contentReference[oaicite:5]{index=5}
  created_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (order_number)
) ENGINE=InnoDB;

-- Tabla especializada: MEDICAMENTOS (nombre, dosis, duración, costo) :contentReference[oaicite:6]{index=6}
CREATE TABLE IF NOT EXISTS order_medications (
  order_number INT NOT NULL,
  item_number  INT NOT NULL,            -- ítem dentro de la orden (comienza en 1) :contentReference[oaicite:7]{index=7}
  medication_name VARCHAR(120) NOT NULL,
  dose            VARCHAR(80)  NOT NULL,
  treatment_days  INT          NOT NULL,
  cost            DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (order_number, item_number),
  CONSTRAINT fk_om_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;

-- Tabla especializada: PROCEDIMIENTOS (nombre, repeticiones, frecuencia, costo, requiere especialista, id especialidad) :contentReference[oaicite:8]{index=8}
CREATE TABLE IF NOT EXISTS order_procedures (
  order_number INT NOT NULL,
  item_number  INT NOT NULL,
  procedure_name VARCHAR(120) NOT NULL,
  times          INT NOT NULL,
  frequency      VARCHAR(60) NOT NULL,
  cost           DECIMAL(12,2) NOT NULL,
  requires_specialist BOOLEAN NOT NULL DEFAULT FALSE,
  specialist_type_id  VARCHAR(20) NULL,
  PRIMARY KEY (order_number, item_number),
  CONSTRAINT fk_op_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;

-- Tabla especializada: AYUDAS DIAGNÓSTICAS (nombre, cantidad, costo) :contentReference[oaicite:9]{index=9}
CREATE TABLE IF NOT EXISTS order_diagnostics (
  order_number INT NOT NULL,
  item_number  INT NOT NULL,
  diagnostic_name VARCHAR(120) NOT NULL,
  quantity         INT NOT NULL,
  cost             DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (order_number, item_number),
  CONSTRAINT fk_od_order FOREIGN KEY (order_number) REFERENCES orders(order_number)
) ENGINE=InnoDB;
