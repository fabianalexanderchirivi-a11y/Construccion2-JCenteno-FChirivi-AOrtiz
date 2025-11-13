package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataOrderDiagnosticRepository extends JpaRepository<OrderDiagnosticEntity, Long> {
    void deleteAllByOrderNumber(Long orderNumber);
    List<OrderDiagnosticEntity> findAllByOrderNumber(Long orderNumber);
}