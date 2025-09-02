package co.edu.tdea.clinicapp.domain.service;

import co.edu.tdea.clinicapp.domain.model.MedicalRecordEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    MedicalRecordEntry createForPatient(String patientId);
    MedicalRecordEntry addEntry(String patientId, MedicalRecordEntry entry);
    Optional<MedicalRecordEntry> findByPatientId(String patientId);
    List<MedicalRecordEntry> findByDateRange(LocalDate from, LocalDate to);
}
