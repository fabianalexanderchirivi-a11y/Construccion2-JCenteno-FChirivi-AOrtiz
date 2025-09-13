package co.edu.tdea.clinicapp.application.port.in;

public class DiagnosticAidItemInput {
    private final String aidId;
    private final int quantity;

    public DiagnosticAidItemInput(String aidId, int quantity) {
        this.aidId = aidId;
        this.quantity = quantity;
    }

    public String getAidId() { return aidId; }
    public int getQuantity() { return quantity; }
}
