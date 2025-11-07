package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.CopaySummaryUseCase;

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

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CopaySummaryService implements CopaySummaryUseCase {

    private final OrderRepository orderRepository;
    private final MedicationRepository medicationRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;

    @Value("${billing.copay.rate:0.0}")
    private double copayRate;

    public CopaySummaryService(OrderRepository orderRepository,
                               MedicationRepository medicationRepository,
                               ProcedureRepository procedureRepository,
                               DiagnosticAidRepository diagnosticAidRepository) {
        this.orderRepository = orderRepository;
        this.medicationRepository = medicationRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
    }

    @Override
    public double copayAccumulated(String patientIdNumber, int year) {
        // ✅ Requiere que el repositorio exponga este método
        List<Order> orders = orderRepository.findAllByPatientIdNumber(patientIdNumber);

        double subtotal = 0.0;

        for (Order o : orders) {
            if (o.getCreatedAt() == null || o.getCreatedAt().getYear() != year) continue;

            for (OrderItem it : o.getItems()) {
                double unitCost = resolveUnitCost(o.getType(), it.getCatalogId());
                subtotal += unitCost * it.getQuantity();
            }
        }
        return subtotal * copayRate;
    }

    private double resolveUnitCost(OrderType type, String catalogId) {
        switch (type) {
            case MEDICATION -> {
                var m = medicationRepository.findById(catalogId);
                if (m.isPresent()) return m.get().getUnitCost();
            }
            case PROCEDURE -> {
                var p = procedureRepository.findById(catalogId);
                if (p.isPresent()) return p.get().getUnitCost();
            }
            case DIAGNOSTIC_AID -> {
                var a = diagnosticAidRepository.findById(catalogId);
                if (a.isPresent()) return a.get().getUnitCost();
            }
        }
        return 0.0; // si el ítem no está en catálogo
    }
}
