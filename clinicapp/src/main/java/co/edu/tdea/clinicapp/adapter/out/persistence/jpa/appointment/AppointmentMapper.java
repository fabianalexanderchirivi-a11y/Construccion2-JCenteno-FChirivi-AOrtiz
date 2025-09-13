package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.appointment;

import co.edu.tdea.clinicapp.domain.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentEntity toEntity(Appointment d) {
        var e = new AppointmentEntity();
        e.setPatientDocument(d.getPatientIdNumber());
        e.setDoctorDocument(d.getDoctorIdNumber());
        e.setScheduledAt(d.getScheduledAt());
        e.setReason(null);
        return e;
    }

    public Appointment toDomain(AppointmentEntity e) {
        return new Appointment(
                e.getPatientDocument(),
                e.getDoctorDocument(),
                e.getScheduledAt()
        );
    }
}
