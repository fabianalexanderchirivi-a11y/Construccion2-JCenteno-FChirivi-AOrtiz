package co.edu.tdea.clinicapp.application.dto;

import java.util.List;

public class InvoiceDto {
    private String patientIdNumber;
    private double subtotal;
    private double copay;
    private double total;
    private String currency;
    private List<InvoiceItemDto> items;

    public InvoiceDto() { }

    public InvoiceDto(String patientIdNumber, double subtotal, double copay, double total, String currency, List<InvoiceItemDto> items) {
        this.patientIdNumber = patientIdNumber;
        this.subtotal = subtotal;
        this.copay = copay;
        this.total = total;
        this.currency = currency;
        this.items = items;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public double getSubtotal() { return subtotal; }
    public double getCopay() { return copay; }
    public double getTotal() { return total; }
    public String getCurrency() { return currency; }
    public List<InvoiceItemDto> getItems() { return items; }

    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public void setCopay(double copay) { this.copay = copay; }
    public void setTotal(double total) { this.total = total; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setItems(List<InvoiceItemDto> items) { this.items = items; }
}
