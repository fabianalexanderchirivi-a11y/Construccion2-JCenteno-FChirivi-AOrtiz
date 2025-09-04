package co.edu.tdea.clinicapp.applications.usecase.Doctor;

import java.util.List;

public final class DoctorOrderProcedure {
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
        private final String procedureCode;
        private final int times;
        private final String frequency;
        private final boolean requiresSpecialist;
        private final String specialtyId;
        private final double cost;

        public Item(int itemNumber, String procedureCode, int times, String frequency, boolean requiresSpecialist, String specialtyId, double cost) {
            this.itemNumber = itemNumber;
            this.procedureCode = procedureCode;
            this.times = times;
            this.frequency = frequency;
            this.requiresSpecialist = requiresSpecialist;
            this.specialtyId = specialtyId;
            this.cost = cost;
        }
        public int itemNumber() { return itemNumber; }
        public String procedureCode() { return procedureCode; }
        public int times() { return times; }
        public String frequency() { return frequency; }
        public boolean requiresSpecialist() { return requiresSpecialist; }
        public String specialtyId() { return specialtyId; }
        public double cost() { return cost; }
    }
}
