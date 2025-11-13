package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;

public interface GetClinicalHistoryByPatientUseCase {
    List<ClinicalHistoryEntry> list(String patientIdNumber);
}
