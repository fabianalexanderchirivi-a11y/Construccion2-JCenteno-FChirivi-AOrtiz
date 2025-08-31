package app.app.domain.ports;

import java.util.List;

import app.app.domain.model.Person;
import app.app.domain.model.Pet;

public interface PetPort {
	public Pet findById(long id) throws Exception;
	public Pet findByName(String name) throws Exception;
	List<Pet> findByOwner(String name, Person owner) throws Exception;
	
	public void save(Pet pet) throws Exception;
}
