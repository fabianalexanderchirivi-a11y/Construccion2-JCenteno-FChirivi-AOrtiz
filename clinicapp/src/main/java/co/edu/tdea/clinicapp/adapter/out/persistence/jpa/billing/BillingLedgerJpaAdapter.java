package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.billing;

import co.edu.tdea.clinicapp.domain.repository.BillingLedgerRepository;
import org.springframework.stereotype.Component;

@Component
public class BillingLedgerJpaAdapter implements BillingLedgerRepository {

    private final SpringDataBillingLedgerRepository repository;

    public BillingLedgerJpaAdapter(SpringDataBillingLedgerRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getYearlyCopayTotal(String patientIdNumber, int year) {
        return repository.sumByPatientAndYear(patientIdNumber, year);
    }

    @Override
    public void addCopay(String patientIdNumber, int year, int amount) {
        BillingLedgerEntryEntity entity = new BillingLedgerEntryEntity();
        entity.setPatientIdNumber(patientIdNumber);
        entity.setYear(year);
        entity.setAmount(amount);
        repository.save(entity);
    }
}
