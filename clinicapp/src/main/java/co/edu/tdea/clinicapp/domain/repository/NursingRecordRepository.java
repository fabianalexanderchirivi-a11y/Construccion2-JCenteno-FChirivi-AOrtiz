package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.NursingRecord;

public interface NursingRecordRepository {
    void add(NursingRecord record);
    List<NursingRecord> findAllByPatient(String patientIdNumber);
}
