package co.edu.tdea.clinicapp.application.port.in;

public class OrderItemSummaryDto {
    private final java.time.LocalDateTime createdAt;
    private final co.edu.tdea.clinicapp.domain.model.OrderType type;
    private final int itemNumber;
    private final String catalogId;
    private final int quantity;

    public OrderItemSummaryDto(java.time.LocalDateTime createdAt,
                               co.edu.tdea.clinicapp.domain.model.OrderType type,
                               int itemNumber,
                               String catalogId,
                               int quantity) {
        this.createdAt = createdAt;
        this.type = type;
        this.itemNumber = itemNumber;
        this.catalogId = catalogId;
        this.quantity = quantity;
    }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public co.edu.tdea.clinicapp.domain.model.OrderType getType() { return type; }
    public int getItemNumber() { return itemNumber; }
    public String getCatalogId() { return catalogId; }
    public int getQuantity() { return quantity; }
}
