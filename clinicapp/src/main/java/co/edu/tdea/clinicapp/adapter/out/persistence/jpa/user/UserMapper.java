package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.user;

import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User d) {
        var e = new UserEntity();
        e.setDocument(d.getIdNumber());
        e.setFullName(d.getFullName());
        e.setEmail(d.getEmail());
        e.setPhone(d.getPhoneNumber());
        e.setBirthDate(d.getBirthDate());
        e.setAddress(d.getAddress());
        e.setRole(d.getRole().name());
        return e;
    }

    public User toDomain(UserEntity e) {
        return new User(
                e.getDocument(),
                e.getFullName(),
                e.getEmail(),
                e.getPhone(),
                e.getBirthDate(),
                e.getAddress(),
                Role.valueOf(e.getRole())
        );
    }
}
