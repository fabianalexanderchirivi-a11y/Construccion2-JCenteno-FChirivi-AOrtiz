package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.diagnostic;

import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticAidMapper {

    public DiagnosticAidEntity toEntity(DiagnosticAid d) {
        if (d == null) return null;
        DiagnosticAidEntity e = new DiagnosticAidEntity();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setUnitCost(d.getUnitCost());
        return e;
    }

    public DiagnosticAid toDomain(DiagnosticAidEntity e) {
        if (e == null) return null;
        return new DiagnosticAid(e.getId(), e.getName(), e.getUnitCost());
    }
}
