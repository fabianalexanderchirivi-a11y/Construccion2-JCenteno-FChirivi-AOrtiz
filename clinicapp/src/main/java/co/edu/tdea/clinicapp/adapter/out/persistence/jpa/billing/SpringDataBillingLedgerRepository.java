package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.billing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataBillingLedgerRepository extends JpaRepository<BillingLedgerEntryEntity, Long> {
    @Query("select coalesce(sum(e.amount),0) from BillingLedgerEntryEntity e where e.patientIdNumber = :patient and e.year = :year")
    int sumByPatientAndYear(@Param("patient") String patientIdNumber, @Param("year") int year);
}
