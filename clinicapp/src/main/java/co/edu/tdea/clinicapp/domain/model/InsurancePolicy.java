package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;

public class InsurancePolicy {
    private String companyName;
    private String policyNumber;
    private boolean active;
    private LocalDate endDate;

    public InsurancePolicy(String companyName, String policyNumber, boolean active, LocalDate endDate) {
        setCompanyName(companyName);
        setPolicyNumber(policyNumber);
        setActive(active);
        setEndDate(endDate);
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) {
        if (companyName == null || companyName.isBlank()) throw new IllegalArgumentException("Nombre de aseguradora inválido.");
        this.companyName = companyName.trim();
    }

    public String getPolicyNumber() { return policyNumber; }
    public void setPolicyNumber(String policyNumber) {
        if (policyNumber == null || policyNumber.isBlank()) throw new IllegalArgumentException("Número de póliza inválido.");
        this.policyNumber = policyNumber.trim();
    }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) {
        if (endDate == null) throw new IllegalArgumentException("Fecha de fin de póliza inválida.");
        this.endDate = endDate;
    }
}
