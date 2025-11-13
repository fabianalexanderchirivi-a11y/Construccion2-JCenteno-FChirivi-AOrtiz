package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataOrderProcedureRepository extends JpaRepository<OrderProcedureEntity, Long> {
    void deleteAllByOrderNumber(Long orderNumber);
    List<OrderProcedureEntity> findAllByOrderNumber(Long orderNumber);
}