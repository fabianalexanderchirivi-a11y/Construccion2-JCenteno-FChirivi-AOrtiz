package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.adapter.in.security.SubjectSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientVitalsController {

    @PreAuthorize("@subjectSecurity.sameUser(#patientId, authentication) or hasRole('ADMIN')")
    @GetMapping("/{patientId}/vitals")
    public ResponseEntity<?> getVitals(@PathVariable String patientId) {
        return ResponseEntity.ok("vitals for " + patientId);
    }
}
