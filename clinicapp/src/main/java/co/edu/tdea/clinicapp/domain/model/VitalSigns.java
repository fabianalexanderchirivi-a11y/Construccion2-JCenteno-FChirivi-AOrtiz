package co.edu.tdea.clinicapp.domain.model;

import java.math.BigDecimal;

public class VitalSigns {
    private String bloodPressure;
    private BigDecimal temperature;
    private Integer pulse;
    private Integer oxygenSaturation;

    public VitalSigns(String bloodPressure, BigDecimal temperature, Integer pulse, Integer oxygenSaturation) {
        setBloodPressure(bloodPressure);
        setTemperature(temperature);
        setPulse(pulse);
        setOxygenSaturation(oxygenSaturation);
    }

    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) {
        if (bloodPressure == null || bloodPressure.isBlank()) throw new IllegalArgumentException("Presión arterial inválida.");
        this.bloodPressure = bloodPressure.trim();
    }

    public BigDecimal getTemperature() { return temperature; }
    public void setTemperature(BigDecimal temperature) {
        if (temperature == null) throw new IllegalArgumentException("Temperatura inválida.");
        this.temperature = temperature;
    }

    public Integer getPulse() { return pulse; }
    public void setPulse(Integer pulse) {
        if (pulse == null || pulse < 0) throw new IllegalArgumentException("Pulso inválido.");
        this.pulse = pulse;
    }

    public Integer getOxygenSaturation() { return oxygenSaturation; }
    public void setOxygenSaturation(Integer oxygenSaturation) {
        if (oxygenSaturation == null || oxygenSaturation < 0 || oxygenSaturation > 100)
            throw new IllegalArgumentException("Saturación de oxígeno inválida.");
        this.oxygenSaturation = oxygenSaturation;
    }
}
