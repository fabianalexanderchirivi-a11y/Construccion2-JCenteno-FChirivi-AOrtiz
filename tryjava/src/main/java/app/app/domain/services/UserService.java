package app.app.domain.services;

import app.app.domain.model.User;
import app.app.domain.ports.UserPort;

public class UserService {
	
	private UserPort userPort;
	
	public void createUser(User user) throws Exception {
		if(userPort.findByDocument(user.getDocument())!=null) {
			throw new Exception("Ya existe un usuario registrado con esta cedula");
		}
		
		if(userPort.findByUserName(user.getUsername())!=null) {
			throw new Exception("Ya existe un usuario con este nombre de usuario");
		}
		
		userPort.save(user);
	}
}
