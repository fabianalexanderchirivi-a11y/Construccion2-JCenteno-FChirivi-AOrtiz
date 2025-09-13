package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;

public class ClinicalHistoryEntry {
    private final LocalDateTime attendedAt; 
    private final String doctorIdNumber;     
    private final String reason;            
    private final String symptoms;          
    private final String diagnosis;         
    private final String diagnosticAidOrderNumber; 
    private final Integer diagnosticAidItemNumber;
    private final String diagnosticAidResult;    

    public ClinicalHistoryEntry(
            LocalDateTime attendedAt,
            String doctorIdNumber,
            String reason,
            String symptoms,
            String diagnosis,
            String diagnosticAidOrderNumber,
            Integer diagnosticAidItemNumber,
            String diagnosticAidResult
    ) {
        if (attendedAt == null) throw new IllegalArgumentException("attendedAt is required");
        if (doctorIdNumber == null || doctorIdNumber.isBlank()) throw new IllegalArgumentException("doctorIdNumber is required");
        if (diagnosis == null || diagnosis.isBlank()) throw new IllegalArgumentException("diagnosis is required");

        this.attendedAt = attendedAt;
        this.doctorIdNumber = doctorIdNumber.trim();
        this.reason = (reason == null || reason.isBlank()) ? null : reason.trim();
        this.symptoms = (symptoms == null || symptoms.isBlank()) ? null : symptoms.trim();
        this.diagnosis = diagnosis.trim();
        this.diagnosticAidOrderNumber = (diagnosticAidOrderNumber == null || diagnosticAidOrderNumber.isBlank())
                ? null : diagnosticAidOrderNumber.trim();
        this.diagnosticAidItemNumber = diagnosticAidItemNumber;
        this.diagnosticAidResult = (diagnosticAidResult == null || diagnosticAidResult.isBlank())
                ? null : diagnosticAidResult.trim();
    }

    public LocalDateTime getAttendedAt() { return attendedAt; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public String getReason() { return reason; }
    public String getSymptoms() { return symptoms; }
    public String getDiagnosis() { return diagnosis; }
    public String getDiagnosticAidOrderNumber() { return diagnosticAidOrderNumber; }
    public Integer getDiagnosticAidItemNumber() { return diagnosticAidItemNumber; }
    public String getDiagnosticAidResult() { return diagnosticAidResult; }
}
