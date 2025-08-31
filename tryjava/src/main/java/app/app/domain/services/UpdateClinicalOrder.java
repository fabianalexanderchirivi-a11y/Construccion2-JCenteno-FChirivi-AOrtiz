package app.app.domain.services;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.ports.ClinicalOrderPort;

public class UpdateClinicalOrder {
	private final ClinicalOrderPort clinicalOrderPort;

    public UpdateClinicalOrder(ClinicalOrderPort orderPort) {
        this.clinicalOrderPort = orderPort;
    }

    public void execute(ClinicalOrder order) throws Exception {

        if (order == null || order.getId() <= 0) {
            throw new Exception("Debe proporcionar una orden clínica existente con un ID válido");
        }

        if (order.getPet() == null) {
            throw new Exception("Debe agregar una mascota existente");
        }

        if (order.getVeterinarian() == null) {
            throw new Exception("Debe tener un veterinario asignado");
        }

        if (order.getMedicine() == null || order.getMedicine().isEmpty()) {
            throw new Exception("Debe tener un medicamento asignado");
        }

        if (order.getDoce() == null || order.getDoce().isEmpty()) {
            throw new Exception("Debe tener una dosis asignada");
        }

        if (order.getPet().getOwner() == null || !order.getPet().getOwner().equals(order.getPerson())) {
            throw new Exception("La persona ingresada no es el dueño de la mascota");
        }

        clinicalOrderPort.update(order);
    }
}
