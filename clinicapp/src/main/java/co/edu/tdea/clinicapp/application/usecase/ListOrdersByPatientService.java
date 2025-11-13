package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;

@Service
@Transactional(readOnly = true)
public class ListOrdersByPatientService
        implements co.edu.tdea.clinicapp.application.port.in.ListOrdersByPatientUseCase {

    private final OrderRepository orderRepository;

    public ListOrdersByPatientService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public java.util.List<co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto> list(String patientIdNumber) {
        java.util.List<Order> orders = orderRepository.findAllByPatientId(patientIdNumber);

        java.util.List<co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto> result = new ArrayList<>();
        for (Order o : orders) {
            for (OrderItem it : o.getItems()) {
                result.add(new co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto(
                        o.getCreatedAt(),
                        o.getType(),
                        it.getItemNumber(),
                        it.getCatalogId(),
                        it.getQuantity()
                ));
            }
        }
        result.sort(
            Comparator
                .comparing(co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto::getCreatedAt)
                .thenComparing(co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto::getItemNumber)
        );
        return result;
    }
}
