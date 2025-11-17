package co.edu.tdea.clinicapp.application.service;

import org.springframework.stereotype.Service;
import co.edu.tdea.clinicapp.application.port.in.ListPatientsUseCase;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;

import java.util.List;

@Service
public class ListPatientsService implements ListPatientsUseCase {

    private final PatientRepository patientRepository;

    public ListPatientsService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> list() {
        return patientRepository.findAll();
    }
}
