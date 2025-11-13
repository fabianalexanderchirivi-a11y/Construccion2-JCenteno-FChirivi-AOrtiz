package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.nursing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataNursingRecordRepository extends JpaRepository<NursingRecordEntity, Long> {

    List<NursingRecordEntity> findAllByPatientIdNumberOrderByPerformedAtDesc(String patientIdNumber);

    List<NursingRecordEntity> findAllByPatientIdNumberAndOrderTypeAndOrderCreatedAtOrderByPerformedAtDesc(
            String patientIdNumber, co.edu.tdea.clinicapp.domain.model.OrderType orderType, LocalDateTime orderCreatedAt
    );

    boolean existsByPatientIdNumberAndOrderTypeAndOrderCreatedAtAndItemNumber(
            String patientIdNumber, co.edu.tdea.clinicapp.domain.model.OrderType orderType, LocalDateTime orderCreatedAt, Integer itemNumber
    );
}
