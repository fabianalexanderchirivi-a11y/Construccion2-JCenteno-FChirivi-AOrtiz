package app.app.domain.services;

import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.Pet;
import app.app.domain.ports.ClinicalOrderPort;
import app.app.domain.ports.PetPort;

public class SearchClinicalOrderByPet {
	private PetPort petPort;
	private ClinicalOrderPort clinicalOrderPort;
	
	public List<ClinicalOrder> search(Pet pet) throws Exception{
		pet = petPort.findById(pet.getId());
		
		if (pet == null) {
			throw new Exception ("Debe consultar ordenes de una mascota registrada");
		}
		return clinicalOrderPort.findByPet(pet);
	}
}
