package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListAppointmentsByDoctorUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ListAppointmentsByDoctorService implements ListAppointmentsByDoctorUseCase {

    private final AppointmentRepository appointmentRepository;

    public ListAppointmentsByDoctorService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> list(String doctorIdNumber) {
        if (doctorIdNumber == null || doctorIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del doctor es requerido.");
        return appointmentRepository.findAllByDoctor(doctorIdNumber);
    }
}
