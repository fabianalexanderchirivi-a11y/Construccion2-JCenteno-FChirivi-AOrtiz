package co.edu.tdea.clinicapp.application.command;

public class OrderItemCommand {

    public enum Type { MEDICATION, PROCEDURE, DIAGNOSTIC_AID }

    private final Type type;
    private final int itemNumber;
    private final String refId;
    private final String name;
    private final String dose;         // medication
    private final String duration;     // medication
    private final Integer times;       // procedure
    private final String frequency;    // procedure
    private final Boolean requiresSpecialist; // procedure/aid
    private final String specialtyId;        // procedure/aid
    private final Integer quantity;    // diagnostic aid
    private final double cost;

    public OrderItemCommand(Type type, int itemNumber, String refId, String name, String dose, String duration, Integer times, String frequency, Boolean requiresSpecialist, String specialtyId, Integer quantity, double cost) {
        this.type = type;
        this.itemNumber = itemNumber;
        this.refId = refId;
        this.name = name;
        this.dose = dose;
        this.duration = duration;
        this.times = times;
        this.frequency = frequency;
        this.requiresSpecialist = requiresSpecialist;
        this.specialtyId = specialtyId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Type getType() { return type; }
    public int getItemNumber() { return itemNumber; }
    public String getRefId() { return refId; }
    public String getName() { return name; }
    public String getDose() { return dose; }
    public String getDuration() { return duration; }
    public Integer getTimes() { return times; }
    public String getFrequency() { return frequency; }
    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public String getSpecialtyId() { return specialtyId; }
    public Integer getQuantity() { return quantity; }
    public double getCost() { return cost; }
}
