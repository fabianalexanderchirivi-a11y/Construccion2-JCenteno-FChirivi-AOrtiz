package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataOrderMedicationRepository extends JpaRepository<OrderMedicationEntity, Long> {
    void deleteAllByOrderNumber(Long orderNumber);
    List<OrderMedicationEntity> findAllByOrderNumber(Long orderNumber);
}
