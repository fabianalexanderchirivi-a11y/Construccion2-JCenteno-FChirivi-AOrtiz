package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class ScheduleAppointmentCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final LocalDateTime scheduledAt;

    public ScheduleAppointmentCommand(String patientIdNumber, String doctorIdNumber, LocalDateTime scheduledAt) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.scheduledAt = scheduledAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
}
