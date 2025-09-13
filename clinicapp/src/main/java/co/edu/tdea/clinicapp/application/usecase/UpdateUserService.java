package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.UpdateUserCommand;
import co.edu.tdea.clinicapp.application.port.in.UpdateUserUseCase;
import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User update(UpdateUserCommand cmd) {
        if (cmd == null || cmd.getIdNumber() == null || cmd.getIdNumber().isBlank()) {
            throw new IllegalArgumentException("El numero de la ID es requerido.");
        }

        User user = userRepository.findByIdNumber(cmd.getIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        if (cmd.getFullName() != null)     user.setFullName(cmd.getFullName());
        if (cmd.getEmail() != null)        user.setEmail(cmd.getEmail());
        if (cmd.getPhoneNumber() != null)  user.setPhoneNumber(cmd.getPhoneNumber());
        if (cmd.getBirthDate() != null)    user.setBirthDate(cmd.getBirthDate());
        if (cmd.getAddress() != null)      user.setAddress(cmd.getAddress());
        if (cmd.getRole() != null)         user.setRole(cmd.getRole());

        return userRepository.save(user);
    }
}
