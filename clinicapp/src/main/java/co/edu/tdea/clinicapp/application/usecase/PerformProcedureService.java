package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.PerformProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.PerformProcedureUseCase;
import co.edu.tdea.clinicapp.domain.model.NursingActionType;
import co.edu.tdea.clinicapp.domain.model.NursingRecord;
import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.repository.NursingRecordRepository;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class PerformProcedureService implements PerformProcedureUseCase {

    private final OrderRepository orderRepository;
    private final NursingRecordRepository nursingRecordRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PerformProcedureService(OrderRepository orderRepository,
                                   NursingRecordRepository nursingRecordRepository,
                                   PatientRepository patientRepository,
                                   UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.nursingRecordRepository = nursingRecordRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NursingRecord perform(PerformProcedureCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");
        if (cmd.getQuantity() < 1) throw new IllegalArgumentException("La cantidad debe ser >= 1");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var nurse = userRepository.findByIdNumber(cmd.getNurseIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Enfermera no encontrada."));
        if (nurse.getRole() != Role.NURSE)
            throw new IllegalArgumentException("El usuario no es una enfermera.");

        var orders = orderRepository.findOpenByPatientAndType(cmd.getPatientIdNumber(), OrderType.PROCEDURE);
        Order target = orders.stream()
                .sorted(java.util.Comparator.comparing(Order::getCreatedAt).reversed())
                .filter(o -> o.getItems().stream().anyMatch(i -> i.getCatalogId().equals(cmd.getProcedureId())))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ninguna orden de procedimiento actual contiene: " + cmd.getProcedureId()));

        var item = target.getItems().stream()
                .filter(i -> i.getCatalogId().equals(cmd.getProcedureId()))
                .findFirst()
                .orElseThrow();

        var rec = new NursingRecord(
                NursingActionType.PROCEDURE_PERFORM,
                cmd.getPerformedAt(),
                cmd.getPatientIdNumber(),
                cmd.getNurseIdNumber(),
                OrderType.PROCEDURE,
                target.getCreatedAt(),
                item.getItemNumber(),
                item.getCatalogId(),
                cmd.getQuantity()
        );
        nursingRecordRepository.add(rec);
        return rec;
    }
}
