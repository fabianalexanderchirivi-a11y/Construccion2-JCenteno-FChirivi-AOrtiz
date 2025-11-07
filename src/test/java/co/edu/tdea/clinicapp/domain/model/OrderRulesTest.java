package co.edu.tdea.clinicapp.domain.model;

import org.junit.jupiter.api.Test;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class OrderRulesTest {

    @Test
    void cannotAddMedicationAfterDiagnosticAid() {
        Order order = makeOrder("ORD-1", "1001", "D-01", LocalDateTime.now());
        order.addItem(makeDiagnosticAid("ORD-1", 1, "XR-001", "Radiografía de tórax", new BigDecimal("80000")));
        assertThrows(IllegalStateException.class, () ->
                order.addItem(makeMedication("ORD-1", 2, "MED-001", "Amoxicilina", new BigDecimal("5000"), 10, "500 mg c/8h"))
        );
    }

    @Test
    void cannotAddDiagnosticAidIfOrderHasItems() {
        Order order = makeOrder("ORD-2", "1002", "D-02", LocalDateTime.now());
        order.addItem(makeProcedure("ORD-2", 1, "PROC-001", "Curación", new BigDecimal("20000"), 1));
        assertThrows(IllegalStateException.class, () ->
                order.addItem(makeDiagnosticAid("ORD-2", 2, "LAB-001", "Hemograma", new BigDecimal("35000")))
        );
    }

    @Test
    void rejectDuplicateItemNumberInSameOrder() {
        Order order = makeOrder("ORD-3", "1003", "D-03", LocalDateTime.now());
        order.addItem(makeProcedure("ORD-3", 1, "PROC-010", "Sutura", new BigDecimal("45000"), 1));
        assertThrows(IllegalArgumentException.class, () ->
                order.addItem(makeMedication("ORD-3", 1, "MED-010", "Ibuprofeno", new BigDecimal("1200"), 5, "200 mg c/8h"))
        );
    }

    @Test
    void itemNumberMustStartAtOne() {
        assertThrows(IllegalArgumentException.class, () ->
                makeProcedure("ORD-4", 0, "PROC-099", "Limpieza", new BigDecimal("10000"), 1)
        );
        assertThrows(IllegalArgumentException.class, () ->
                makeMedication("ORD-4", -1, "MED-099", "Paracetamol", new BigDecimal("900"), 2, "500 mg")
        );
    }

    private static Order makeOrder(String ord, String patient, String doctor, LocalDateTime created) {
        Order o = (Order) instantiate(Order.class);
        setAny(o, new String[]{"orderNumber","number","orderId","id"}, ord);
        setAny(o, new String[]{"patientId","patientIdNumber","patient","patientID"}, patient);
        setAny(o, new String[]{"doctorId","doctorIdNumber","doctor","doctorID"}, doctor);
        setAny(o, new String[]{"createdAt","dateTime","orderedAt","issuedAt","created","timestamp"}, created);
        return o;
    }

    private static MedicationOrderItem makeMedication(String ord, int num, String code, String desc, BigDecimal price, int qty, String dose) {
        MedicationOrderItem it = (MedicationOrderItem) instantiate(MedicationOrderItem.class);
        set(it, "orderNumber", ord);
        set(it, "itemNumber", num);
        set(it, "code", code);
        set(it, "description", desc);
        set(it, "unitPrice", price);
        set(it, "quantity", qty);
        set(it, "dosage", dose);
        return it;
    }

    private static ProcedureOrderItem makeProcedure(String ord, int num, String code, String desc, BigDecimal price, int qty) {
        ProcedureOrderItem it = (ProcedureOrderItem) instantiate(ProcedureOrderItem.class);
        set(it, "orderNumber", ord);
        set(it, "itemNumber", num);
        set(it, "code", code);
        set(it, "description", desc);
        set(it, "unitPrice", price);
        set(it, "quantity", qty);
        return it;
    }

    private static DiagnosticAidOrderItem makeDiagnosticAid(String ord, int num, String code, String desc, BigDecimal price) {
        DiagnosticAidOrderItem it = (DiagnosticAidOrderItem) instantiate(DiagnosticAidOrderItem.class);
        set(it, "orderNumber", ord);
        set(it, "itemNumber", num);
        set(it, "code", code);
        set(it, "description", desc);
        set(it, "unitPrice", price);
        set(it, "quantity", 1);
        return it;
    }

    private static Object instantiate(Class<?> cls) {
        for (Constructor<?> c : cls.getDeclaredConstructors()) {
            try {
                c.setAccessible(true);
                Object[] args = new Object[c.getParameterCount()];
                Class<?>[] types = c.getParameterTypes();
                for (int i = 0; i < types.length; i++) args[i] = dummy(types[i]);
                return c.newInstance(args);
            } catch (ReflectiveOperationException ignored) {}
        }
        throw new IllegalStateException("No constructor usable para " + cls.getSimpleName());
    }

    private static Object dummy(Class<?> t) {
        if (t == String.class) return "X";
        if (t == int.class || t == Integer.class) return 1;
        if (t == long.class || t == Long.class) return 1L;
        if (t == boolean.class || t == Boolean.class) return false;
        if (t == BigDecimal.class) return new BigDecimal("1");
        if (t == LocalDateTime.class) return LocalDateTime.now();
        if (t == LocalDate.class) return LocalDate.now();
        if (t.isEnum()) return t.getEnumConstants()[0];
        try { return t.getDeclaredConstructor().newInstance(); } catch (Exception e) { return null; }
    }

    private static void setAny(Object target, String[] names, Object value) {
        for (String n : names) {
            if (trySetter(target, n, value)) return;
            if (tryField(target, n, value)) return;
        }
    }

    private static void set(Object target, String name, Object value) {
        if (!trySetter(target, name, value)) tryField(target, name, value);
    }

    private static boolean trySetter(Object target, String name, Object value) {
        String m = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        for (Method method : target.getClass().getMethods()) {
            if (method.getName().equals(m) && method.getParameterCount() == 1) {
                Class<?> pt = method.getParameterTypes()[0];
                if (isAssignable(pt, value)) {
                    try { method.invoke(target, value); return true; } catch (Exception ignored) {}
                }
            }
        }
        return false;
    }

    private static boolean tryField(Object target, String name, Object value) {
        Class<?> c = target.getClass();
        while (c != null) {
            try {
                Field f = c.getDeclaredField(name);
                f.setAccessible(true);
                if (isAssignable(f.getType(), value)) { f.set(target, value); return true; }
                return false;
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            } catch (ReflectiveOperationException e) {
                return false;
            }
        }
        return false;
    }

    private static boolean isAssignable(Class<?> type, Object value) {
        if (value == null) return true;
        if (type.isPrimitive()) {
            if (type == int.class) return value instanceof Integer;
            if (type == long.class) return value instanceof Long;
            if (type == boolean.class) return value instanceof Boolean;
        }
        return type.isInstance(value);
    }
}
