package co.edu.tdea.clinicapp.application.port.in;

public interface CopaySummaryUseCase {
    double copayAccumulated(String patientIdNumber, int year);
}
