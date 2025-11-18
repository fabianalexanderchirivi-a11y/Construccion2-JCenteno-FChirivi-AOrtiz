package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinicalhistory;

import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import org.springframework.stereotype.Component;

@Component
public class ClinicalHistoryMapper {
    public ClinicalHistoryEntryEntity toEntity(String patientIdNumber, ClinicalHistoryEntry entry) {
        ClinicalHistoryEntryEntity entity = new ClinicalHistoryEntryEntity();
        entity.setPatientIdNumber(patientIdNumber);
        entity.setAttendedAt(entry.getAttendedAt());
        entity.setDoctorIdNumber(entry.getDoctorIdNumber());
        entity.setReason(entry.getReason());
        entity.setSymptoms(entry.getSymptoms());
        entity.setDiagnosis(entry.getDiagnosis());
        entity.setDiagnosticAidOrderNumber(entry.getDiagnosticAidOrderNumber());
        entity.setDiagnosticAidItemNumber(entry.getDiagnosticAidItemNumber());
        entity.setDiagnosticAidResult(entry.getDiagnosticAidResult());
        return entity;
    }

    public ClinicalHistoryEntry toDomain(ClinicalHistoryEntryEntity entity) {
        return new ClinicalHistoryEntry(
                entity.getAttendedAt(),
                entity.getDoctorIdNumber(),
                entity.getReason(),
                entity.getSymptoms(),
                entity.getDiagnosis(),
                entity.getDiagnosticAidOrderNumber(),
                entity.getDiagnosticAidItemNumber(),
                entity.getDiagnosticAidResult()
        );
    }
}
