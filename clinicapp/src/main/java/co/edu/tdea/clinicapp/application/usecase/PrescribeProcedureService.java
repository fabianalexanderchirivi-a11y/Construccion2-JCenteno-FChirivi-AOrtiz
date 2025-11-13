package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.PrescribeProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.PrescribeProcedureUseCase;
import co.edu.tdea.clinicapp.application.port.in.ProcedureItemInput;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.model.ProcedureOrderItem;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class PrescribeProcedureService implements PrescribeProcedureUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;

    public PrescribeProcedureService(OrderRepository orderRepository,
                                     PatientRepository patientRepository,
                                     UserRepository userRepository,
                                     ProcedureRepository procedureRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.procedureRepository = procedureRepository;
    }

    @Override
    public Order prescribe(PrescribeProcedureCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));
        if (doctor.getRole() != Role.DOCTOR)
            throw new IllegalArgumentException("El usuario no es un doctor.");

        Order order = new Order(cmd.getPatientIdNumber(), cmd.getDoctorIdNumber(), OrderType.PROCEDURE);

        if (cmd.getItems() == null || cmd.getItems().isEmpty())
            throw new IllegalArgumentException("Almenos un item de procedimiento es necesario.");

        for (ProcedureItemInput i : cmd.getItems()) {
            if (!procedureRepository.existsById(i.getProcedureId()))
                throw new IllegalArgumentException("El siguiente procedimiento no fue encontrado: " + i.getProcedureId());

            var item = new ProcedureOrderItem(
                    i.getProcedureId(),
                    i.getQuantity(),
                    i.getFrequency(),
                    i.getSpecialist()
            );
            order.addItem(item);
        }

        return orderRepository.save(order);
    }
}
