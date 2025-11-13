package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.nursing;

import co.edu.tdea.clinicapp.domain.model.NursingActionType;
import co.edu.tdea.clinicapp.domain.model.OrderType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "nursing_records")
public class NursingRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false, length = 40)
    private NursingActionType type;

    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;

    @Column(name = "patient_id_number", nullable = false, length = 30)
    private String patientIdNumber;

    @Column(name = "nurse_id_number", nullable = false, length = 30)
    private String nurseIdNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false, length = 40)
    private OrderType orderType;

    @Column(name = "order_created_at", nullable = false)
    private LocalDateTime orderCreatedAt;

    @Column(name = "item_number", nullable = false)
    private Integer itemNumber;

    @Column(name = "catalog_id", nullable = false, length = 50)
    private String catalogId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public Long getId() { return id; }
    public NursingActionType getType() { return type; }
    public void setType(NursingActionType type) { this.type = type; }
    public LocalDateTime getPerformedAt() { return performedAt; }
    public void setPerformedAt(LocalDateTime performedAt) { this.performedAt = performedAt; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public void setPatientIdNumber(String patientIdNumber) { this.patientIdNumber = patientIdNumber; }
    public String getNurseIdNumber() { return nurseIdNumber; }
    public void setNurseIdNumber(String nurseIdNumber) { this.nurseIdNumber = nurseIdNumber; }
    public OrderType getOrderType() { return orderType; }
    public void setOrderType(OrderType orderType) { this.orderType = orderType; }
    public LocalDateTime getOrderCreatedAt() { return orderCreatedAt; }
    public void setOrderCreatedAt(LocalDateTime orderCreatedAt) { this.orderCreatedAt = orderCreatedAt; }
    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }
    public String getCatalogId() { return catalogId; }
    public void setCatalogId(String catalogId) { this.catalogId = catalogId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
