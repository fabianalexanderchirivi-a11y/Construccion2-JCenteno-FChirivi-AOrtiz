package app.application.usecases;

import app.domain.model.User;
import app.domain.model.enu.Role;
import app.domain.services.CreateUser;

public class AdminUseCase {

    private CreateUser createUser;

    public AdminUseCase(CreateUser createUser) {
        this.createUser = createUser;
    }

    public boolean createVeterinarian(User user) throws Exception {
        user.setRole(Role.VETERINARIAN);
        createUser.create(user);
        return true;
    }

    public boolean createClient(User user) throws Exception {
        user.setRole(Role.CLIENT);
        createUser.create(user);
        return true;
    }
}
