package co.edu.tdea.clinicapp.domain.service;

import co.edu.tdea.clinicapp.domain.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientManagementService {
    Patient register(Patient patient);
    Patient update(Patient patient);
    Optional<Patient> findByPatientId(String patientId);
    List<Patient> findAll();
    void deactivate(String patientId);
}
