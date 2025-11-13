package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import co.edu.tdea.clinicapp.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public OrderEntity toHeaderEntity(Order d) {
        var e = new OrderEntity();
        e.setPatientDocument(d.getPatientIdNumber());
        e.setDoctorDocument(d.getDoctorIdNumber());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    public List<OrderMedicationEntity> toMedicationEntities(Order d, Long orderNumber) {
        var list = new ArrayList<OrderMedicationEntity>();
        for (OrderItem it : d.getItems()) {
            if (it instanceof MedicationOrderItem m) {
                var e = new OrderMedicationEntity();
                e.setOrderNumber(orderNumber);
                e.setItemNumber(m.getItemNumber());
                e.setMedicationName(m.getCatalogId());
                e.setDose(m.getDose());
                e.setDuration(String.valueOf(m.getDurationDays()));
                e.setCost(null);
                list.add(e);
            }
        }
        return list;
    }

    public List<OrderProcedureEntity> toProcedureEntities(Order d, Long orderNumber) {
        var list = new ArrayList<OrderProcedureEntity>();
        for (OrderItem it : d.getItems()) {
            if (it instanceof ProcedureOrderItem p) {
                var e = new OrderProcedureEntity();
                e.setOrderNumber(orderNumber);
                e.setItemNumber(p.getItemNumber());
                e.setProcedureName(p.getCatalogId());
                e.setTimes(p.getQuantity());
                e.setFrequency(p.getFrequency());
                boolean requires = p.getSpecialist() != null && !p.getSpecialist().isBlank();
                e.setRequiresSpecialist(requires);
                e.setSpecialistTypeId(requires ? p.getSpecialist() : null);
                e.setCost(null);
                list.add(e);
            }
        }
        return list;
    }

    public List<OrderDiagnosticEntity> toDiagnosticEntities(Order d, Long orderNumber) {
        var list = new ArrayList<OrderDiagnosticEntity>();
        for (OrderItem it : d.getItems()) {
            if (it instanceof DiagnosticAidOrderItem x) {
                var e = new OrderDiagnosticEntity();
                e.setOrderNumber(orderNumber);
                e.setItemNumber(x.getItemNumber());
                e.setDiagnosticName(x.getCatalogId());
                e.setQuantity(x.getQuantity());
                e.setRequiresSpecialist(null);
                e.setSpecialistTypeId(null);
                e.setCost(null);
                list.add(e);
            }
        }
        return list;
    }
    
    public Order toDomain(OrderEntity h,
                          List<OrderMedicationEntity> meds,
                          List<OrderProcedureEntity> procs,
                          List<OrderDiagnosticEntity> diags) {

        OrderType type = defaultType();
        var order = new Order(h.getPatientDocument(), h.getDoctorDocument(), type);

        for (var m : meds) {
            int durationDays = parseIntOrDefault(m.getDuration(), 1);
            int qty = 1;
            String frequency = "N/A"; 
            var item = new MedicationOrderItem(
                    m.getMedicationName(), 
                    qty,
                    m.getDose(),
                    frequency,
                    durationDays
            );
            order.addItem(item);
        }

        for (var p : procs) {
            int qty = p.getTimes() != null ? p.getTimes() : 1;
            String specialist = p.getSpecialistTypeId();
            var item = new ProcedureOrderItem(
                    p.getProcedureName(), 
                    qty,
                    p.getFrequency(),
                    specialist
            );
            order.addItem(item);
        }

        for (var d : diags) {
            int qty = d.getQuantity() != null ? d.getQuantity() : 1;
            var item = new DiagnosticAidOrderItem(
                    d.getDiagnosticName(), 
                    qty
            );
            order.addItem(item);
        }

        return order;
    }

    private static int parseIntOrDefault(String s, int def) {
        if (s == null || s.isBlank()) return def;
        try {
            String digits = s.replaceAll("\\D+", "");
            return digits.isEmpty() ? def : Integer.parseInt(digits);
        } catch (Exception ex) {
            return def;
        }
    }

    private static OrderType defaultType() {
        OrderType[] all = OrderType.values();
        if (all.length == 0) throw new IllegalStateException("OrderType enum sin valores definidos");
        return all[0];
    }
}
