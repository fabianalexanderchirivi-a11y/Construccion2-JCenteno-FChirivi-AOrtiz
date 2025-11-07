package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.UpsertDiagnosticAidCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class UpsertDiagnosticAidService implements UpsertDiagnosticAidUseCase {

    private final DiagnosticAidRepository diagnosticAidRepository;

    public UpsertDiagnosticAidService(DiagnosticAidRepository diagnosticAidRepository) {
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public DiagnosticAid upsert(UpsertDiagnosticAidCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");

        if (diagnosticAidRepository.existsById(cmd.getId())) {
            var aid = diagnosticAidRepository.findById(cmd.getId())
                    .orElseThrow(() -> new IllegalStateException("Ayuda diagnóstica no encontrada."));
            aid.setName(cmd.getName());
            aid.setUnitCost(cmd.getUnitCost());
            return diagnosticAidRepository.save(aid);
        }
        var aid = new DiagnosticAid(cmd.getId(), cmd.getName(), cmd.getUnitCost());
        return diagnosticAidRepository.save(aid);
    }
}
