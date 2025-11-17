// src/main/java/co/edu/tdea/clinicapp/application/usecase/ListPatientsService.java
package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListPatientsUseCase;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
