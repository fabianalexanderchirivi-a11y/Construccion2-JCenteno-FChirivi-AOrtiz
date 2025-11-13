package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface ListAppointmentsByDoctorUseCase {
    List<Appointment> list(String doctorIdNumber);
}
