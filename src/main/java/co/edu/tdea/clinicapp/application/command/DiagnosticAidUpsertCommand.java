package co.edu.tdea.clinicapp.application.command;

public class DiagnosticAidUpsertCommand {
    private final String id;
    private final String name;
    private final double cost;
    private final boolean requiresSpecialist;

    public DiagnosticAidUpsertCommand(String id, String name, double cost, boolean requiresSpecialist) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.requiresSpecialist = requiresSpecialist;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getCost() { return cost; }
    public boolean isRequiresSpecialist() { return requiresSpecialist; }
}
