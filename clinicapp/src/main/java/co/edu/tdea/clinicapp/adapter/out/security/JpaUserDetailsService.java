package co.edu.tdea.clinicapp.adapter.out.security;

import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.AccountEntity;
import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.SpringDataAccountRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service("jpaUserDetailsService")
@Primary
public class JpaUserDetailsService implements UserDetailsService {

    private final SpringDataAccountRepository repository;

    public JpaUserDetailsService(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity acc = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String role = mapRole(acc.getRole()); // ADMINISTRATIVE, HUMAN_RESOURCES, DOCTOR, NURSE, SUPPORT

        return User.withUsername(acc.getUsername())
                // IMPORTANTE: usar el hash que trae la entidad
                .password(acc.getPasswordHash())   // Debe estar en BCrypt
                .roles(role)                       // genera autoridad ROLE_{role}
                .build();
    }

    private String mapRole(String value) {
        if (value == null) throw new UsernameNotFoundException("Usuario sin rol asignado");
        String r = value.trim().toUpperCase();
        return switch (r) {
            case "ADMIN", "ADMIN_STAFF", "ADMINISTRATIVO", "ADMINISTRATIVE" -> "ADMINISTRATIVE";
            case "HUMAN_RESOURCES", "HR", "RECURSOS_HUMANOS"               -> "HUMAN_RESOURCES";
            case "MEDICAL", "MEDICO", "DOCTOR"                             -> "DOCTOR";
            case "NURSE", "ENFERMERA", "ENFERMERO"                         -> "NURSE";
            case "SUPPORT", "SOPORTE", "SUPPORT_INFO"                      -> "SUPPORT";
            default -> r;
        };
    }
}
