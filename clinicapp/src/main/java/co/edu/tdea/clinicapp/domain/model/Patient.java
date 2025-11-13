package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private static final String EMAIL_RX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    private static final String PHONE10_RX = "\\d{10}";
    private static final String ID_1_10_RX = "\\d{1,10}";

    private String idNumber;
    private String fullName;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private String email;
    private EmergencyContact emergencyContact;
    private InsurancePolicy insurancePolicy;

    public Patient(String idNumber, String fullName, LocalDate birthDate, Gender gender,
                   String address, String phoneNumber, String email,
                   EmergencyContact emergencyContact, InsurancePolicy insurancePolicy) {
        setIdNumber(idNumber);
        setFullName(fullName);
        setBirthDate(birthDate);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setEmergencyContact(emergencyContact);
        setInsurancePolicy(insurancePolicy);
    }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) {
        if (idNumber == null || !idNumber.matches(ID_1_10_RX))
            throw new IllegalArgumentException("La cédula debe tener entre 1 y 10 dígitos.");
        this.idNumber = idNumber;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Nombre completo requerido.");
        this.fullName = fullName.trim();
    }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) throw new IllegalArgumentException("Fecha de nacimiento requerida.");
        int years = Period.between(birthDate, LocalDate.now()).getYears();
        if (years > 150) throw new IllegalArgumentException("La edad debe ser ≤ 150 años.");
        this.birthDate = birthDate;
    }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) {
        if (gender == null) throw new IllegalArgumentException("Genero requerido.");
        this.gender = gender;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) {
        if (address == null || address.isBlank())
            throw new IllegalArgumentException("Direccion requerida.");
        this.address = address.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches(PHONE10_RX))
            throw new IllegalArgumentException("El numero de telefono debe ser de 10 digitos.");
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email != null && !email.isBlank() && !email.matches(EMAIL_RX))
            throw new IllegalArgumentException("Correo invalido");
        this.email = (email == null) ? null : email.trim();
    }

    public EmergencyContact getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(EmergencyContact emergencyContact) {
        if (emergencyContact == null) throw new IllegalArgumentException("Contacto de emergencia requerido.");
        this.emergencyContact = emergencyContact;
    }

    public InsurancePolicy getInsurancePolicy() { return insurancePolicy; }
    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        if (insurancePolicy == null) throw new IllegalArgumentException("Poliza requerida.");
        this.insurancePolicy = insurancePolicy;
    }
}
