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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    public ResponseEntity<User> register(@Valid @RequestBody CreateUserRequest req) {
        User user = registerUserUseCase.register(new RegisterUserCommand(
                trim(req.document()),
                trim(req.fullName()),
                trim(req.email()),
                trim(req.phone()),
                req.birthDate(),
                trim(req.address()),
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
    public ResponseEntity<User> update(@PathVariable String idNumber, @Valid @RequestBody UpdateUserRequest req) {
        User updated = updateUserUseCase.update(new UpdateUserCommand(
                idNumber,
                trim(req.fullName()),
                trim(req.email()),
                trim(req.phone()),
                req.birthDate(),
                trim(req.address()),
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
            @NotBlank(message = "El documento es obligatorio")
            @Pattern(regexp = "\\d{1,10}", message = "La cédula debe tener entre 1 y 10 dígitos.")
            String document,

            @NotBlank(message = "El nombre es obligatorio")
            String fullName,

            @NotBlank(message = "El correo es obligatorio")
            @Email(message = "Correo electrónico inválido.")
            String email,

            @NotBlank(message = "El teléfono es obligatorio")
            @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos.")
            String phone,

            @NotNull(message = "La fecha de nacimiento es obligatoria")
            LocalDate birthDate,

            @NotBlank(message = "La dirección es obligatoria")
            String address,

            @NotNull(message = "El rol es obligatorio")
            Role role
    ) { }

    public record UpdateUserRequest(
            @NotBlank(message = "El nombre es obligatorio")
            String fullName,

            @NotBlank(message = "El correo es obligatorio")
            @Email(message = "Correo electrónico inválido.")
            String email,

            @NotBlank(message = "El teléfono es obligatorio")
            @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos.")
            String phone,

            @NotNull(message = "La fecha de nacimiento es obligatoria")
            LocalDate birthDate,

            @NotBlank(message = "La dirección es obligatoria")
            String address,

            @NotNull(message = "El rol es obligatorio")
            Role role
    ) { }

    private String trim(String value) {
        return value == null ? null : value.trim();
    }
}
