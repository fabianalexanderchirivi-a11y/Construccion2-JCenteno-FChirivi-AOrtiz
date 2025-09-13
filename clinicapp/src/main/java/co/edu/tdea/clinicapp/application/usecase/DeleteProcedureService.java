package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.DeleteProcedureUseCase;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;

public class DeleteProcedureService implements DeleteProcedureUseCase {

    private final ProcedureRepository procedureRepository;

    public DeleteProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id requerida");
        if (!procedureRepository.existsById(id)) throw new IllegalArgumentException("Procedimiento no encontrado.");
        procedureRepository.deleteById(id);
    }
}
