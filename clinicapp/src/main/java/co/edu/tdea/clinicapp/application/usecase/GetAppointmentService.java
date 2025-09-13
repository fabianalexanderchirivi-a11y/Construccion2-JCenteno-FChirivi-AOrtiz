package co.edu.tdea.clinicapp.application.usecase;

import java.time.LocalDateTime;
import java.util.Optional;

import co.edu.tdea.clinicapp.application.port.in.GetAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;

public class GetAppointmentService implements GetAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;

    public GetAppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> byPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es requerido.");
        if (scheduledAt == null)
            throw new IllegalArgumentException("Cuando fue agendado es requerido.");

        return appointmentRepository.findByPatientAndDateTime(patientIdNumber, scheduledAt);
    }
}
