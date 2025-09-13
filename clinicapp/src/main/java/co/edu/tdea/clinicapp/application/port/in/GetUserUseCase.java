package co.edu.tdea.clinicapp.application.port.in;

import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.User;

public interface GetUserUseCase {
    Optional<User> byIdNumber(String idNumber);
}
