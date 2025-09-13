package co.edu.tdea.clinicapp.application.port.in;

public class UpsertProcedureCommand {
    private final String id;
    private final String name;
    private final int unitCost;

    public UpsertProcedureCommand(String id, String name, int unitCost) {
        this.id = id;
        this.name = name;
        this.unitCost = unitCost;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getUnitCost() { return unitCost; }
}
