package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_procedures")
public class OrderProcedureEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;
    private Integer itemNumber;
    private String procedureName;
    private Integer times;
    private String frequency;
    private Boolean requiresSpecialist;
    private String specialistTypeId;
    private BigDecimal cost;

    public Long getId() { return id; }
    public Long getOrderNumber() { return orderNumber; }
    public Integer getItemNumber() { return itemNumber; }
    public String getProcedureName() { return procedureName; }
    public Integer getTimes() { return times; }
    public String getFrequency() { return frequency; }
    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public String getSpecialistTypeId() { return specialistTypeId; }
    public BigDecimal getCost() { return cost; }
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }
    public void setTimes(Integer times) { this.times = times; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setRequiresSpecialist(Boolean requiresSpecialist) { this.requiresSpecialist = requiresSpecialist; }
    public void setSpecialistTypeId(String specialistTypeId) { this.specialistTypeId = specialistTypeId; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}