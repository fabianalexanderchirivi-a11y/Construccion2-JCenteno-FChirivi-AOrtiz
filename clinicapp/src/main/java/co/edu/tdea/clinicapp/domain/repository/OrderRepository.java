package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;

import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findOpenByPatientAndType(String patientIdNumber, OrderType type);


    Optional<Order> findByOrderNumber(String orderNumber);
    List<Order> findAllByPatientId(String patientIdNumber);
    String nextOrderNumber();
}
