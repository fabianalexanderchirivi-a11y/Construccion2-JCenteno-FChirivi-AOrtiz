package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SpringDataAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    boolean existsByDoctorDocumentAndScheduledAt(String doctorDocument, LocalDateTime scheduledAt);
    boolean existsByPatientDocumentAndScheduledAt(String patientDocument, LocalDateTime scheduledAt);
    Optional<AppointmentEntity> findByPatientDocumentAndScheduledAt(String patientDocument, LocalDateTime scheduledAt);
    List<AppointmentEntity> findAllByPatientDocumentOrderByScheduledAtAsc(String patientDocument);
    List<AppointmentEntity> findAllByDoctorDocumentOrderByScheduledAtAsc(String doctorDocument);
}