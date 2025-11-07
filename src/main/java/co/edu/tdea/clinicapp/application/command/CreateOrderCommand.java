package co.edu.tdea.clinicapp.application.command;

import java.time.LocalDate;
import java.util.List;

public class CreateOrderCommand {
    private final String orderNumber;
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final LocalDate creationDate;
    private final List<OrderItemCommand> items;

    public CreateOrderCommand(String orderNumber, String patientIdNumber, String doctorIdNumber, LocalDate creationDate, List<OrderItemCommand> items) {
        this.orderNumber = orderNumber;
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.creationDate = creationDate;
        this.items = items;
    }

    public String getOrderNumber() { return orderNumber; }
    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public LocalDate getCreationDate() { return creationDate; }
    public List<OrderItemCommand> getItems() { return items; }
}
