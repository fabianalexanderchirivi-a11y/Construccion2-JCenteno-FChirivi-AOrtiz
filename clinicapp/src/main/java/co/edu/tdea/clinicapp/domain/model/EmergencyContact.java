package co.edu.tdea.clinicapp.domain.model;

public class EmergencyContact {

    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("El teléfono de emergencia debe tener 10 dígitos");
        }
        this.phoneNumber = phoneNumber;
    }
}
