package co.edu.tdea.clinicapp.adapter.in.security;

import co.edu.tdea.clinicapp.config.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

// ⚠️ IMPORTANTE:
import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                 JwtAuthenticationConverter jwtAuthConverter) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            // Público / Auth / Docs
            .requestMatchers("/actuator/health", "/public/**", "/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

            // Seguros genéricos
            .requestMatchers("/secure/**").authenticated()

            // -------- Pacientes --------
            // CRUD solo Administrativo
            .requestMatchers(HttpMethod.POST,   "/patients/**").hasRole("ADMINISTRATIVE")
            .requestMatchers(HttpMethod.PUT,    "/patients/**").hasRole("ADMINISTRATIVE")
            .requestMatchers(HttpMethod.DELETE, "/patients/**").hasRole("ADMINISTRATIVE")
            // Lectura: Administrativo, Doctor, Enfermera (HR y SUPPORT no)
            .requestMatchers(HttpMethod.GET,    "/patients/**").hasAnyRole("ADMINISTRATIVE","DOCTOR","NURSE")

            // -------- Inventario (medicamentos/procedimientos/ayudas) --------
            // Soporte gestiona inventario
            .requestMatchers("/inventory/**").hasRole("SUPPORT")
            // Si necesitas lectura para DOCTOR/NURSE, destapa esto (No puedo confirmar esto):
            // .requestMatchers(HttpMethod.GET, "/inventory/**").hasAnyRole("SUPPORT","DOCTOR","NURSE")

            // -------- Signos vitales --------
            // Enfermera registra
            .requestMatchers(HttpMethod.POST, "/vital-signs/**").hasRole("NURSE")
            // Lectura: Doctor y Enfermera
            .requestMatchers(HttpMethod.GET,  "/vital-signs/**").hasAnyRole("DOCTOR","NURSE")

            // -------- Órdenes --------
            // Crea Doctor
            .requestMatchers(HttpMethod.POST,   "/orders/**").hasRole("DOCTOR")
            // Actualiza aplicación/estado: Enfermera (si aplica a tu diseño)
            .requestMatchers(HttpMethod.PUT,    "/orders/**").hasRole("NURSE")
            // Lectura: Doctor y Enfermera (Admin opcional para facturación; No puedo confirmar esto)
            .requestMatchers(HttpMethod.GET,    "/orders/**").hasAnyRole("DOCTOR","NURSE")
            // .requestMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("DOCTOR","NURSE","ADMINISTRATIVE")

            // -------- Historia clínica (NoSQL) --------
            // Full: Doctor
            .requestMatchers(HttpMethod.POST, "/history/**").hasRole("DOCTOR")
            .requestMatchers(HttpMethod.PUT,  "/history/**").hasRole("DOCTOR")
            // Lectura: Doctor y Enfermera
            .requestMatchers(HttpMethod.GET,  "/history/**").hasAnyRole("DOCTOR","NURSE")

            // -------- HR (recursos humanos) --------
            // Gestión de cuentas/empleados
            .requestMatchers("/hr/**").hasRole("HUMAN_RESOURCES")

            // -------- Admin (pings/ajustes internos) --------
            .requestMatchers("/admin/**").hasRole("ADMINISTRATIVE")

            // Cualquier otro endpoint seguro
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)))
        .build();
  }

  @Bean
  public JwtDecoder jwtDecoder(JwtProperties props) {
    SecretKey key = new SecretKeySpec(props.getSecret().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    return NimbusJwtDecoder.withSecretKey(key).build();
  }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter gac = new JwtGrantedAuthoritiesConverter();
    gac.setAuthoritiesClaimName("role"); // <- claim singular
    gac.setAuthorityPrefix("ROLE_");
    JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
    converter.setJwtGrantedAuthoritiesConverter(gac);
    return converter;
  }
}
