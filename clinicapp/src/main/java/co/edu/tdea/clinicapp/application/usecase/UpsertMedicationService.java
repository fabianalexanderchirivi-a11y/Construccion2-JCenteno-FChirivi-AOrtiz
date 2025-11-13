package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.UpsertMedicationCommand;
import co.edu.tdea.clinicapp.application.port.in.UpsertMedicationUseCase;
import co.edu.tdea.clinicapp.domain.model.Medication;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpsertMedicationService implements UpsertMedicationUseCase {

    private final MedicationRepository medicationRepository;

    public UpsertMedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication upsert(UpsertMedicationCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");

        if (medicationRepository.existsById(cmd.getId())) {
            var m = medicationRepository.findById(cmd.getId())
                    .orElseThrow(() -> new IllegalStateException("Medicamento no encontrado."));
            m.setName(cmd.getName());
            m.setUnitCost(cmd.getUnitCost());
            return medicationRepository.save(m);
        }
        var m = new Medication(cmd.getId(), cmd.getName(), cmd.getUnitCost());
        return medicationRepository.save(m);
    }
}
