package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataClinicalHistoryRepository extends JpaRepository<ClinicalHistoryEntryEntity, Long> {

    List<ClinicalHistoryEntryEntity>
    findAllByPatientIdNumberOrderByAttendedAtAsc(String patientIdNumber);
}
