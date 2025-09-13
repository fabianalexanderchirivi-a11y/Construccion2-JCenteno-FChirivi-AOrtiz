package co.edu.tdea.clinicapp.application.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import co.edu.tdea.clinicapp.application.port.in.*;
import co.edu.tdea.clinicapp.domain.model.*;
import co.edu.tdea.clinicapp.domain.repository.*;

public class GenerateInvoiceService implements GenerateInvoiceUseCase {

    private static final int COPAY_PER_INVOICE = 50_000;
    private static final int YEARLY_COPAY_CAP = 1_000_000;

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final MedicationRepository medicationRepository;
    private final ProcedureRepository procedureRepository;
    private final DiagnosticAidRepository diagnosticAidRepository;
    private final BillingLedgerRepository billingLedgerRepository;

    public GenerateInvoiceService(PatientRepository patientRepository,
                                  UserRepository userRepository,
                                  MedicationRepository medicationRepository,
                                  ProcedureRepository procedureRepository,
                                  DiagnosticAidRepository diagnosticAidRepository,
                                  BillingLedgerRepository billingLedgerRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;
        this.procedureRepository = procedureRepository;
        this.diagnosticAidRepository = diagnosticAidRepository;
        this.billingLedgerRepository = billingLedgerRepository;
    }

    @Override
    public Invoice generate(GenerateInvoiceCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException("Un comando es requerido.");

        var patient = patientRepository.findByIdNumber(cmd.getPatientIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado."));

        var doctor = userRepository.findByIdNumber(cmd.getDoctorIdNumber())
                .orElseThrow(() -> new IllegalArgumentException("Doctor no encontrado."));

        int age = Period.between(patient.getBirthDate(), LocalDate.now()).getYears();

        String insuranceCompany = null;
        String policyNumber = null;
        boolean policyActive = false;
        LocalDate policyEndDate = null;
        Integer policyDaysRemaining = null;

        if (patient.getInsurancePolicy() != null) {
            var p = patient.getInsurancePolicy();
            insuranceCompany = p.getCompanyName();
            policyNumber = p.getPolicyNumber();
            policyActive = p.isActive();
            policyEndDate = p.getEndDate();
            if (policyEndDate != null) {
                policyDaysRemaining = (int) java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), policyEndDate);
            }
        }

        List<InvoiceItem> items = new ArrayList<>();
        int gross = 0;

        if (cmd.getMedications() != null) {
            for (var m : cmd.getMedications()) {
                var med = medicationRepository.findById(m.getMedicationId())
                        .orElseThrow(() -> new IllegalArgumentException("El siguiente medicamento no fue encontrado: " + m.getMedicationId()));
                var item = new InvoiceItem(
                        InvoiceItemType.MEDICATION,
                        med.getId(),
                        med.getName(),
                        med.getUnitCost(),
                        m.getQuantity(),
                        m.getDose(),
                        m.getFrequency(),
                        m.getDurationDays(),
                        null
                );
                items.add(item);
                gross += item.getLineTotal();
            }
        }

        if (cmd.getProcedures() != null) {
            for (var p : cmd.getProcedures()) {
                var proc = procedureRepository.findById(p.getProcedureId())
                        .orElseThrow(() -> new IllegalArgumentException("El siguiente procedimiento no fue encontrado: " + p.getProcedureId()));
                var item = new InvoiceItem(
                        InvoiceItemType.PROCEDURE,
                        proc.getId(),
                        proc.getName(),
                        proc.getUnitCost(),
                        p.getQuantity(),
                        null,
                        p.getFrequency(),
                        null,
                        p.getSpecialist()
                );
                items.add(item);
                gross += item.getLineTotal();
            }
        }

        if (cmd.getDiagnosticAids() != null) {
            for (var a : cmd.getDiagnosticAids()) {
                var aid = diagnosticAidRepository.findById(a.getAidId())
                        .orElseThrow(() -> new IllegalArgumentException("El siguiente diagnositco de ayuda no fue encontrado: " + a.getAidId()));
                var item = new InvoiceItem(
                        InvoiceItemType.DIAGNOSTIC_AID,
                        aid.getId(),
                        aid.getName(),
                        aid.getUnitCost(),
                        a.getQuantity(),
                        null,
                        null,
                        null,
                        null
                );
                items.add(item);
                gross += item.getLineTotal();
            }
        }

        int year = LocalDate.now().getYear();
        int previousCopayThisYear = billingLedgerRepository.getYearlyCopayTotal(patient.getIdNumber(), year);
        int remainingCap = Math.max(0, YEARLY_COPAY_CAP - previousCopayThisYear);

        int copay;
        int insurerPortion;
        int patientPayable;

        if (!policyActive) {
            copay = 0;
            insurerPortion = 0;
            patientPayable = gross;
        } else if (remainingCap <= 0) {
            copay = 0;
            insurerPortion = gross;
            patientPayable = 0;
        } else {
            copay = Math.min(Math.min(COPAY_PER_INVOICE, remainingCap), gross);
            insurerPortion = gross - copay;
            patientPayable = copay;
        }

        if (copay > 0) {
            billingLedgerRepository.addCopay(patient.getIdNumber(), year, copay);
        }

        return new Invoice(
                LocalDateTime.now(),
                patient.getIdNumber(),
                patient.getFullName(),
                age,
                doctor.getFullName(),
                insuranceCompany,
                policyNumber,
                policyActive,
                policyEndDate,
                policyDaysRemaining,
                items,
                gross,
                copay,
                insurerPortion,
                patientPayable
        );
    }
}
