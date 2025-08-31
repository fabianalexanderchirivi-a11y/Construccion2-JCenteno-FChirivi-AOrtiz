package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.MedicalOrder;
import co.edu.tdea.clinicapp.domain.model.MedicationOrder;
import co.edu.tdea.clinicapp.domain.model.ProcedureOrder;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAidOrder;
import java.util.List;
import java.util.Optional;

public interface MedicalOrderUseCase {

    void createOrder(MedicalOrder order);

    void addMedicationItem(String orderNumber, MedicationOrder item);

    void addProcedureItem(String orderNumber, ProcedureOrder item);

    void addDiagnosticAidItem(String orderNumber, DiagnosticAidOrder item);

    Optional<MedicalOrder> getOrder(String orderNumber);

    List<MedicalOrder> getOrdersByPatient(String patientIdNumber);
}
