package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.UpsertProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertProcedureUseCase;
import co.edu.tdea.clinicapp.domain.model.Procedure;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;

public class UpsertProcedureService implements UpsertProcedureUseCase {

    private final ProcedureRepository procedureRepository;

    public UpsertProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public Procedure upsert(UpsertProcedureCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");

        if (procedureRepository.existsById(cmd.getId())) {
            var p = procedureRepository.findById(cmd.getId())
                    .orElseThrow(() -> new IllegalStateException("Procedimiento no encontrado."));
            p.setName(cmd.getName());
            p.setUnitCost(cmd.getUnitCost());
            return procedureRepository.save(p);
        }
        var p = new Procedure(cmd.getId(), cmd.getName(), cmd.getUnitCost());
        return procedureRepository.save(p);
    }
}
