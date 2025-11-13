package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.vitals;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
public class VitalSignsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="patient_id_number", nullable=false, length=30)
    private String patientIdNumber;

    @Column(name="nurse_id_number", nullable=false, length=30)
    private String nurseIdNumber;

    @Column(name="recorded_at", nullable=false)
    private LocalDateTime recordedAt;

    @Column(name="systolic")
    private Integer systolic;

    @Column(name="diastolic")
    private Integer diastolic;

    @Column(name="temperature")
    private Double temperature;

    @Column(name="pulse")
    private Integer pulse;

    @Column(name="oxygen")
    private Integer oxygen;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }

    public String getNurseIdNumber() { return nurseIdNumber; }
    public void setNurseIdNumber(String nurseIdNumber) { this.nurseIdNumber = nurseIdNumber; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public Integer getSystolic() { return systolic; }
    public void setSystolic(Integer systolic) { this.systolic = systolic; }

    public Integer getDiastolic() { return diastolic; }
    public void setDiastolic(Integer diastolic) { this.diastolic = diastolic; }

    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }

    public Integer getPulse() { return pulse; }
    public void setPulse(Integer pulse) { this.pulse = pulse; }

    public Integer getOxygen() { return oxygen; }
    public void setOxygen(Integer oxygen) { this.oxygen = oxygen; }
}
