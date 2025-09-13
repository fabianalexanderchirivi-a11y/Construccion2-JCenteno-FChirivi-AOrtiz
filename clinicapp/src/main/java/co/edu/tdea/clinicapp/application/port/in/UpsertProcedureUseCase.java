package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Procedure;

public interface UpsertProcedureUseCase {
    Procedure upsert(UpsertProcedureCommand command);
}
