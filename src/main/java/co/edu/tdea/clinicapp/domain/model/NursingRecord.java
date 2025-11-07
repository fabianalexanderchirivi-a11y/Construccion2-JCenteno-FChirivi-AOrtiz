package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;

public class NursingRecord {
    private final NursingActionType type;
    private final LocalDateTime performedAt;
    private final String patientIdNumber;
    private final String nurseIdNumber;

    private final OrderType orderType;
    private final LocalDateTime orderCreatedAt;
    private final int itemNumber;             
    private final String catalogId;            
    private final int quantity;               

    public NursingRecord(NursingActionType type, LocalDateTime performedAt, String patientIdNumber,
                         String nurseIdNumber, OrderType orderType, LocalDateTime orderCreatedAt,
                         int itemNumber, String catalogId, int quantity) {
        if (type == null) throw new IllegalArgumentException("Un tipo es requerido");
        if (performedAt == null) throw new IllegalArgumentException("Se require realizado en.");
        if (patientIdNumber == null || patientIdNumber.isBlank()) throw new IllegalArgumentException("El numero de ID del paciente es requerido");
        if (nurseIdNumber == null || nurseIdNumber.isBlank()) throw new IllegalArgumentException("El numero de ID de la enfermera es requerido.");
        if (orderType == null) throw new IllegalArgumentException("El tipo de la orden es requerido");
        if (orderCreatedAt == null) throw new IllegalArgumentException("El pedido creado es necesario");
        if (itemNumber < 1) throw new IllegalArgumentException("El numero del item debe ser >= 1");
        if (catalogId == null || catalogId.isBlank()) throw new IllegalArgumentException("La ID del catalogo es necesaria.");
        if (quantity < 1) throw new IllegalArgumentException("La cantidad debe ser >= 1");

        this.type = type;
        this.performedAt = performedAt;
        this.patientIdNumber = patientIdNumber.trim();
        this.nurseIdNumber = nurseIdNumber.trim();
        this.orderType = orderType;
        this.orderCreatedAt = orderCreatedAt;
        this.itemNumber = itemNumber;
        this.catalogId = catalogId.trim();
        this.quantity = quantity;
    }

    public NursingActionType getType() { return type; }
    public LocalDateTime getPerformedAt() { return performedAt; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public OrderType getOrderType() { return orderType; }
    public LocalDateTime getOrderCreatedAt() { return orderCreatedAt; }
    public int getItemNumber() { return itemNumber; }
    public String getCatalogId() { return catalogId; }
    public int getQuantity() { return quantity; }
}
