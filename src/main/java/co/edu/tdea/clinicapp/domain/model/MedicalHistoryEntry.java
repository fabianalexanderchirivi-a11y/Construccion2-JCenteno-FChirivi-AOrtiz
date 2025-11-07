package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;

public class MedicalHistoryEntry {
    private static final String DOCTOR_ID_1_10_RX = "\\d{1,10}";
    private static final String PATIENT_ID_DIGITS_RX = "\\d+";

    private String patientId;
    private LocalDateTime date;
    private String doctorId;
    private String visitReason;
    private String symptoms;
    private String diagnosis;
    private VitalSigns vitalSigns;

    public MedicalHistoryEntry(String patientId, LocalDateTime date, String doctorId,
                               String visitReason, String symptoms, String diagnosis, VitalSigns vitalSigns) {
        setPatientId(patientId);
        setDate(date);
        setDoctorId(doctorId);
        setVisitReason(visitReason);
        setSymptoms(symptoms);
        setDiagnosis(diagnosis);
        setVitalSigns(vitalSigns);
    }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) {
        if (patientId == null || !patientId.matches(PATIENT_ID_DIGITS_RX))
            throw new IllegalArgumentException("Id del paciente inválido.");
        this.patientId = patientId;
    }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) {
        if (date == null) throw new IllegalArgumentException("Fecha de atención inválida.");
        this.date = date;
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) {
        if (doctorId == null || !doctorId.matches(DOCTOR_ID_1_10_RX))
            throw new IllegalArgumentException("Cédula del médico inválida: 1 a 10 dígitos.");
        this.doctorId = doctorId;
    }

    public String getVisitReason() { return visitReason; }
    public void setVisitReason(String visitReason) {
        if (visitReason == null || visitReason.isBlank()) throw new IllegalArgumentException("Motivo de consulta inválido.");
        this.visitReason = visitReason.trim();
    }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) {
        if (symptoms == null || symptoms.isBlank()) throw new IllegalArgumentException("Sintomatología inválida.");
        this.symptoms = symptoms.trim();
    }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = (diagnosis == null) ? null : diagnosis.trim();
    }

    public VitalSigns getVitalSigns() { return vitalSigns; }
    public void setVitalSigns(VitalSigns vitalSigns) { this.vitalSigns = vitalSigns; }
}
