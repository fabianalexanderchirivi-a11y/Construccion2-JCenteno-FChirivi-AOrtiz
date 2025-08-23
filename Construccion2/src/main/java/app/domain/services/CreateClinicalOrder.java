package app.domain.services;

import app.domain.model.ClinicalOrder;
import app.domain.ports.ClinicalOrderPort;

public class CreateClinicalOrder {

    private ClinicalOrderPort clinicalOrderPort;

    public CreateClinicalOrder(ClinicalOrderPort clinicalOrderPort) {
        this.clinicalOrderPort = clinicalOrderPort;
    }

    public ClinicalOrder create(ClinicalOrder clinicalOrder) throws Exception {
        if (clinicalOrder == null) {
            throw new Exception("La orden clínica no puede ser nula");
        }
        return clinicalOrderPort.save(clinicalOrder);
    }
}
