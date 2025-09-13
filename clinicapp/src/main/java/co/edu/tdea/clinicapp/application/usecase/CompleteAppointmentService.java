package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.CompleteAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.CompleteAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.model.AppointmentStatus;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;

public class CompleteAppointmentService implements CompleteAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public CompleteAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void complete(CompleteAppointmentCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Commando requerido.");

        Appointment appt = appointmentRepository
                .findByPatientAndDateTime(cmd.getPatientIdNumber(), cmd.getScheduledAt())
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada."));

        if (appt.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new IllegalStateException("Solo se pueden cancelar citas en estado SCHEDULED.");
        }

        appt.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appt);
    }
}
