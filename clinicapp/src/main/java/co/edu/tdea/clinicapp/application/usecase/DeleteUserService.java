package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.DeleteUserUseCase;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;

public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteByIdNumber(String idNumber) {
        if (idNumber == null || idNumber.isBlank()) {
            throw new IllegalArgumentException("El numero de la ID es requerido.");
        }
        if (!userRepository.existsByIdNumber(idNumber)) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
        userRepository.deleteByIdNumber(idNumber);
    }
}
