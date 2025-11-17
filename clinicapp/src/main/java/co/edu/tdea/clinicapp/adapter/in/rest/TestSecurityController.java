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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/ping")
    public ResponseEntity<String> adminPing() { return ResponseEntity.ok("admin"); }
}
