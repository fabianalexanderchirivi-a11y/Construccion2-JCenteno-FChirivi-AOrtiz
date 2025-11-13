package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.Appointment;

public interface ScheduleAppointmentUseCase {
    Appointment schedule(ScheduleAppointmentCommand command);
}
