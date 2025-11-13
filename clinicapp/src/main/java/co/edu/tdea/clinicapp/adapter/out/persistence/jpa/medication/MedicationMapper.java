package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.medication;

import co.edu.tdea.clinicapp.domain.model.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {

    public MedicationEntity toEntity(Medication d) {
        if (d == null) return null;
        MedicationEntity e = new MedicationEntity();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setUnitCost(d.getUnitCost());
        return e;
    }

    public Medication toDomain(MedicationEntity e) {
        if (e == null) return null;
        return new Medication(e.getId(), e.getName(), e.getUnitCost());
    }
}
