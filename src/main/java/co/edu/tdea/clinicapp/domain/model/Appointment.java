package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;

public class Appointment {
    private final String patientIdNumber; 
    private final String doctorIdNumber;  
    private final LocalDateTime scheduledAt;
    private AppointmentStatus status;

    public Appointment(String patientIdNumber, String doctorIdNumber, LocalDateTime scheduledAt) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("Cédula de paciente requerida.");
        if (doctorIdNumber == null || doctorIdNumber.isBlank())
            throw new IllegalArgumentException("ID de médico requerido.");
        if (scheduledAt == null)
            throw new IllegalArgumentException("Fecha/hora de cita requerida.");
        if (scheduledAt.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("La fecha/hora de la cita debe ser futura.");

        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.scheduledAt = scheduledAt;
        this.status = AppointmentStatus.SCHEDULED;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
}
