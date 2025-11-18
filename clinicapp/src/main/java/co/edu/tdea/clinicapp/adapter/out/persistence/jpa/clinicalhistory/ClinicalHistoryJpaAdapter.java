package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinicalhistory;

import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.ClinicalHistoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component("clinicalHistoryJpaAdapterV2")
public class ClinicalHistoryJpaAdapter implements ClinicalHistoryRepository {

    private final SpringDataClinicalHistoryRepository repository;
    private final ClinicalHistoryMapper mapper;

    public ClinicalHistoryJpaAdapter(SpringDataClinicalHistoryRepository repository, ClinicalHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void addEntry(String patientIdNumber, ClinicalHistoryEntry entry) {
        repository.save(mapper.toEntity(patientIdNumber, entry));
    }

    @Override
    public List<ClinicalHistoryEntry> findAllByPatient(String patientIdNumber) {
        return repository.findByPatientIdNumberOrderByAttendedAtAsc(patientIdNumber)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
