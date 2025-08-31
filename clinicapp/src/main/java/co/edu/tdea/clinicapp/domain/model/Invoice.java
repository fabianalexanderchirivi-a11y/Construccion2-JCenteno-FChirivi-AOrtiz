package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;

public class Invoice {

    private String patientFullName;
    private int patientAge;
    private String patientIdNumber;
    private String treatingDoctorName;
    private String insuranceCompanyName;
    private String policyNumber;
    private LocalDate policyValidUntil;
    private long policyDaysRemaining;
    private long copayAmount;
    private long totalAmount;

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getTreatingDoctorName() {
        return treatingDoctorName;
    }

    public void setTreatingDoctorName(String treatingDoctorName) {
        this.treatingDoctorName = treatingDoctorName;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public LocalDate getPolicyValidUntil() {
        return policyValidUntil;
    }

    public void setPolicyValidUntil(LocalDate policyValidUntil) {
        this.policyValidUntil = policyValidUntil;
    }

    public long getPolicyDaysRemaining() {
        return policyDaysRemaining;
    }

    public void setPolicyDaysRemaining(long policyDaysRemaining) {
        this.policyDaysRemaining = policyDaysRemaining;
    }

    public long getCopayAmount() {
        return copayAmount;
    }

    public void setCopayAmount(long copayAmount) {
        this.copayAmount = copayAmount;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
