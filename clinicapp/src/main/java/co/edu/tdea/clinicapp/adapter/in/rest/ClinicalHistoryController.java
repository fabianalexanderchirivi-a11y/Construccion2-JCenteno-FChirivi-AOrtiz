package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.GetClinicalHistoryByPatientBetweenDatesUseCase;
import co.edu.tdea.clinicapp.application.port.in.GetClinicalHistoryByPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.RecordDiagnosticResultCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordDiagnosticResultUseCase;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/clinical-history")
public class ClinicalHistoryController {

    private final RecordDiagnosticResultUseCase recordDiagnosticResultUseCase;
    private final GetClinicalHistoryByPatientUseCase getClinicalHistoryByPatientUseCase;
    private final GetClinicalHistoryByPatientBetweenDatesUseCase getClinicalHistoryByPatientBetweenDatesUseCase;

    public ClinicalHistoryController(RecordDiagnosticResultUseCase recordDiagnosticResultUseCase,
                                     GetClinicalHistoryByPatientUseCase getClinicalHistoryByPatientUseCase,
                                     GetClinicalHistoryByPatientBetweenDatesUseCase getClinicalHistoryByPatientBetweenDatesUseCase) {
        this.recordDiagnosticResultUseCase = recordDiagnosticResultUseCase;
        this.getClinicalHistoryByPatientUseCase = getClinicalHistoryByPatientUseCase;
        this.getClinicalHistoryByPatientBetweenDatesUseCase = getClinicalHistoryByPatientBetweenDatesUseCase;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public ResponseEntity<ClinicalHistoryEntry> register(@RequestBody ClinicalNoteRequest request) {
        ClinicalHistoryEntry entry = recordDiagnosticResultUseCase.record(new RecordDiagnosticResultCommand(
                request.patientIdNumber(),
                request.doctorIdNumber(),
                request.attendedAt(),
                request.diagnosis(),
                request.diagnosticAidOrderNumber(),
                request.diagnosticAidItemNumber(),
                request.diagnosticAidResult(),
                request.reason(),
                request.symptoms()
        ));
        return ResponseEntity.ok(entry);
    }

    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    @GetMapping("/{patientIdNumber}")
    public List<ClinicalHistoryEntry> listByPatient(@PathVariable String patientIdNumber) {
        return getClinicalHistoryByPatientUseCase.list(patientIdNumber);
    }

    @PreAuthorize("hasAnyRole('DOCTOR','NURSE')")
    @GetMapping("/{patientIdNumber}/range")
    public List<ClinicalHistoryEntry> listByPatientAndRange(@PathVariable String patientIdNumber,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return getClinicalHistoryByPatientBetweenDatesUseCase.list(patientIdNumber, from, to);
    }

    public record ClinicalNoteRequest(String patientIdNumber,
                                      LocalDateTime attendedAt,
                                      String doctorIdNumber,
                                      String reason,
                                      String symptoms,
                                      String diagnosis,
                                      String diagnosticAidOrderNumber,
                                      Integer diagnosticAidItemNumber,
                                      String diagnosticAidResult) { }
}
