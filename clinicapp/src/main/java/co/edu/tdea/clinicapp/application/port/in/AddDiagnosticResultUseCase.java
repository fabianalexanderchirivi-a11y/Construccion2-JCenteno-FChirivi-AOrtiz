package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.command.AddDiagnosticResultCommand;

public interface AddDiagnosticResultUseCase {
    void add(AddDiagnosticResultCommand cmd);
}
