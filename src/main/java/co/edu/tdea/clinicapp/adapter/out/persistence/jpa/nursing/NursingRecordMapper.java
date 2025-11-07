package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.nursing;

import co.edu.tdea.clinicapp.domain.model.NursingRecord;
import org.springframework.stereotype.Component;

@Component
public class NursingRecordMapper {

    public NursingRecordEntity toEntity(NursingRecord d) {
        if (d == null) return null;
        NursingRecordEntity e = new NursingRecordEntity();
        e.setType(d.getType());
        e.setPerformedAt(d.getPerformedAt());
        e.setPatientIdNumber(d.getPatientIdNumber());
        e.setNurseIdNumber(d.getNurseIdNumber());
        e.setOrderType(d.getOrderType());
        e.setOrderCreatedAt(d.getOrderCreatedAt());
        e.setItemNumber(d.getItemNumber());
        e.setCatalogId(d.getCatalogId());
        e.setQuantity(d.getQuantity());
        return e;
    }

    public NursingRecord toDomain(NursingRecordEntity e) {
        if (e == null) return null;
        return new NursingRecord(
                e.getType(),
                e.getPerformedAt(),
                e.getPatientIdNumber(),
                e.getNurseIdNumber(),
                e.getOrderType(),
                e.getOrderCreatedAt(),
                e.getItemNumber(),
                e.getCatalogId(),
                e.getQuantity()
        );
    }
}
