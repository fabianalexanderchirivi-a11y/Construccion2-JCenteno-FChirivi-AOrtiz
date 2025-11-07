package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Procedure;

public interface ProcedureRepository {
    boolean existsById(String id);
    Optional<Procedure> findById(String id);
    List<Procedure> findAll();
    Procedure save(Procedure procedure);
    void deleteById(String id);
}
