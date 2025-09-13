package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;

public class VitalSignsRecord {
    private final LocalDateTime recordedAt;
    private final String nurseIdNumber;
    private final Integer systolic;   
    private final Integer diastolic; 
    private final Double temperature; 
    private final Integer pulse;   
    private final Integer oxygen;  
    public VitalSignsRecord(LocalDateTime recordedAt, String nurseIdNumber,
                            Integer systolic, Integer diastolic,
                            Double temperature, Integer pulse, Integer oxygen) {
        if (recordedAt == null) throw new IllegalArgumentException("recordedAt is required");
        if (nurseIdNumber == null || nurseIdNumber.isBlank())
            throw new IllegalArgumentException("nurseIdNumber is required");
        this.recordedAt = recordedAt;
        this.nurseIdNumber = nurseIdNumber.trim();
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.temperature = temperature;
        this.pulse = pulse;
        this.oxygen = oxygen;
    }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public Integer getSystolic() { return systolic; }
    public Integer getDiastolic() { return diastolic; }
    public Double getTemperature() { return temperature; }
    public Integer getPulse() { return pulse; }
    public Integer getOxygen() { return oxygen; }
}
