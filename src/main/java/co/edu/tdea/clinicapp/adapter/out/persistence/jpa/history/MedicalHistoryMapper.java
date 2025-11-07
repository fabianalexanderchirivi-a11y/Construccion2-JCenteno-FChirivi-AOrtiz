package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.history;

import co.edu.tdea.clinicapp.domain.model.MedicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.model.VitalSigns;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public MedicalHistoryEntity toEntity(MedicalHistoryEntry d) {
        if (d == null) return null;

        var e = new MedicalHistoryEntity();
        e.setPatientDocument(d.getPatientId());
        e.setDateTime(d.getDate());
        e.setDoctorDocument(d.getDoctorId());
        e.setReason(d.getVisitReason());    
        e.setSymptoms(d.getSymptoms());
        e.setDiagnosis(d.getDiagnosis());

        try {
            e.setPayload(d.getVitalSigns() != null ? MAPPER.writeValueAsString(d.getVitalSigns()) : null);
        } catch (Exception ex) {
            throw new IllegalStateException("Error serializando VitalSigns a JSON", ex);
        }
        return e;
    }

    public MedicalHistoryEntry toDomain(MedicalHistoryEntity e) {
        if (e == null) return null;

        VitalSigns vs = null;
        String payload = e.getPayload();
        if (payload != null && !payload.isBlank()) {
            try {
                vs = MAPPER.readValue(payload, VitalSigns.class);
            } catch (Exception ex) {
                vs = null;
            }
        }

        return new MedicalHistoryEntry(
                e.getPatientDocument(),
                e.getDateTime(),
                e.getDoctorDocument(),
                e.getReason(),
                e.getSymptoms(),
                e.getDiagnosis(),
                vs
        );
    }
}
