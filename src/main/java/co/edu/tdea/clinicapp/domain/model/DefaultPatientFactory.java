package co.edu.tdea.clinicapp.domain.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultPatientFactory implements PatientFactory {

    @Override
    public Patient fromPersistence(
            String idNumber,
            String fullName,
            LocalDate birthDate,
            Gender gender,
            String address,
            String phoneNumber,
            String email
    ) {
        String[] parts = splitName(fullName);
        String ecFirst = parts[0];
        String ecLast  = parts[1];
        String ecRel   = "FAMILY"; 

        String ecPhone = (phoneNumber != null && phoneNumber.matches("\\d{10}"))
                ? phoneNumber
                : "0000000000"; 

        EmergencyContact ec = new EmergencyContact(ecFirst, ecLast, ecRel, ecPhone);

        String companyName  = "NONE";
        String policyNumber = idNumber != null ? idNumber : "N/A";
        boolean active      = false;
        LocalDate endDate   = LocalDate.now().plusYears(1);

        InsurancePolicy ip = new InsurancePolicy(companyName, policyNumber, active, endDate);

        Gender safeGender = (gender != null) ? gender : Gender.valueOf("O"); 

        String safeAddress = (address == null || address.isBlank()) ? "N/A" : address;
        String safePhone   = (phoneNumber != null && phoneNumber.matches("\\d{10}")) ? phoneNumber : "0000000000";

        return new Patient(
                idNumber,
                fullName,
                birthDate,
                safeGender,
                safeAddress,
                safePhone,
                email,
                ec,
                ip
        );
    }

    private static String[] splitName(String fullName) {
        if (fullName == null || fullName.isBlank()) return new String[]{"N/A", "N/A"};
        String[] tokens = fullName.trim().split("\\s+");
        if (tokens.length == 1) return new String[]{tokens[0], "N/A"};
        String first = tokens[0];
        String last  = String.join(" ", java.util.Arrays.copyOfRange(tokens, 1, tokens.length));
        return new String[]{first, last};
    }
}
