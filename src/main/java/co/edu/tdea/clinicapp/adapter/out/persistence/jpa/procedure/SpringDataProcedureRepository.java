package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.procedure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataProcedureRepository extends JpaRepository<ProcedureEntity, String> {
}
