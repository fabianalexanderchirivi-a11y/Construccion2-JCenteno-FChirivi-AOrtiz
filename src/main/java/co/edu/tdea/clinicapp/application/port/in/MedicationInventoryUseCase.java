package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.command.MedicationUpsertCommand;
import co.edu.tdea.clinicapp.application.dto.InventoryItemDto;
import java.util.List;

public interface MedicationInventoryUseCase {
    InventoryItemDto create(MedicationUpsertCommand cmd);
    InventoryItemDto update(MedicationUpsertCommand cmd);
    void delete(String id);
    List<InventoryItemDto> list();
}
