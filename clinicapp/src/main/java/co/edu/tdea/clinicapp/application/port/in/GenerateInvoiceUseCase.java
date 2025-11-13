package co.edu.tdea.clinicapp.application.port.in;

import co.edu.tdea.clinicapp.application.dto.InvoiceDto;

public interface GenerateInvoiceUseCase {
    InvoiceDto generateForPatient(String patientIdNumber, Integer year);
}
