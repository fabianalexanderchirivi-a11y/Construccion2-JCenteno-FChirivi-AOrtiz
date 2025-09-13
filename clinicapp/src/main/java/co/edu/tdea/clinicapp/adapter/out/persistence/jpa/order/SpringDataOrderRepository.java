package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select coalesce(max(o.orderNumber)+1, 1) from OrderEntity o")
    Long nextOrderNumber();
    List<OrderEntity> findAllByPatientDocumentOrderByCreatedAtAsc(String patientDocument);
}
