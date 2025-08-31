package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Invoice;
import java.util.List;

public interface BillingUseCase {

    Invoice generateInvoice(String patientIdNumber);

    List<Invoice> getInvoicesByPatient(String patientIdNumber);
}
