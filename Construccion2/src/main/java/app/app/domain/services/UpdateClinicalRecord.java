package app.app.domain.services;

import app.app.domain.model.ClinicalRecord;
import app.app.domain.ports.ClinicalRecordPort;

public class UpdateClinicalRecord {
	private final ClinicalRecordPort clinicalRecordPort;

    public UpdateClinicalRecord(ClinicalRecordPort clinicalRecordPort) {
        this.clinicalRecordPort = clinicalRecordPort;
    }

    public void execute(ClinicalRecord record) throws Exception {

        if (record == null || record.getId() <= 0) {
            throw new Exception("Debe proporcionar un registro clínico existente con un ID válido");
        }

        if (record.getPet() == null) {
            throw new Exception("El registro clínico debe estar asociado a una mascota");
        }

        if (record.getVeterinarian() == null) {
            throw new Exception("El registro clínico debe tener un veterinario asignado");
        }

        if (record.getDiagnosis() == null || record.getDiagnosis().isEmpty()) {
            throw new Exception("El registro clínico debe contener un diagnóstico");
        }

        clinicalRecordPort.update(record);
    }
}
