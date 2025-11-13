package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_diagnostics")
public class OrderDiagnosticEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderNumber;
    private Integer itemNumber;
    private String diagnosticName;
    private Integer quantity;
    private Boolean requiresSpecialist;
    private String specialistTypeId;
    private BigDecimal cost;

    public Long getId() { return id; }
    public Long getOrderNumber() { return orderNumber; }
    public Integer getItemNumber() { return itemNumber; }
    public String getDiagnosticName() { return diagnosticName; }
    public Integer getQuantity() { return quantity; }
    public Boolean getRequiresSpecialist() { return requiresSpecialist; }
    public String getSpecialistTypeId() { return specialistTypeId; }
    public BigDecimal getCost() { return cost; }
    public void setId(Long id) { this.id = id; }
    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    public void setDiagnosticName(String diagnosticName) { this.diagnosticName = diagnosticName; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setRequiresSpecialist(Boolean requiresSpecialist) { this.requiresSpecialist = requiresSpecialist; }
    public void setSpecialistTypeId(String specialistTypeId) { this.specialistTypeId = specialistTypeId; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
}
