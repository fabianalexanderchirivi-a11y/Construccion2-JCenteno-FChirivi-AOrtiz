package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.DeleteUserUseCase;
import co.edu.tdea.clinicapp.application.port.in.GetUserUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListUsersUseCase;
import co.edu.tdea.clinicapp.application.port.in.RegisterUserCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterUserUseCase;
import co.edu.tdea.clinicapp.application.port.in.UpdateUserCommand;
import co.edu.tdea.clinicapp.application.port.in.UpdateUserUseCase;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('HUMAN_RESOURCES')")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          GetUserUseCase getUserUseCase,
                          ListUsersUseCase listUsersUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          UpdateUserUseCase updateUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody CreateUserRequest req) {
        User user = registerUserUseCase.register(new RegisterUserCommand(
                req.idNumber(),
                req.fullName(),
                req.email(),
                req.phoneNumber(),
                req.birthDate(),
                req.address(),
                req.role()
        ));
        URI location = URI.create("/api/users/" + user.getIdNumber());
        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("/{idNumber}")
    public ResponseEntity<User> get(@PathVariable String idNumber) {
        return getUserUseCase.byIdNumber(idNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        List<User> users = listUsersUseCase.list();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{idNumber}")
    public ResponseEntity<User> update(@PathVariable String idNumber, @RequestBody UpdateUserRequest req) {
        User updated = updateUserUseCase.update(new UpdateUserCommand(
                idNumber,
                req.fullName(),
                req.email(),
                req.phoneNumber(),
                req.birthDate(),
                req.address(),
                req.role()
        ));
        return ResponseEntity.ok(updated);
    }

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

    public record UpdateUserRequest(
            String fullName,
            String email,
            String phoneNumber,
            LocalDate birthDate,
            String address,
            Role role
    ) { }
}
