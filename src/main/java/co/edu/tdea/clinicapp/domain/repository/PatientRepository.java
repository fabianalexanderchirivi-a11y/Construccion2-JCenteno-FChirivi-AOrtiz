package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Patient;

public interface PatientRepository {
    boolean existsByIdNumber(String idNumber);
    Optional<Patient> findByIdNumber(String idNumber);
    Patient save(Patient patient);
    List<Patient> findAll();
}
