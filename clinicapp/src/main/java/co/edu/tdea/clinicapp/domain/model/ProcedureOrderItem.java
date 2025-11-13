package co.edu.tdea.clinicapp.domain.model;

import java.math.BigDecimal;

public class ProcedureOrderItem extends OrderItem {
    private final String frequency;   
    private final String specialist;  

    public ProcedureOrderItem(String procedureId, int quantity, String frequency, String specialist) {
        super(procedureId, quantity);
        this.frequency = (frequency == null || frequency.isBlank()) ? null : frequency.trim();
        this.specialist = (specialist == null || specialist.isBlank()) ? null : specialist.trim();
    }

    public String getFrequency() { return frequency; }
    public String getSpecialist() { return specialist; }

    public BigDecimal getCost(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.signum() <= 0) {
            throw new IllegalArgumentException("El costo de unidad debe ser > 0");
        }
        return unitPrice.multiply(BigDecimal.valueOf(getQuantity()));
    }
}
