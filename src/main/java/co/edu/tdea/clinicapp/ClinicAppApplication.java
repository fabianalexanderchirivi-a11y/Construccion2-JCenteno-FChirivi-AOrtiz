package co.edu.tdea.clinicapp;

import co.edu.tdea.clinicapp.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(co.edu.tdea.clinicapp.config.JwtProperties.class)
public class ClinicAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicAppApplication.class, args);
    }
}
