package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class RescheduleAppointmentCommand {
    private final String patientIdNumber;
    private final LocalDateTime oldScheduledAt;
    private final LocalDateTime newScheduledAt;

    public RescheduleAppointmentCommand(String patientIdNumber,
                                        LocalDateTime oldScheduledAt,
                                        LocalDateTime newScheduledAt) {
        this.patientIdNumber = patientIdNumber;
        this.oldScheduledAt = oldScheduledAt;
        this.newScheduledAt = newScheduledAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public LocalDateTime getOldScheduledAt() { return oldScheduledAt; }
    public LocalDateTime getNewScheduledAt() { return newScheduledAt; }
}
