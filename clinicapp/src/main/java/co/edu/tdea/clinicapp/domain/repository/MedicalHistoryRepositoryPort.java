package co.edu.tdea.clinicapp.domain.repository;

import co.edu.tdea.clinicapp.domain.model.MedicalHistoryEntry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepositoryPort {
    void append(MedicalHistoryEntry entry);
    List<MedicalHistoryEntry> findByPatientId(String patientId);
    Optional<MedicalHistoryEntry> findByPatientIdAndDate(String patientId, LocalDateTime dateTime);
}
