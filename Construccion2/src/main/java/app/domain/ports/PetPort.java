package app.domain.ports;

import app.domain.model.Pet;
import java.util.List;

public interface PetPort {
    public Pet save(Pet pet) throws Exception;
    public Pet findById(Pet pet) throws Exception;
    public List<Pet> findByOwner(Long ownerId) throws Exception;
}
