package co.edu.tdea.clinicapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "Clinic123!";
        String hash = encoder.encode(rawPassword);
        System.out.println("Password hash: " + hash);
    }
}
