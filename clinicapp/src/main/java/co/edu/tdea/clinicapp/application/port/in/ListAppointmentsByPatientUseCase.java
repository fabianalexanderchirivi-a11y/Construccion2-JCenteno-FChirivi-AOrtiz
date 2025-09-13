package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface ListAppointmentsByPatientUseCase {
    List<Appointment> list(String patientIdNumber);
}
