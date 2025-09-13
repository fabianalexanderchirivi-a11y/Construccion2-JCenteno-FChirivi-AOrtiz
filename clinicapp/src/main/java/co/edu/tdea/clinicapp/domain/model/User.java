package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private static final String EMAIL_RX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    private static final String PHONE10_RX = "\\d{10}";
    private static final String ID_1_10_RX = "\\d{1,10}";

    private String idNumber;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;
    private Role role;

    public User(String idNumber, String fullName, String email, String phoneNumber, LocalDate birthDate, String address, Role role) {
        setIdNumber(idNumber);
        setFullName(fullName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setAddress(address);
        setRole(role);
    }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) {
        if (idNumber == null || !idNumber.matches(ID_1_10_RX)) throw new IllegalArgumentException("La cédula debe tener entre 1 y 10 dígitos.");
        this.idNumber = idNumber;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Nombre completo inválido.");
        this.fullName = fullName.trim();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.matches(EMAIL_RX)) throw new IllegalArgumentException("Correo electrónico inválido.");
        this.email = email.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches(PHONE10_RX)) throw new IllegalArgumentException("El número de teléfono debe tener 10 dígitos.");
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) throw new IllegalArgumentException("Fecha de nacimiento inválida.");
        int years = Period.between(birthDate, LocalDate.now()).getYears();
        if (years > 150) throw new IllegalArgumentException("La edad no puede superar 150 años.");
        this.birthDate = birthDate;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Dirección inválida.");
        this.address = address.trim();
    }

    public Role getRole() { return role; }
    public void setRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Rol inválido.");
        this.role = role;
    }
}
