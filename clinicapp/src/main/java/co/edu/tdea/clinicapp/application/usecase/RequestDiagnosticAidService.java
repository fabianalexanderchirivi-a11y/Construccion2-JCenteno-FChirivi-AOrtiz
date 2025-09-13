package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.DiagnosticAidItemInput;
import co.edu.tdea.clinicapp.application.port.in.RequestDiagnosticAidCommand;
import co.edu.tdea.clinicapp.application.port.in.RequestDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAidOrderItem;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class RequestDiagnosticAidService implements RequestDiagnosticAidUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;

    public RequestDiagnosticAidService(OrderRepository orderRepository,
                                       PatientRepository patientRepository,
                                       UserRepository userRepository,
                                       DiagnosticAidRepository diagnosticAidRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public Order request(RequestDiagnosticAidCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));
        if (doctor.getRole() != Role.DOCTOR)
            throw new IllegalArgumentException("El usuario no es un doctor.");

        Order order = new Order(cmd.getPatientIdNumber(), cmd.getDoctorIdNumber(), OrderType.DIAGNOSTIC_AID);

        if (cmd.getItems() == null || cmd.getItems().isEmpty())
            throw new IllegalArgumentException("Se requiere al menos un ítem de ayuda diagnóstica.");

        for (DiagnosticAidItemInput i : cmd.getItems()) {
            if (!diagnosticAidRepository.existsById(i.getAidId()))
                throw new IllegalArgumentException("La siguiente ayuda diagnóstica no fue encontrada: " + i.getAidId());
            var item = new DiagnosticAidOrderItem(i.getAidId(), i.getQuantity());
            order.addItem(item);
        }

        return orderRepository.save(order);
    }
}
