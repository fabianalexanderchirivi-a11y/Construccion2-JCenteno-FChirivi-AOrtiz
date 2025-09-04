package co.edu.tdea.clinicapp.applications.usecase.Admin;

import java.time.LocalDate;

public final class AdminRegisterPatient {
    public String execute(Command command) {
        return command.idNumber;
    }
    public static final class Command {
        private final String idNumber;
        private final String fullName;
        private final String username;
        private final String rawPassword;
        private final LocalDate birthDate;
        private final String gender;
        private final String address;
        private final String phoneNumber;
        private final String email;
        private final EmergencyContact emergencyContact;

        public Command(String idNumber, String fullName, String username, String rawPassword, LocalDate birthDate, String gender, String address, String phoneNumber, String email, EmergencyContact emergencyContact) {
            this.idNumber = idNumber;
            this.fullName = fullName;
            this.username = username;
            this.rawPassword = rawPassword;
            this.birthDate = birthDate;
            this.gender = gender;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.emergencyContact = emergencyContact;
        }
        public String idNumber() { return idNumber; }
        public String fullName() { return fullName; }
        public String username() { return username; }
        public String rawPassword() { return rawPassword; }
        public LocalDate birthDate() { return birthDate; }
        public String gender() { return gender; }
        public String address() { return address; }
        public String phoneNumber() { return phoneNumber; }
        public String email() { return email; }
        public EmergencyContact emergencyContact() { return emergencyContact; }
    }

    public static final class EmergencyContact {
        private final String firstName;
        private final String lastName;
        private final String relationship;
        private final String phoneNumber;

        public EmergencyContact(String firstName, String lastName, String relationship, String phoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.relationship = relationship;
            this.phoneNumber = phoneNumber;
        }
        public String firstName() { return firstName; }
        public String lastName() { return lastName; }
        public String relationship() { return relationship; }
        public String phoneNumber() { return phoneNumber; }
    }
}
