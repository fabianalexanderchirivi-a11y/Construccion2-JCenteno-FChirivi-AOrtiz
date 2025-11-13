package co.edu.tdea.clinicapp.application.port.in;

import java.util.List;

public class PrescribeMedicationCommand {
    private final String patientIdNumber;
    private final String doctorIdNumber;
    private final List<MedicationItemInput> items;

    public PrescribeMedicationCommand(String patientIdNumber, String doctorIdNumber, List<MedicationItemInput> items) {
        this.patientIdNumber = patientIdNumber;
        this.doctorIdNumber = doctorIdNumber;
        this.items = items;
    }

    public String getPatientIdNumber() { return patientIdNumber; }
    public String getDoctorIdNumber() { return doctorIdNumber; }
    public List<MedicationItemInput> getItems() { return items; }
}
