package app.app.domain.ports;

import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.ClinicalRecord;
import app.app.domain.model.Pet;

public interface ClinicalRecordPort {
	
	public ClinicalRecord findById(long id) throws Exception;
	public List<Pet> findByPet(String name) throws Exception;
	public ClinicalOrder findByClinicalOrder(ClinicalOrder order) throws Exception;
	public List<ClinicalRecord> findAll() throws Exception;
	
	public void save(ClinicalRecord clinicalRecord) throws Exception;
}
