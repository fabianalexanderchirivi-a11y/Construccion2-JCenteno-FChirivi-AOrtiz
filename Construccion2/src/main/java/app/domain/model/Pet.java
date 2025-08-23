package app.domain.model;

import app.domain.model.enu.Species;

public class Pet extends Person {

	private long id;
	private String name;
	private int age;
	private double weight;
	private Species spices;
	private String features;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Species getSpices() {
		return spices;
	}
	public void setSpices(Species spices) {
		this.spices = spices;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	
	
	
}

