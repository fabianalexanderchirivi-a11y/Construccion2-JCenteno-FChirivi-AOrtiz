package co.edu.tdea.clinicapp.domain.repository;

import java.util.List;
import java.util.Optional;
import co.edu.tdea.clinicapp.domain.model.User;

public interface UserRepository {
    boolean existsByIdNumber(String idNumber);
    boolean existsByEmail(String email);

    Optional<User> findByIdNumber(String idNumber);

    User save(User user);

    List<User> findAll();
    
    void deleteByIdNumber(String idNumber);
}
