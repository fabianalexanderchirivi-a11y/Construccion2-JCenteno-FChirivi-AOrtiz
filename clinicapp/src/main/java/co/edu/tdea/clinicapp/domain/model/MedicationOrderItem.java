package co.edu.tdea.clinicapp.domain.model;

import java.math.BigDecimal;

public class MedicationOrderItem extends OrderItem {
    private final String dose;         
    private final String frequency;    
    private final int durationDays;   

    public MedicationOrderItem(String medicationId, int quantity, String dose, String frequency, int durationDays) {
        super(medicationId, quantity);
        if (dose == null || dose.isBlank()) throw new IllegalArgumentException("Una dosis es requerida");
        if (frequency == null || frequency.isBlank()) throw new IllegalArgumentException("La frecuencia es requerida");
        if (durationDays < 1) throw new IllegalArgumentException("Dias de duracion debe ser mayor a >= 1");
        this.dose = dose.trim();
        this.frequency = frequency.trim();
        this.durationDays = durationDays;
    }

    public String getDose() { return dose; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }

    public BigDecimal getCost(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.signum() <= 0) {
            throw new IllegalArgumentException("El precio de la unidad debe ser mayor a > 0");
        }
        return unitPrice
            .multiply(BigDecimal.valueOf(getQuantity()))
            .multiply(BigDecimal.valueOf(durationDays));
    }
}
