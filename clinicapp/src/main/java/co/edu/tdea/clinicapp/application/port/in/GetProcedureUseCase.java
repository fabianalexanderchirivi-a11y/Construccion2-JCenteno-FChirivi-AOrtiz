package co.edu.tdea.clinicapp.application.port.in;

import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Procedure;

public interface GetProcedureUseCase {
    Optional<Procedure> byId(String id);
}
