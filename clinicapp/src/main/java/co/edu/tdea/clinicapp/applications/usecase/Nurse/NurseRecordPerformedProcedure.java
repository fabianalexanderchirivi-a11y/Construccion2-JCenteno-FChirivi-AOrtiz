package co.edu.tdea.clinicapp.applications.usecase.Nurse;

import java.time.LocalDateTime;

public final class NurseRecordPerformedProcedure {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String orderNumber;
        private final int itemNumber;
        private final String procedureCode;
        private final String notes;
        private final LocalDateTime dateTime;

        public Command(String patientIdNumber, String orderNumber, int itemNumber, String procedureCode, String notes, LocalDateTime dateTime) {
            this.patientIdNumber = patientIdNumber;
            this.orderNumber = orderNumber;
            this.itemNumber = itemNumber;
            this.procedureCode = procedureCode;
            this.notes = notes;
            this.dateTime = dateTime;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String orderNumber() { return orderNumber; }
        public int itemNumber() { return itemNumber; }
        public String procedureCode() { return procedureCode; }
        public String notes() { return notes; }
        public LocalDateTime dateTime() { return dateTime; }
    }
}