package co.edu.tdea.clinicapp.domain.repository;

public interface BillingLedgerRepository {
    int getYearlyCopayTotal(String patientIdNumber, int year);
    void addCopay(String patientIdNumber, int year, int amount);
}
