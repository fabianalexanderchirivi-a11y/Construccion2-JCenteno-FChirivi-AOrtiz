package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.history;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_history",
       indexes = {
           @Index(name = "idx_history_patient", columnList = "patient_document"),
           @Index(name = "uk_history_patient_date", columnList = "patient_document,date_time", unique = true)
       })
public class MedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_document", nullable=false, length=15)
    private String patientDocument;

    @Column(name="date_time", nullable=false)
    private LocalDateTime dateTime;

    @Column(name="doctor_document", nullable=false, length=15)
    private String doctorDocument;

    @Column(length = 160)
    private String reason;

    @Column(columnDefinition = "text")
    private String symptoms;

    @Column(columnDefinition = "text")
    private String diagnosis;

    @Column(columnDefinition = "text")
    private String payload; // JSON/text

    public Long getId() { return id; }
    public String getPatientDocument() { return patientDocument; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getDoctorDocument() { return doctorDocument; }
    public String getReason() { return reason; }
    public String getSymptoms() { return symptoms; }
    public String getDiagnosis() { return diagnosis; }
    public String getPayload() { return payload; }

    public void setId(Long id) { this.id = id; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setDoctorDocument(String doctorDocument) { this.doctorDocument = doctorDocument; }
    public void setReason(String reason) { this.reason = reason; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setPayload(String payload) { this.payload = payload; }
}
