package co.edu.tdea.clinicapp.applications.usecase.Doctor;

import java.time.LocalDateTime;
import java.util.Map;

public final class DoctorRegisterDiagnosticResultsAndTreatments {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String newDateKey;
        private final Map<String, Object> content;
        private final LocalDateTime createdAt;

        public Command(String patientIdNumber, String newDateKey, Map<String, Object> content, LocalDateTime createdAt) {
            this.patientIdNumber = patientIdNumber;
            this.newDateKey = newDateKey;
            this.content = content;
            this.createdAt = createdAt;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String newDateKey() { return newDateKey; }
        public Map<String, Object> content() { return content; }
        public LocalDateTime createdAt() { return createdAt; }
    }
}