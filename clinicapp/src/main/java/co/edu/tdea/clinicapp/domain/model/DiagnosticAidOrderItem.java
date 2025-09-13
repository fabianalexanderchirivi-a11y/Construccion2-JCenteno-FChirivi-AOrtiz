package co.edu.tdea.clinicapp.domain.model;

import java.math.BigDecimal;

public class DiagnosticAidOrderItem extends OrderItem {
    public DiagnosticAidOrderItem(String aidId, int quantity) {
        super(aidId, quantity);
    }

    public BigDecimal getCost(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.signum() <= 0) {
            throw new IllegalArgumentException("unitPrice must be > 0");
        }
        return unitPrice.multiply(BigDecimal.valueOf(getQuantity()));
    }
}
