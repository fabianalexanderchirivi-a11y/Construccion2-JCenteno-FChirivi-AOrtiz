package app.domain.ports;

import app.domain.model.Invoice;

public interface InvoicePort {
    public Invoice save(Invoice invoice) throws Exception;
}
