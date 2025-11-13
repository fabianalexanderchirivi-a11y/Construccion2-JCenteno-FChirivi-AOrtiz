package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.medication;

import co.edu.tdea.clinicapp.domain.model.Medication;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicationJpaAdapter implements MedicationRepository {

    private final SpringDataMedicationRepository jpa;
    private final MedicationMapper mapper;

    public MedicationJpaAdapter(SpringDataMedicationRepository jpa,
                                MedicationMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    // === variantes de guardado ===
    public Medication save(Medication med) {
        var saved = jpa.save(mapper.toEntity(med));
        return mapper.toDomain(saved);
    }

    public Medication upsert(Medication med) {
        var saved = jpa.save(mapper.toEntity(med));
        return mapper.toDomain(saved);
    }

    // === consultas ===
    public List<Medication> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public List<Medication> list() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    public Optional<Medication> findById(String id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    public boolean existsById(String id) {
        return jpa.existsById(id);
    }

    // === eliminación ===
    public void deleteById(String id) {
        jpa.deleteById(id);
    }
}
