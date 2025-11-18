package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.GetPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListPatientsUseCase;
import co.edu.tdea.clinicapp.application.port.in.RegisterPatientCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpdatePatientCommand;
import co.edu.tdea.clinicapp.application.port.in.UpdatePatientUseCase;
import co.edu.tdea.clinicapp.domain.model.EmergencyContact;
import co.edu.tdea.clinicapp.domain.model.Gender;
import co.edu.tdea.clinicapp.domain.model.InsurancePolicy;
import co.edu.tdea.clinicapp.domain.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final RegisterPatientUseCase registerPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final GetPatientUseCase getPatientUseCase;
    private final ListPatientsUseCase listPatientsUseCase;

    public PatientController(RegisterPatientUseCase registerPatientUseCase,
                             UpdatePatientUseCase updatePatientUseCase,
                             GetPatientUseCase getPatientUseCase,
                             ListPatientsUseCase listPatientsUseCase) {
        this.registerPatientUseCase = registerPatientUseCase;
        this.updatePatientUseCase = updatePatientUseCase;
        this.getPatientUseCase = getPatientUseCase;
        this.listPatientsUseCase = listPatientsUseCase;
    }

    // CREAR paciente → solo ADMINISTRATIVE
    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','ADMIN_STAFF')")
    @PostMapping
    public ResponseEntity<PatientResponse> register(@RequestBody PatientRequest request) {
        RegisterPatientCommand command = mapToRegisterCommand(request);
        Patient patient = registerPatientUseCase.register(command);
        return ResponseEntity.ok(mapToResponse(patient));
    }

    // ACTUALIZAR paciente → solo ADMINISTRATIVE
    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','ADMIN_STAFF')")
    @PutMapping("/{idNumber}")
    public ResponseEntity<PatientResponse> update(@PathVariable String idNumber,
                                                  @RequestBody PatientRequest request) {
        UpdatePatientCommand command = mapToUpdateCommand(idNumber, request);
        Patient patient = updatePatientUseCase.update(command);
        return ResponseEntity.ok(mapToResponse(patient));
    }

    // CONSULTAR un paciente → solo ADMINISTRATIVE
    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','ADMIN_STAFF')")
    @GetMapping("/{idNumber}")
    public ResponseEntity<PatientResponse> getById(@PathVariable String idNumber) {
        return getPatientUseCase.byIdNumber(idNumber)
                .map(p -> ResponseEntity.ok(mapToResponse(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    // LISTAR pacientes → solo ADMINISTRATIVE
    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','ADMIN_STAFF')")
    @GetMapping
    public List<PatientResponse> list() {
        return listPatientsUseCase.list()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RegisterPatientCommand mapToRegisterCommand(PatientRequest r) {
        EmergencyContactDto ec = r.emergencyContact();
        InsurancePolicyDto ip = r.insurancePolicy();

        return new RegisterPatientCommand(
                r.idNumber(),
                r.fullName(),
                r.birthDate(),
                r.gender(),
                r.address(),
                r.phoneNumber(),
                r.email(),
                ec != null ? ec.firstName() : null,
                ec != null ? ec.lastName() : null,
                ec != null ? ec.relationship() : null,
                ec != null ? ec.phoneNumber() : null,
                ip != null ? ip.companyName() : null,
                ip != null ? ip.policyNumber() : null,
                ip != null && ip.active(),
                ip != null ? ip.endDate() : null
        );
    }

    private UpdatePatientCommand mapToUpdateCommand(String idNumber, PatientRequest r) {
        EmergencyContactDto ec = r.emergencyContact();
        InsurancePolicyDto ip = r.insurancePolicy();

        return new UpdatePatientCommand(
                idNumber,
                r.fullName(),
                r.birthDate(),
                r.gender(),
                r.address(),
                r.phoneNumber(),
                r.email(),
                ec != null ? ec.firstName() : null,
                ec != null ? ec.lastName() : null,
                ec != null ? ec.relationship() : null,
                ec != null ? ec.phoneNumber() : null,
                ip != null ? ip.companyName() : null,
                ip != null ? ip.policyNumber() : null,
                ip != null ? ip.active() : null,
                ip != null ? ip.endDate() : null
        );
    }

    private PatientResponse mapToResponse(Patient patient) {
        EmergencyContact ec = patient.getEmergencyContact();
        EmergencyContactDto ecDto = null;
        if (ec != null) {
            ecDto = new EmergencyContactDto(
                    ec.getFirstName(),
                    ec.getLastName(),
                    ec.getRelationship(),
                    ec.getPhoneNumber()
            );
        }

        InsurancePolicy ip = patient.getInsurancePolicy();
        InsurancePolicyDto ipDto = null;
        if (ip != null) {
            ipDto = new InsurancePolicyDto(
                    ip.getCompanyName(),
                    ip.getPolicyNumber(),
                    ip.isActive(),
                    ip.getEndDate()
            );
        }

        return new PatientResponse(
                patient.getIdNumber(),
                patient.getFullName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                ecDto,
                ipDto
        );
    }

    public record PatientRequest(
            String idNumber,
            String fullName,
            LocalDate birthDate,
            Gender gender,
            String address,
            String phoneNumber,
            String email,
            EmergencyContactDto emergencyContact,
            InsurancePolicyDto insurancePolicy
    ) { }

    public record EmergencyContactDto(
            String firstName,
            String lastName,
            String relationship,
            String phoneNumber
    ) { }

    public record InsurancePolicyDto(
            String companyName,
            String policyNumber,
            boolean active,
            LocalDate endDate
    ) { }

    public record PatientResponse(
            String idNumber,
            String fullName,
            LocalDate birthDate,
            Gender gender,
            String address,
            String phoneNumber,
            String email,
            EmergencyContactDto emergencyContact,
            InsurancePolicyDto insurancePolicy
    ) { }
}
