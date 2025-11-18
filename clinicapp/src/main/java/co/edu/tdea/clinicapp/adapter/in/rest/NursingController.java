package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.AdministerMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.AdministerMedicationUseCase;
import co.edu.tdea.clinicapp.application.port.in.PerformProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.PerformProcedureUseCase;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsUseCase;
import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/nursing")
@PreAuthorize("hasRole('NURSE')")
public class NursingController {

    private final RecordVitalSignsUseCase recordVitalSignsUseCase;
    private final AdministerMedicationUseCase administerMedicationUseCase;
    private final PerformProcedureUseCase performProcedureUseCase;

    public NursingController(RecordVitalSignsUseCase recordVitalSignsUseCase,
                             AdministerMedicationUseCase administerMedicationUseCase,
                             PerformProcedureUseCase performProcedureUseCase) {
        this.recordVitalSignsUseCase = recordVitalSignsUseCase;
        this.administerMedicationUseCase = administerMedicationUseCase;
        this.performProcedureUseCase = performProcedureUseCase;
    }

    @PostMapping("/vitals")
    public ResponseEntity<VitalSignsRecord> recordVitals(@RequestBody VitalSignsRequest request) {
        VitalSignsRecord record = recordVitalSignsUseCase.record(new RecordVitalSignsCommand(
                request.patientIdNumber(),
                request.nurseIdNumber(),
                request.temperature(),
                request.heartRate(),
                request.bloodPressure(),
                request.respiratoryRate(),
                request.oxygenSaturation(),
                request.measuredAt()
        ));
        return ResponseEntity.ok(record);
    }

    @PostMapping("/medications")
    public ResponseEntity<Void> administerMedication(@RequestBody AdministerMedicationRequest request) {
        administerMedicationUseCase.administer(new AdministerMedicationCommand(
                request.orderNumber(),
                request.itemNumber(),
                request.nurseIdNumber(),
                request.performedAt()
        ));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/procedures")
    public ResponseEntity<Void> performProcedure(@RequestBody PerformProcedureRequest request) {
        performProcedureUseCase.perform(new PerformProcedureCommand(
                request.orderNumber(),
                request.itemNumber(),
                request.nurseIdNumber(),
                request.performedAt()
        ));
        return ResponseEntity.noContent().build();
    }

    public record VitalSignsRequest(String patientIdNumber,
                                    String nurseIdNumber,
                                    double temperature,
                                    int heartRate,
                                    String bloodPressure,
                                    int respiratoryRate,
                                    int oxygenSaturation,
                                    LocalDateTime measuredAt) { }

    public record AdministerMedicationRequest(int orderNumber, int itemNumber, String nurseIdNumber, LocalDateTime performedAt) { }

    public record PerformProcedureRequest(int orderNumber, int itemNumber, String nurseIdNumber, LocalDateTime performedAt) { }
}
