package app.app.adapter.in.builder;

import app.app.adapter.in.validators.UserValidator;
import app.app.domain.model.User;

public class UserBuilder {
	private UserValidator userValidator;
	
	public User build(String name, String document, String age, String userName, String password) throws Exception {
		User user = new User();
		
		user.setName(userValidator.nameValidator(name));
		user.setDocument(userValidator.documentValidator(document));
		user.setAge(userValidator.ageValidator(age));
		user.setUsername(userValidator.userNameValidator(userName));
		user.setPassword(userValidator.passwordValidator(password));
		
		return user;
	}
}
