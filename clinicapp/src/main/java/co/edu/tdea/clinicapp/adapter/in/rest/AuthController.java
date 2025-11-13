package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.AccountEntity;
import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.SpringDataAccountRepository;
import co.edu.tdea.clinicapp.adapter.out.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final SpringDataAccountRepository accountRepository;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          SpringDataAccountRepository accountRepository,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(auth);
        AccountEntity acc = accountRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String token = jwtService.generate(
                acc.getUsername(),
                Map.of("role", acc.getRole(), "idNumber", acc.getSubjectDocument())
        );

        return ResponseEntity.ok(new LoginResponse(
                token,
                acc.getRole(),
                acc.getSubjectDocument(),
                jwtService.getExpiresAt()
        ));
    }

    public record LoginRequest(String username, String password) { }

    public record LoginResponse(String token, String role, String idNumber, Instant expiresAt) { }
}
