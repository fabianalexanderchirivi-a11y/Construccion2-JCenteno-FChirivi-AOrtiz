package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class CompleteAppointmentCommand {
    private final String patientIdNumber;
    private final LocalDateTime scheduledAt;

    public CompleteAppointmentCommand(String patientIdNumber, LocalDateTime scheduledAt) {
        this.patientIdNumber = patientIdNumber;
        this.scheduledAt = scheduledAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
}
