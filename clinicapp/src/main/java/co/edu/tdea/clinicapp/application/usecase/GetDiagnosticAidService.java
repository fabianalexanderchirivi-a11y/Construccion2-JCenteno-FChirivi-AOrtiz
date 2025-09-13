package co.edu.tdea.clinicapp.application.usecase;

import java.util.Optional;

import co.edu.tdea.clinicapp.application.port.in.GetDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;

public class GetDiagnosticAidService implements GetDiagnosticAidUseCase {

    private final DiagnosticAidRepository diagnosticAidRepository;

    public GetDiagnosticAidService(DiagnosticAidRepository diagnosticAidRepository) {
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public Optional<DiagnosticAid> byId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Una ID es necesaria.");
        return diagnosticAidRepository.findById(id);
    }
}
