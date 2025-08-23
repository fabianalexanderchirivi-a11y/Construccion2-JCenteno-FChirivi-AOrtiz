package app.domain.services;

import app.domain.model.Invoice;
import app.domain.ports.InvoicePort;

public class CreateInvoice {

    private InvoicePort invoicePort;

    public CreateInvoice(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }

    public Invoice create(Invoice invoice) throws Exception {
        if (invoice == null) {
            throw new Exception("La factura no puede ser nula");
        }
        return invoicePort.save(invoice);
    }
}
