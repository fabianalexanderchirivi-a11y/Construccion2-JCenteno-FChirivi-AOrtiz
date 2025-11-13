package co.edu.tdea.clinicapp.adapter.in.cli;

import co.edu.tdea.clinicapp.application.dto.InvoiceDto;
import co.edu.tdea.clinicapp.application.port.in.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicConsoleMenu {

    // ==== Proveedores opcionales (no bloquean el arranque si faltan beans) ====
    // Usuarios/Pacientes/Citas
    private final ObjectProvider<RegisterUserUseCase> registerUserUseCase;
    private final ObjectProvider<GetUserUseCase> getUserUseCase;
    private final ObjectProvider<ListUsersUseCase> listUsersUseCase;
    private final ObjectProvider<UpdateUserUseCase> updateUserUseCase;
    private final ObjectProvider<DeleteUserUseCase> deleteUserUseCase;

    private final ObjectProvider<RegisterPatientUseCase> registerPatientUseCase;
    private final ObjectProvider<UpdatePatientUseCase> updatePatientUseCase;
    private final ObjectProvider<GetPatientUseCase> getPatientUseCase;
    private final ObjectProvider<ListPatientsUseCase> listPatientsUseCase;

    private final ObjectProvider<ScheduleAppointmentUseCase> scheduleAppointmentUseCase;
    private final ObjectProvider<RescheduleAppointmentUseCase> rescheduleAppointmentUseCase;
    private final ObjectProvider<CancelAppointmentUseCase> cancelAppointmentUseCase;
    private final ObjectProvider<CompleteAppointmentUseCase> completeAppointmentUseCase;
    private final ObjectProvider<GetAppointmentUseCase> getAppointmentUseCase;
    private final ObjectProvider<ListAppointmentsByPatientUseCase> listAppointmentsByPatientUseCase;
    private final ObjectProvider<ListAppointmentsByDoctorUseCase> listAppointmentsByDoctorUseCase;

    // Inventarios
    private final ObjectProvider<UpsertMedicationUseCase> upsertMedicationUseCase;
    private final ObjectProvider<ListMedicationsUseCase> listMedicationsUseCase;
    private final ObjectProvider<DeleteMedicationUseCase> deleteMedicationUseCase;

    private final ObjectProvider<UpsertProcedureUseCase> upsertProcedureUseCase;
    private final ObjectProvider<ListProceduresUseCase> listProceduresUseCase;
    private final ObjectProvider<DeleteProcedureUseCase> deleteProcedureUseCase;

    private final ObjectProvider<UpsertDiagnosticAidUseCase> upsertDiagnosticAidUseCase;
    private final ObjectProvider<ListDiagnosticAidsUseCase> listDiagnosticAidsUseCase;
    private final ObjectProvider<DeleteDiagnosticAidUseCase> deleteDiagnosticAidUseCase;

    // Órdenes
    private final ObjectProvider<PrescribeMedicationUseCase> prescribeMedicationUseCase;
    private final ObjectProvider<PrescribeProcedureUseCase> prescribeProcedureUseCase;
    private final ObjectProvider<RequestDiagnosticAidUseCase> requestDiagnosticAidUseCase;

    private final ObjectProvider<ListOpenMedicationOrdersByPatientUseCase> listOpenMedicationOrdersByPatientUseCase;
    private final ObjectProvider<ListOpenProcedureOrdersByPatientUseCase> listOpenProcedureOrdersByPatientUseCase;
    private final ObjectProvider<ListOpenDiagnosticAidOrdersByPatientUseCase> listOpenDiagnosticAidOrdersByPatientUseCase;

    // Enfermería / Historia / Facturación
    private final ObjectProvider<RecordVitalSignsUseCase> recordVitalSignsUseCase;
    private final ObjectProvider<RecordDiagnosticResultUseCase> recordDiagnosticResultUseCase;
    private final ObjectProvider<GenerateInvoiceUseCase> generateInvoiceUseCase;

    private final Scanner in = new Scanner(System.in);
    private final DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Autowired
    public ClinicConsoleMenu(
            // Usuarios/Pacientes/Citas
            ObjectProvider<RegisterUserUseCase> registerUserUseCase,
            ObjectProvider<GetUserUseCase> getUserUseCase,
            ObjectProvider<ListUsersUseCase> listUsersUseCase,
            ObjectProvider<UpdateUserUseCase> updateUserUseCase,
            ObjectProvider<DeleteUserUseCase> deleteUserUseCase,
            ObjectProvider<RegisterPatientUseCase> registerPatientUseCase,
            ObjectProvider<UpdatePatientUseCase> updatePatientUseCase,
            ObjectProvider<GetPatientUseCase> getPatientUseCase,
            ObjectProvider<ListPatientsUseCase> listPatientsUseCase,
            ObjectProvider<ScheduleAppointmentUseCase> scheduleAppointmentUseCase,
            ObjectProvider<RescheduleAppointmentUseCase> rescheduleAppointmentUseCase,
            ObjectProvider<CancelAppointmentUseCase> cancelAppointmentUseCase,
            ObjectProvider<CompleteAppointmentUseCase> completeAppointmentUseCase,
            ObjectProvider<GetAppointmentUseCase> getAppointmentUseCase,
            ObjectProvider<ListAppointmentsByPatientUseCase> listAppointmentsByPatientUseCase,
            ObjectProvider<ListAppointmentsByDoctorUseCase> listAppointmentsByDoctorUseCase,

            // Inventarios
            ObjectProvider<UpsertMedicationUseCase> upsertMedicationUseCase,
            ObjectProvider<ListMedicationsUseCase> listMedicationsUseCase,
            ObjectProvider<DeleteMedicationUseCase> deleteMedicationUseCase,
            ObjectProvider<UpsertProcedureUseCase> upsertProcedureUseCase,
            ObjectProvider<ListProceduresUseCase> listProceduresUseCase,
            ObjectProvider<DeleteProcedureUseCase> deleteProcedureUseCase,
            ObjectProvider<UpsertDiagnosticAidUseCase> upsertDiagnosticAidUseCase,
            ObjectProvider<ListDiagnosticAidsUseCase> listDiagnosticAidsUseCase,
            ObjectProvider<DeleteDiagnosticAidUseCase> deleteDiagnosticAidUseCase,

            // Órdenes
            ObjectProvider<PrescribeMedicationUseCase> prescribeMedicationUseCase,
            ObjectProvider<PrescribeProcedureUseCase> prescribeProcedureUseCase,
            ObjectProvider<RequestDiagnosticAidUseCase> requestDiagnosticAidUseCase,
            ObjectProvider<ListOpenMedicationOrdersByPatientUseCase> listOpenMedicationOrdersByPatientUseCase,
            ObjectProvider<ListOpenProcedureOrdersByPatientUseCase> listOpenProcedureOrdersByPatientUseCase,
            ObjectProvider<ListOpenDiagnosticAidOrdersByPatientUseCase> listOpenDiagnosticAidOrdersByPatientUseCase,

            // Enfermería / Historia / Facturación
            ObjectProvider<RecordVitalSignsUseCase> recordVitalSignsUseCase,
            ObjectProvider<RecordDiagnosticResultUseCase> recordDiagnosticResultUseCase,
            ObjectProvider<GenerateInvoiceUseCase> generateInvoiceUseCase
    ) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.registerPatientUseCase = registerPatientUseCase;
        this.updatePatientUseCase = updatePatientUseCase;
        this.getPatientUseCase = getPatientUseCase;
        this.listPatientsUseCase = listPatientsUseCase;
        this.scheduleAppointmentUseCase = scheduleAppointmentUseCase;
        this.rescheduleAppointmentUseCase = rescheduleAppointmentUseCase;
        this.cancelAppointmentUseCase = cancelAppointmentUseCase;
        this.completeAppointmentUseCase = completeAppointmentUseCase;
        this.getAppointmentUseCase = getAppointmentUseCase;
        this.listAppointmentsByPatientUseCase = listAppointmentsByPatientUseCase;
        this.listAppointmentsByDoctorUseCase = listAppointmentsByDoctorUseCase;

        this.upsertMedicationUseCase = upsertMedicationUseCase;
        this.listMedicationsUseCase = listMedicationsUseCase;
        this.deleteMedicationUseCase = deleteMedicationUseCase;
        this.upsertProcedureUseCase = upsertProcedureUseCase;
        this.listProceduresUseCase = listProceduresUseCase;
        this.deleteProcedureUseCase = deleteProcedureUseCase;
        this.upsertDiagnosticAidUseCase = upsertDiagnosticAidUseCase;
        this.listDiagnosticAidsUseCase = listDiagnosticAidsUseCase;
        this.deleteDiagnosticAidUseCase = deleteDiagnosticAidUseCase;

        this.prescribeMedicationUseCase = prescribeMedicationUseCase;
        this.prescribeProcedureUseCase = prescribeProcedureUseCase;
        this.requestDiagnosticAidUseCase = requestDiagnosticAidUseCase;
        this.listOpenMedicationOrdersByPatientUseCase = listOpenMedicationOrdersByPatientUseCase;
        this.listOpenProcedureOrdersByPatientUseCase = listOpenProcedureOrdersByPatientUseCase;
        this.listOpenDiagnosticAidOrdersByPatientUseCase = listOpenDiagnosticAidOrdersByPatientUseCase;

        this.recordVitalSignsUseCase = recordVitalSignsUseCase;
        this.recordDiagnosticResultUseCase = recordDiagnosticResultUseCase;
        this.generateInvoiceUseCase = generateInvoiceUseCase;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            println("");
            println("=== Clínica IPS — Menú Principal ===");
            println("1. Gestión de Usuarios");
            println("2. Gestión de Pacientes");
            println("3. Citas");
            println("4. Inventarios");
            println("5. Órdenes");
            println("6. Enfermería / Signos vitales");
            println("7. Historia clínica — Resultados diagnósticos");
            println("8. Facturación");
            println("0. Salir");
            int op = askInt("Seleccione una opción: ");
            try {
                switch (op) {
                    case 1 -> showUserMenu();
                    case 2 -> showPatientMenu();
                    case 3 -> showAppointmentMenu();
                    case 4 -> showInventoryMenu();
                    case 5 -> showOrdersMenu();
                    case 6 -> showNurseMenu();
                    case 7 -> showClinicalHistoryMenu();
                    case 8 -> showBillingMenu();
                    case 0 -> { println("Saliendo..."); exit = true; }
                    default -> println("Opción no válida.");
                }
            } catch (Exception ex) {
                println("Error: " + ex.getMessage());
            }
        }
    }

    // ========== INVENTARIOS ==========
    private void showInventoryMenu() {
        boolean back = false;
        while (!back) {
            println("");
            println("--- Inventarios ---");
            println("1. Medicamentos");
            println("2. Procedimientos");
            println("3. Ayudas diagnósticas");
            println("0. Volver");
            int op = askInt("Seleccione una opción: ");
            switch (op) {
                case 1 -> inventoryMedications();
                case 2 -> inventoryProcedures();
                case 3 -> inventoryDiagnosticAids();
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void inventoryMedications() {
        UpsertMedicationUseCase upsert = upsertMedicationUseCase.getIfAvailable();
        ListMedicationsUseCase list = listMedicationsUseCase.getIfAvailable();
        DeleteMedicationUseCase del = deleteMedicationUseCase.getIfAvailable();
        if (upsert == null || list == null || del == null) { println("Funcionalidad no disponible: Medicamentos."); return; }

        boolean back = false;
        while (!back) {
            println("");
            println(">>> Inventario de Medicamentos");
            println("1. Crear/Actualizar");
            println("2. Eliminar");
            println("3. Listar");
            println("0. Volver");
            int op = askInt("Seleccione: ");
            switch (op) {
                case 1 -> {
                    String id = askString("Id (1-10 alfanumérico): ");
                    String name = askString("Nombre: ");
                    int unitCost = askInt("Costo unitario (COP): ");
                    var saved = upsert.upsert(new UpsertMedicationCommand(id, name, unitCost));
                    println("OK: " + saved.getId() + " | " + saved.getName() + " | " + saved.getUnitCost());
                }
                case 2 -> {
                    String id = askString("Id: ");
                    del.deleteById(id);
                    println("Eliminado.");
                }
                case 3 -> {
                    var all = list.list();
                    if (all.isEmpty()) println("(sin medicamentos)");
                    else all.forEach(m -> println(m.getId() + " | " + m.getName() + " | " + m.getUnitCost()));
                }
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void inventoryProcedures() {
        UpsertProcedureUseCase upsert = upsertProcedureUseCase.getIfAvailable();
        ListProceduresUseCase list = listProceduresUseCase.getIfAvailable();
        DeleteProcedureUseCase del = deleteProcedureUseCase.getIfAvailable();
        if (upsert == null || list == null || del == null) { println("Funcionalidad no disponible: Procedimientos."); return; }

        boolean back = false;
        while (!back) {
            println("");
            println(">>> Inventario de Procedimientos");
            println("1. Crear/Actualizar");
            println("2. Eliminar");
            println("3. Listar");
            println("0. Volver");
            int op = askInt("Seleccione: ");
            switch (op) {
                case 1 -> {
                    String id = askString("Id: ");
                    String name = askString("Nombre: ");
                    int unitCost = askInt("Costo unitario (COP): ");
                    var saved = upsert.upsert(new UpsertProcedureCommand(id, name, unitCost));
                    println("OK: " + saved.getId() + " | " + saved.getName() + " | " + saved.getUnitCost());
                }
                case 2 -> {
                    String id = askString("Id: ");
                    del.deleteById(id);
                    println("Eliminado.");
                }
                case 3 -> {
                    var all = list.list();
                    if (all.isEmpty()) println("(sin procedimientos)");
                    else all.forEach(p -> println(p.getId() + " | " + p.getName() + " | " + p.getUnitCost()));
                }
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void inventoryDiagnosticAids() {
        UpsertDiagnosticAidUseCase upsert = upsertDiagnosticAidUseCase.getIfAvailable();
        ListDiagnosticAidsUseCase list = listDiagnosticAidsUseCase.getIfAvailable();
        DeleteDiagnosticAidUseCase del = deleteDiagnosticAidUseCase.getIfAvailable();
        if (upsert == null || list == null || del == null) { println("Funcionalidad no disponible: Ayudas diagnósticas."); return; }

        boolean back = false;
        while (!back) {
            println("");
            println(">>> Inventario de Ayudas diagnósticas");
            println("1. Crear/Actualizar");
            println("2. Eliminar");
            println("3. Listar");
            println("0. Volver");
            int op = askInt("Seleccione: ");
            switch (op) {
                case 1 -> {
                    String id = askString("Id: ");
                    String name = askString("Nombre: ");
                    int unitCost = askInt("Costo unitario (COP): ");
                    var saved = upsert.upsert(new UpsertDiagnosticAidCommand(id, name, unitCost));
                    println("OK: " + saved.getId() + " | " + saved.getName() + " | " + saved.getUnitCost());
                }
                case 2 -> {
                    String id = askString("Id: ");
                    del.deleteById(id);
                    println("Eliminado.");
                }
                case 3 -> {
                    var all = list.list();
                    if (all.isEmpty()) println("(sin ayudas)");
                    else all.forEach(a -> println(a.getId() + " | " + a.getName() + " | " + a.getUnitCost()));
                }
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    // ========== ÓRDENES ==========
    private void showOrdersMenu() {
        boolean back = false;
        while (!back) {
            println("");
            println("--- Órdenes ---");
            println("1. Prescribir medicamentos");
            println("2. Prescribir procedimientos");
            println("3. Solicitar ayudas diagnósticas");
            println("4. Listar órdenes abiertas por paciente (MEDICAMENTOS)");
            println("5. Listar órdenes abiertas por paciente (PROCEDIMIENTOS)");
            println("6. Listar órdenes abiertas por paciente (AYUDAS)");
            println("0. Volver");
            int op = askInt("Seleccione una opción: ");
            switch (op) {
                case 1 -> prescribeMedicationsFlow();
                case 2 -> prescribeProceduresFlow();
                case 3 -> requestDiagnosticAidsFlow();
                case 4 -> listOpenOrdersMedication();
                case 5 -> listOpenOrdersProcedure();
                case 6 -> listOpenOrdersDiagnostic();
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void prescribeMedicationsFlow() {
        PrescribeMedicationUseCase uc = prescribeMedicationUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: prescripción de medicamentos."); return; }

        println("");
        println(">>> Prescribir medicamentos");
        String patient = askString("Cédula del paciente: ");
        String doctor  = askString("Cédula del médico: ");
        List<MedicationItemInput> items = new ArrayList<>();
        boolean more = true;
        while (more) {
            String medId = askString("Id medicamento: ");
            int qty = askInt("Cantidad: ");
            String dose = askString("Dosis (ej. 500mg): ");
            String freq = askString("Frecuencia (ej. cada 8h): ");
            int days = askInt("Duración (días): ");
            items.add(new MedicationItemInput(medId, qty, dose, freq, days));
            more = askYN("¿Agregar otro ítem? (s/n): ");
        }
        var order = uc.prescribe(new PrescribeMedicationCommand(patient, doctor, items));
        println("Orden creada (MEDICATION). Ítems: " + order.getItems().size());
    }

    private void prescribeProceduresFlow() {
        PrescribeProcedureUseCase uc = prescribeProcedureUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: prescripción de procedimientos."); return; }

        println("");
        println(">>> Prescribir procedimientos");
        String patient = askString("Cédula del paciente: ");
        String doctor  = askString("Cédula del médico: ");
        List<ProcedureItemInput> items = new ArrayList<>();
        boolean more = true;
        while (more) {
            String procId = askString("Id procedimiento: ");
            int qty = askInt("Cantidad/veces: ");
            String freq = askString("Frecuencia (opcional, Enter para omitir): ");
            String spec = askString("Especialista (opcional, Enter para omitir): ");
            items.add(new ProcedureItemInput(procId, qty, emptyToNull(freq), emptyToNull(spec)));
            more = askYN("¿Agregar otro ítem? (s/n): ");
        }
        var order = uc.prescribe(new PrescribeProcedureCommand(patient, doctor, items));
        println("Orden creada (PROCEDURE). Ítems: " + order.getItems().size());
    }

    private void requestDiagnosticAidsFlow() {
        RequestDiagnosticAidUseCase uc = requestDiagnosticAidUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: solicitud de ayudas diagnósticas."); return; }

        println("");
        println(">>> Solicitar ayudas diagnósticas");
        String patient = askString("Cédula del paciente: ");
        String doctor  = askString("Cédula del médico: ");
        List<DiagnosticAidItemInput> items = new ArrayList<>();
        boolean more = true;
        while (more) {
            String aidId = askString("Id ayuda diagnóstica: ");
            int qty = askInt("Cantidad: ");
            items.add(new DiagnosticAidItemInput(aidId, qty));
            more = askYN("¿Agregar otro ítem? (s/n): ");
        }
        var order = uc.request(new RequestDiagnosticAidCommand(patient, doctor, items));
        println("Orden creada (DIAGNOSTIC_AID). Ítems: " + order.getItems().size());
    }

    private void listOpenOrdersMedication() {
        ListOpenMedicationOrdersByPatientUseCase uc = listOpenMedicationOrdersByPatientUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: listar órdenes de medicamentos."); return; }

        String patient = askString("Cédula del paciente: ");
        var list = uc.list(patient);
        if (list.isEmpty()) { println("(sin órdenes abiertas)"); return; }
        list.forEach(o -> {
            println("Orden MEDICATION — ítems: " + o.getItems().size());
            o.getItems().forEach(i -> println("  Ítem " + i.getItemNumber() + " | catalogId=" + i.getCatalogId() + " | qty=" + i.getQuantity()));
        });
    }

    private void listOpenOrdersProcedure() {
        ListOpenProcedureOrdersByPatientUseCase uc = listOpenProcedureOrdersByPatientUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: listar órdenes de procedimientos."); return; }

        String patient = askString("Cédula del paciente: ");
        var list = uc.list(patient);
        if (list.isEmpty()) { println("(sin órdenes abiertas)"); return; }
        list.forEach(o -> {
            println("Orden PROCEDURE — ítems: " + o.getItems().size());
            o.getItems().forEach(i -> println("  Ítem " + i.getItemNumber() + " | catalogId=" + i.getCatalogId() + " | qty=" + i.getQuantity()));
        });
    }

    private void listOpenOrdersDiagnostic() {
        ListOpenDiagnosticAidOrdersByPatientUseCase uc = listOpenDiagnosticAidOrdersByPatientUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: listar órdenes de ayudas."); return; }

        String patient = askString("Cédula del paciente: ");
        var list = uc.list(patient);
        if (list.isEmpty()) { println("(sin órdenes abiertas)"); return; }
        list.forEach(o -> {
            println("Orden DIAGNOSTIC_AID — ítems: " + o.getItems().size());
            o.getItems().forEach(i -> println("  Ítem " + i.getItemNumber() + " | catalogId=" + i.getCatalogId() + " | qty=" + i.getQuantity()));
        });
    }

    // ========== ENFERMERÍA ==========
    private void showNurseMenu() {
        boolean back = false;
        while (!back) {
            println("");
            println("--- Enfermería / Signos vitales ---");
            println("1. Registrar signos vitales");
            println("0. Volver");
            int op = askInt("Seleccione una opción: ");
            switch (op) {
                case 1 -> recordVitals();
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void recordVitals() {
        RecordVitalSignsUseCase uc = recordVitalSignsUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: registro de signos vitales."); return; }

        println("");
        println(">>> Registrar signos vitales");
        String patient = askString("Cédula del paciente: ");
        String nurse   = askString("Cédula de la enfermera: ");
        Integer sys = askInt("Sistólica (mmHg): ");
        Integer dia = askInt("Diastólica (mmHg): ");
        Double temp = askDouble("Temperatura (°C): ");
        Integer pulse = askInt("Pulso (lpm): ");
        Integer ox = askInt("Oxígeno (%): ");

        var cmd = new RecordVitalSignsCommand(patient, nurse, LocalDateTime.now(), sys, dia, temp, pulse, ox);
        var rec = uc.record(cmd);
        println("Signos registrados a las " + rec.getRecordedAt().format(dtFmt) + " por " + rec.getNurseIdNumber() + ".");
    }

    // ========== HISTORIA CLÍNICA ==========
    private void showClinicalHistoryMenu() {
        boolean back = false;
        while (!back) {
            println("");
            println("--- Historia clínica ---");
            println("1. Registrar resultado de ayuda diagnóstica");
            println("0. Volver");
            int op = askInt("Seleccione una opción: ");
            switch (op) {
                case 1 -> recordDiagnosticResult();
                case 0 -> back = true;
                default -> println("Opción no válida.");
            }
        }
    }

    private void recordDiagnosticResult() {
        RecordDiagnosticResultUseCase uc = recordDiagnosticResultUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: registro de resultados diagnósticos."); return; }

        println("");
        println(">>> Registrar resultado de ayuda diagnóstica");
        String patient = askString("Cédula del paciente: ");
        String doctor  = askString("Cédula del médico: ");
        String reason  = askString("Motivo de consulta: ");
        String symptoms= askString("Síntomas: ");
        String diagnosis = askString("Diagnóstico final: ");
        String orderNumber = askString("Número de orden (diagnóstico): ");
        Integer itemNumber = askInt("Número de ítem en la orden: ");
        String result = askString("Resultado de la ayuda: ");

        var cmd = new RecordDiagnosticResultCommand(
                patient, doctor, LocalDateTime.now(),
                diagnosis, orderNumber, itemNumber, result, reason, symptoms
        );
        uc.record(cmd);
        println("Resultado registrado en historia clínica.");
    }

    // ========== FACTURACIÓN ==========
    private void showBillingMenu() {
        GenerateInvoiceUseCase uc = generateInvoiceUseCase.getIfAvailable();
        if (uc == null) { println("Funcionalidad no disponible: facturación."); return; }

        println("");
        println(">>> Generar factura (según contrato actual del proyecto)");
        String patient = askString("Cédula del paciente: ");
        Integer year = askInt("Año (yyyy): ");

        try {
            InvoiceDto invoice = uc.generateForPatient(patient, year);
            println("--- FACTURA ---");
            if (invoice == null) {
                println("(sin datos de factura)");
            } else {
                // Imprime representación del DTO para evitar depender de getters específicos
                println(invoice.toString());
            }
        } catch (Exception ex) {
            println("Error al generar factura: " + ex.getMessage());
        }
    }

    // ==== UTILIDADES ====
    private String askString(String prompt) { System.out.print(prompt); return in.nextLine().trim(); }
    private int askInt(String prompt) {
        while (true) {
            try { System.out.print(prompt); return Integer.parseInt(in.nextLine().trim()); }
            catch (Exception e) { println("Entrada inválida. Intente de nuevo."); }
        }
    }
    private double askDouble(String prompt) {
        while (true) {
            try { System.out.print(prompt); return Double.parseDouble(in.nextLine().trim()); }
            catch (Exception e) { println("Entrada inválida. Intente de nuevo."); }
        }
    }
    private boolean askYN(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("s")) return true;
            if (s.equals("n")) return false;
            println("Responda 's' o 'n'.");
        }
    }
    private String emptyToNull(String s) { return (s == null || s.isBlank()) ? null : s; }
    private void println(String s) { System.out.println(s); }

    // Menús aún no implementados (no bloquean el arranque)
    private void showUserMenu() { println("(menú de usuarios no disponible en esta versión)"); }
    private void showPatientMenu() { println("(menú de pacientes no disponible en esta versión)"); }
    private void showAppointmentMenu() { println("(menú de citas no disponible en esta versión)"); }
}
