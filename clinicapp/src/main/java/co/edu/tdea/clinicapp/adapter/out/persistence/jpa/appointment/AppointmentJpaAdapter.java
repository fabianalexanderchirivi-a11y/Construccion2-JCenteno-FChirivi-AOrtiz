package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.appointment;

import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AppointmentJpaAdapter implements AppointmentRepository {

    private final SpringDataAppointmentRepository repository;
    private final AppointmentMapper mapper;

    public AppointmentJpaAdapter(SpringDataAppointmentRepository repository, AppointmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByDoctorAndDateTime(String doctorIdNumber, LocalDateTime scheduledAt) {
        return repository.existsByDoctorDocumentAndScheduledAt(doctorIdNumber, scheduledAt);
    }

    @Override
    public boolean existsByPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt) {
        return repository.existsByPatientDocumentAndScheduledAt(patientIdNumber, scheduledAt);
    }

    @Override
    public Appointment save(Appointment appointment) {
        var saved = repository.save(mapper.toEntity(appointment));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Appointment> findByPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt) {
        return repository.findByPatientDocumentAndScheduledAt(patientIdNumber, scheduledAt).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findAllByPatient(String patientIdNumber) {
        return repository.findAllByPatientDocumentOrderByScheduledAtAsc(patientIdNumber)
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Appointment> findAllByDoctor(String doctorIdNumber) {
        return repository.findAllByDoctorDocumentOrderByScheduledAtAsc(doctorIdNumber)
                .stream().map(mapper::toDomain).toList();
    }
}