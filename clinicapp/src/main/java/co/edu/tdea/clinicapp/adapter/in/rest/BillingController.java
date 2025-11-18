package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.CopaySummaryUseCase;
import co.edu.tdea.clinicapp.application.port.in.GenerateInvoiceCommand;
import co.edu.tdea.clinicapp.application.port.in.GenerateInvoiceUseCase;
import co.edu.tdea.clinicapp.domain.model.Invoice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Invoice> generateInvoice(@RequestBody InvoiceRequest request) {
        Invoice invoice = generateInvoiceUseCase.generate(new GenerateInvoiceCommand(
                request.orderNumber(),
                request.items()
        ));
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/copay/{patientId}/{year}")
    public ResponseEntity<Integer> copaySummary(@PathVariable String patientId, @PathVariable int year) {
        return ResponseEntity.ok(copaySummaryUseCase.getCopaySummary(patientId, year));
    }

    public record InvoiceRequest(int orderNumber, List<Integer> items) { }
}
