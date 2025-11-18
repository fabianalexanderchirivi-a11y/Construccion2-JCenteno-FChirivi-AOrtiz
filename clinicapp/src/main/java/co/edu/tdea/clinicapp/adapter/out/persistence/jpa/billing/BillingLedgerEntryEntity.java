package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.billing;

import jakarta.persistence.*;

@Entity
@Table(name = "billing_ledger")
public class BillingLedgerEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientIdNumber;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int amount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
