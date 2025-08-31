package co.edu.tdea.clinicapp.domain.model;

public abstract class OrderItem {

    private String orderNumber;
    private int itemNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        if (itemNumber < 1) {
            throw new IllegalArgumentException("El número de ítem debe ser mayor o igual a 1");
        }
        this.itemNumber = itemNumber;
    }
}
