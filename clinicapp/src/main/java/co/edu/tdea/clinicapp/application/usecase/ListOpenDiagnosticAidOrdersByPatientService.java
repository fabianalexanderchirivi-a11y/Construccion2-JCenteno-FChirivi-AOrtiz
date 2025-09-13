package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;
import co.edu.tdea.clinicapp.application.port.in.ListOpenDiagnosticAidOrdersByPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;

public class ListOpenDiagnosticAidOrdersByPatientService implements ListOpenDiagnosticAidOrdersByPatientUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;

    public ListOpenDiagnosticAidOrdersByPatientService(OrderRepository orderRepository,
                                                       PatientRepository patientRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Order> list(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es necesario.");
        if (!patientRepository.existsByIdNumber(patientIdNumber))
            throw new IllegalArgumentException("Paciente no encontrado.");

        return orderRepository.findOpenByPatientAndType(patientIdNumber, OrderType.DIAGNOSTIC_AID);
    }
}
