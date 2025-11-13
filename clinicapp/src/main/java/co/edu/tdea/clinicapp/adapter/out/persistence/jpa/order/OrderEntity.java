package co.edu.tdea.clinicapp.adapter.out.persistence.jpa.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private Long orderNumber;

    @Column(nullable = false, length = 15)
    private String patientDocument;

    @Column(nullable = false, length = 15)
    private String doctorDocument;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Long getOrderNumber() { return orderNumber; }
    public String getPatientDocument() { return patientDocument; }
    public String getDoctorDocument() { return doctorDocument; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setOrderNumber(Long orderNumber) { this.orderNumber = orderNumber; }
    public void setPatientDocument(String patientDocument) { this.patientDocument = patientDocument; }
    public void setDoctorDocument(String doctorDocument) { this.doctorDocument = doctorDocument; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
