package co.edu.tdea.clinicapp.application.usecase;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.tdea.clinicapp.application.port.in.GetClinicalHistoryByPatientBetweenDatesUseCase;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.ClinicalHistoryRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GetClinicalHistoryByPatientBetweenDatesService
        implements GetClinicalHistoryByPatientBetweenDatesUseCase {

    private final ClinicalHistoryRepository clinicalHistoryRepository;
    private final PatientRepository patientRepository;

    public GetClinicalHistoryByPatientBetweenDatesService(ClinicalHistoryRepository clinicalHistoryRepository,
                                                          PatientRepository patientRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<ClinicalHistoryEntry> list(String patientIdNumber,
                                           LocalDateTime fromInclusive,
                                           LocalDateTime toExclusive) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es requerido.");
        if (fromInclusive == null || toExclusive == null || !toExclusive.isAfter(fromInclusive))
            throw new IllegalArgumentException("El rango de la cita de la fecha, es invalido.");
        if (!patientRepository.existsByIdNumber(patientIdNumber))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var entries = clinicalHistoryRepository.findAllByPatient(patientIdNumber).stream()
                .filter(e -> !e.getAttendedAt().isBefore(fromInclusive) && e.getAttendedAt().isBefore(toExclusive))
                .sorted(Comparator.comparing(ClinicalHistoryEntry::getAttendedAt).reversed())
                .collect(Collectors.toList());
        return entries;
    }
}
