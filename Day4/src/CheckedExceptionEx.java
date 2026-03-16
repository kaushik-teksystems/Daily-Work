import java.io.*;

public class CheckedExceptionEx {
	public static void main(String[] args) {
		try {
			FileReader file = new FileReader("data.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
		finally {
			System.out.println("Please add the file first.");
		}
	}
}
