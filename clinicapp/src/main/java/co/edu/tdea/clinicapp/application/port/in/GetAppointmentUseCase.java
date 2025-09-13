package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface GetAppointmentUseCase {
    Optional<Appointment> byPatientAndDateTime(String patientIdNumber, LocalDateTime scheduledAt);
}
