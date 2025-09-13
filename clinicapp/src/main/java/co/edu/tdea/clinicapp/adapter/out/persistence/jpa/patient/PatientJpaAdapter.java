package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.patient;

import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PatientJpaAdapter implements PatientRepository {

    private final SpringDataPatientRepository repository;
    private final PatientMapper mapper;

    public PatientJpaAdapter(SpringDataPatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return repository.existsByDocument(idNumber);
    }

    @Override
    public Optional<Patient> findByIdNumber(String idNumber) {
        return repository.findByDocument(idNumber).map(mapper::toDomain);
    }

    @Override
    public Patient save(Patient patient) {
        var existing = repository.findByDocument(patient.getIdNumber()).orElse(null);
        var entity = mapper.toEntity(patient);
        if (existing != null) entity.setId(existing.getId()); // upsert
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }
}