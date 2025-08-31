package app.app.domain.model;

import java.sql.Date;

public class ClinicalRecord {

	private long id;
	private Pet pet;
	private User veterinarian;
	private Date date;
	private String motive;
	private String diagnosis;
	private String medicine;
	private String medicalProcedure;
	private String doce;
	private ClinicalOrder order;
	private String vaccinationRecord;
	private String allergies;
	private String procedureDetail;
	private String symptoms;
	private boolean status;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public User getVeterinarian() {
		return veterinarian;
	}
	public void setVeterinarian(User veterinarian) {
		this.veterinarian = veterinarian;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getMedicalProcedure() {
		return medicalProcedure;
	}
	public void setMedicalProcedure(String medicalProcedure) {
		this.medicalProcedure = medicalProcedure;
	}
	public String getDoce() {
		return doce;
	}
	public void setDoce(String doce) {
		this.doce = doce;
	}
	public ClinicalOrder getOrder() {
		return order;
	}
	public void setOrder(ClinicalOrder order) {
		this.order = order;
	}
	public String getVaccinationRecord() {
		return vaccinationRecord;
	}
	public void setVaccinationRecord(String vaccinationRecord) {
		this.vaccinationRecord = vaccinationRecord;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getProcedureDetail() {
		return procedureDetail;
	}
	public void setProcedureDetail(String procedureDetail) {
		this.procedureDetail = procedureDetail;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
