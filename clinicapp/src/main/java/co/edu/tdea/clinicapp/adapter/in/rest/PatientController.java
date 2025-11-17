package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.GetPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListPatientsUseCase;
import co.edu.tdea.clinicapp.application.port.in.RegisterPatientCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpdatePatientCommand;
import co.edu.tdea.clinicapp.application.port.in.UpdatePatientUseCase;
import co.edu.tdea.clinicapp.domain.model.Gender;
import co.edu.tdea.clinicapp.domain.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients")
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


    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    @PostMapping
    public ResponseEntity<Patient> register(@RequestBody CreatePatientRequest req) {
        Patient p = registerPatientUseCase.register(new RegisterPatientCommand(
                req.idNumber,
                req.fullName,
                req.birthDate,
                req.gender,
                req.address,
                req.phoneNumber,
                req.email,
                req.ecFirstName,
                req.ecLastName,
                req.ecRelationship,
                req.ecPhoneNumber,
                req.insuranceCompanyName,
                req.insurancePolicyNumber,
                req.insuranceActive,
                req.insuranceEndDate
        ));
        return ResponseEntity.ok(p);
    }


    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    @PutMapping("/{idNumber}")
    public ResponseEntity<Patient> update(@PathVariable String idNumber, @RequestBody UpdatePatientRequest req) {
        Patient p = updatePatientUseCase.update(new UpdatePatientCommand(
                idNumber,
                req.fullName,
                req.birthDate,
                req.gender,
                req.address,
                req.phoneNumber,
                req.email,
                req.ecFirstName,
                req.ecLastName,
                req.ecRelationship,
                req.ecPhoneNumber,
                req.insuranceCompanyName,
                req.insurancePolicyNumber,
                req.insuranceActive,
                req.insuranceEndDate
        ));
        return ResponseEntity.ok(p);
    }


    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','DOCTOR','NURSING')")
    @GetMapping("/{idNumber}")
    public ResponseEntity<Patient> get(@PathVariable String patientId) {
        return getPatientUseCase.byIdNumber(patientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasAnyRole('ADMINISTRATIVE','DOCTOR','NURSING')")
    @GetMapping
    public List<Patient> list() {
        return listPatientsUseCase.list();
    }


    public static class CreatePatientRequest {
        public String idNumber;
        public String fullName;
        public LocalDate birthDate;
        public Gender gender;
        public String address;
        public String phoneNumber;
        public String email;
        public String ecFirstName;
        public String ecLastName;
        public String ecRelationship;
        public String ecPhoneNumber;
        public String insuranceCompanyName;
        public String insurancePolicyNumber;
        public boolean insuranceActive;
        public LocalDate insuranceEndDate;
    }

    public static class UpdatePatientRequest {
        public String fullName;
        public LocalDate birthDate;
        public Gender gender;
        public String address;
        public String phoneNumber;
        public String email;
        public String ecFirstName;
        public String ecLastName;
        public String ecRelationship;
        public String ecPhoneNumber;
        public String insuranceCompanyName;
        public String insurancePolicyNumber;
        public Boolean insuranceActive;
        public LocalDate insuranceEndDate;
    }
}
