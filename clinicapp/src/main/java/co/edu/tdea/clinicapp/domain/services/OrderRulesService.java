package co.edu.tdea.clinicapp.domain.services;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAidOrderItem;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;

public final class OrderRulesService {

    public void ensureItemAllowed(Order order, OrderItem newItem) {
        if (order == null || newItem == null) {
            throw new IllegalArgumentException("Datos de la orden/ítem inválidos.");
        }
        boolean hasDiagnostic = order.getItems().stream().anyMatch(i -> i instanceof DiagnosticAidOrderItem);
        boolean addingDiagnostic = newItem instanceof DiagnosticAidOrderItem;

        if (hasDiagnostic && !(newItem instanceof DiagnosticAidOrderItem)) {
            throw new IllegalStateException("No se puede mezclar ayuda diagnóstica con otros ítems en la misma orden.");
        }
        if (addingDiagnostic && !order.getItems().isEmpty()) {
            throw new IllegalStateException("La ayuda diagnóstica debe ser el único ítem de la orden.");
        }
    }

    public boolean isDiagnosticOnly(Order order) {
        return order != null
                && !order.getItems().isEmpty()
                && order.getItems().stream().allMatch(i -> i instanceof DiagnosticAidOrderItem);
    }
}
