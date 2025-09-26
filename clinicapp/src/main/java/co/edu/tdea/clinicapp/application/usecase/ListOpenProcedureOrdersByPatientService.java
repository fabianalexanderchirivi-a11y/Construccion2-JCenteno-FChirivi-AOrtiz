package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;
import co.edu.tdea.clinicapp.application.port.in.ListOpenProcedureOrdersByPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ListOpenProcedureOrdersByPatientService implements ListOpenProcedureOrdersByPatientUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;

    public ListOpenProcedureOrdersByPatientService(OrderRepository orderRepository,
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

        return orderRepository.findOpenByPatientAndType(patientIdNumber, OrderType.PROCEDURE);
    }
}
