package app.app.domain.services;

import java.util.ArrayList;
import java.util.List;

import app.app.domain.model.Invoice;
import app.app.domain.model.User;

public class SearchInvoiceByOwner {
	public List<Invoice> execute(List<Invoice> invoices, User owner) throws Exception {
        if (invoices == null || invoices.isEmpty()) {
            throw new Exception("No hay facturas registradas para buscar");
        }

        if (owner == null || owner.getId() == 0) {
            throw new Exception("Debe proporcionar un dueño válido para la búsqueda");
        }

        List<Invoice> result = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getOwner() != null && invoice.getOwner().getId() == owner.getId()) {
                result.add(invoice);
            }
        }

        return result;
    }
}
