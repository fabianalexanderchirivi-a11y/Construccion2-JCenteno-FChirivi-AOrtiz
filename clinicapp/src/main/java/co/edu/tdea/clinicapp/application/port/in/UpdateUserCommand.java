package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDate;
import co.edu.tdea.clinicapp.domain.model.Role;

public class UpdateUserCommand {
    private final String idNumber;    
    private final String fullName;     
    private final String email;        
    private final String phoneNumber;  
    private final LocalDate birthDate; 
    private final String address;     
    private final Role role;          

    public UpdateUserCommand(String idNumber, String fullName, String email,
                             String phoneNumber, LocalDate birthDate,
                             String address, Role role) {
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.address = address;
        this.role = role;
    }

    public String getIdNumber()     { return idNumber; }
    public String getFullName()     { return fullName; }
    public String getEmail()        { return email; }
    public String getPhoneNumber()  { return phoneNumber; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getAddress()      { return address; }
    public Role getRole()           { return role; }
}

