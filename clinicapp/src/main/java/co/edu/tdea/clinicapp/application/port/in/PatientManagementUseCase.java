package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Patient;
import java.util.Optional;

public interface PatientManagementUseCase {

    void registerPatient(Patient patient);

    void updatePatient(Patient patient);

    Optional<Patient> getPatientByUsername(String username);

    Optional<Patient> getPatientByIdNumber(String idNumber);
}
