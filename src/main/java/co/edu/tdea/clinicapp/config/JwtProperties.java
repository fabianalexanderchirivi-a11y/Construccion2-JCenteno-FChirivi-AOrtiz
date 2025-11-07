package co.edu.tdea.clinicapp.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  @NotBlank private String secret;
  @NotBlank private String issuer;
  @Positive private long ttlSeconds;

  public String getSecret() { return secret; }
  public void setSecret(String secret) { this.secret = secret; }
  public String getIssuer() { return issuer; }
  public void setIssuer(String issuer) { this.issuer = issuer; }
  public long getTtlSeconds() { return ttlSeconds; }
  public void setTtlSeconds(long ttlSeconds) { this.ttlSeconds = ttlSeconds; }
}
