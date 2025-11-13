package co.edu.tdea.clinicapp.application.command;

public class MedicationUpsertCommand {
    private final String id;
    private final String name;
    private final double cost;

    public MedicationUpsertCommand(String id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getCost() { return cost; }
}
