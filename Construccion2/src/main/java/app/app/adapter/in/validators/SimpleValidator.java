package app.app.adapter.in.validators;

public abstract class SimpleValidator {
	public String stringValidator(String element, String value) throws Exception{
		if(value == null || value.equals("")) {
			throw new Exception(element +" no puede tener un valor vacio o nulo");
		}
		return value;
	}
	
	public int integerValidator(String element, String value) throws Exception{
		stringValidator(element, value);
		try {
			int intValue = Integer.parseInt(value);
			return intValue;
		}
		catch(Exception e) {
			throw new Exception(element +" debe ser un valor numérico");
		}
	}
	
	public long longValidator(String element, String value) throws Exception{
		stringValidator(element, value);
		try {
			long longValue = Long.parseLong(value);
			return longValue;
		}
		catch(Exception e) {
			throw new Exception(element +" debe ser un valor numérico");
		}
	}
}
