package co.edu.tdea.clinicapp.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface AppointmentRepository {
    boolean existsByDoctorAndDateTime(String doctorIdNumber, LocalDateTime scheduledAt);
    boolean existsByPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt);
    Appointment save(Appointment appointment);
    Optional<Appointment> findByPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt);
    List<Appointment> findAllByPatient(String patientIdNumber);
    List<Appointment> findAllByDoctor(String doctorIdNumber);
}
