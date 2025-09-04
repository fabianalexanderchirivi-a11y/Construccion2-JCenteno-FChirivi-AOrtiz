package co.edu.tdea.clinicapp.applications.usecase.Admin;

import java.time.LocalDateTime;

public final class AdminScheduleAppointment {
    public String execute(Command command) {
        return command.externalIdHint == null ? command.patientIdNumber + "-" + command.doctorIdNumber : command.externalIdHint;
    }
    public static final class Command {
        private final String patientIdNumber;
        private final String doctorIdNumber;
        private final LocalDateTime dateTime;
        private final String reason;
        private final String externalIdHint;

        public Command(String patientIdNumber, String doctorIdNumber, LocalDateTime dateTime, String reason, String externalIdHint) {
            this.patientIdNumber = patientIdNumber;
            this.doctorIdNumber = doctorIdNumber;
            this.dateTime = dateTime;
            this.reason = reason;
            this.externalIdHint = externalIdHint;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String doctorIdNumber() { return doctorIdNumber; }
        public LocalDateTime dateTime() { return dateTime; }
        public String reason() { return reason; }
        public String externalIdHint() { return externalIdHint; }
    }
}
