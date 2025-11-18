package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinicalhistory;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "ClinicalHistoryEntry")
@Table(name = "clinical_history")
public class ClinicalHistoryEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientIdNumber;

    @Column(nullable = false)
    private LocalDateTime attendedAt;

    @Column(nullable = false)
    private String doctorIdNumber;

    private String reason;
    private String symptoms;

    @Column(nullable = false)
    private String diagnosis;

    private String diagnosticAidOrderNumber;
    private Integer diagnosticAidItemNumber;
    private String diagnosticAidResult;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }
    public LocalDateTime getAttendedAt() { return attendedAt; }
    public void setAttendedAt(LocalDateTime attendedAt) { this.attendedAt = attendedAt; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public void setDoctorIdNumber(String doctorIdNumber) { this.doctorIdNumber = doctorIdNumber; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getDiagnosticAidOrderNumber() { return diagnosticAidOrderNumber; }
    public void setDiagnosticAidOrderNumber(String diagnosticAidOrderNumber) { this.diagnosticAidOrderNumber = diagnosticAidOrderNumber; }
    public Integer getDiagnosticAidItemNumber() { return diagnosticAidItemNumber; }
    public void setDiagnosticAidItemNumber(Integer diagnosticAidItemNumber) { this.diagnosticAidItemNumber = diagnosticAidItemNumber; }
    public String getDiagnosticAidResult() { return diagnosticAidResult; }
    public void setDiagnosticAidResult(String diagnosticAidResult) { this.diagnosticAidResult = diagnosticAidResult; }
}
