package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;

public class MedicalRecordEntry {

    private String patientIdNumber;
    private LocalDate date;
    private String doctorIdNumber;
    private String reason;
    private String symptoms;
    private String diagnosis;
    private String notes;

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorIdNumber() {
        return doctorIdNumber;
    }

    public void setDoctorIdNumber(String doctorIdNumber) {
        if (doctorIdNumber != null && !doctorIdNumber.matches("\\d{1,10}")) {
            throw new IllegalArgumentException("La cédula del médico debe tener entre 1 y 10 dígitos");
        }
        this.doctorIdNumber = doctorIdNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
