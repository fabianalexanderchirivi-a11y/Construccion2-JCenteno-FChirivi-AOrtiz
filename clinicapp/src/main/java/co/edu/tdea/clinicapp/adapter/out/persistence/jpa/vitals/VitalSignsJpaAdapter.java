package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.vitals;

import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import co.edu.tdea.clinicapp.domain.repository.VitalSignsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VitalSignsJpaAdapter implements VitalSignsRepository {

    private final SpringDataVitalSignsRepository repo;

    public VitalSignsJpaAdapter(SpringDataVitalSignsRepository repo) {
        this.repo = repo;
    }

    @Override
    public void add(String patientIdNumber, VitalSignsRecord r) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("patientIdNumber es requerido");
        if (r == null) throw new IllegalArgumentException("record es requerido");

        VitalSignsEntity e = new VitalSignsEntity();
        e.setPatientIdNumber(patientIdNumber.trim());
        e.setNurseIdNumber(r.getNurseIdNumber());
        e.setRecordedAt(r.getRecordedAt());
        e.setSystolic(r.getSystolic());
        e.setDiastolic(r.getDiastolic());
        e.setTemperature(r.getTemperature());
        e.setPulse(r.getPulse());
        e.setOxygen(r.getOxygen());

        repo.save(e); // <- sin retorno, para que coincida con la interfaz
    }

    @Override
    public List<VitalSignsRecord> findAllByPatient(String patientIdNumber) {
        if (patientIdNumber == null || patientIdNumber.isBlank())
            throw new IllegalArgumentException("patientIdNumber es requerido");

        return repo.findAllByPatientIdNumberOrderByRecordedAtAsc(patientIdNumber)
                .stream()
                .map(e -> new VitalSignsRecord(
                        e.getRecordedAt(),
                        e.getNurseIdNumber(),
                        e.getSystolic(),
                        e.getDiastolic(),
                        e.getTemperature(),
                        e.getPulse(),
                        e.getOxygen()
                ))
                .toList();
    }
}
