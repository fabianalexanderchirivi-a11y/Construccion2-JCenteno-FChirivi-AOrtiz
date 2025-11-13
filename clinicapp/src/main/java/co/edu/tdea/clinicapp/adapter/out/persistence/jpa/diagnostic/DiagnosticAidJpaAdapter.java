package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.diagnostic;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DiagnosticAidJpaAdapter implements DiagnosticAidRepository {

    private final SpringDataDiagnosticAidRepository jpa;
    private final DiagnosticAidMapper mapper;

    public DiagnosticAidJpaAdapter(SpringDataDiagnosticAidRepository jpa,
                                   DiagnosticAidMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    // guarda/actualiza
    public DiagnosticAid save(DiagnosticAid aid) {
        var saved = jpa.save(mapper.toEntity(aid));
        return mapper.toDomain(saved);
    }

    public DiagnosticAid upsert(DiagnosticAid aid) {
        var saved = jpa.save(mapper.toEntity(aid));
        return mapper.toDomain(saved);
    }

    // consultas
    public List<DiagnosticAid> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public List<DiagnosticAid> list() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public Optional<DiagnosticAid> findById(String id) {
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
