package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinical;

import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.ClinicalHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClinicalHistoryJpaAdapter implements ClinicalHistoryRepository {

    private final SpringDataClinicalHistoryRepository repo;
    private final ClinicalHistoryMapper mapper = new ClinicalHistoryMapper();

    public ClinicalHistoryJpaAdapter(SpringDataClinicalHistoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public void addEntry(String patientIdNumber, ClinicalHistoryEntry entry) {
        var entity = mapper.toEntity(patientIdNumber, entry);
        repo.save(entity);
    }

    @Override
    public List<ClinicalHistoryEntry> findAllByPatient(String patientIdNumber) {
        var rows = repo.findAllByPatientIdNumberOrderByAttendedAtAsc(patientIdNumber);
        return rows.stream().map(mapper::toDomain).toList();
    }
}
