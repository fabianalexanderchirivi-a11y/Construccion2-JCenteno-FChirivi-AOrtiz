package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.diagnostic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataDiagnosticAidRepository extends JpaRepository<DiagnosticAidEntity, String> {
}
