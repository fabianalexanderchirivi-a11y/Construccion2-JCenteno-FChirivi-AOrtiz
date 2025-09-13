package co.edu.tdea.clinicapp.domain.services;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAidOrderItem;
import co.edu.tdea.clinicapp.domain.model.MedicationOrderItem;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;
import co.edu.tdea.clinicapp.domain.model.ProcedureOrderItem;

import java.math.BigDecimal;
import java.util.function.Function;

public final class BillingPolicyService {
    private static final BigDecimal COPAY = new BigDecimal("50000");
    private static final BigDecimal YEAR_CAP = new BigDecimal("1000000");

    public record CopayResult(BigDecimal total, BigDecimal copay, BigDecimal insurerShare, BigDecimal patientShare) {}

    public CopayResult calculateFor(Order order, boolean policyActive, BigDecimal yearCopayAccumulated,
                                    Function<String, BigDecimal> priceOf) {
        if (order == null) throw new IllegalArgumentException("Orden inválida.");
        if (priceOf == null) throw new IllegalArgumentException("priceOf no puede ser null.");
        BigDecimal total = totalCost(order, priceOf);
        return calculate(total, policyActive, yearCopayAccumulated);
    }

    public CopayResult calculate(BigDecimal total, boolean policyActive, BigDecimal yearCopayAccumulated) {
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total inválido.");
        }
        if (yearCopayAccumulated == null || yearCopayAccumulated.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Acumulado anual inválido.");
        }

        if (!policyActive) {
            return new CopayResult(total, BigDecimal.ZERO, BigDecimal.ZERO, total);
        }
        if (yearCopayAccumulated.compareTo(YEAR_CAP) >= 0) {
            return new CopayResult(total, BigDecimal.ZERO, total, BigDecimal.ZERO);
        }

        BigDecimal copay = total.min(COPAY);
        BigDecimal insurer = total.subtract(copay);
        return new CopayResult(total, copay, insurer, copay);
    }

    private BigDecimal totalCost(Order order, Function<String, BigDecimal> priceOf) {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem i : order.getItems()) {
            BigDecimal unit = requirePositive(priceOf.apply(i.getCatalogId()), i.getCatalogId());

            if (i instanceof MedicationOrderItem m) {
                sum = sum.add(nz(m.getCost(unit)));
            } else if (i instanceof ProcedureOrderItem p) {
                sum = sum.add(nz(p.getCost(unit)));
            } else if (i instanceof DiagnosticAidOrderItem d) {
                sum = sum.add(nz(d.getCost(unit)));
            }
        }
        return sum;
    }

    private static BigDecimal nz(BigDecimal v) { return v == null ? BigDecimal.ZERO : v; }

    private static BigDecimal requirePositive(BigDecimal v, String catalogId) {
        if (v == null || v.signum() <= 0) {
            throw new IllegalStateException("Precio unitario inválido para catalogId=" + catalogId);
        }
        return v;
    }
}
