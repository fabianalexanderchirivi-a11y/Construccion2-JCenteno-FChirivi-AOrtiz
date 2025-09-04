package co.edu.tdea.clinicapp.applications.usecase.Doctor;

import java.util.List;

public final class DoctorOrderDiagnosticAid {
    public void execute(Command command) { }
    public static final class Command {
        private final String visitId;
        private final String orderNumber;
        private final String patientIdNumber;
        private final String doctorIdNumber;
        private final List<Item> items;

        public Command(String visitId, String orderNumber, String patientIdNumber, String doctorIdNumber, List<Item> items) {
            this.visitId = visitId;
            this.orderNumber = orderNumber;
            this.patientIdNumber = patientIdNumber;
            this.doctorIdNumber = doctorIdNumber;
            this.items = items;
        }
        public String visitId() { return visitId; }
        public String orderNumber() { return orderNumber; }
        public String patientIdNumber() { return patientIdNumber; }
        public String doctorIdNumber() { return doctorIdNumber; }
        public List<Item> items() { return items; }
    }

    public static final class Item {
        private final int itemNumber;
        private final String diagnosticAidCode;
        private final int quantity;
        private final boolean requiresSpecialist;
        private final String specialtyId;

        public Item(int itemNumber, String diagnosticAidCode, int quantity, boolean requiresSpecialist, String specialtyId) {
            this.itemNumber = itemNumber;
            this.diagnosticAidCode = diagnosticAidCode;
            this.quantity = quantity;
            this.requiresSpecialist = requiresSpecialist;
            this.specialtyId = specialtyId;
        }
        public int itemNumber() { return itemNumber; }
        public String diagnosticAidCode() { return diagnosticAidCode; }
        public int quantity() { return quantity; }
        public boolean requiresSpecialist() { return requiresSpecialist; }
        public String specialtyId() { return specialtyId; }
    }
}