package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.patient;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patients",
       indexes = @Index(name = "uk_patient_document", columnList = "document", unique = true))
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 15)
    private String document;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(length = 120)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 120)
    private String address;

    @Column(length = 1)
    private String gender;

    public Integer getId() { return id; }
    public String getDocument() { return document; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }

    public void setId(Integer id) { this.id = id; }
    public void setDocument(String document) { this.document = document; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setAddress(String address) { this.address = address; }
    public void setGender(String gender) { this.gender = gender; }
}