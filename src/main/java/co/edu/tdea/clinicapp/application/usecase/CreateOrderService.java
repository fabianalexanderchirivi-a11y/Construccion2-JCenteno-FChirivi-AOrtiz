package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.CreateOrderUseCase;
import co.edu.tdea.clinicapp.application.port.in.CreateOrderCommand;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;

    public CreateOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void create(CreateOrderCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("El comando es requerido.");
        if (cmd.getItems() == null || cmd.getItems().isEmpty())
            throw new IllegalArgumentException("La orden debe tener al menos un ítem.");

        Order order = new Order(cmd.getPatientIdNumber(), cmd.getDoctorIdNumber(), cmd.getType());

        for (CreateOrderCommand.Item it : cmd.getItems()) {
            OrderItem item = new OrderItem(it.getCatalogId(), it.getQuantity()) { };
            order.addItem(item);
        }
        orderRepository.save(order);
    }
}
