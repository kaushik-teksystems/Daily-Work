package day2;

public class Person {
	private String name;// Mutable
	private int age; // Mutable
	private Address address;
	private Phone phone;
	Pet pet;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName(String name) {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void adoptPet(Pet pet) {
		System.out.println("Adopted a Pet.");
	}

}
