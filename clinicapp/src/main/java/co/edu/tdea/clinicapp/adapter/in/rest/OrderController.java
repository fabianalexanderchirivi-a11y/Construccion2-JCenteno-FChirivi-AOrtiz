package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.CreateOrderUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListOrdersByPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.MedicationItemInput;
import co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto;
import co.edu.tdea.clinicapp.application.port.in.PrescribeMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.PrescribeMedicationUseCase;
import co.edu.tdea.clinicapp.application.port.in.PrescribeProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.PrescribeProcedureUseCase;
import co.edu.tdea.clinicapp.application.port.in.ProcedureItemInput;
import co.edu.tdea.clinicapp.application.port.in.RequestDiagnosticAidCommand;
import co.edu.tdea.clinicapp.application.port.in.RequestDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.domain.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@PreAuthorize("hasRole('DOCTOR')")
public class OrderController {

    private final PrescribeMedicationUseCase prescribeMedicationUseCase;
    private final PrescribeProcedureUseCase prescribeProcedureUseCase;
    private final RequestDiagnosticAidUseCase requestDiagnosticAidUseCase;
    private final ListOrdersByPatientUseCase listOrdersByPatientUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(PrescribeMedicationUseCase prescribeMedicationUseCase,
                           PrescribeProcedureUseCase prescribeProcedureUseCase,
                           RequestDiagnosticAidUseCase requestDiagnosticAidUseCase,
                           ListOrdersByPatientUseCase listOrdersByPatientUseCase,
                           CreateOrderUseCase createOrderUseCase) {
        this.prescribeMedicationUseCase = prescribeMedicationUseCase;
        this.prescribeProcedureUseCase = prescribeProcedureUseCase;
        this.requestDiagnosticAidUseCase = requestDiagnosticAidUseCase;
        this.listOrdersByPatientUseCase = listOrdersByPatientUseCase;
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping("/medications")
    public ResponseEntity<Order> prescribeMedications(@RequestBody MedicationOrderRequest request) {
        Order order = prescribeMedicationUseCase.prescribe(new PrescribeMedicationCommand(
                request.patientIdNumber(),
                request.doctorIdNumber(),
                request.items().stream()
                        .map(i -> new MedicationItemInput(i.medicationId(), i.quantity(), i.dose(), i.frequency(), i.durationDays()))
                        .toList()
        ));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/procedures")
    public ResponseEntity<Order> prescribeProcedures(@RequestBody ProcedureOrderRequest request) {
        Order order = prescribeProcedureUseCase.prescribe(new PrescribeProcedureCommand(
                request.patientIdNumber(),
                request.doctorIdNumber(),
                request.items().stream()
                        .map(i -> new ProcedureItemInput(i.procedureId(), i.times(), i.frequency()))
                        .toList()
        ));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/diagnostic-aids")
    public ResponseEntity<Order> requestDiagnostics(@RequestBody DiagnosticAidOrderRequest request) {
        Order order = requestDiagnosticAidUseCase.request(new RequestDiagnosticAidCommand(
                request.patientIdNumber(),
                request.doctorIdNumber(),
                request.items()
        ));
        return ResponseEntity.ok(order);
    }

    @GetMapping("/patient/{patientId}")
    public List<OrderItemSummaryDto> listByPatient(@PathVariable String patientId) {
        return listOrdersByPatientUseCase.list(patientId);
    }

    @PostMapping
    public ResponseEntity<Integer> createBaseOrder(@RequestBody CreateOrderRequest request) {
        int number = createOrderUseCase.create(request.patientIdNumber(), request.doctorIdNumber());
        return ResponseEntity.ok(number);
    }

    public record MedicationOrderRequest(String patientIdNumber, String doctorIdNumber, List<MedicationItemRequest> items) { }

    public record MedicationItemRequest(String medicationId, int quantity, String dose, String frequency, int durationDays) { }

    public record ProcedureOrderRequest(String patientIdNumber, String doctorIdNumber, List<ProcedureItemRequest> items) { }

    public record ProcedureItemRequest(String procedureId, int times, String frequency) { }

    public record DiagnosticAidOrderRequest(String patientIdNumber, String doctorIdNumber, List<String> items) { }

    public record CreateOrderRequest(String patientIdNumber, String doctorIdNumber) { }
}
