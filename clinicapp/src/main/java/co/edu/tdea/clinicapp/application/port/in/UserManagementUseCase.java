package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.User;
import java.util.Optional;

public interface UserManagementUseCase {

    void createUser(User user);

    void deleteUser(String idNumber);

    Optional<User> getUserByIdNumber(String idNumber);
}
