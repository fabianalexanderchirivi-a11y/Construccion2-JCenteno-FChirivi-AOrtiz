package app.app.domain.services;

import app.app.domain.model.Pet;

public class DeletePet {
	public void execute(Pet pet) throws Exception {
        if (pet == null || pet.getId() == 0) {
            throw new Exception("Debe proporcionar una mascota existente con un ID válido para eliminar");
        }
    }
}
