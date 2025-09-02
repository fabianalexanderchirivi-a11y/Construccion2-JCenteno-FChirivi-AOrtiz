package co.edu.tdea.clinicapp.domain.service;

import co.edu.tdea.clinicapp.domain.model.Invoice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BillingService {
    Invoice generateForOrder(String orderId);
    Invoice generateForPatient(String patientId);
    Optional<Invoice> findInvoice(String invoiceId);
    List<Invoice> findInvoicesByPatient(String patientId);
    List<Invoice> findInvoicesByDateRange(LocalDate from, LocalDate to);
    void cancelInvoice(String invoiceId, String reason);
}
