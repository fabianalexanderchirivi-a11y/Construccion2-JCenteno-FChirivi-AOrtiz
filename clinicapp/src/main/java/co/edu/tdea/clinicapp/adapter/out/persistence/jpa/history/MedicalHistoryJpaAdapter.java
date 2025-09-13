package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.history;

import co.edu.tdea.clinicapp.domain.model.MedicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.MedicalHistoryRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MedicalHistoryJpaAdapter implements MedicalHistoryRepositoryPort {

    private final SpringDataMedicalHistoryRepository repository;
    private final MedicalHistoryMapper mapper;

    public MedicalHistoryJpaAdapter(SpringDataMedicalHistoryRepository repository, MedicalHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void append(MedicalHistoryEntry entry) {
        repository.save(mapper.toEntity(entry));
    }

    @Override
    public List<MedicalHistoryEntry> findByPatientId(String patientIdNumber) {
        return repository.findAllByPatientDocumentOrderByDateTimeAsc(patientIdNumber)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<MedicalHistoryEntry> findByPatientIdAndDate(String patientIdNumber, LocalDateTime dateTime) {
        return repository.findByPatientDocumentAndDateTime(patientIdNumber, dateTime)
                .map(mapper::toDomain);
    }
}