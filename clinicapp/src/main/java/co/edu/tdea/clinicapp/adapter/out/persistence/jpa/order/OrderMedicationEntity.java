package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_medications")
public class OrderMedicationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;
    private Integer itemNumber;
    private String medicationName;
    private String dose;
    private String duration;
    private BigDecimal cost;

    public Long getId() { return id; }
    public Long getOrderNumber() { return orderNumber; }
    public Integer getItemNumber() { return itemNumber; }
    public String getMedicationName() { return medicationName; }
    public String getDose() { return dose; }
    public String getDuration() { return duration; }
    public BigDecimal getCost() { return cost; }

    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }
    public void setDose(String dose) { this.dose = dose; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}