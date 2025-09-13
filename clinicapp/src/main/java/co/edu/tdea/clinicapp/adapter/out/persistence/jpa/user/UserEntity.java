package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.user;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document", nullable = false, unique = true, length = 15)
    private String document;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address", length = 120)
    private String address;

    @Column(name = "role", nullable = false, length = 30)
    private String role;

    public Integer getId() { return id; }
    public String getDocument() { return document; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getAddress() { return address; }
    public String getRole() { return role; }

    public void setId(Integer id) { this.id = id; }
    public void setDocument(String document) { this.document = document; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setAddress(String address) { this.address = address; }
    public void setRole(String role) { this.role = role; }
}