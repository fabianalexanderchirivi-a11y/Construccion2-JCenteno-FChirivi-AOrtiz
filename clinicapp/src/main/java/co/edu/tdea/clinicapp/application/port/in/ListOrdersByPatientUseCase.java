package co.edu.tdea.clinicapp.application.port.in;

public interface ListOrdersByPatientUseCase {
    java.util.List<co.edu.tdea.clinicapp.application.port.in.OrderItemSummaryDto> list(String patientIdNumber);
}
