package co.edu.tdea.clinicapp.adapter.in.rest;

import co.edu.tdea.clinicapp.application.port.in.CancelAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.CancelAppointmentUseCase;
import co.edu.tdea.clinicapp.application.port.in.CompleteAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.CompleteAppointmentUseCase;
import co.edu.tdea.clinicapp.application.port.in.GetAppointmentUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListAppointmentsByDoctorUseCase;
import co.edu.tdea.clinicapp.application.port.in.ListAppointmentsByPatientUseCase;
import co.edu.tdea.clinicapp.application.port.in.RescheduleAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.RescheduleAppointmentUseCase;
import co.edu.tdea.clinicapp.application.port.in.ScheduleAppointmentCommand;
import co.edu.tdea.clinicapp.application.port.in.ScheduleAppointmentUseCase;
import co.edu.tdea.clinicapp.domain.model.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@PreAuthorize("hasAnyRole('ADMINISTRATIVE','ADMIN_STAFF')")
public class AppointmentController {

    private final ScheduleAppointmentUseCase scheduleAppointmentUseCase;
    private final RescheduleAppointmentUseCase rescheduleAppointmentUseCase;
    private final CancelAppointmentUseCase cancelAppointmentUseCase;
    private final CompleteAppointmentUseCase completeAppointmentUseCase;
    private final ListAppointmentsByPatientUseCase listAppointmentsByPatientUseCase;
    private final ListAppointmentsByDoctorUseCase listAppointmentsByDoctorUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;

    public AppointmentController(ScheduleAppointmentUseCase scheduleAppointmentUseCase,
                                 RescheduleAppointmentUseCase rescheduleAppointmentUseCase,
                                 CancelAppointmentUseCase cancelAppointmentUseCase,
                                 CompleteAppointmentUseCase completeAppointmentUseCase,
                                 ListAppointmentsByPatientUseCase listAppointmentsByPatientUseCase,
                                 ListAppointmentsByDoctorUseCase listAppointmentsByDoctorUseCase,
                                 GetAppointmentUseCase getAppointmentUseCase) {
        this.scheduleAppointmentUseCase = scheduleAppointmentUseCase;
        this.rescheduleAppointmentUseCase = rescheduleAppointmentUseCase;
        this.cancelAppointmentUseCase = cancelAppointmentUseCase;
        this.completeAppointmentUseCase = completeAppointmentUseCase;
        this.listAppointmentsByPatientUseCase = listAppointmentsByPatientUseCase;
        this.listAppointmentsByDoctorUseCase = listAppointmentsByDoctorUseCase;
        this.getAppointmentUseCase = getAppointmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Appointment> schedule(@RequestBody AppointmentRequest request) {
        Appointment appointment = scheduleAppointmentUseCase.schedule(new ScheduleAppointmentCommand(
                request.appointmentId(),
                request.patientIdNumber(),
                request.doctorIdNumber(),
                request.appointmentDateTime(),
                request.reason()
        ));
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{appointmentId}/reschedule")
    public ResponseEntity<Appointment> reschedule(@PathVariable String appointmentId, @RequestBody RescheduleRequest request) {
        Appointment appointment = rescheduleAppointmentUseCase.reschedule(new RescheduleAppointmentCommand(
                appointmentId,
                request.newDateTime()
        ));
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String appointmentId) {
        cancelAppointmentUseCase.cancel(new CancelAppointmentCommand(appointmentId));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{appointmentId}/complete")
    public ResponseEntity<Void> complete(@PathVariable String appointmentId) {
        completeAppointmentUseCase.complete(new CompleteAppointmentCommand(appointmentId));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> listByPatient(@PathVariable String patientId) {
        return listAppointmentsByPatientUseCase.list(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> listByDoctor(@PathVariable String doctorId) {
        return listAppointmentsByDoctorUseCase.list(doctorId);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointment> get(@PathVariable String appointmentId) {
        return getAppointmentUseCase.get(appointmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public record AppointmentRequest(String appointmentId,
                                     String patientIdNumber,
                                     String doctorIdNumber,
                                     LocalDateTime appointmentDateTime,
                                     String reason) { }

    public record RescheduleRequest(LocalDateTime newDateTime) { }
}
