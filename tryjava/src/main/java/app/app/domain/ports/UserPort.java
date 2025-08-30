package app.app.domain.ports;

import app.app.domain.model.User;

public interface UserPort {
	 public User findByDocument(long document) throws Exception;
	 public User findByUserName(String username) throws Exception;
	 
	 public void save(User user) throws Exception;
}
