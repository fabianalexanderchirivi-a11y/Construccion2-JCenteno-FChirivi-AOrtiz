package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;

public interface RecordDiagnosticResultUseCase {
    ClinicalHistoryEntry record(RecordDiagnosticResultCommand command);
}
