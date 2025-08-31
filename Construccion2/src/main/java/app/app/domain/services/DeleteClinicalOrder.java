package app.app.domain.services;

import app.app.domain.model.ClinicalOrder;

public class DeleteClinicalOrder {
	public void execute(ClinicalOrder order) throws Exception {

        if (order == null || order.getId() == 0) {
            throw new Exception("Debe proporcionar una orden clínica existente con un ID válido para eliminar");
        }
    }
}
