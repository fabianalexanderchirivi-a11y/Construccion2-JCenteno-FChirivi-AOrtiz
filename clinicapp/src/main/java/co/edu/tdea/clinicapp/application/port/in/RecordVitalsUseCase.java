package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.command.RecordVitalsCommand;

public interface RecordVitalsUseCase {
    void record(RecordVitalsCommand cmd);
}
