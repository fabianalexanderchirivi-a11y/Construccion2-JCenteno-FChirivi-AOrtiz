package app.app.domain.services;

import app.app.domain.model.Pet;

public class UpdatePet {
	public Pet execute(Pet pet) throws Exception {

        if (pet == null || pet.getId() == 0) {
            throw new Exception("Debe proporcionar una mascota existente con un ID válido");
        }

        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new Exception("El nombre de la mascota es obligatorio");
        }

        if (pet.getOwner() == null || pet.getOwner().getId() == 0) {
            throw new Exception("Debe asignar un propietario válido a la mascota");
        }

        return pet;
    }
}
