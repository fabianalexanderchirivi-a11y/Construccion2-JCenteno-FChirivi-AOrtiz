package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.NursingRecord;

public interface AdministerMedicationUseCase {
    NursingRecord administer(AdministerMedicationCommand command);
}
