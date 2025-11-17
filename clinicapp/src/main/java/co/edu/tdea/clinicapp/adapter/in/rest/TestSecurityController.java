package co.edu.tdea.clinicapp.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    @GetMapping("/public/ping")
    public ResponseEntity<String> publicPing() { return ResponseEntity.ok("public"); }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/secure/ping")
    public ResponseEntity<String> securePing() { return ResponseEntity.ok("secure"); }

    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    @GetMapping("/admin/ping")
    public ResponseEntity<String> adminPing() { return ResponseEntity.ok("admin"); }

    @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    @GetMapping("/hr/ping")
    public ResponseEntity<String> hrPing() { return ResponseEntity.ok("hr"); }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping("/doctor/ping")
    public ResponseEntity<String> doctorPing() { return ResponseEntity.ok("doctor"); }

    @PreAuthorize("hasRole('NURSE')")
    @GetMapping("/nurse/ping")
    public ResponseEntity<String> nursePing() { return ResponseEntity.ok("nurse"); }

    @PreAuthorize("hasRole('SUPPORT')")
    @GetMapping("/support/ping")
    public ResponseEntity<String> supportPing() { return ResponseEntity.ok("support"); }

}
