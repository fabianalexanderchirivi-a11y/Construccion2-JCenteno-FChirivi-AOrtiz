package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.domain.model.User;

public interface UpdateUserUseCase {
    User update(UpdateUserCommand command);
}

