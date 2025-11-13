package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;
import java.util.List;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;

public interface GetClinicalHistoryByPatientBetweenDatesUseCase {
    List<ClinicalHistoryEntry> list(String patientIdNumber, LocalDateTime fromInclusive, LocalDateTime toExclusive);
}
