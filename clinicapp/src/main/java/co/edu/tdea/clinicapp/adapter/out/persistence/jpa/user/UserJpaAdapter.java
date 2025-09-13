package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.user;

import co.edu.tdea.clinicapp.domain.model.User;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJpaAdapter implements UserRepository {

    private final SpringDataUserRepository repository;
    private final UserMapper mapper;

    public UserJpaAdapter(SpringDataUserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByIdNumber(String idNumber) {
        return repository.existsByDocument(idNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByIdNumber(String idNumber) {
        return repository.findByDocument(idNumber).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        var saved = repository.save(toUpsert(user));
        return mapper.toDomain(saved);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteByIdNumber(String idNumber) {
        repository.findByDocument(idNumber).ifPresent(e -> repository.deleteById(e.getId()));
    }

    private UserEntity toUpsert(User user) {
        // si ya existe, preserva el id para update
        var existing = repository.findByDocument(user.getIdNumber()).orElse(null);
        var e = mapper.toEntity(user);
        if (existing != null) e.setId(existing.getId());
        return e;
    }
}