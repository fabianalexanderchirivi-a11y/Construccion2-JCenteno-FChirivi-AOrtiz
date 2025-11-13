package co.edu.tdea.clinicapp.application.usecase;

import java.util.Optional;

import co.edu.tdea.clinicapp.application.port.in.GetProcedureUseCase;
import co.edu.tdea.clinicapp.domain.model.Procedure;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class GetProcedureService implements GetProcedureUseCase {

    private final ProcedureRepository procedureRepository;

    public GetProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public Optional<Procedure> byId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Una ID es requerida.");
        return procedureRepository.findById(id);
    }
}
