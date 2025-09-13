package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Invoice;

public interface GenerateInvoiceUseCase {
    Invoice generate(GenerateInvoiceCommand command);
}
