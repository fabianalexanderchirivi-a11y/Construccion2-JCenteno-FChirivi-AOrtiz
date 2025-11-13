package co.edu.tdea.clinicapp.application.usecase;

import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsCommand;
import co.edu.tdea.clinicapp.application.port.in.RecordVitalSignsUseCase;
import co.edu.tdea.clinicapp.domain.model.Role;
import co.edu.tdea.clinicapp.domain.model.VitalSignsRecord;
import co.edu.tdea.clinicapp.domain.repository.PatientRepository;
import co.edu.tdea.clinicapp.domain.repository.UserRepository;
import co.edu.tdea.clinicapp.domain.repository.VitalSignsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class RecordVitalSignsService implements RecordVitalSignsUseCase {

    private final VitalSignsRepository vitalSignsRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public RecordVitalSignsService(VitalSignsRepository vitalSignsRepository,
                                   PatientRepository patientRepository,
                                   UserRepository userRepository) {
        this.vitalSignsRepository = vitalSignsRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VitalSignsRecord record(RecordVitalSignsCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es necesario.");
        if (!patientRepository.existsByIdNumber(cmd.getPatientIdNumber()))
            throw new IllegalArgumentException("Paciente no encontrado.");

        var nurse = userRepository.findByIdNumber(cmd.getNurseIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Enfermera no encontrada."));
        if (nurse.getRole() != Role.NURSE)
            throw new IllegalArgumentException("El usuario no es una enfermera.");

        var rec = new VitalSignsRecord(
                cmd.getRecordedAt(),
                cmd.getNurseIdNumber(),
                cmd.getSystolic(),
                cmd.getDiastolic(),
                cmd.getTemperature(),
                cmd.getPulse(),
                cmd.getOxygen()
        );
        vitalSignsRepository.add(cmd.getPatientIdNumber(), rec);
        return rec;
    }
}
