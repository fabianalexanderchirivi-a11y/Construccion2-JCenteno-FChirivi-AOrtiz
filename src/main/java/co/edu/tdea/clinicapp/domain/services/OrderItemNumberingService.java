package co.edu.tdea.clinicapp.domain.services;

import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;

public final class OrderItemNumberingService {

    public int nextItemNumber(Order order) {
        if (order == null) throw new IllegalArgumentException("Orden inválida.");
        int max = 0;
        for (OrderItem i : order.getItems()) {
            if (i.getItemNumber() > max) max = i.getItemNumber();
        }
        return max + 1;
    }
}
