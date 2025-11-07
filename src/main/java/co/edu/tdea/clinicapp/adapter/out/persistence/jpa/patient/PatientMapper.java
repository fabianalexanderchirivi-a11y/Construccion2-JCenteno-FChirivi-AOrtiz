package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.patient;

import co.edu.tdea.clinicapp.domain.model.Gender;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.model.PatientFactory;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    private final PatientFactory patientFactory;

    public PatientMapper(PatientFactory patientFactory) {
        this.patientFactory = patientFactory;
    }

    public PatientEntity toEntity(Patient d) {
        var e = new PatientEntity();
        e.setDocument(d.getIdNumber());
        e.setFullName(d.getFullName());
        e.setEmail(d.getEmail());
        e.setPhone(d.getPhoneNumber());
        e.setBirthDate(d.getBirthDate());
        e.setAddress(d.getAddress());
        e.setGender(d.getGender() != null ? d.getGender().name() : null);
        return e;
    }

    public Patient toDomain(PatientEntity e) {
        Gender g = e.getGender() != null ? Gender.valueOf(e.getGender()) : null;
        return patientFactory.fromPersistence(
            e.getDocument(),
            e.getFullName(),
            e.getBirthDate(),
            g,
            e.getAddress(),
            e.getPhone(),
            e.getEmail()
        );
    }
}
