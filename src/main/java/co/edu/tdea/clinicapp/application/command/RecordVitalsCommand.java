package co.edu.tdea.clinicapp.application.command;

import java.time.LocalDateTime;

public class RecordVitalsCommand {
    private final String patientIdNumber;
    private final String bloodPressure;
    private final double temperature;
    private final int pulse;
    private final int oxygen;
    private final LocalDateTime takenAt;

    public RecordVitalsCommand(String patientIdNumber, String bloodPressure, double temperature, int pulse, int oxygen, LocalDateTime takenAt) {
        this.patientIdNumber = patientIdNumber;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygen = oxygen;
        this.takenAt = takenAt;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getBloodPressure() { return bloodPressure; }
    public double getTemperature() { return temperature; }
    public int getPulse() { return pulse; }
    public int getOxygen() { return oxygen; }
    public LocalDateTime getTakenAt() { return takenAt; }
}
