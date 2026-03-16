package fileio;
import java.io.*;

public class FileReadExample {
	public static void main(String[] args) throws IOException{
		FileReader reader = new FileReader("data.txt");
		int character;
		while((character = reader.read()) != -1) {
			System.out.print((char) character);
		}
		reader.close();
	}
}
