package fileio;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CustomFileWriterExample {
	public static void main(String[] args) throws IOException {
		FileWriter writer = new FileWriter("Received Input File.txt", true);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the text you want in the file, press ctrl C to close:");
		try {
			while(true) {
				String input = sc.nextLine();
				writer.write(input+ "\n");
				writer.flush();
				System.out.println("If you want to enter more, go ahead: ");
				if (input.equalsIgnoreCase("Exit")) {
					System.out.println("You're exiting the write operation.");
					writer.close();
					System.out.println("Closed");
				}
			}
		} finally {
			sc.close();
		}
	}
}
