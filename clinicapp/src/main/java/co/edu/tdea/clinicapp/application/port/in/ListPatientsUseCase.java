package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.Patient;

public interface ListPatientsUseCase {
    List<Patient> list();
}
