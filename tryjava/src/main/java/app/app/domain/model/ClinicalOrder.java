package app.app.domain.model;

import java.sql.Date;

public class ClinicalOrder {
	
	private long id;
	private Pet pet;
	private Person person;
	private User veterninarian;
	private String medicine;
	private String doce;
	private Date date;
	
	
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public User getVeterninarian() {
		return veterninarian;
	}
	public void setVeterninarian(User veterninarian) {
		this.veterninarian = veterninarian;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getDoce() {
		return doce;
	}
	public void setDoce(String doce) {
		this.doce = doce;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
