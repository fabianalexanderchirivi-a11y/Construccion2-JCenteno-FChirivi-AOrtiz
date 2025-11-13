package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.vitals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataVitalSignsRepository extends JpaRepository<VitalSignsEntity, Long> {
    List<VitalSignsEntity> findAllByPatientIdNumberOrderByRecordedAtAsc(String patientIdNumber);
}
