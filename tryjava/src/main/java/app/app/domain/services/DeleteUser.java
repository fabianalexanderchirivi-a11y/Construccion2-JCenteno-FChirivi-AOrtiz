package app.app.domain.services;

import app.app.domain.model.User;

public class DeleteUser {
	public void execute(User user) throws Exception {

        if (user == null || user.getId() == 0) {
            throw new Exception("Debe proporcionar un usuario existente con un ID válido para eliminar");
        }
    }
}
