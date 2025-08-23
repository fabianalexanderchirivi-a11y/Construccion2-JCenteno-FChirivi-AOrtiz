package app.domain.services;

import app.domain.model.Pet;
import app.domain.ports.PetPort;

public class CreatePet {

    private PetPort petPort;

    public CreatePet(PetPort petPort) {
        this.petPort = petPort;
    }

    public Pet create(Pet pet) throws Exception {
        if (pet == null) {
            throw new Exception("La mascota no puede ser nula");
        }
        return petPort.save(pet);
    }
}
