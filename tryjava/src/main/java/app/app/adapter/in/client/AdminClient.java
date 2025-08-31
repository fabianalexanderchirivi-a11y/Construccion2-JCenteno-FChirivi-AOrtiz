package app.app.adapter.in.client;

import java.util.Scanner;

import app.app.adapter.in.builder.UserBuilder;
import app.app.application.usecases.AdminUseCase;
import app.app.domain.model.User;

public class AdminClient {
	private static final String Menu = "Ingrese una de las opciones \n"
			+ "1. Crear veterinario \n"
			+ "2. Crear vendedor\n"
			+ "3. Salir";
	
	private static Scanner reader = new Scanner(System.in);
	private AdminUseCase adminUseCase;
	private UserBuilder userBuilder;
	
	public void session() {
		boolean session = true;
		while(true) {
			session = menu();
		}
	}
	
	private boolean menu() {
		try{
			System.out.println(Menu);
			String option = reader.nextLine();
			
			switch (option) {
			case "1":{
				User user = readInforFromUser();
				adminUseCase.createVeterninarian(user);
				return true;
			}
			case "2":{
				User user = readInforFromUser();
				adminUseCase.createSeller(user);
				return true;
			}
			case "3":{
				System.out.println("Hasta luego");
				return false;
			}
			default:{
				System.out.println("Ingrese una opcion valida");
				return true;
			}
			
			
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return true;
		}
	}
	
	private User readInfoFromUser() throws Exception{
		System.out.println("Ingrese los siguientes datos");
		
		System.out.println("\n Nombre:");
		String name = reader.nextLine();
		System.out.println("Cedula:");
		String document = reader.nextLine();
		System.out.println("Nombre de usuario:");
		String userName = reader.nextLine();
		System.out.println("Contraseña:");
		String password = reader.nextLine();
		System.out.println("Edad:");
		String age = reader.nextLine();
		
		return userBuilder.build(name, document, age, userName, password);
	}
	
	
}
