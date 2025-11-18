package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import java.util.List;

public interface ListVitalSignsUseCase {
    List<VitalSignsRecord> listByPatient(String patientIdNumber);
}
