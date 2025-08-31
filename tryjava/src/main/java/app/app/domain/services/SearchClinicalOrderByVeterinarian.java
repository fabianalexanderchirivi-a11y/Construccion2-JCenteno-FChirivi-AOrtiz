package app.app.domain.services;

import java.util.ArrayList;
import java.util.List;

import app.app.domain.model.ClinicalOrder;
import app.app.domain.model.User;

public class SearchClinicalOrderByVeterinarian {
	public List<ClinicalOrder> execute(List<ClinicalOrder> orders, User veterinarian) throws Exception {
        if (orders == null || orders.isEmpty()) {
            throw new Exception("No hay órdenes clínicas registradas para buscar");
        }

        if (veterinarian == null || veterinarian.getId() == 0) {
            throw new Exception("Debe proporcionar un veterinario válido para la búsqueda");
        }

        List<ClinicalOrder> result = new ArrayList<>();
        for (ClinicalOrder order : orders) {
            if (order.getVeterinarian() != null && order.getVeterinarian().getId() == veterinarian.getId()) {
                result.add(order);
            }
        }

        return result;
    }
}
