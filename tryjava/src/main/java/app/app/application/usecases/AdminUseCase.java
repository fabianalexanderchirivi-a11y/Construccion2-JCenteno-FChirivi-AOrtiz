package app.app.application.usecases;

import app.app.domain.model.User;
import app.app.domain.model.enums.Role;
import app.app.domain.services.CreateUser;

public class AdminUseCase {
	
	private CreateUser createUser;
	
	public void createVeterninarian(User user) throws Exception {
		user.setRole(Role.VETERINARIAN);
		createUser.excecute(user);
	}
	
	public void createSeller(User user) throws Exception{
		user.setRole(Role.SELLER);
		createUser.excecute(user);
	}
}
