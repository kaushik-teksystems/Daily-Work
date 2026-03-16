import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThrowableCheckedException {
	public static void main(String[] args) {
		try {
			readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void readFile() throws FileNotFoundException {
		FileReader read = new FileReader("data.txt");
	}
}
