package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.MedicationItemInput;
import co.edu.tdea.clinicapp.application.port.in.PrescribeMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.PrescribeMedicationUseCase;
import co.edu.tdea.clinicapp.domain.model.MedicationOrderItem;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class PrescribeMedicationService implements PrescribeMedicationUseCase {

    private final OrderRepository orderRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final MedicationRepository medicationRepository;

    public PrescribeMedicationService(OrderRepository orderRepository,
                                      PatientRepository patientRepository,
                                      UserRepository userRepository,
                                      MedicationRepository medicationRepository) {
        this.orderRepository = orderRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Order prescribe(PrescribeMedicationCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));
        if (doctor.getRole() != Role.DOCTOR)
            throw new IllegalArgumentException("El usuario no es un doctor.");

        Order order = new Order(cmd.getPatientIdNumber(), cmd.getDoctorIdNumber(), OrderType.MEDICATION);

        if (cmd.getItems() == null || cmd.getItems().isEmpty())
            throw new IllegalArgumentException("Almenos 1 item de medicacion es necsario.");

        for (MedicationItemInput i : cmd.getItems()) {
            if (!medicationRepository.existsById(i.getMedicationId()))
                throw new IllegalArgumentException("El siguiente medicamento no fue encontrado: " + i.getMedicationId());

            var item = new MedicationOrderItem(
                    i.getMedicationId(),
                    i.getQuantity(),
                    i.getDose(),
                    i.getFrequency(),
                    i.getDurationDays()
            );
            order.addItem(item);
        }

        return orderRepository.save(order);
    }
}
