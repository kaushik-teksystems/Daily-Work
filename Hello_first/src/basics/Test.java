package basics;

public class Test {
	int x = 10;
	float interest = 13.1f;
	void display() {
		System.out.println(x);
	}
	public static void main(String[]args) {
		Test obj = new Test();
//		obj.display();
		whileLoop();
	}
	private static void whileLoop() {
		int i = 1;
		while(i >= 5) {
			System.out.println(i);
			i++;
		}
	}
}
