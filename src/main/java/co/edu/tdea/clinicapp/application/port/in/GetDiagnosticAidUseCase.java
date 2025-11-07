package co.edu.tdea.clinicapp.application.port.in;

import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;

public interface GetDiagnosticAidUseCase {
    Optional<DiagnosticAid> byId(String id);
}
