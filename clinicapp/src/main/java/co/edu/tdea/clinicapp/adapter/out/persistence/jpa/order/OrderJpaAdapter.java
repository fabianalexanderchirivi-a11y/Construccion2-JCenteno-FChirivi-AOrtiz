package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import co.edu.tdea.clinicapp.domain.model.Order;
import co.edu.tdea.clinicapp.domain.model.OrderStatus;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderJpaAdapter implements OrderRepository {

    private final SpringDataOrderRepository orderRepo;
    private final SpringDataOrderMedicationRepository medRepo;
    private final SpringDataOrderProcedureRepository procRepo;
    private final SpringDataOrderDiagnosticRepository diagRepo;
    private final OrderMapper mapper;

    public OrderJpaAdapter(SpringDataOrderRepository orderRepo,
                           SpringDataOrderMedicationRepository medRepo,
                           SpringDataOrderProcedureRepository procRepo,
                           SpringDataOrderDiagnosticRepository diagRepo,
                           OrderMapper mapper) {
        this.orderRepo = orderRepo;
        this.medRepo = medRepo;
        this.procRepo = procRepo;
        this.diagRepo = diagRepo;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        if (order == null) throw new IllegalArgumentException("Orden inválida.");

        Long number = orderRepo.nextOrderNumber();

        var header = mapper.toHeaderEntity(order);
        header.setOrderNumber(number);
        var savedHeader = orderRepo.save(header);

        medRepo.deleteAllByOrderNumber(number);
        procRepo.deleteAllByOrderNumber(number);
        diagRepo.deleteAllByOrderNumber(number);

        var meds  = mapper.toMedicationEntities(order, number);
        var procs = mapper.toProcedureEntities(order, number);
        var diags = mapper.toDiagnosticEntities(order, number);

        if (!meds.isEmpty())  medRepo.saveAll(meds);
        if (!procs.isEmpty()) procRepo.saveAll(procs);
        if (!diags.isEmpty()) diagRepo.saveAll(diags);

        var medsDb  = medRepo.findAllByOrderNumber(number);
        var procsDb = procRepo.findAllByOrderNumber(number);
        var diagsDb = diagRepo.findAllByOrderNumber(number);

        return mapper.toDomain(savedHeader, medsDb, procsDb, diagsDb);
    }

    @Override
    public List<Order> findOpenByPatientAndType(String patientIdNumber, OrderType type) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("patientIdNumber requerido");
        if (type == null)
            throw new IllegalArgumentException("Tipo requerido");

        var headers = orderRepo.findAllByPatientDocumentOrderByCreatedAtAsc(patientIdNumber);

        return headers.stream()
                .map(h -> mapper.toDomain(
                        h,
                        medRepo.findAllByOrderNumber(h.getOrderNumber()),
                        procRepo.findAllByOrderNumber(h.getOrderNumber()),
                        diagRepo.findAllByOrderNumber(h.getOrderNumber())
                ))
                .filter(o -> o.getType() == type && o.getStatus() == OrderStatus.OPEN)
                .toList();
    }

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        try {
            Long id = Long.parseLong(orderNumber);
            var optHeader = orderRepo.findById(id);
            if (optHeader.isEmpty()) return Optional.empty();

            var h      = optHeader.get();
            var meds   = medRepo.findAllByOrderNumber(id);
            var procs  = procRepo.findAllByOrderNumber(id);
            var diags  = diagRepo.findAllByOrderNumber(id);

            return Optional.of(mapper.toDomain(h, meds, procs, diags));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAllByPatientId(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("patientIdNumber requerido");

        var headers = orderRepo.findAllByPatientDocumentOrderByCreatedAtAsc(patientIdNumber);

        return headers.stream()
                .map(h -> mapper.toDomain(
                        h,
                        medRepo.findAllByOrderNumber(h.getOrderNumber()),
                        procRepo.findAllByOrderNumber(h.getOrderNumber()),
                        diagRepo.findAllByOrderNumber(h.getOrderNumber())
                ))
                .toList();
    }

    @Override
    public String nextOrderNumber() {
        return String.valueOf(orderRepo.nextOrderNumber());
    }
}
