package co.edu.tdea.clinicapp.domain.model;

public class EmergencyContact {
    private static final String PHONE10_RX = "\\d{10}";

    private String firstName;
    private String lastName;
    private String relationship;
    private String phoneNumber;

    public EmergencyContact(String firstName, String lastName, String relationship, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setRelationship(relationship);
        setPhoneNumber(phoneNumber);
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("Nombre del contacto inválido.");
        this.firstName = firstName.trim();
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Apellido del contacto inválido.");
        this.lastName = lastName.trim();
    }

    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) {
        if (relationship == null || relationship.isBlank()) throw new IllegalArgumentException("Relación inválida.");
        this.relationship = relationship.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches(PHONE10_RX))
            throw new IllegalArgumentException("El teléfono de emergencia debe tener 10 dígitos.");
        this.phoneNumber = phoneNumber;
    }
}
