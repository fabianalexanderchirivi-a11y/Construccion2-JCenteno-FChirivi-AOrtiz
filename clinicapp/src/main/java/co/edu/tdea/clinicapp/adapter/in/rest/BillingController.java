package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.dto.InvoiceDto;
import co.edu.tdea.clinicapp.application.port.in.CopaySummaryUseCase;
import co.edu.tdea.clinicapp.application.port.in.GenerateInvoiceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
@PreAuthorize("hasAnyRole('SUPPORT','ADMINISTRATIVE','ADMIN_STAFF')")
public class BillingController {

    private final GenerateInvoiceUseCase generateInvoiceUseCase;
    private final CopaySummaryUseCase copaySummaryUseCase;

    public BillingController(GenerateInvoiceUseCase generateInvoiceUseCase,
                             CopaySummaryUseCase copaySummaryUseCase) {
        this.generateInvoiceUseCase = generateInvoiceUseCase;
        this.copaySummaryUseCase = copaySummaryUseCase;
    }

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceDto> generateInvoice(@RequestBody InvoiceRequest request) {
        InvoiceDto invoice = generateInvoiceUseCase.generateForPatient(request.patientIdNumber(), request.year());
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/copay/{patientId}/{year}")
    public ResponseEntity<Double> copaySummary(@PathVariable String patientId, @PathVariable int year) {
        return ResponseEntity.ok(copaySummaryUseCase.copayAccumulated(patientId, year));
    }

    @GetMapping("/copays")
    public ResponseEntity<Double> copaySummaryQuery(@RequestParam String patientIdNumber, @RequestParam int year) {
        return ResponseEntity.ok(copaySummaryUseCase.copayAccumulated(patientIdNumber, year));
    }

    public record InvoiceRequest(String patientIdNumber, int year) { }
}
