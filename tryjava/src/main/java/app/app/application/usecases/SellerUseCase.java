package app.app.application.usecases;

import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.Invoice;
import app.app.domain.model.Pet;
import app.app.domain.services.CreateInvoice;
import app.app.domain.services.SearchClinicalOrderByPet;

public class SellerUseCase {
	private CreateInvoice createInvoice;
	private SearchClinicalOrderByPet searchClinicalOrderByPet;
	
	public void createInvoice(Invoice invoice) throws Exception{
		createInvoice.execute(invoice);
	}
	
	public List<ClinicalOrder> searchClinicalOrder(Pet pet) throws Exception{
		return searchClinicalOrderByPet.search(pet);
	}
}
