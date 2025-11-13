package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataPatientRepository extends JpaRepository<PatientEntity, Integer> {
    boolean existsByDocument(String document);
    Optional<PatientEntity> findByDocument(String document);
}
