package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.UpdatePatientCommand;
import co.edu.tdea.clinicapp.application.port.in.UpdatePatientUseCase;
import co.edu.tdea.clinicapp.domain.model.EmergencyContact;
import co.edu.tdea.clinicapp.domain.model.InsurancePolicy;
import co.edu.tdea.clinicapp.domain.model.Patient;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class UpdatePatientService implements UpdatePatientUseCase {

    private final PatientRepository patientRepository;

    public UpdatePatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient update(UpdatePatientCommand cmd) {
        if (cmd == null || cmd.getIdNumber() == null || cmd.getIdNumber().isBlank()) {
            throw new IllegalArgumentException("Un numero de ID es requerido.");
        }

        Patient p = patientRepository.findByIdNumber(cmd.getIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado."));

        if (cmd.getFullName() != null)    p.setFullName(cmd.getFullName());
        if (cmd.getBirthDate() != null)   p.setBirthDate(cmd.getBirthDate());
        if (cmd.getGender() != null)      p.setGender(cmd.getGender());
        if (cmd.getAddress() != null)     p.setAddress(cmd.getAddress());
        if (cmd.getPhoneNumber() != null) p.setPhoneNumber(cmd.getPhoneNumber());
        if (cmd.getEmail() != null)       p.setEmail(cmd.getEmail());
        
        boolean hasECUpdate =
                cmd.getEcFirstName() != null || cmd.getEcLastName() != null ||
                cmd.getEcRelationship() != null || cmd.getEcPhoneNumber() != null;

        if (hasECUpdate) {
            String first  = (cmd.getEcFirstName()  != null) ? cmd.getEcFirstName()  : p.getEmergencyContact().getFirstName();
            String last   = (cmd.getEcLastName()   != null) ? cmd.getEcLastName()   : p.getEmergencyContact().getLastName();
            String rel    = (cmd.getEcRelationship()!= null) ? cmd.getEcRelationship(): p.getEmergencyContact().getRelationship();
            String phone  = (cmd.getEcPhoneNumber()!= null) ? cmd.getEcPhoneNumber(): p.getEmergencyContact().getPhoneNumber();
            p.setEmergencyContact(new EmergencyContact(first, last, rel, phone));
        }

        boolean hasPolicyUpdate =
                cmd.getInsuranceCompanyName() != null ||
                cmd.getInsurancePolicyNumber() != null ||
                cmd.getInsuranceActive() != null ||
                cmd.getInsuranceEndDate() != null;

        if (hasPolicyUpdate) {
            String company   = (cmd.getInsuranceCompanyName() != null) ? cmd.getInsuranceCompanyName() : p.getInsurancePolicy().getCompanyName();
            String number    = (cmd.getInsurancePolicyNumber() != null) ? cmd.getInsurancePolicyNumber() : p.getInsurancePolicy().getPolicyNumber();
            boolean active   = (cmd.getInsuranceActive() != null) ? cmd.getInsuranceActive() : p.getInsurancePolicy().isActive();
            java.time.LocalDate end =
                    (cmd.getInsuranceEndDate() != null) ? cmd.getInsuranceEndDate() : p.getInsurancePolicy().getEndDate();

            p.setInsurancePolicy(new InsurancePolicy(company, number, active, end));
        }

        return patientRepository.save(p);
    }
}
