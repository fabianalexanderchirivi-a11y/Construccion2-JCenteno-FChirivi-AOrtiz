package app.app.domain.services;

import app.app.domain.model.ClinicalRecord;
import app.app.domain.ports.ClinicalRecordPort;

public class CreateClinicalRecord {

    private final ClinicalRecordPort clinicalRecordPort;

    public CreateClinicalRecord(ClinicalRecordPort clinicalRecordPort) {
        this.clinicalRecordPort = clinicalRecordPort;
    }

    public void execute(ClinicalRecord clinicalRecord) throws Exception {

        if (clinicalRecord == null) {
            throw new Exception("El registro clínico no puede ser nulo");
        }

        if (clinicalRecord.getPet() == null) {
            throw new Exception("Debe asociarse una mascota al registro clínico");
        }

        if (clinicalRecord.getVeterinarian() == null) {
            throw new Exception("Debe asignarse un veterinario al registro clínico");
        }

        if (clinicalRecord.getMotive() == null || clinicalRecord.getMotive().isEmpty()) {
            throw new Exception("Debe indicar el motivo de la consulta");
        }

        if (clinicalRecord.getOrder() != null) {
            ClinicalRecord existing = clinicalRecordPort.findByOrder(clinicalRecord.getOrder());
            if (existing != null) {
                throw new Exception("Ya existe un registro clínico para esta orden");
            }
        }

        clinicalRecordPort.save(clinicalRecord);
    }
}