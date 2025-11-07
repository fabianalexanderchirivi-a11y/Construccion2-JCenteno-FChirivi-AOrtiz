package co.edu.tdea.clinicapp.application.usecase;

import java.util.Comparator;
import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.GetClinicalHistoryByPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.ClinicalHistoryRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GetClinicalHistoryByPatientService implements GetClinicalHistoryByPatientUseCase {

    private final ClinicalHistoryRepository clinicalHistoryRepository;
    private final PatientRepository patientRepository;

    public GetClinicalHistoryByPatientService(ClinicalHistoryRepository clinicalHistoryRepository,
                                              PatientRepository patientRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<ClinicalHistoryEntry> list(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es necesario.");
        if (!patientRepository.existsByIdNumber(patientIdNumber))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var entries = clinicalHistoryRepository.findAllByPatient(patientIdNumber);
        entries.sort(Comparator.comparing(ClinicalHistoryEntry::getAttendedAt).reversed());
        return entries;
    }
}
