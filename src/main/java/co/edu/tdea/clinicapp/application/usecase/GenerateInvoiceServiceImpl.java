package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.GenerateInvoiceUseCase;
import co.edu.tdea.clinicapp.application.dto.InvoiceDto;
import co.edu.tdea.clinicapp.application.dto.InvoiceItemDto;

import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderItem;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import co.edu.tdea.clinicapp.domain.repository.ProcedureRepository;
import co.edu.tdea.clinicapp.domain.repository.DiagnosticAidRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GenerateInvoiceServiceImpl implements GenerateInvoiceUseCase {

    private final OrderRepository orderRepository;
    private final MedicationRepository medicationRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;

    @Value("${billing.copay.rate:0.0}")
    private double copayRate;

    @Value("${billing.currency:COP}")
    private String currency;

    public GenerateInvoiceServiceImpl(OrderRepository orderRepository,
                                  MedicationRepository medicationRepository,
                                  ProcedureRepository procedureRepository,
                                  DiagnosticAidRepository diagnosticAidRepository) {
        this.orderRepository = orderRepository;
        this.medicationRepository = medicationRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public InvoiceDto generateForPatient(String patientIdNumber, Integer year) {
        int targetYear = (year == null ? Year.now().getValue() : year);

        // ✅ ahora existe en tu repo:
        List<Order> orders = orderRepository.findAllByPatientIdNumber(patientIdNumber);

        List<InvoiceItemDto> items = new ArrayList<>();
        double subtotal = 0.0;

        for (Order o : orders) {
            if (o.getCreatedAt() == null || o.getCreatedAt().getYear() != targetYear) continue;

            for (OrderItem it : o.getItems()) {
                Resolved r = resolveLine(o.getType(), it.getCatalogId());
                String name = (r.name != null && !r.name.isBlank()) ? r.name : it.getCatalogId();
                double unitCost = r.unitCost;
                double lineTotal = unitCost * it.getQuantity();

                subtotal += lineTotal;

                items.add(new InvoiceItemDto(
                        null,                  // tu Order no expone número de orden
                        it.getItemNumber(),    // ítem
                        name,                  // descripción
                        lineTotal              // total línea
                ));
            }
        }

        double copay = subtotal * copayRate;
        double total = subtotal + copay;
        return new InvoiceDto(patientIdNumber, subtotal, copay, total, currency, items);
    }

    private Resolved resolveLine(OrderType type, String catalogId) {
        switch (type) {
            case MEDICATION -> {
                var opt = medicationRepository.findById(catalogId);
                if (opt.isPresent()) {
                    var m = opt.get();
                    return new Resolved(m.getName(), m.getUnitCost());
                }
            }
            case PROCEDURE -> {
                var opt = procedureRepository.findById(catalogId);
                if (opt.isPresent()) {
                    var p = opt.get();
                    return new Resolved(p.getName(), p.getUnitCost());
                }
            }
            case DIAGNOSTIC_AID -> {
                var opt = diagnosticAidRepository.findById(catalogId);
                if (opt.isPresent()) {
                    var a = opt.get();
                    return new Resolved(a.getName(), a.getUnitCost());
                }
            }
        }
        return new Resolved(null, 0.0);
    }

    private static class Resolved {
        final String name;
        final double unitCost;
        Resolved(String name, double unitCost) { this.name = name; this.unitCost = unitCost; }
    }
}
