package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByDocument(String document);
    Optional<UserEntity> findByEmail(String email);
}
