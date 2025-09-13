package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListDiagnosticAidsUseCase;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;

public class ListDiagnosticAidsService implements ListDiagnosticAidsUseCase {

    private final DiagnosticAidRepository diagnosticAidRepository;

    public ListDiagnosticAidsService(DiagnosticAidRepository diagnosticAidRepository) {
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public List<DiagnosticAid> list() {
        return diagnosticAidRepository.findAll();
    }
}
