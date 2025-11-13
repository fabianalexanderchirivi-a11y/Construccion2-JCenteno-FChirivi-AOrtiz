package co.edu.tdea.clinicapp.domain.services;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAidOrderItem;
import co.edu.tdea.clinicapp.domain.model.MedicationOrderItem;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;
import co.edu.tdea.clinicapp.domain.model.ProcedureOrderItem;

import java.math.BigDecimal;
import java.util.function.Function;

public final class OrderTotalsService {

    public record Totals(BigDecimal medications, BigDecimal procedures, BigDecimal diagnostics, BigDecimal overall) {}

    public Totals calculate(Order order, Function<String, BigDecimal> priceOf) {
        if (order == null) throw new IllegalArgumentException("Orden inválida.");
        if (priceOf == null) throw new IllegalArgumentException("El precio no puede estar vacio.");

        BigDecimal meds = BigDecimal.ZERO;
        BigDecimal procs = BigDecimal.ZERO;
        BigDecimal diags = BigDecimal.ZERO;

        for (OrderItem i : order.getItems()) {
            BigDecimal unit = requirePositive(priceOf.apply(i.getCatalogId()), i.getCatalogId());

            if (i instanceof MedicationOrderItem m) {
                meds = meds.add(nz(m.getCost(unit)));
            } else if (i instanceof ProcedureOrderItem p) {
                procs = procs.add(nz(p.getCost(unit)));
            } else if (i instanceof DiagnosticAidOrderItem d) {
                diags = diags.add(nz(d.getCost(unit)));
            }
        }
        BigDecimal overall = meds.add(procs).add(diags);
        return new Totals(meds, procs, diags, overall);
    }

    private static BigDecimal nz(BigDecimal v) { return v == null ? BigDecimal.ZERO : v; }

    private static BigDecimal requirePositive(BigDecimal v, String catalogId) {
        if (v == null || v.signum() <= 0) {
            throw new IllegalStateException("Precio unitario inválido para Id del catalogo=" + catalogId);
        }
        return v;
    }
}
