package com.tek.fi;

@FunctionalInterface //annotation
interface Animal{
	void eat();
	//void run(); //Functional Interfaces can have exactly one abstract method
}
class Cat implements Animal{
	@Override
	public void eat() {
		System.out.println("animal eat inside class"); //Object oriented way of programming
	}
}
public class FunctionalInterfaceDemo {
	public static void main(String[] args) {
		ooplay();
		functional();
	}
	private static void ooplay() {
		Animal animal = new Cat();
		animal.eat();
	}
	private static void functional() {
		Animal animal = () -> { //Inline lambda expression or function
			System.out.println("animal ear in Lambda");
		};
		animal.eat();
	}
}
