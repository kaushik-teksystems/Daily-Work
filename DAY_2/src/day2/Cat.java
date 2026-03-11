package day2;

public class Cat extends Animal implements Pet{
	private String furColor;
	@Override
	public void play() {
		System.out.println("Playing with "+getName());	
	}
	public void meow() {
		System.out.println(getName()+" is meowing");
	}

}
