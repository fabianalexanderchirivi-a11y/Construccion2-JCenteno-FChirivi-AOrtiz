package app.domain.services;

import app.domain.model.User;
import app.domain.ports.UserPort;

public class CreateUser {

    private UserPort userPort;

    public CreateUser(UserPort userPort) {
        this.userPort = userPort;
    }

    public void create(User user) throws Exception {
        if (user == null) {
            throw new Exception("El usuario no puede ser nulo");
        }
        userPort.save(user);
    }
}
