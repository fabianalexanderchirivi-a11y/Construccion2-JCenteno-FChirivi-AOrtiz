package co.edu.tdea.clinicapp.applications.usecase.Doctor;

import java.util.List;

public final class DoctorPrescribeMedication {
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
        private final String medicationCode;
        private final String dose;
        private final String duration;
        private final double cost;

        public Item(int itemNumber, String medicationCode, String dose, String duration, double cost) {
            this.itemNumber = itemNumber;
            this.medicationCode = medicationCode;
            this.dose = dose;
            this.duration = duration;
            this.cost = cost;
        }
        public int itemNumber() { return itemNumber; }
        public String medicationCode() { return medicationCode; }
        public String dose() { return dose; }
        public String duration() { return duration; }
        public double cost() { return cost; }
    }
}