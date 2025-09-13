package co.edu.tdea.clinicapp.application.usecase;

import java.util.Optional;

import co.edu.tdea.clinicapp.application.port.in.GetUserUseCase;
import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class GetUserService implements GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> byIdNumber(String idNumber) {
        if (idNumber == null || idNumber.isBlank()) {
            throw new IllegalArgumentException("El numero de la ID es requerido.");
        }
        return userRepository.findByIdNumber(idNumber);
    }
}
