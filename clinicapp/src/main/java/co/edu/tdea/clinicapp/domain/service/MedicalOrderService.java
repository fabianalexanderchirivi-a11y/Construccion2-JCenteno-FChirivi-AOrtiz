package co.edu.tdea.clinicapp.domain.service;

import co.edu.tdea.clinicapp.domain.model.MedicalOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalOrderService {
    MedicalOrder create(MedicalOrder order);
    MedicalOrder update(MedicalOrder order);
    void cancel(String orderId, String reason);
    MedicalOrder changeStatus(String orderId, MedicalOrder status);
    Optional<MedicalOrder> findById(String orderId);
    List<MedicalOrder> findByPatient(String patientId);
    List<MedicalOrder> findByDate(LocalDate date);
}
