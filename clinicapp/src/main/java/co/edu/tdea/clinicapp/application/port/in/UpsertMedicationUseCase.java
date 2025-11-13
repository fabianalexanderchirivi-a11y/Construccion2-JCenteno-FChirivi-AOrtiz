package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Medication;

public interface UpsertMedicationUseCase {
    Medication upsert(UpsertMedicationCommand command);
}
