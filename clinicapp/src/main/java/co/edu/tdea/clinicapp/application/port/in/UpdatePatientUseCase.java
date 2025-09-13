package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Patient;

public interface UpdatePatientUseCase {
    Patient update(UpdatePatientCommand command);
}
