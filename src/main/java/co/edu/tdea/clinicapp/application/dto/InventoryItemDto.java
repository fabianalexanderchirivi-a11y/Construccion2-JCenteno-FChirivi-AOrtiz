package co.edu.tdea.clinicapp.application.dto;

public class InventoryItemDto {
    private String id;
    private String name;
    private double cost;

    public InventoryItemDto() { }
    public InventoryItemDto(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getCost() { return cost; }
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCost(double cost) { this.cost = cost; }
}
