package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.ListMedicationsUseCase;
import co.edu.tdea.clinicapp.domain.model.Medication;
import co.edu.tdea.clinicapp.domain.repository.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ListMedicationsService implements ListMedicationsUseCase {

    private final MedicationRepository medicationRepository;

    public ListMedicationsService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> list() {
        return medicationRepository.findAll();
    }
}
