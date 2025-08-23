package app.domain.ports;

import app.domain.model.ClinicalOrder;
import app.domain.model.Pet;
import java.util.List;

public interface ClinicalOrderPort {
	
    public ClinicalOrder save(ClinicalOrder order) throws Exception;
    public List<ClinicalOrder> findByPet(Pet pet) throws Exception;
    
}
