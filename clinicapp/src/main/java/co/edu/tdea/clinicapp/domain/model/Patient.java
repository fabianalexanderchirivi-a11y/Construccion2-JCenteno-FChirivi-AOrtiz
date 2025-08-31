package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {

    private String username;
    private String password;
    private String idNumber;
    private String fullName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String email;
    private EmergencyContact emergencyContact;
    private InsurancePolicy insurancePolicy;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && (!username.matches("[A-Za-z0-9]+") || username.length() > 15)) {
            throw new IllegalArgumentException("El usuario debe ser alfanumérico y tener máximo 15 caracteres");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}")) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres, 1 mayúscula, 1 número y 1 símbolo");
        }
        this.password = password;
    }

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 10 dígitos");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
