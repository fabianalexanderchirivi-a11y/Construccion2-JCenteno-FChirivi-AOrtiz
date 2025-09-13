package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.RegisterUserCommand;
import co.edu.tdea.clinicapp.application.port.in.RegisterUserUseCase;
import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterUserCommand cmd) {
        checkUniqueness(cmd);
        User user = new User(
                cmd.getIdNumber(),
                cmd.getFullName(),
                cmd.getEmail(),
                cmd.getPhoneNumber(),
                cmd.getBirthDate(),
                cmd.getAddress(),
                cmd.getRole()
        );
        return userRepository.save(user);
    }

    private void checkUniqueness(RegisterUserCommand cmd) {
        if (userRepository.existsByIdNumber(cmd.getIdNumber())) {
            throw new IllegalArgumentException("El numero de ID ya existe.");
        }
        if (userRepository.existsByEmail(cmd.getEmail())) {
            throw new IllegalArgumentException("El correo ya existe.");
        }
    }
}
