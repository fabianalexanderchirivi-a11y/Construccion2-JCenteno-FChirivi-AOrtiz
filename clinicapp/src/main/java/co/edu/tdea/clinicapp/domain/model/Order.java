package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final LocalDateTime createdAt;
    private final OrderType type;
    private OrderStatus status;
    private final List<OrderItem> items;

    public Order(String patientIdNumber, String doctorIdNumber, OrderType type) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es requerido.");
        if (doctorIdNumber == null || doctorIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del doctor es requerido.");
        if (type == null) throw new IllegalArgumentException("El tipo es requerido.");

        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.type = type;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.OPEN;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        if (item == null) throw new IllegalArgumentException("El item es requerido.");
        boolean duplicate = items.stream().anyMatch(i -> i.getCatalogId().equals(item.getCatalogId()));
        if (duplicate) throw new IllegalArgumentException("Numero duplicado para la misma ID del catalogo en este item.");
        item.setItemNumber(items.size() + 1);
        items.add(item);
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderType getType() { return type; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
}
