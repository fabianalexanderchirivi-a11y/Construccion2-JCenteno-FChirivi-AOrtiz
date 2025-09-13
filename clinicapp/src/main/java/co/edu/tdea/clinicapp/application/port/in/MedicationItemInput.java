package co.edu.tdea.clinicapp.application.port.in;

public class MedicationItemInput {
    private final String medicationId;
    private final int quantity;
    private final String dose;
    private final String frequency;
    private final int durationDays;

    public MedicationItemInput(String medicationId, int quantity, String dose, String frequency, int durationDays) {
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.dose = dose;
        this.frequency = frequency;
        this.durationDays = durationDays;
    }

    public String getMedicationId() { return medicationId; }
    public int getQuantity() { return quantity; }
    public String getDose() { return dose; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }
}
