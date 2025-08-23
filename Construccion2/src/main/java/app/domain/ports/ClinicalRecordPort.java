package app.domain.ports;

import app.domain.model.ClinicalRecord;
import app.domain.model.Pet;
import java.util.List;

public interface ClinicalRecordPort {
    public ClinicalRecord save(ClinicalRecord record) throws Exception;
    public List<ClinicalRecord> findByPet(Pet pet) throws Exception;
}
