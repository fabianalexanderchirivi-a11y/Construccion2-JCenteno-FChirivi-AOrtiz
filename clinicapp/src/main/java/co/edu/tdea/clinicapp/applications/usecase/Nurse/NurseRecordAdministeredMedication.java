package co.edu.tdea.clinicapp.applications.usecase.Nurse;

import java.time.LocalDateTime;

public final class NurseRecordAdministeredMedication {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String orderNumber;
        private final int itemNumber;
        private final String medicationCode;
        private final String dose;
        private final String route;
        private final String notes;
        private final LocalDateTime dateTime;

        public Command(String patientIdNumber, String orderNumber, int itemNumber, String medicationCode, String dose, String route, String notes, LocalDateTime dateTime) {
            this.patientIdNumber = patientIdNumber;
            this.orderNumber = orderNumber;
            this.itemNumber = itemNumber;
            this.medicationCode = medicationCode;
            this.dose = dose;
            this.route = route;
            this.notes = notes;
            this.dateTime = dateTime;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String orderNumber() { return orderNumber; }
        public int itemNumber() { return itemNumber; }
        public String medicationCode() { return medicationCode; }
        public String dose() { return dose; }
        public String route() { return route; }
        public String notes() { return notes; }
        public LocalDateTime dateTime() { return dateTime; }
    }
}