package co.edu.tdea.clinicapp.application.usecase;

import java.time.LocalDateTime;

import co.edu.tdea.clinicapp.application.port.in.RescheduleAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.RescheduleAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.model.AppointmentStatus;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class RescheduleAppointmentService implements RescheduleAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public RescheduleAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment reschedule(RescheduleAppointmentCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");
        LocalDateTime newAt = cmd.getNewScheduledAt();
        if (newAt == null || newAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("newScheduledAt debe ser una fecha/hora futura.");
        }

        Appointment original = appointmentRepository
                .findByPatientAndDateTime(cmd.getPatientIdNumber(), cmd.getOldScheduledAt())
                .orElseThrow(() -> new IllegalArgumentException("Cita original no encontrada."));

        if (original.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalStateException("Solo se pueden reprogramar citas en estado SCHEDULED.");
        }

        String doctorId = original.getDoctorIdNumber();
        String patientId = original.getPatientIdNumber();

        if (appointmentRepository.existsByDoctorAndDateTime(doctorId, newAt)) {
            throw new IllegalArgumentException("El médico ya tiene una cita programada en la nueva hora.");
        }
        if (appointmentRepository.existsByPatientAndDateTime(patientId, newAt)) {
            throw new IllegalArgumentException("El paciente ya tiene una cita programada en la nueva hora.");
        }

        original.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(original);

        Appointment rebooked = new Appointment(patientId, doctorId, newAt);
        return appointmentRepository.save(rebooked);
    }
}
