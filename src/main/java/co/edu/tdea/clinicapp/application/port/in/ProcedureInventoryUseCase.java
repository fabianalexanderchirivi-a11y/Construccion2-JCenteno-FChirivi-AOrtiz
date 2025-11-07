package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.command.ProcedureUpsertCommand;
import co.edu.tdea.clinicapp.application.dto.InventoryItemDto;
import java.util.List;

public interface ProcedureInventoryUseCase {
    InventoryItemDto create(ProcedureUpsertCommand cmd);
    InventoryItemDto update(ProcedureUpsertCommand cmd);
    void delete(String id);
    List<InventoryItemDto> list();
}
