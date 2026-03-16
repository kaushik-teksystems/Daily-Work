import java.io.*;
public class UncheckedExceptionEx {
	public static void main(String[] args) {
//		String str = null;
//		System.out.println(str.length());
		try {
			validateAge(17);
		} catch (InvalidAgeException e) {//generally we don't catch checked exceptions
			e.printStackTrace();
		}
		System.out.println("finished");
	}

	private static void validateAge(int age) {
		if(age < 18) {
			throw new InvalidAgeException("Age must be 18+");
		}
	}
}
