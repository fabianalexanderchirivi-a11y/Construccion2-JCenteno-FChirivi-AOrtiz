package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class AdministerMedicationCommand {
    private final String patientIdNumber;
    private final String nurseIdNumber;
    private final String medicationId;
    private final int quantity; // >=1
    private final LocalDateTime performedAt;

    public AdministerMedicationCommand(String patientIdNumber, String nurseIdNumber,
                                       String medicationId, int quantity, LocalDateTime performedAt) {
        this.patientIdNumber = patientIdNumber;
        this.nurseIdNumber = nurseIdNumber;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.performedAt = performedAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public String getMedicationId() { return medicationId; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getPerformedAt() { return performedAt; }
}
