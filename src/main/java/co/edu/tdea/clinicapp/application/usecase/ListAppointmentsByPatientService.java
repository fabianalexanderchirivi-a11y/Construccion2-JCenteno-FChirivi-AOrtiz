package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListAppointmentsByPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import co.edu.tdea.clinicapp.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ListAppointmentsByPatientService implements ListAppointmentsByPatientUseCase {

    private final AppointmentRepository appointmentRepository;

    public ListAppointmentsByPatientService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> list(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("El numero de ID del paciente es requerido.");
        return appointmentRepository.findAllByPatient(patientIdNumber);
    }
}
