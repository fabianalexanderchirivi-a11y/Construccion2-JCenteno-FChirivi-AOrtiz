package app.app.domain.services;

import java.sql.Date;

import app.app.domain.model.Invoice;
import app.app.domain.ports.InvoicePort;

public class CreateInvoice {
	private final InvoicePort invoicePort;
	
	public CreateInvoice(InvoicePort invoicePort) {
        this.invoicePort = invoicePort;
    }
	
	public Invoice execute(Invoice invoice) throws Exception{
		
		if (invoice.getOrder() == null) {
            throw new Exception("La factura debe estar asociada a una orden clínica");
        }
		
		if (invoice.getProductName() == null || invoice.getProductName().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío");
        }
		
		if (invoice.getProductAmount() <= 0) {
            throw new Exception("La cantidad del producto debe ser mayor a cero");
        }
		
		if (invoice.getOrder() != null) {
			Invoice existing = invoicePort.findByOrder(invoice.getOrder());
            if (existing != null) {
                throw new Exception("Ya existe una factura para esta orden clínica");
            }
		}
		
		if (invoice.getDate() == null) {
            invoice.setDate(new java.sql.Date(System.currentTimeMillis()));
        }
		
		return invoicePort.save(invoice);
	}
}
