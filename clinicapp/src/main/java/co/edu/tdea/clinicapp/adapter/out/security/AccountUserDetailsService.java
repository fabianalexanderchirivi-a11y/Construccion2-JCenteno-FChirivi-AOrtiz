package co.edu.tdea.clinicapp.adapter.out.security;

import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.AccountEntity;
import co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth.SpringDataAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final SpringDataAccountRepository repository;

    public AccountUserDetailsService(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AccountEntity acc = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + acc.getRole());
        return new org.springframework.security.core.userdetails.User(
                acc.getUsername(),
                acc.getPasswordHash(),
                List.of(auth)
        );
    }
}
