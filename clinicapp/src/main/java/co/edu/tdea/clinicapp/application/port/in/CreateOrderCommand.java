package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;
import co.edu.tdea.clinicapp.domain.model.OrderType;

public class CreateOrderCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final OrderType type;
    private final List<Item> items;

    public CreateOrderCommand(String patientIdNumber, String doctorIdNumber, OrderType type, List<Item> items) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.type = type;
        this.items = items;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public OrderType getType() { return type; }
    public List<Item> getItems() { return items; }

    public static class Item {
        private final String catalogId;
        private final int quantity;

        public Item(String catalogId, int quantity) {
            this.catalogId = catalogId;
            this.quantity = quantity;
        }

        public String getCatalogId() { return catalogId; }
        public int getQuantity() { return quantity; }
    }
}
