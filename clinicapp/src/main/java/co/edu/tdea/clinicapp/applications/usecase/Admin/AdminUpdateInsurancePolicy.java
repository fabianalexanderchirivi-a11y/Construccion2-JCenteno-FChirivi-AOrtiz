package co.edu.tdea.clinicapp.applications.usecase.Admin;

import java.time.LocalDate;

public final class AdminUpdateInsurancePolicy {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String companyName;
        private final String policyNumber;
        private final boolean active;
        private final LocalDate expirationDate;

        public Command(String patientIdNumber, String companyName, String policyNumber, boolean active, LocalDate expirationDate) {
            this.patientIdNumber = patientIdNumber;
            this.companyName = companyName;
            this.policyNumber = policyNumber;
            this.active = active;
            this.expirationDate = expirationDate;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String companyName() { return companyName; }
        public String policyNumber() { return policyNumber; }
        public boolean active() { return active; }
        public LocalDate expirationDate() { return expirationDate; }
    }
}
