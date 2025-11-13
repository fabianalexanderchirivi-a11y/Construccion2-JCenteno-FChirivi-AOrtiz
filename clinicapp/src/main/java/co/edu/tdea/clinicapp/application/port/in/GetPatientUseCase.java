package co.edu.tdea.clinicapp.application.port.in;

import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Patient;

public interface GetPatientUseCase {
    Optional<Patient> byIdNumber(String idNumber);
}
