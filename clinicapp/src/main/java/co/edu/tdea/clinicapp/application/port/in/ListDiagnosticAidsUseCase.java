package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;

public interface ListDiagnosticAidsUseCase {
    List<DiagnosticAid> list();
}
