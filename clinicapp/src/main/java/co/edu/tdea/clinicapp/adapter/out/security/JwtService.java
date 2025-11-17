package co.edu.tdea.clinicapp.adapter.out.security;

import co.edu.tdea.clinicapp.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final JwtProperties props;
    private volatile Instant lastExpiresAt;

    public JwtService(JwtProperties props) {
        this.props = props;
    }

    public String generate(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getTtlSeconds());
        Key key = Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .setIssuer(props.getIssuer())
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key)
                .compact();

        this.lastExpiresAt = exp;
        return token;
    }

    public Instant getExpiresAt() {
        return lastExpiresAt;
    }
}

