package app.app.domain.services;

import java.util.List;

import app.app.domain.model.Pet;
import app.app.domain.ports.PetPort;

public class CreatePet {
	private final PetPort petPort;

    public CreatePet(PetPort petPort) {
        this.petPort = petPort;
    }

    public void execute(Pet pet) throws Exception {

        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new Exception("El nombre de la mascota no puede estar vacío");
        }

        if (pet.getAge() <= 0) {
            throw new Exception("La edad de la mascota debe ser mayor a 0");
        }

        if (pet.getWeight() <= 0) {
            throw new Exception("El peso de la mascota debe ser mayor a 0");
        }

        if (pet.getSpice() == null) {
            throw new Exception("Debe especificar la especie de la mascota");
        }

        if (pet.getOwner() == null) {
            throw new Exception("La mascota debe tener un dueño");
        }

        List<Pet> existingPets = petPort.findByOwner(pet.getName(), pet.getOwner());
        if (existingPets != null && !existingPets.isEmpty()) {
            throw new Exception("Ya existe una mascota con este nombre para este dueño");
        }


        petPort.save(pet);
    }
}
