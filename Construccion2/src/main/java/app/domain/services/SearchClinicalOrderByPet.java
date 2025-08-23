package app.domain.services;

public class SearchClinicalOrderByPet {
	private PetPort petPort;
	private ClinicalOrderPort clinicalOrderPort;
	
	public List<ClinicalOrder> search(Pet pet) throws Exception {
		pet= petPort.findById(pet);
		if(pet== null) {
			throw new Exceptio("debe consultar ordenes de una mascota registrada");
			
		}
		return clinical
	}

}
