package co.edu.tdea.clinicapp.domain.model;

public abstract class OrderItem {
    private int itemNumber;         
    private final String catalogId; 
    private final int quantity;     

    protected OrderItem(String catalogId, int quantity) {
        if (catalogId == null || catalogId.isBlank()) throw new IllegalArgumentException("catalogId is required");
        if (quantity < 1) throw new IllegalArgumentException("quantity must be >= 1");
        this.catalogId = catalogId;
        this.quantity = quantity;
    }

    public int getItemNumber() { return itemNumber; }
    public void setItemNumber(int itemNumber) {
        if (itemNumber < 1) throw new IllegalArgumentException("itemNumber must be >= 1");
        this.itemNumber = itemNumber;
    }

    public String getCatalogId() { return catalogId; }
    public int getQuantity() { return quantity; }
}
