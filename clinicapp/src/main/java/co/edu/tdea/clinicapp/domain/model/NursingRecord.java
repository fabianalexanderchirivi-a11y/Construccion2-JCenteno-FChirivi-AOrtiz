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
        if (type == null) throw new IllegalArgumentException("type is required");
        if (performedAt == null) throw new IllegalArgumentException("performedAt is required");
        if (patientIdNumber == null || patientIdNumber.isBlank()) throw new IllegalArgumentException("patientIdNumber is required");
        if (nurseIdNumber == null || nurseIdNumber.isBlank()) throw new IllegalArgumentException("nurseIdNumber is required");
        if (orderType == null) throw new IllegalArgumentException("orderType is required");
        if (orderCreatedAt == null) throw new IllegalArgumentException("orderCreatedAt is required");
        if (itemNumber < 1) throw new IllegalArgumentException("itemNumber must be >= 1");
        if (catalogId == null || catalogId.isBlank()) throw new IllegalArgumentException("catalogId is required");
        if (quantity < 1) throw new IllegalArgumentException("quantity must be >= 1");

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
