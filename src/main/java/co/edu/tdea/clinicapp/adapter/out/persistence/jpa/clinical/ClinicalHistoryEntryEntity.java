package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinical;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clinical_history_entries")
public class ClinicalHistoryEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_id_number", nullable=false, length=30)
    private String patientIdNumber;

    @Column(name="attended_at", nullable=false)
    private LocalDateTime attendedAt;

    @Column(name="doctor_id_number", nullable=false, length=30)
    private String doctorIdNumber;

    @Column(name="reason", length=500)
    private String reason;

    @Column(name="symptoms", length=1000)
    private String symptoms;

    @Column(name="diagnosis", nullable=false, length=500)
    private String diagnosis;

    @Column(name="diag_order_number", length=30)
    private String diagnosticAidOrderNumber;

    @Column(name="diag_item_number")
    private Integer diagnosticAidItemNumber;

    @Column(name="diag_result", length=4000)
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
