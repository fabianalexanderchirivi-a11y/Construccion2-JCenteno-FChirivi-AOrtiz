package co.edu.tdea.clinicapp.domain.model;

import java.math.BigDecimal;

public class MedicationOrderItem extends OrderItem {
    private final String dose;        // ej. "500mg"
    private final String frequency;   // ej. "cada 8h"
    private final int durationDays;   // >=1

    public MedicationOrderItem(String medicationId, int quantity, String dose, String frequency, int durationDays) {
        super(medicationId, quantity);
        if (dose == null || dose.isBlank()) throw new IllegalArgumentException("dose is required");
        if (frequency == null || frequency.isBlank()) throw new IllegalArgumentException("frequency is required");
        if (durationDays < 1) throw new IllegalArgumentException("durationDays must be >= 1");
        this.dose = dose.trim();
        this.frequency = frequency.trim();
        this.durationDays = durationDays;
    }

    public String getDose() { return dose; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }

    public BigDecimal getCost(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.signum() <= 0) {
            throw new IllegalArgumentException("unitPrice must be > 0");
        }
        return unitPrice
            .multiply(BigDecimal.valueOf(getQuantity()))
            .multiply(BigDecimal.valueOf(durationDays));
    }
}
