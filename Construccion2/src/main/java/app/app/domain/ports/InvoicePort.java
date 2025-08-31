package app.app.domain.ports;

import java.util.Date;
import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.Invoice;
import app.app.domain.model.Person;
import app.app.domain.model.Pet;

public interface InvoicePort {

	public Invoice save(Invoice invoice) throws Exception;

	public Invoice findById(long id) throws Exception;

	public List<Invoice> findAll() throws Exception;

	public List<Invoice> findByPet(Pet pet) throws Exception;

	public List<Invoice> findByOwner(Person owner) throws Exception;

	public Invoice findByOrder(ClinicalOrder order) throws Exception;

	public List<Invoice> findByProductName(String productName) throws Exception;

	public List<Invoice> findByDateRange(Date startDate, Date endDate) throws Exception;

    void update(Invoice invoice) throws Exception;

    void deleteById(long id) throws Exception;
}
