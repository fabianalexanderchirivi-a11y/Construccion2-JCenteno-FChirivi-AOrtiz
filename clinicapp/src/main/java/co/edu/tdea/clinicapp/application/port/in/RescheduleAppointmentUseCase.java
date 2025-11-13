package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface RescheduleAppointmentUseCase {
    Appointment reschedule(RescheduleAppointmentCommand command);
}
