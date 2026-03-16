package fileio;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
	public static void main(String[] args) throws IOException{
		FileWriter writer = new FileWriter("output.txt");
		writer.write("Hello Java File IO");
		writer.close();
	}
}
