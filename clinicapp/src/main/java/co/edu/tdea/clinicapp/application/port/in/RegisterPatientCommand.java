package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDate;

import co.edu.tdea.clinicapp.domain.model.Gender;

public class RegisterPatientCommand {
    private final String idNumber;
    private final String fullName;
    private final LocalDate birthDate;
    private final Gender gender;
    private final String address;
    private final String phoneNumber;
    private final String email;

    // Emergency contact
    private final String ecFirstName;
    private final String ecLastName;
    private final String ecRelationship;
    private final String ecPhoneNumber;

    // Insurance
    private final String insuranceCompanyName;
    private final String insurancePolicyNumber;
    private final boolean insuranceActive;
    private final LocalDate insuranceEndDate;

    public RegisterPatientCommand(String idNumber, String fullName, LocalDate birthDate, Gender gender,
                                  String address, String phoneNumber, String email,
                                  String ecFirstName, String ecLastName, String ecRelationship, String ecPhoneNumber,
                                  String insuranceCompanyName, String insurancePolicyNumber,
                                  boolean insuranceActive, LocalDate insuranceEndDate) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ecFirstName = ecFirstName;
        this.ecLastName = ecLastName;
        this.ecRelationship = ecRelationship;
        this.ecPhoneNumber = ecPhoneNumber;
        this.insuranceCompanyName = insuranceCompanyName;
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.insuranceActive = insuranceActive;
        this.insuranceEndDate = insuranceEndDate;
    }

    public String getIdNumber() { return idNumber; }
    public String getFullName() { return fullName; }
    public LocalDate getBirthDate() { return birthDate; }
    public Gender getGender() { return gender; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getEcFirstName() { return ecFirstName; }
    public String getEcLastName() { return ecLastName; }
    public String getEcRelationship() { return ecRelationship; }
    public String getEcPhoneNumber() { return ecPhoneNumber; }
    public String getInsuranceCompanyName() { return insuranceCompanyName; }
    public String getInsurancePolicyNumber() { return insurancePolicyNumber; }
    public boolean isInsuranceActive() { return insuranceActive; }
    public LocalDate getInsuranceEndDate() { return insuranceEndDate; }
}
