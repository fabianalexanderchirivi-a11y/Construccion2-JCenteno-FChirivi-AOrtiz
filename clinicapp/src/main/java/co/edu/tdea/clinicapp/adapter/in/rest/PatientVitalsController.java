package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.ListVitalSignsUseCase;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsUseCase;
import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vital-signs")
public class PatientVitalsController {

    private final RecordVitalSignsUseCase recordVitalSignsUseCase;
    private final ListVitalSignsUseCase listVitalSignsUseCase;

    public PatientVitalsController(RecordVitalSignsUseCase recordVitalSignsUseCase,
                                   ListVitalSignsUseCase listVitalSignsUseCase) {
        this.recordVitalSignsUseCase = recordVitalSignsUseCase;
        this.listVitalSignsUseCase = listVitalSignsUseCase;
    }

    @PreAuthorize("hasRole('NURSE')")
    @PostMapping("/{patientId}")
    public ResponseEntity<VitalSignsResponse> record(@PathVariable String patientId,
                                                     @RequestBody VitalSignsRequest request) {
        RecordVitalSignsCommand cmd = new RecordVitalSignsCommand(
                patientId,
                request.nurseIdNumber(),
                request.recordedAt() != null ? request.recordedAt() : LocalDateTime.now(),
                request.systolic(),
                request.diastolic(),
                request.temperature(),
                request.pulse(),
                request.oxygen()
        );
        VitalSignsRecord saved = recordVitalSignsUseCase.record(cmd);
        return ResponseEntity.ok(toResponse(saved));
    }

    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("/{patientId}")
    public ResponseEntity<List<VitalSignsResponse>> list(@PathVariable String patientId) {
        List<VitalSignsResponse> vitals = listVitalSignsUseCase.listByPatient(patientId)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(vitals);
    }

    private VitalSignsResponse toResponse(VitalSignsRecord r) {
        return new VitalSignsResponse(
                r.getRecordedAt(),
                r.getNurseIdNumber(),
                r.getSystolic(),
                r.getDiastolic(),
                r.getTemperature(),
                r.getPulse(),
                r.getOxygen()
        );
    }

    public record VitalSignsRequest(
            LocalDateTime recordedAt,
            String nurseIdNumber,
            Integer systolic,
            Integer diastolic,
            Double temperature,
            Integer pulse,
            Integer oxygen
    ) { }

    public record VitalSignsResponse(
            LocalDateTime recordedAt,
            String nurseIdNumber,
            Integer systolic,
            Integer diastolic,
            Double temperature,
            Integer pulse,
            Integer oxygen
    ) { }
}
