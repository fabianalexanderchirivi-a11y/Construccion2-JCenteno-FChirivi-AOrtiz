package app.app.domain.services;

import app.app.domain.model.ClinicalRecord;

public class DeleteClinicalRecord {
	public void execute(ClinicalRecord record) throws Exception {

        if (record == null || record.getId() == 0) {
            throw new Exception("Debe proporcionar un registro clínico existente con un ID válido para eliminar");
        }
    }
}
