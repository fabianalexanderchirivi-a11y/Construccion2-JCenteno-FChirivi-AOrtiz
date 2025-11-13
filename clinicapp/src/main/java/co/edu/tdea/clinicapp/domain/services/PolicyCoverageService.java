package co.edu.tdea.clinicapp.domain.services;

import co.edu.tdea.clinicapp.domain.model.InsurancePolicy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class PolicyCoverageService {

    public record Coverage(boolean activeOnDate, long remainingDays, LocalDate endDate) {}

    public Coverage evaluate(InsurancePolicy policy, LocalDate onDate) {
        if (onDate == null) throw new IllegalArgumentException("Fecha inválida.");
        if (policy == null) return new Coverage(false, 0, null);

        boolean active = policy.isActive() && (policy.getEndDate() == null || !policy.getEndDate().isBefore(onDate));
        long remaining = 0;
        if (policy.getEndDate() != null) {
            remaining = Math.max(0, ChronoUnit.DAYS.between(onDate, policy.getEndDate()));
        }
        return new Coverage(active, remaining, policy.getEndDate());
    }
}
