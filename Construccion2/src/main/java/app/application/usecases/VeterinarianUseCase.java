package app.application.usecases;

import app.domain.model.Pet;
import app.domain.model.ClinicalOrder;
import app.domain.services.CreatePet;
import app.domain.services.CreateClinicalOrder;
import app.domain.services.SearchClinicalOrderByPet;

public class VeterinarianUseCase {

    private final CreatePet createPet;
    private final CreateClinicalOrder createClinicalOrder;
    private final SearchClinicalOrderByPet searchClinicalOrder;

    public VeterinarianUseCase(CreatePet createPet,
                               CreateClinicalOrder createClinicalOrder,
                               SearchClinicalOrderByPet searchClinicalOrder) {
        this.createPet = createPet;
        this.createClinicalOrder = createClinicalOrder;
        this.searchClinicalOrder = searchClinicalOrder;
    }

    public void createPet(Pet pet) throws Exception {
        createPet.create(pet);
    }

    public void createOrder(ClinicalOrder order) throws Exception {
        createClinicalOrder.create(order);
    }
}
