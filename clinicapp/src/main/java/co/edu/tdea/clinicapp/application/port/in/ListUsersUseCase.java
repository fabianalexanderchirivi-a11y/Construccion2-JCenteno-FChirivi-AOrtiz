package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.User;

public interface ListUsersUseCase {
    List<User> list();
}
