package co.edu.tdea.clinicapp.domain.model;

public class InvoiceItem {
    private final InvoiceItemType type;
    private final String catalogId;
    private final String name;
    private final int unitCost;
    private final int quantity;
    private final String dose;
    private final String frequency;
    private final Integer durationDays;
    private final String specialist;

    public InvoiceItem(InvoiceItemType type, String catalogId, String name, int unitCost, int quantity,
                       String dose, String frequency, Integer durationDays, String specialist) {
        if (type == null) throw new IllegalArgumentException("Un tipo es necesario");
        if (catalogId == null || catalogId.isBlank()) throw new IllegalArgumentException("EL ID del catalogo es requerido.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("El nombre es requerido.");
        if (unitCost < 0) throw new IllegalArgumentException("El costo de la unidad debe ser >= 0");
        if (quantity < 1) throw new IllegalArgumentException("La cantidad debe ser >= 1");
        this.type = type;
        this.catalogId = catalogId;
        this.name = name.trim();
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.dose = (dose == null || dose.isBlank()) ? null : dose.trim();
        this.frequency = (frequency == null || frequency.isBlank()) ? null : frequency.trim();
        this.durationDays = durationDays;
        this.specialist = (specialist == null || specialist.isBlank()) ? null : specialist.trim();
    }

    public InvoiceItemType getType() { return type; }
    public String getCatalogId() { return catalogId; }
    public String getName() { return name; }
    public int getUnitCost() { return unitCost; }
    public int getQuantity() { return quantity; }
    public String getDose() { return dose; }
    public String getFrequency() { return frequency; }
    public Integer getDurationDays() { return durationDays; }
    public String getSpecialist() { return specialist; }
    public int getLineTotal() { return unitCost * quantity; }
}
