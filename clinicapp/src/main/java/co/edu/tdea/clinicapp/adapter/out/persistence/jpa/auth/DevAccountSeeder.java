package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile("dev")
public class DevAccountSeeder {

    private final SpringDataAccountRepository repository;
    private final PasswordEncoder encoder;

    public DevAccountSeeder(SpringDataAccountRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() {
        seed("rh1", "rh123", "HUMAN_RESOURCES", "USER", "900000001");
        seed("adm1", "adm123", "ADMINISTRATIVE", "USER", "900000002");
        seed("med1", "med123", "DOCTOR", "USER", "900000003");
        seed("enf1", "enf123", "NURSING", "USER", "900000004");
    }

    private void seed(String username, String raw, String role, String subjectType, String doc) {
        repository.findByUsername(username).orElseGet(() -> {
            AccountEntity e = new AccountEntity();
            e.setUsername(username);
            e.setPasswordHash(encoder.encode(raw));
            e.setRole(role);
            e.setSubjectType(subjectType);
            e.setSubjectDocument(doc);
            return repository.save(e);
        });
    }
}
