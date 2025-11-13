package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SpringDataMedicalHistoryRepository extends JpaRepository<MedicalHistoryEntity, Long> {
    List<MedicalHistoryEntity> findAllByPatientDocumentOrderByDateTimeAsc(String patientDocument);
    Optional<MedicalHistoryEntity> findByPatientDocumentAndDateTime(String patientDocument, LocalDateTime dateTime);
}
