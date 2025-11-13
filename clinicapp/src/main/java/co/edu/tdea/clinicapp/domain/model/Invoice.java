package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Invoice {
    private final LocalDateTime createdAt;
    private final String patientIdNumber;
    private final String patientFullName;
    private final int patientAge;
    private final String doctorFullName;
    private final String insuranceCompany;
    private final String policyNumber;
    private final boolean policyActive;
    private final LocalDate policyEndDate;
    private final Integer policyDaysRemaining;
    private final List<InvoiceItem> items;
    private final int grossTotal;
    private final int copay;
    private final int insurerPortion;
    private final int patientPayable;

    public Invoice(LocalDateTime createdAt,
                   String patientIdNumber,
                   String patientFullName,
                   int patientAge,
                   String doctorFullName,
                   String insuranceCompany,
                   String policyNumber,
                   boolean policyActive,
                   LocalDate policyEndDate,
                   Integer policyDaysRemaining,
                   List<InvoiceItem> items,
                   int grossTotal,
                   int copay,
                   int insurerPortion,
                   int patientPayable) {
        this.createdAt = createdAt;
        this.patientIdNumber = patientIdNumber;
        this.patientFullName = patientFullName;
        this.patientAge = patientAge;
        this.doctorFullName = doctorFullName;
        this.insuranceCompany = insuranceCompany;
        this.policyNumber = policyNumber;
        this.policyActive = policyActive;
        this.policyEndDate = policyEndDate;
        this.policyDaysRemaining = policyDaysRemaining;
        this.items = items;
        this.grossTotal = grossTotal;
        this.copay = copay;
        this.insurerPortion = insurerPortion;
        this.patientPayable = patientPayable;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public String getPatientFullName() { return patientFullName; }
    public int getPatientAge() { return patientAge; }
    public String getDoctorFullName() { return doctorFullName; }
    public String getInsuranceCompany() { return insuranceCompany; }
    public String getPolicyNumber() { return policyNumber; }
    public boolean isPolicyActive() { return policyActive; }
    public LocalDate getPolicyEndDate() { return policyEndDate; }
    public Integer getPolicyDaysRemaining() { return policyDaysRemaining; }
    public List<InvoiceItem> getItems() { return Collections.unmodifiableList(items); }
    public int getGrossTotal() { return grossTotal; }
    public int getCopay() { return copay; }
    public int getInsurerPortion() { return insurerPortion; }
    public int getPatientPayable() { return patientPayable; }
}
