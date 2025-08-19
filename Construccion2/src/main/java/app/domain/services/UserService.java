package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPort;

public class UserService {
	
	private UserPort userPort;

	public void createUser(User user) throws Exception {
			
		//Validar que solo exista una persona con la cedula
		if (userPort.findByDocument(user)!=null) {
			throw new Exception ("Ya existe una persona registrada con esta cedula");
		}
		//Validar que solo exista un usuario con ese UserName
		if (userPort.findByUserName(user)!=null) {
			throw new Exception ("Ya existe una persona registrada con este nombre");
		}
		userPort.save(user);
	}
	
}
