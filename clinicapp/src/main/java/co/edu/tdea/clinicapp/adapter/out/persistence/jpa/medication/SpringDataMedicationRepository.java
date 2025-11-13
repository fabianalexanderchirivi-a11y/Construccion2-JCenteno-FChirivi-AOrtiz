package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMedicationRepository extends JpaRepository<MedicationEntity, String> {
}
