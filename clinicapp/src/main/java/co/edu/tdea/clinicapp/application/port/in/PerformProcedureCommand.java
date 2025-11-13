package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class PerformProcedureCommand {
    private final String patientIdNumber;
    private final String nurseIdNumber;
    private final String procedureId;
    private final int quantity; // >=1
    private final LocalDateTime performedAt;

    public PerformProcedureCommand(String patientIdNumber, String nurseIdNumber,
                                   String procedureId, int quantity, LocalDateTime performedAt) {
        this.patientIdNumber = patientIdNumber;
        this.nurseIdNumber = nurseIdNumber;
        this.procedureId = procedureId;
        this.quantity = quantity;
        this.performedAt = performedAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public String getProcedureId() { return procedureId; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getPerformedAt() { return performedAt; }
}
