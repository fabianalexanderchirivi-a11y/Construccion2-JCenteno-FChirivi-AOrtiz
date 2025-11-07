package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.procedure;

import co.edu.tdea.clinicapp.domain.model.Procedure;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProcedureJpaAdapter implements ProcedureRepository {

    private final SpringDataProcedureRepository jpa;
    private final ProcedureMapper mapper;

    public ProcedureJpaAdapter(SpringDataProcedureRepository jpa,
                               ProcedureMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    // guarda/actualiza
    public Procedure save(Procedure proc) {
        var saved = jpa.save(mapper.toEntity(proc));
        return mapper.toDomain(saved);
    }

    public Procedure upsert(Procedure proc) {
        var saved = jpa.save(mapper.toEntity(proc));
        return mapper.toDomain(saved);
    }

    // consultas
    public List<Procedure> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public List<Procedure> list() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public Optional<Procedure> findById(String id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    public boolean existsById(String id) {
        return jpa.existsById(id);
    }

    // eliminación
    public void deleteById(String id) {
        jpa.deleteById(id);
    }
}
