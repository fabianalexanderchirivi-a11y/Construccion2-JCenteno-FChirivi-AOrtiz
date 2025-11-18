package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinicalhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataClinicalHistoryRepository extends JpaRepository<ClinicalHistoryEntryEntity, Long> {
    List<ClinicalHistoryEntryEntity> findByPatientIdNumberOrderByAttendedAtAsc(String patientIdNumber);
}
