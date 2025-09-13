package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;

public class PrescribeProcedureCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final List<ProcedureItemInput> items;

    public PrescribeProcedureCommand(String patientIdNumber, String doctorIdNumber, List<ProcedureItemInput> items) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.items = items;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public List<ProcedureItemInput> getItems() { return items; }
}
