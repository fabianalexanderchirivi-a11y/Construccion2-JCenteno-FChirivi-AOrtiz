package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.RecordDiagnosticResultCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordDiagnosticResultUseCase;
import co.edu.tdea.clinicapp.domain.model.ClinicalHistoryEntry;
import co.edu.tdea.clinicapp.domain.repository.ClinicalHistoryRepository;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import co.edu.tdea.clinicapp.domain.model.Role;

public class RecordDiagnosticResultService implements RecordDiagnosticResultUseCase {

    private final ClinicalHistoryRepository clinicalHistoryRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public RecordDiagnosticResultService(ClinicalHistoryRepository clinicalHistoryRepository,
                                         PatientRepository patientRepository,
                                         UserRepository userRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ClinicalHistoryEntry record(RecordDiagnosticResultCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");

        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber())) {
            throw new IllegalArgumentException("Paciente no encontrado.");
        }

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));
        if (doctor.getRole() != Role.DOCTOR) {
            throw new IllegalArgumentException("El usuario no es un doctor.");
        }

        var entry = new ClinicalHistoryEntry(
                cmd.getAttendedAt(),
                cmd.getDoctorIdNumber(),
                cmd.getReason(),
                cmd.getSymptoms(),
                cmd.getDiagnosis(),
                cmd.getDiagnosticAidOrderNumber(),
                cmd.getDiagnosticAidItemNumber(),
                cmd.getDiagnosticAidResult()
        );

        clinicalHistoryRepository.addEntry(cmd.getPatientIdNumber(), entry);
        return entry;
    }
}
