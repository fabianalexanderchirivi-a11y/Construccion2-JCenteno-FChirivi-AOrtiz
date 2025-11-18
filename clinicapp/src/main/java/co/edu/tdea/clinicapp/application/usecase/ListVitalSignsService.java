package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.ListVitalSignsUseCase;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.VitalSignsRepository;
import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListVitalSignsService implements ListVitalSignsUseCase {

    private final VitalSignsRepository vitalSignsRepository;
    private final PatientRepository patientRepository;

    public ListVitalSignsService(VitalSignsRepository vitalSignsRepository,
                                 PatientRepository patientRepository) {
        this.vitalSignsRepository = vitalSignsRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<VitalSignsRecord> listByPatient(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank()) {
            throw new IllegalArgumentException("El paciente es requerido.");
        }
        if (!patientRepository.existsByIdNumber(patientIdNumber)) {
            throw new IllegalArgumentException("Paciente no encontrado.");
        }
        return vitalSignsRepository.findAllByPatient(patientIdNumber);
    }
}
