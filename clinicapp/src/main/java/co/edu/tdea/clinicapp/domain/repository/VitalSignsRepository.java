package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;

public interface VitalSignsRepository {
    void add(String patientIdNumber, VitalSignsRecord record);
    List<VitalSignsRecord> findAllByPatient(String patientIdNumber);
}
