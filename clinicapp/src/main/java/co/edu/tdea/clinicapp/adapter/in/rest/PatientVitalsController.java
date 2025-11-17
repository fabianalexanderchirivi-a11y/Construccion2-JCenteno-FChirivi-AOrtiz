package co.edu.tdea.clinicapp.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientVitalsController {

    @PreAuthorize("hasRole('NURSING')")
    @GetMapping("/patients/{patientId}/vitals")
    public ResponseEntity<?> getVitals(@PathVariable String patientId) {

        throw new UnsupportedOperationException("Implementado en tu código actual");
    }

    @PreAuthorize("hasRole('NURSING')")
    @PostMapping("/patients/{patientId}/vitals")
    public ResponseEntity<?> addVitals(@PathVariable String patientId, @RequestBody Object body) {

        throw new UnsupportedOperationException("Implementa llamando a tu caso de uso");
    }

    @PreAuthorize("hasRole('NURSING')")
    @PutMapping("/patients/{patientId}/vitals/{vitalId}")
    public ResponseEntity<?> updateVitals(@PathVariable String patientId, @PathVariable String vitalId, @RequestBody Object body) {

        throw new UnsupportedOperationException("Implementa llamando a tu caso de uso");
    }
}
