package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.appointment;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments",
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_doc_time", columnNames = {"doctor_document","scheduled_at"}),
           @UniqueConstraint(name = "uk_pat_time", columnNames = {"patient_document","scheduled_at"})
       })
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_document", nullable = false, length = 15)
    private String patientDocument;

    @Column(name = "doctor_document", nullable = false, length = 15)
    private String doctorDocument;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(length = 160)
    private String reason;

    public Long getId() { return id; }
    public String getPatientDocument() { return patientDocument; }
    public String getDoctorDocument() { return doctorDocument; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public String getReason() { return reason; }

    public void setId(Long id) { this.id = id; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public void setDoctorDocument(String doctorDocument) { this.doctorDocument = doctorDocument; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public void setReason(String reason) { this.reason = reason; }
}