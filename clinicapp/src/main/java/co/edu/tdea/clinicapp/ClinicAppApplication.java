package co.edu.tdea.clinicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
@EnableJpaRepositories(basePackages = "co.edu.tdea.clinicapp.adapter.out.persistence.jpa",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = "co\\.edu\\.tdea\\.clinicapp\\.adapter\\.out\\.persistence\\.jpa\\.clinical\\..*"))
@org.springframework.boot.autoconfigure.domain.EntityScan(basePackages = {
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.auth",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.appointment",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.billing",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.catalog",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.clinicalhistory",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.diagnostic",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.history",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.medication",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.nursing",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.patient",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.procedure",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.user",
        "co.edu.tdea.clinicapp.adapter.out.persistence.jpa.vitals"
})
@EnableConfigurationProperties(JwtProperties.class)
public class ClinicAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicAppApplication.class, args);
    }
}
