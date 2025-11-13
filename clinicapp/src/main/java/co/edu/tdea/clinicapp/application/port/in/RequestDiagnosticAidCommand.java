package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;

public class RequestDiagnosticAidCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final List<DiagnosticAidItemInput> items;

    public RequestDiagnosticAidCommand(String patientIdNumber, String doctorIdNumber, List<DiagnosticAidItemInput> items) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.items = items;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public List<DiagnosticAidItemInput> getItems() { return items; }
}
