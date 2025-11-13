package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Medication;
import java.util.List;

public interface ListMedicationsUseCase {
    List<Medication> list();
}
