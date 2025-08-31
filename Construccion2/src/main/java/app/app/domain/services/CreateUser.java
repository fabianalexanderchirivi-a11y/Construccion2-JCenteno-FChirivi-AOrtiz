package app.app.domain.services;

import app.app.domain.model.User;
import app.app.domain.ports.UserPort;

public class CreateUser {
	private final UserPort userPort;
	
	public CreateUser(UserPort userPort) {
	    this.userPort = userPort;
	}
	
	public void excecute(User user) throws Exception {
		
		if(userPort.findByDocument(user.getDocument())!= null) {
			throw new Exception("Ya existe un usuario con este numero de documento");
		}
		
		if(userPort.findByUserName(user.getUsername())!=null) {
			throw new Exception("Ya existe un usuario con este nombre de cuenta");
		}
		
		userPort.save(user);
	}
}
