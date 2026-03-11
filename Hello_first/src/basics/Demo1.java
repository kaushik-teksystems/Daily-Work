package basics;

public class Demo1 {
	public static void main(String[] args) {
		int age = 10;
		System.out.print(age);
		System.out.println("Hello World!");
		boolean result = oddOrEven(age);
		System.out.println(result);
	}

	static boolean oddOrEven(int number) {
		return number % 2 == 0;
	}
}
