package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;

public interface UpsertDiagnosticAidUseCase {
    DiagnosticAid upsert(UpsertDiagnosticAidCommand command);
}
