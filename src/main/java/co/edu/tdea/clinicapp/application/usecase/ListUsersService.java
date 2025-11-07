package co.edu.tdea.clinicapp.application.usecase;

import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.ListUsersUseCase;
import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ListUsersService implements ListUsersUseCase {

    private final UserRepository userRepository;

    public ListUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}
