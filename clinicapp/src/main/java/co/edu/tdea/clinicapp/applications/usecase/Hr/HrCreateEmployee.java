package co.edu.tdea.clinicapp.applications.usecase.Hr;

import java.time.LocalDate;

public final class HrCreateEmployee {
    public String execute(Command command) {
        return command.idNumber;
    }
    public static final class Command {
        private final String requesterRole;
        private final String idNumber;
        private final String fullName;
        private final String email;
        private final String phoneNumber;
        private final LocalDate birthDate;
        private final String address;
        private final String role;

        public Command(String requesterRole, String idNumber, String fullName, String email, String phoneNumber, LocalDate birthDate, String address, String role) {
            this.requesterRole = requesterRole;
            this.idNumber = idNumber;
            this.fullName = fullName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.birthDate = birthDate;
            this.address = address;
            this.role = role;
        }
        public String requesterRole() { return requesterRole; }
        public String idNumber() { return idNumber; }
        public String fullName() { return fullName; }
        public String email() { return email; }
        public String phoneNumber() { return phoneNumber; }
        public LocalDate birthDate() { return birthDate; }
        public String address() { return address; }
        public String role() { return role; }
    }
}
