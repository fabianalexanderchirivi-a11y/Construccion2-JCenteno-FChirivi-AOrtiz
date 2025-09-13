package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.DeleteDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;

public class DeleteDiagnosticAidService implements DeleteDiagnosticAidUseCase {

    private final DiagnosticAidRepository diagnosticAidRepository;

    public DeleteDiagnosticAidService(DiagnosticAidRepository diagnosticAidRepository) {
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id requerida");
        if (!diagnosticAidRepository.existsById(id)) throw new IllegalArgumentException("Ayuda diagnóstica no encontrada.");
        diagnosticAidRepository.deleteById(id);
    }
}
