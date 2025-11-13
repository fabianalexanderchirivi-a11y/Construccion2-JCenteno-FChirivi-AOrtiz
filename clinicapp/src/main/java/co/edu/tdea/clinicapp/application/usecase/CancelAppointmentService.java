package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.CancelAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.CancelAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.model.AppointmentStatus;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class CancelAppointmentService implements CancelAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public CancelAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void cancel(CancelAppointmentCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Commando requerido.");

        Appointment appt = appointmentRepository
                .findByPatientAndDateTime(cmd.getPatientIdNumber(), cmd.getScheduledAt())
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));

        if (appt.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalStateException("Solo se pueden cancelar citas en estado SCHEDULED.");
        }

        appt.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appt);
    }
}
