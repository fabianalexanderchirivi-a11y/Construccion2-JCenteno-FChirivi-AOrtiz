package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.MedicalRecordEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordUseCase {

    void appendEntry(MedicalRecordEntry entry);

    List<MedicalRecordEntry> getHistoryByPatient(String patientIdNumber);

    Optional<MedicalRecordEntry> getEntryByPatientAndDate(String patientIdNumber, LocalDate date);
}
