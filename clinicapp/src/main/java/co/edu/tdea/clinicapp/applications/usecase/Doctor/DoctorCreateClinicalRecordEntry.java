package co.edu.tdea.clinicapp.applications.usecase.Doctor;


import java.time.LocalDateTime;
import java.util.Map;

public final class DoctorCreateClinicalRecordEntry {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String doctorIdNumber;
        private final String dateKey;
        private final Map<String, Object> content;
        private final LocalDateTime createdAt;

        public Command(String patientIdNumber, String doctorIdNumber, String dateKey, Map<String, Object> content, LocalDateTime createdAt) {
            this.patientIdNumber = patientIdNumber;
            this.doctorIdNumber = doctorIdNumber;
            this.dateKey = dateKey;
            this.content = content;
            this.createdAt = createdAt;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String doctorIdNumber() { return doctorIdNumber; }
        public String dateKey() { return dateKey; }
        public Map<String, Object> content() { return content; }
        public LocalDateTime createdAt() { return createdAt; }
    }
}