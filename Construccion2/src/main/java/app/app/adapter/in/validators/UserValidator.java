package app.app.adapter.in.validators;

public class UserValidator extends SimpleValidator{
	
	public String nameValidator(String value) throws Exception {
		return stringValidator("Nombre de la persona", value);
	}
	
	public String userNameValidator(String value) throws Exception {
		return stringValidator("Nombre del usuario", value);
	}
	
	public String passwordValidator(String value) throws Exception {
		return stringValidator("contraseña", value);
	}
	
	public long documentValidator(String value) throws Exception {
		return longValidator("Documento de la persona", value);
	}
	
	public int ageValidator(String value) throws Exception {
		return integerValidator("Edad de la persona", value);
	}
}
