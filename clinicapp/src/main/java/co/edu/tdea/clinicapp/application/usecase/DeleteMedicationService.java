package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.DeleteMedicationUseCase;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteMedicationService implements DeleteMedicationUseCase {

    private final MedicationRepository medicationRepository;

    public DeleteMedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public void deleteById(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Id requerida");
        if (!medicationRepository.existsById(id)) throw new IllegalArgumentException("Medicamento no encontrado.");
        medicationRepository.deleteById(id);
    }
}
