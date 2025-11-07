package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.procedure;

import co.edu.tdea.clinicapp.domain.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureMapper {

    public ProcedureEntity toEntity(Procedure d) {
        if (d == null) return null;
        ProcedureEntity e = new ProcedureEntity();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setUnitCost(d.getUnitCost());
        return e;
    }

    public Procedure toDomain(ProcedureEntity e) {
        if (e == null) return null;
        return new Procedure(e.getId(), e.getName(), e.getUnitCost());
    }
}
