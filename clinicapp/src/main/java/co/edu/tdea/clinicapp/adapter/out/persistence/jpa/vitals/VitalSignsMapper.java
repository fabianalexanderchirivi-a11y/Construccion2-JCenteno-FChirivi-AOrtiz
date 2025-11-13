package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.vitals;

import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import org.springframework.stereotype.Component;

@Component
public class VitalSignsMapper {

    public VitalSignsEntity toEntity(String patientIdNumber, VitalSignsRecord r) {
        if (patientIdNumber == null || patientIdNumber.isBlank()) {
            throw new IllegalArgumentException("patientIdNumber es requerido");
        }
        if (r == null) throw new IllegalArgumentException("record es requerido");

        VitalSignsEntity e = new VitalSignsEntity();
        e.setPatientIdNumber(patientIdNumber.trim());
        e.setNurseIdNumber(r.getNurseIdNumber());
        e.setRecordedAt(r.getRecordedAt());
        e.setSystolic(r.getSystolic());
        e.setDiastolic(r.getDiastolic());
        e.setTemperature(r.getTemperature());
        e.setPulse(r.getPulse());
        e.setOxygen(r.getOxygen());
        return e;
    }

    public VitalSignsRecord toDomain(VitalSignsEntity e) {
        if (e == null) throw new IllegalArgumentException("entity es requerida");

        return new VitalSignsRecord(
                e.getRecordedAt(),
                e.getNurseIdNumber(),
                e.getSystolic(),
                e.getDiastolic(),
                e.getTemperature(),
                e.getPulse(),
                e.getOxygen()
        );
    }
}
