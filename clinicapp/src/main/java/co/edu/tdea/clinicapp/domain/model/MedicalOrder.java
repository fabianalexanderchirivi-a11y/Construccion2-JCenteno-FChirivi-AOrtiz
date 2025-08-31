package co.edu.tdea.clinicapp.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalOrder {

    private String orderNumber;
    private String patientIdNumber;
    private String doctorIdNumber;
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public MedicalOrder() {
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        if (orderNumber != null && !orderNumber.matches("\\d{1,6}")) {
            throw new IllegalArgumentException("El número de orden debe tener entre 1 y 6 dígitos");
        }
        this.orderNumber = orderNumber;
    }

    public String getPatientIdNumber() {
        return patientIdNumber;
    }

    public void setPatientIdNumber(String patientIdNumber) {
        this.patientIdNumber = patientIdNumber;
    }

    public String getDoctorIdNumber() {
        return doctorIdNumber;
    }

    public void setDoctorIdNumber(String doctorIdNumber) {
        if (doctorIdNumber != null && !doctorIdNumber.matches("\\d{1,10}")) {
            throw new IllegalArgumentException("La cédula del médico debe tener entre 1 y 10 dígitos");
        }
        this.doctorIdNumber = doctorIdNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
