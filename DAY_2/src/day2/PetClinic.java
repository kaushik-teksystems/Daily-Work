package day2;

import java.util.ArrayList;
import java.util.List;

public class PetClinic {
	public static void main(String[] args) {
		demo1();
		methodOverriding();
	}
	public static void methodOverriding() {
		Pet pet = new Dog("Husky");
		Animal animal = (Animal)pet; //upcasting
		animal.sound();
	}
	public static void demo1() {
		Dog dog = new Dog("Husky");
		dog.setName("Pluto");
		Pet dog1 = new Dog("Husky");
		Cat cat = new Cat();
		cat.setName("Lucy");
		dog.bark();
		List<Pet> pets = new ArrayList<Pet>();
		pets.add(dog);
		pets.add(dog1);
		pets.add(cat);
		pets.forEach((pet) -> pet.play());
}
}
