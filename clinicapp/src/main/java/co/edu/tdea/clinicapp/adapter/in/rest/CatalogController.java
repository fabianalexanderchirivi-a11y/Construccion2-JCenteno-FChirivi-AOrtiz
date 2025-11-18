package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.DeleteDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.application.port.in.DeleteMedicationUseCase;
import co.edu.tdea.clinicapp.application.port.in.DeleteProcedureUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListDiagnosticAidsUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListMedicationsUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListProceduresUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpsertDiagnosticAidCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertDiagnosticAidUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpsertMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertMedicationUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpsertProcedureCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertProcedureUseCase;
import co.edu.tdea.clinicapp.domain.model.DiagnosticAid;
import co.edu.tdea.clinicapp.domain.model.Medication;
import co.edu.tdea.clinicapp.domain.model.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
@PreAuthorize("hasRole('SUPPORT')")
public class CatalogController {

    private final UpsertMedicationUseCase upsertMedicationUseCase;
    private final UpsertProcedureUseCase upsertProcedureUseCase;
    private final UpsertDiagnosticAidUseCase upsertDiagnosticAidUseCase;
    private final ListMedicationsUseCase listMedicationsUseCase;
    private final ListProceduresUseCase listProceduresUseCase;
    private final ListDiagnosticAidsUseCase listDiagnosticAidsUseCase;
    private final DeleteMedicationUseCase deleteMedicationUseCase;
    private final DeleteProcedureUseCase deleteProcedureUseCase;
    private final DeleteDiagnosticAidUseCase deleteDiagnosticAidUseCase;

    public CatalogController(UpsertMedicationUseCase upsertMedicationUseCase,
                             UpsertProcedureUseCase upsertProcedureUseCase,
                             UpsertDiagnosticAidUseCase upsertDiagnosticAidUseCase,
                             ListMedicationsUseCase listMedicationsUseCase,
                             ListProceduresUseCase listProceduresUseCase,
                             ListDiagnosticAidsUseCase listDiagnosticAidsUseCase,
                             DeleteMedicationUseCase deleteMedicationUseCase,
                             DeleteProcedureUseCase deleteProcedureUseCase,
                             DeleteDiagnosticAidUseCase deleteDiagnosticAidUseCase) {
        this.upsertMedicationUseCase = upsertMedicationUseCase;
        this.upsertProcedureUseCase = upsertProcedureUseCase;
        this.upsertDiagnosticAidUseCase = upsertDiagnosticAidUseCase;
        this.listMedicationsUseCase = listMedicationsUseCase;
        this.listProceduresUseCase = listProceduresUseCase;
        this.listDiagnosticAidsUseCase = listDiagnosticAidsUseCase;
        this.deleteMedicationUseCase = deleteMedicationUseCase;
        this.deleteProcedureUseCase = deleteProcedureUseCase;
        this.deleteDiagnosticAidUseCase = deleteDiagnosticAidUseCase;
    }

    @GetMapping("/medications")
    public List<Medication> listMedications() {
        return listMedicationsUseCase.list();
    }

    @PostMapping("/medications")
    public ResponseEntity<Medication> upsertMedication(@RequestBody MedicationRequest request) {
        Medication medication = upsertMedicationUseCase.upsert(new UpsertMedicationCommand(
                request.id(),
                request.name(),
                request.unitPrice()
        ));
        return ResponseEntity.ok(medication);
    }

    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable String id) {
        deleteMedicationUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/procedures")
    public List<Procedure> listProcedures() {
        return listProceduresUseCase.list();
    }

    @PostMapping("/procedures")
    public ResponseEntity<Procedure> upsertProcedure(@RequestBody ProcedureRequest request) {
        Procedure procedure = upsertProcedureUseCase.upsert(new UpsertProcedureCommand(
                request.id(),
                request.name(),
                request.cost()
        ));
        return ResponseEntity.ok(procedure);
    }

    @DeleteMapping("/procedures/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable String id) {
        deleteProcedureUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/diagnostic-aids")
    public List<DiagnosticAid> listDiagnosticAids() {
        return listDiagnosticAidsUseCase.list();
    }

    @PostMapping("/diagnostic-aids")
    public ResponseEntity<DiagnosticAid> upsertDiagnosticAid(@RequestBody DiagnosticAidRequest request) {
        DiagnosticAid diagnosticAid = upsertDiagnosticAidUseCase.upsert(new UpsertDiagnosticAidCommand(
                request.id(),
                request.name(),
                request.cost()
        ));
        return ResponseEntity.ok(diagnosticAid);
    }

    @DeleteMapping("/diagnostic-aids/{id}")
    public ResponseEntity<Void> deleteDiagnosticAid(@PathVariable String id) {
        deleteDiagnosticAidUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public record MedicationRequest(String id, String name, int unitPrice) { }

    public record ProcedureRequest(String id, String name, int cost) { }

    public record DiagnosticAidRequest(String id, String name, int cost) { }
}
