package co.edu.tdea.clinicapp.application.port.in;

public class BilledProcedureInput {
    private final String procedureId;
    private final int quantity;
    private final String frequency;
    private final String specialist;

    public BilledProcedureInput(String procedureId, int quantity, String frequency, String specialist) {
        this.procedureId = procedureId;
        this.quantity = quantity;
        this.frequency = frequency;
        this.specialist = specialist;
    }

    public String getProcedureId() { return procedureId; }
    public int getQuantity() { return quantity; }
    public String getFrequency() { return frequency; }
    public String getSpecialist() { return specialist; }
}
