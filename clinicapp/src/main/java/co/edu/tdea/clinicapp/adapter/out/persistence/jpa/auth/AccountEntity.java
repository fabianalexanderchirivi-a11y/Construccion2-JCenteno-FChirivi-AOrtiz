package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts", indexes = @Index(name = "uk_accounts_username", columnList = "username", unique = true))
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(nullable = false, length = 30)
    private String role;

    @Column(name = "subject_type", nullable = false, length = 10)
    private String subjectType;

    @Column(name = "subject_document", nullable = false, length = 15)
    private String subjectDocument;

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public String getSubjectType() { return subjectType; }
    public String getSubjectDocument() { return subjectDocument; }

    public void setId(Integer id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    public void setSubjectType(String subjectType) { this.subjectType = subjectType; }
    public void setSubjectDocument(String subjectDocument) { this.subjectDocument = subjectDocument; }
}
