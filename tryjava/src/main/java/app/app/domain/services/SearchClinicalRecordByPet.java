package app.app.domain.services;

import java.util.List;

import app.app.domain.model.ClinicalRecord;
import app.app.domain.model.Pet;
import app.app.domain.ports.ClinicalRecordPort;

public class SearchClinicalRecordByPet {
	private final ClinicalRecordPort clinicalRecordPort;

    public SearchClinicalRecordByPet(ClinicalRecordPort clinicalRecordPort) {
        this.clinicalRecordPort = clinicalRecordPort;
    }

    public List<ClinicalRecord> execute(Pet pet) throws Exception {
        if (pet == null) {
            throw new Exception("Debe proporcionar una mascota válida");
        }

        List<ClinicalRecord> records = clinicalRecordPort.findByPet(pet);

        if (records == null || records.isEmpty()) {
            throw new Exception("No se encontraron registros clínicos para esta mascota");
        }

        return records;
    }
}
