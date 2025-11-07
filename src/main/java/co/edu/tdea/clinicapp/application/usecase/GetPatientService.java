package co.edu.tdea.clinicapp.application.usecase;

import java.util.Optional;

import co.edu.tdea.clinicapp.application.port.in.GetPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GetPatientService implements GetPatientUseCase {

    private final PatientRepository patientRepository;

    public GetPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> byIdNumber(String idNumber) {
        if (idNumber == null || idNumber.isBlank()) {
            throw new IllegalArgumentException("El numero de la ID es requerido.");
        }
        return patientRepository.findByIdNumber(idNumber);
    }
}
