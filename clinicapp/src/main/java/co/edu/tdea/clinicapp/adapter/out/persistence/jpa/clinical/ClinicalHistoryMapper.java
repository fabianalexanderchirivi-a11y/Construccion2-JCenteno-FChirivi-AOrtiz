package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinical;

import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;

public final class ClinicalHistoryMapper {

    public ClinicalHistoryEntryEntity toEntity(String patientIdNumber, ClinicalHistoryEntry entry) {
        if (entry == null) return null;
        ClinicalHistoryEntryEntity e = new ClinicalHistoryEntryEntity();
        e.setPatientIdNumber(patientIdNumber);
        e.setAttendedAt(entry.getAttendedAt());
        e.setDoctorIdNumber(entry.getDoctorIdNumber());
        e.setReason(entry.getReason());
        e.setSymptoms(entry.getSymptoms());
        e.setDiagnosis(entry.getDiagnosis());
        e.setDiagnosticAidOrderNumber(entry.getDiagnosticAidOrderNumber());
        e.setDiagnosticAidItemNumber(entry.getDiagnosticAidItemNumber());
        e.setDiagnosticAidResult(entry.getDiagnosticAidResult());
        return e;
    }

    public ClinicalHistoryEntry toDomain(ClinicalHistoryEntryEntity e) {
        if (e == null) return null;
        return new ClinicalHistoryEntry(
                e.getAttendedAt(),
                e.getDoctorIdNumber(),
                e.getReason(),
                e.getSymptoms(),
                e.getDiagnosis(),
                e.getDiagnosticAidOrderNumber(),
                e.getDiagnosticAidItemNumber(),
                e.getDiagnosticAidResult()
        );
    }
}
