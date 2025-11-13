package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;

public interface RecordVitalSignsUseCase {
    VitalSignsRecord record(RecordVitalSignsCommand command);
}
