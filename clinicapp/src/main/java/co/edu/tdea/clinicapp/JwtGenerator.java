package co.edu.tdea.clinicapp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JwtGenerator {
    public static void main(String[] args) {
        String secret = "12345678901234567890123456789012_ABCDEF";
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Instant now = Instant.now();

        String adminToken = Jwts.builder()
                .setIssuer("tdea-clinicapp-auth")
                .setSubject("admin-001")
                .addClaims(Map.of("roles", "ADMIN"))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(3600)))
                .signWith(key)
                .compact();

        String patientToken = Jwts.builder()
                .setIssuer("tdea-clinicapp-auth")
                .setSubject("12345")             
                .addClaims(Map.of("roles", "USER"))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(3600)))
                .signWith(key)
                .compact();

        System.out.println("ADMIN TOKEN:\n" + adminToken + "\n");
        System.out.println("PATIENT TOKEN (sub=12345):\n" + patientToken + "\n");
    }
}
