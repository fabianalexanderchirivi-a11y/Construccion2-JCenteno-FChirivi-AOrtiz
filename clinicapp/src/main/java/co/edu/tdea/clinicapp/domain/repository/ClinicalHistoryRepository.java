package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;

public interface ClinicalHistoryRepository {
    void addEntry(String patientIdNumber, ClinicalHistoryEntry entry);
    List<ClinicalHistoryEntry> findAllByPatient(String patientIdNumber);
}
