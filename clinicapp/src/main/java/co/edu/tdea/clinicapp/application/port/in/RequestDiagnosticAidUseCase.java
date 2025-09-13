package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Order;

public interface RequestDiagnosticAidUseCase {
    Order request(RequestDiagnosticAidCommand command);
}
