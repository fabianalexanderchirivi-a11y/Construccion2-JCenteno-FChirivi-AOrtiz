package co.edu.tdea.clinicapp.applications.usecase.Hr;

public final class HrAssignRoleToEmployee {
    public void execute(Command command) { }
    public static final class Command {
        private final String requesterRole;
        private final String idNumber;
        private final String newRole;

        public Command(String requesterRole, String idNumber, String newRole) {
            this.requesterRole = requesterRole;
            this.idNumber = idNumber;
            this.newRole = newRole;
        }
        public String requesterRole() { return requesterRole; }
        public String idNumber() { return idNumber; }
        public String newRole() { return newRole; }
    }
}
