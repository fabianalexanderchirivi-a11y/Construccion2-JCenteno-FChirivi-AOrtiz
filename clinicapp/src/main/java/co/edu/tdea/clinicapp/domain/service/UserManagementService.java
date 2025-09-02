package co.edu.tdea.clinicapp.domain.service;

import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface UserManagementService {
    User create(User user);
    User update(User user);
    Optional<User> findByIdNumber(String idNumber);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findByRole(Role role);
    void deactivate(String idNumber);
}
