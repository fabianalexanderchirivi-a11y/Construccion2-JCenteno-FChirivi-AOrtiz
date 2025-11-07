package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.*;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          GetUserUseCase getUserUseCase,
                          ListUsersUseCase listUsersUseCase,
                          DeleteUserUseCase deleteUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> register(@RequestBody CreateUserRequest req) {
        User u = registerUserUseCase.register(new RegisterUserCommand(
                req.idNumber(), req.fullName(), req.email(), req.phoneNumber(),
                req.birthDate(), req.address(), req.role()
        ));
        return ResponseEntity.ok(u);
    }

    @PreAuthorize("@subjectSecurity.sameUser(#idNumber, authentication) or hasRole('ADMIN')")
    @GetMapping("/{idNumber}")
    public ResponseEntity<User> get(@PathVariable String idNumber) {
        return getUserUseCase.byIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> list() {
        return listUsersUseCase.list();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idNumber}")
    public ResponseEntity<Void> delete(@PathVariable String idNumber) {
        deleteUserUseCase.deleteByIdNumber(idNumber);
        return ResponseEntity.noContent().build();
    }

    public record CreateUserRequest(
            String idNumber,
            String fullName,
            String email,
            String phoneNumber,
            LocalDate birthDate,
            String address,
            Role role
    ) { }
}
