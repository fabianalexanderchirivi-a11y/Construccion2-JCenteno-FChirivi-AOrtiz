package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class RecordDiagnosticResultCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final LocalDateTime attendedAt;   
    private final String diagnosis;            
    private final String diagnosticAidOrderNumber; 
    private final Integer diagnosticAidItemNumber; 
    private final String diagnosticAidResult;    
    private final String reason;          
    private final String symptoms;            

    public RecordDiagnosticResultCommand(
            String patientIdNumber,
            String doctorIdNumber,
            LocalDateTime attendedAt,
            String diagnosis,
            String diagnosticAidOrderNumber,
            Integer diagnosticAidItemNumber,
            String diagnosticAidResult,
            String reason,
            String symptoms
    ) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.attendedAt = attendedAt;
        this.diagnosis = diagnosis;
        this.diagnosticAidOrderNumber = diagnosticAidOrderNumber;
        this.diagnosticAidItemNumber = diagnosticAidItemNumber;
        this.diagnosticAidResult = diagnosticAidResult;
        this.reason = reason;
        this.symptoms = symptoms;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public LocalDateTime getAttendedAt() { return attendedAt; }
    public String getDiagnosis() { return diagnosis; }
    public String getDiagnosticAidOrderNumber() { return diagnosticAidOrderNumber; }
    public Integer getDiagnosticAidItemNumber() { return diagnosticAidItemNumber; }
    public String getDiagnosticAidResult() { return diagnosticAidResult; }
    public String getReason() { return reason; }
    public String getSymptoms() { return symptoms; }
}
