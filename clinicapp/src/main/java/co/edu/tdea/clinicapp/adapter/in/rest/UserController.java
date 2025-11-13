package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.DeleteUserUseCase;
import co.edu.tdea.clinicapp.application.port.in.GetUserUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListUsersUseCase;
import co.edu.tdea.clinicapp.application.port.in.RegisterUserCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterUserUseCase;
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

    @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    @PostMapping
    public ResponseEntity<User> register(@RequestBody CreateUserRequest req) {
        User u = registerUserUseCase.register(new RegisterUserCommand(
                req.idNumber(),
                req.fullName(),
                req.email(),
                req.phoneNumber(),
                req.birthDate(),
                req.address(),
                req.role()
        ));
        return ResponseEntity.ok(u);
    }

    @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    @GetMapping("/{idNumber}")
    public ResponseEntity<User> get(@PathVariable String idNumber) {
        return getUserUseCase.byIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('HUMAN_RESOURCES')")
    @GetMapping
    public List<User> list() {
        return listUsersUseCase.list();
    }

    @PreAuthorize("hasRole('HUMAN_RESOURCES')")
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
