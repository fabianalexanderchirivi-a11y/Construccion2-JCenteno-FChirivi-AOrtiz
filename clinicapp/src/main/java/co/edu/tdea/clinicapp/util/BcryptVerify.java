package co.edu.tdea.clinicapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptVerify {
    public static void main(String[] args) {
        String plain = "enf123";
        String hash  = "$2a$10$h0ciYAgLleBlEiBaLw8tK.HSZpe8CwxWFm63RRelVJaTxAOuOwB4G";
        System.out.println(new BCryptPasswordEncoder().matches(plain, hash)); // debe imprimir true
    }
}
