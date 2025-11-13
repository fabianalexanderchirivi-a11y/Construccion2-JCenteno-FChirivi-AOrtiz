package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;

public interface DiagnosticAidRepository {
    boolean existsById(String id);
    Optional<DiagnosticAid> findById(String id);
    List<DiagnosticAid> findAll();
    DiagnosticAid save(DiagnosticAid aid);
    void deleteById(String id);
}
