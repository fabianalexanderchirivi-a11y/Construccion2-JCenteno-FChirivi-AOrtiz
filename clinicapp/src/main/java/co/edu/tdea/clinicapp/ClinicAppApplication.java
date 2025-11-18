package co.edu.tdea.clinicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import co.edu.tdea.clinicapp.config.JwtProperties;

@SpringBootApplication
@ComponentScan(
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "co\\.edu\\.tdea\\.clinicapp\\.application\\.service\\..*"),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "co\\.edu\\.tdea\\.clinicapp\\.adapter\\.in\\.security\\.SecurityConfig"),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "co\\.edu\\.tdea\\.clinicapp\\.adapter\\.out\\.security\\.AuthBeansConfig"),
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = "co\\.edu\\.tdea\\.clinicapp\\.adapter\\.out\\.persistence\\.jpa\\.clinical\\..*")
        })
@EnableConfigurationProperties(JwtProperties.class)
public class ClinicAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicAppApplication.class, args);
    }
}
