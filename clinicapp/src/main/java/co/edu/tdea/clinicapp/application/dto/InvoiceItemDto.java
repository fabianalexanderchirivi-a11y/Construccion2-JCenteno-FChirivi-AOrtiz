package co.edu.tdea.clinicapp.application.dto;

public class InvoiceItemDto {
    private String orderNumber;
    private int itemNumber;
    private String description;
    private double cost;

    public InvoiceItemDto() { }

    public InvoiceItemDto(String orderNumber, int itemNumber, String description, double cost) {
        this.orderNumber = orderNumber;
        this.itemNumber = itemNumber;
        this.description = description;
        this.cost = cost;
    }

    public String getOrderNumber() { return orderNumber; }
    public int getItemNumber() { return itemNumber; }
    public String getDescription() { return description; }
    public double getCost() { return cost; }

    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public void setItemNumber(int itemNumber) { this.itemNumber = itemNumber; }
    public void setDescription(String description) { this.description = description; }
    public void setCost(double cost) { this.cost = cost; }
}
