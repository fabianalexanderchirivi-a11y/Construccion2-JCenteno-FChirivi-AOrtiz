package app.domain.ports;

import app.domain.model.User;

public interface UserPort {
	
	public User findByDocument( User User) throws Exception;
	public User findByUserName( User User) throws Exception;
	public void save(User user) throws Exception;

}
