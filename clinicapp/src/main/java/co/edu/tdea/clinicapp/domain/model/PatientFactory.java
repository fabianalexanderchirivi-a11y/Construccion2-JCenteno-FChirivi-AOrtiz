package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;

public interface PatientFactory {
    Patient fromPersistence(
        String idNumber,
        String fullName,
        LocalDate birthDate,
        Gender gender,
        String address,
        String phoneNumber,
        String email
    );
}
