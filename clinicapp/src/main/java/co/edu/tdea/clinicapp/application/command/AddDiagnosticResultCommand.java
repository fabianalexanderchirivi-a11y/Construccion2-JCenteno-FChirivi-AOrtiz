package co.edu.tdea.clinicapp.application.command;

public class AddDiagnosticResultCommand {
    private final String patientIdNumber;
    private final String orderNumber;
    private final String resultSummary;
    private final String finalDiagnosis;

    public AddDiagnosticResultCommand(String patientIdNumber, String orderNumber, String resultSummary, String finalDiagnosis) {
        this.patientIdNumber = patientIdNumber;
        this.orderNumber = orderNumber;
        this.resultSummary = resultSummary;
        this.finalDiagnosis = finalDiagnosis;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getOrderNumber() { return orderNumber; }
    public String getResultSummary() { return resultSummary; }
    public String getFinalDiagnosis() { return finalDiagnosis; }
}
