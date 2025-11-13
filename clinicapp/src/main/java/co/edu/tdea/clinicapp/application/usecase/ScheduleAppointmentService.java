package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.ScheduleAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.ScheduleAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ScheduleAppointmentService implements ScheduleAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public ScheduleAppointmentService(AppointmentRepository appointmentRepository,
                                      PatientRepository patientRepository,
                                      UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Appointment schedule(ScheduleAppointmentCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Commando requerido.");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));
        if (doctor.getRole() != Role.DOCTOR)
            throw new IllegalArgumentException("El usuario no es un doctor.");

        if (appointmentRepository.existsByDoctorAndDateTime(cmd.getDoctorIdNumber(), cmd.getScheduledAt()))
            throw new IllegalArgumentException("El médico ya tiene una cita programada en ese horario.");
        if (appointmentRepository.existsByPatientAndDateTime(cmd.getPatientIdNumber(), cmd.getScheduledAt()))
            throw new IllegalArgumentException("El paciente ya tiene una cita programada en ese horario.");

        Appointment appt = new Appointment(
                cmd.getPatientIdNumber(),
                cmd.getDoctorIdNumber(),
                cmd.getScheduledAt()
        );
        return appointmentRepository.save(appt);
    }
}
