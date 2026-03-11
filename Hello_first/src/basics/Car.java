package basics;

import java.util.Date;

public class Car {
	private String model; //Immutable, for immutable properties just use getter 
	private String color; // Mutable, both getter & setter
	private float price; // Mutable
	private String engine; //MUTABLE,    when there is a has-A relationship, define a class for it
	String fuelType; //IMMUTABLE
	String number; // IMMUTABLE
	boolean insured; // MUTABLE
	String transmission; //IMMUTABLE
	List<Seat> seats; // MUTABLE
	float weight; //IMMUTABLE
	String design; // IMMUTABLE
	float mileage; //MUTABLE
	String brand; //IMMUTABLE
	boolean ev; //IMMUTABLE
	Date manufacturingDate; //IMMUTABLE
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
