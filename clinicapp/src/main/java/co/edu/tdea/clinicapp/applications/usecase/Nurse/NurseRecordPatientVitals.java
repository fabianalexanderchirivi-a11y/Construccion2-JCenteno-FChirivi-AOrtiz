package co.edu.tdea.clinicapp.applications.usecase.Nurse;

import java.time.LocalDateTime;

public final class NurseRecordPatientVitals {
    public void execute(Command command) { }
    public static final class Command {
        private final String patientIdNumber;
        private final String nurseIdNumber;
        private final String bloodPressure;
        private final double temperature;
        private final int pulse;
        private final int oxygenSaturation;
        private final String observations;
        private final LocalDateTime dateTime;

        public Command(String patientIdNumber, String nurseIdNumber, String bloodPressure, double temperature, int pulse, int oxygenSaturation, String observations, LocalDateTime dateTime) {
            this.patientIdNumber = patientIdNumber;
            this.nurseIdNumber = nurseIdNumber;
            this.bloodPressure = bloodPressure;
            this.temperature = temperature;
            this.pulse = pulse;
            this.oxygenSaturation = oxygenSaturation;
            this.observations = observations;
            this.dateTime = dateTime;
        }
        public String patientIdNumber() { return patientIdNumber; }
        public String nurseIdNumber() { return nurseIdNumber; }
        public String bloodPressure() { return bloodPressure; }
        public double temperature() { return temperature; }
        public int pulse() { return pulse; }
        public int oxygenSaturation() { return oxygenSaturation; }
        public String observations() { return observations; }
        public LocalDateTime dateTime() { return dateTime; }
    }
}