package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.Order;

public interface ListOpenMedicationOrdersByPatientUseCase {
    List<Order> list(String patientIdNumber);
}
