package co.edu.tdea.clinicapp.applications.usecase.Hr;

import java.time.LocalDate;

public final class HrUpdateEmployee {
    public void execute(Command command) { }
    public static final class Command {
        private final String requesterRole;
        private final String idNumber;
        private final String fullName;
        private final String email;
        private final String phoneNumber;
        private final String address;
        private final LocalDate birthDate;

        public Command(String requesterRole, String idNumber, String fullName, String email, String phoneNumber, String address, LocalDate birthDate) {
            this.requesterRole = requesterRole;
            this.idNumber = idNumber;
            this.fullName = fullName;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.birthDate = birthDate;
        }
        public String requesterRole() { return requesterRole; }
        public String idNumber() { return idNumber; }
        public String fullName() { return fullName; }
        public String email() { return email; }
        public String phoneNumber() { return phoneNumber; }
        public String address() { return address; }
        public LocalDate birthDate() { return birthDate; }
    }
}
