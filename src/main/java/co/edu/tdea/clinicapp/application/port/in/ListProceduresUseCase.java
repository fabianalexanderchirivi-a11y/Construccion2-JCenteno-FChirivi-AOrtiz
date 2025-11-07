package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.Procedure;

public interface ListProceduresUseCase {
    List<Procedure> list();
}
