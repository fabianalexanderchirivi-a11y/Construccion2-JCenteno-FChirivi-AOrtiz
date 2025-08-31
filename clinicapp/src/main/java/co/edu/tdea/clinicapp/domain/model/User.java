package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;
import java.time.Period;

public class User {

    private String idNumber;
    private String fullName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;
    private Role role;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.matches("\\d{1,10}")) {
            throw new IllegalArgumentException("El teléfono debe tener entre 1 y 10 dígitos");
        }
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate != null) {
            int years = Period.between(birthDate, LocalDate.now()).getYears();
            if (years > 150) {
                throw new IllegalArgumentException("La fecha de nacimiento implica una edad mayor a 150 años");
            }
        }
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.length() > 30) {
            throw new IllegalArgumentException("La dirección no puede superar 30 caracteres");
        }
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
