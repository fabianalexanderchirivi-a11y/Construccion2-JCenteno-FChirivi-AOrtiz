package app.app.application.usecases;

import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.Pet;
import app.app.domain.model.User;
import app.app.domain.model.enums.Role;
import app.app.domain.services.CreateClinicalOrder;
import app.app.domain.services.CreatePet;
import app.app.domain.services.CreateUser;
import app.app.domain.services.SearchClinicalOrderByPet;

public class VeterinarianUseCase {
	
	private CreateUser createUser;
	private CreatePet createPet;
	private CreateClinicalOrder createClinicalOrder;
	private SearchClinicalOrderByPet searchClinicalOrderByPet;
	
	public void createOwner(User user) throws Exception{
		user.setRole(Role.OWNER);
		createUser.excecute(user);
	}
	
	public void createPet(Pet pet) throws Exception{
		createPet.execute(pet);
	}
	
	public void createOrder(ClinicalOrder order) throws Exception{
		createClinicalOrder.execute(order);
	}
	
	public List<ClinicalOrder> searchOrders(Pet pet) throws Exception{
		return searchClinicalOrderByPet.search(pet);
	}
}
