package app.app.domain.services;

import app.app.domain.model.User;

public class UpdateUser {
	public User execute(User user) throws Exception {

        if (user == null || user.getId() == 0) {
            throw new Exception("Debe proporcionar un usuario existente con un ID válido");
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new Exception("El nombre del usuario es obligatorio");
        }

        if (user.getRole() == null ) {
            throw new Exception("Debe asignar un rol válido al usuario");
        }

        return user;
    }
}
