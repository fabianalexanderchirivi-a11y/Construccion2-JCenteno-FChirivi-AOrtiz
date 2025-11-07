package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.command.DiagnosticAidUpsertCommand;
import co.edu.tdea.clinicapp.application.dto.InventoryItemDto;
import java.util.List;

public interface DiagnosticAidInventoryUseCase {
    InventoryItemDto create(DiagnosticAidUpsertCommand cmd);
    InventoryItemDto update(DiagnosticAidUpsertCommand cmd);
    void delete(String id);
    List<InventoryItemDto> list();
}
