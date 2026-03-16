
public class ExceptionExample {
	public static void main(String[] args) {
		try {
			int result = 10 / 0;
			System.out.println(result);
		} catch (ArithmeticException e) {
			System.out.println("Cannot Divide by Zero");
		} finally {
			System.out.println("Program Finished");
		}
	}
}
