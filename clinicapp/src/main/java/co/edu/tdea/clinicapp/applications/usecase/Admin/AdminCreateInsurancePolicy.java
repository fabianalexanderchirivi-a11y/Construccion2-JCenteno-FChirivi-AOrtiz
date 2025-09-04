package co.edu.tdea.clinicapp.applications.usecase.Admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class AdminCreateInsurancePolicy {
    public String execute(Command command) {
        return command.policyNumber;
    }
    public static final class Command {
        private final String patientIdNumber;
        private final String companyName;
        private final String policyNumber;
        private final boolean active;
        private final LocalDate expirationDate;
        private final LocalDateTime issuedAt;

        public Command(String patientIdNumber, String companyName, String policyNumber, boolean active, LocalDate expirationDate, LocalDateTime issuedAt) {
            this.patientIdNumber = patientIdNumber;
            this.companyName = companyName;
            this.policyNumber = policyNumber;
            this.active = active;
            this.expirationDate = expirationDate;
            this.issuedAt = issuedAt;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String companyName() { return companyName; }
        public String policyNumber() { return policyNumber; }
        public boolean active() { return active; }
        public LocalDate expirationDate() { return expirationDate; }
        public LocalDateTime issuedAt() { return issuedAt; }
    }
}
