package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListProceduresUseCase;
import co.edu.tdea.clinicapp.domain.model.Procedure;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ListProceduresService implements ListProceduresUseCase {

    private final ProcedureRepository procedureRepository;

    public ListProceduresService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public List<Procedure> list() {
        return procedureRepository.findAll();
    }
}
