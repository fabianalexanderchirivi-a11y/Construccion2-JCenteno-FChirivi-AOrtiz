package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Medication;

public interface MedicationRepository {
    boolean existsById(String id);
    Optional<Medication> findById(String id);
    List<Medication> findAll();
    Medication save(Medication medication);
    void deleteById(String id);
}
