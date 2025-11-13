package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.nursing;

import co.edu.tdea.clinicapp.domain.model.NursingRecord;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import co.edu.tdea.clinicapp.domain.repository.NursingRecordRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class NursingRecordJpaAdapter implements NursingRecordRepository {

    private final SpringDataNursingRecordRepository jpa;
    private final NursingRecordMapper mapper;

    public NursingRecordJpaAdapter(SpringDataNursingRecordRepository jpa,
                                   NursingRecordMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    // ====== Métodos del puerto (deben coincidir EXACTO con la interfaz) ======

    @Override
    public void add(NursingRecord record) {
        if (record == null) throw new IllegalArgumentException("Registro de enfermería requerido.");
        jpa.save(mapper.toEntity(record)); // la interfaz no retorna nada
    }

    @Override
    public List<NursingRecord> findAllByPatient(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("patientIdNumber es requerido");
        return jpa.findAllByPatientIdNumberOrderByPerformedAtDesc(patientIdNumber)
                  .stream()
                  .map(mapper::toDomain)
                  .toList();
    }

    // ====== Métodos auxiliares (no forman parte del puerto; opcionales) ======

    public List<NursingRecord> findAllByPatientAndOrder(String patientIdNumber,
                                                        OrderType orderType,
                                                        LocalDateTime orderCreatedAt) {
        return jpa.findAllByPatientIdNumberAndOrderTypeAndOrderCreatedAtOrderByPerformedAtDesc(
                        patientIdNumber, orderType, orderCreatedAt)
                  .stream()
                  .map(mapper::toDomain)
                  .toList();
    }

    public boolean existsForOrderItem(String patientIdNumber,
                                      OrderType orderType,
                                      LocalDateTime orderCreatedAt,
                                      int itemNumber) {
        return jpa.existsByPatientIdNumberAndOrderTypeAndOrderCreatedAtAndItemNumber(
                patientIdNumber, orderType, orderCreatedAt, itemNumber
        );
    }
}
