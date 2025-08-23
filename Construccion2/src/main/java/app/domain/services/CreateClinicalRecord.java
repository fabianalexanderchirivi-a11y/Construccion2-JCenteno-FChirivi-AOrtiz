package app.domain.services;

import app.domain.model.ClinicalRecord;
import app.domain.ports.ClinicalRecordPort;

public class CreateClinicalRecord {

    private ClinicalRecordPort clinicalRecordPort;

    public CreateClinicalRecord(ClinicalRecordPort clinicalRecordPort) {
        this.clinicalRecordPort = clinicalRecordPort;
    }

    public ClinicalRecord create(ClinicalRecord clinicalRecord) throws Exception {
        if (clinicalRecord == null) {
            throw new Exception("El registro clínico no puede ser nulo");
        }
        return clinicalRecordPort.save(clinicalRecord);
    }
}
