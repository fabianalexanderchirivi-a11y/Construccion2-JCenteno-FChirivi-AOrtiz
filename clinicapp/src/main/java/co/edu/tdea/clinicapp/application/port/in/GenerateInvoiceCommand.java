package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;

public class GenerateInvoiceCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final List<BilledMedicationInput> medications;
    private final List<BilledProcedureInput> procedures;
    private final List<BilledDiagnosticAidInput> diagnosticAids;

    public GenerateInvoiceCommand(String patientIdNumber,
                                  String doctorIdNumber,
                                  List<BilledMedicationInput> medications,
                                  List<BilledProcedureInput> procedures,
                                  List<BilledDiagnosticAidInput> diagnosticAids) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.medications = medications;
        this.procedures = procedures;
        this.diagnosticAids = diagnosticAids;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public List<BilledMedicationInput> getMedications() { return medications; }
    public List<BilledProcedureInput> getProcedures() { return procedures; }
    public List<BilledDiagnosticAidInput> getDiagnosticAids() { return diagnosticAids; }
}
