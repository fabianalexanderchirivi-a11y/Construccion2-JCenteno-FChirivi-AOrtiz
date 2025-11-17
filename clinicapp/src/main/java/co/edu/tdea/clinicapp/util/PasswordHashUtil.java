package co.edu.tdea.clinicapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashUtil {
    public static void main(String[] args) {
        var enc = new BCryptPasswordEncoder();
        System.out.println("admin   : " + enc.encode("Admin123!"));
        System.out.println("hr1     : " + enc.encode("Hr123$%a"));
        System.out.println("doc1    : " + enc.encode("Doc123$%a"));
        System.out.println("nurse1  : " + enc.encode("Nur123$%a"));
        System.out.println("sup1    : " + enc.encode("Sup123$%a"));
    }
}
