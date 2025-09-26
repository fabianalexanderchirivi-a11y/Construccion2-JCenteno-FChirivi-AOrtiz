package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.RegisterPatientCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterPatientUseCase;
import co.edu.tdea.clinicapp.domain.model.EmergencyContact;
import co.edu.tdea.clinicapp.domain.model.InsurancePolicy;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class RegisterPatientService implements RegisterPatientUseCase {

    private final PatientRepository patientRepository;

    public RegisterPatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient register(RegisterPatientCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");
        if (patientRepository.existsByIdNumber(cmd.getIdNumber())) {
            throw new IllegalArgumentException("El numero de ID ya existe.");
        }

        EmergencyContact ec = new EmergencyContact(
                cmd.getEcFirstName(),
                cmd.getEcLastName(),
                cmd.getEcRelationship(),
                cmd.getEcPhoneNumber()
        );

        InsurancePolicy policy = new InsurancePolicy(
                cmd.getInsuranceCompanyName(),
                cmd.getInsurancePolicyNumber(),
                cmd.isInsuranceActive(),
                cmd.getInsuranceEndDate()
        );

        Patient patient = new Patient(
                cmd.getIdNumber(),
                cmd.getFullName(),
                cmd.getBirthDate(),
                cmd.getGender(),
                cmd.getAddress(),
                cmd.getPhoneNumber(),
                cmd.getEmail(),
                ec,
                policy
        );

        return patientRepository.save(patient);
    }
}
