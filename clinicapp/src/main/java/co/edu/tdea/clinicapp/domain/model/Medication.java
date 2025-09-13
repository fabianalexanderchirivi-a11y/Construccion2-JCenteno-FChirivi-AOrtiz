package co.edu.tdea.clinicapp.domain.model;

public class Medication {
    private static final String ID_RX = "^[A-Za-z0-9]{1,10}$";

    private final String id;
    private String name;
    private int unitCost; // COP >= 0

    public Medication(String id, String name, int unitCost) {
        if (id == null || !id.matches(ID_RX)) throw new IllegalArgumentException("invalid id");
        this.id = id;
        setName(name);
        setUnitCost(unitCost);
    }

    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        this.name = name.trim();
    }

    public int getUnitCost() { return unitCost; }
    public void setUnitCost(int unitCost) {
        if (unitCost < 0) throw new IllegalArgumentException("unitCost must be >= 0");
        this.unitCost = unitCost;
    }
}
