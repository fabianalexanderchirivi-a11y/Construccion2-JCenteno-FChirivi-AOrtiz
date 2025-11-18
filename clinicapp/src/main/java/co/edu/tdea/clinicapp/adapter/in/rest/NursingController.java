package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.AdministerMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.AdministerMedicationUseCase;
import co.edu.tdea.clinicapp.application.port.in.PerformProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.PerformProcedureUseCase;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsUseCase;
import co.edu.tdea.clinicapp.domain.model.NursingRecord;
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
                request.measuredAt() != null ? request.measuredAt() : LocalDateTime.now(),
                request.systolic(),
                request.diastolic(),
                request.temperature(),
                request.pulse(),
                request.oxygenSaturation()
        ));
        return ResponseEntity.ok(record);
    }

    @PostMapping("/medications")
    public ResponseEntity<NursingRecord> administerMedication(@RequestBody AdministerMedicationRequest request) {
        NursingRecord record = administerMedicationUseCase.administer(new AdministerMedicationCommand(
                request.patientIdNumber(),
                request.nurseIdNumber(),
                request.medicationId(),
                request.quantity(),
                request.performedAt()
        ));
        return ResponseEntity.ok(record);
    }

    @PostMapping("/procedures")
    public ResponseEntity<NursingRecord> performProcedure(@RequestBody PerformProcedureRequest request) {
        NursingRecord record = performProcedureUseCase.perform(new PerformProcedureCommand(
                request.patientIdNumber(),
                request.nurseIdNumber(),
                request.procedureId(),
                request.quantity(),
                request.performedAt()
        ));
        return ResponseEntity.ok(record);
    }

    public record VitalSignsRequest(String patientIdNumber,
                                    String nurseIdNumber,
                                    Integer systolic,
                                    Integer diastolic,
                                    Double temperature,
                                    Integer pulse,
                                    Integer oxygenSaturation,
                                    LocalDateTime measuredAt) { }

    public record AdministerMedicationRequest(String patientIdNumber, String nurseIdNumber,
                                              String medicationId, int quantity, LocalDateTime performedAt) { }

    public record PerformProcedureRequest(String patientIdNumber, String nurseIdNumber,
                                          String procedureId, int quantity, LocalDateTime performedAt) { }
}
