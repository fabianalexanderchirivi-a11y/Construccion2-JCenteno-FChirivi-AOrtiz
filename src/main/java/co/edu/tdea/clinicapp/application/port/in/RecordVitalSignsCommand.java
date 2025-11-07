package co.edu.tdea.clinicapp.application.port.in;

import java.time.LocalDateTime;

public class RecordVitalSignsCommand {
    private final String patientIdNumber;
    private final String nurseIdNumber;
    private final LocalDateTime recordedAt;
    private final Integer systolic;
    private final Integer diastolic;
    private final Double temperature;
    private final Integer pulse;
    private final Integer oxygen;

    public RecordVitalSignsCommand(String patientIdNumber, String nurseIdNumber, LocalDateTime recordedAt,
                                   Integer systolic, Integer diastolic, Double temperature,
                                   Integer pulse, Integer oxygen) {
        this.patientIdNumber = patientIdNumber;
        this.nurseIdNumber = nurseIdNumber;
        this.recordedAt = recordedAt;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygen = oxygen;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public Integer getSystolic() { return systolic; }
    public Integer getDiastolic() { return diastolic; }
    public Double getTemperature() { return temperature; }
    public Integer getPulse() { return pulse; }
    public Integer getOxygen() { return oxygen; }
}
