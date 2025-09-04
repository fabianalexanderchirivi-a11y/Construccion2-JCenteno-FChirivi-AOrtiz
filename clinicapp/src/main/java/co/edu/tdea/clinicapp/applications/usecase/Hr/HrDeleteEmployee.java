package co.edu.tdea.clinicapp.applications.usecase.Hr;

public final class HrDeleteEmployee {
    public void execute(Command command) { }
    public static final class Command {
        private final String requesterRole;
        private final String idNumber;

        public Command(String requesterRole, String idNumber) {
            this.requesterRole = requesterRole;
            this.idNumber = idNumber;
        }
        public String requesterRole() { return requesterRole; }
        public String idNumber() { return idNumber; }
    }
}
