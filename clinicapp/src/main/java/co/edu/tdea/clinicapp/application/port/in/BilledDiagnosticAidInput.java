package co.edu.tdea.clinicapp.application.port.in;

public class BilledDiagnosticAidInput {
    private final String aidId;
    private final int quantity;

    public BilledDiagnosticAidInput(String aidId, int quantity) {
        this.aidId = aidId;
        this.quantity = quantity;
    }

    public String getAidId() { return aidId; }
    public int getQuantity() { return quantity; }
}
