package co.edu.tdea.clinicapp.applications.usecase.Admin;

public final class AdminGenerateInvoice {
    public String execute(Command command) {
        return command.visitId + "-INV";
    }
    public static final class Command {
        private final String patientIdNumber;
        private final String visitId;

        public Command(String patientIdNumber, String visitId) {
            this.patientIdNumber = patientIdNumber;
            this.visitId = visitId;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String visitId() { return visitId; }
    }
}
