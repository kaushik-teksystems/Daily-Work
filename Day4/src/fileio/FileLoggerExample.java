package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class FileLoggerExample {
	public static final Logger logger = Logger.getLogger(FileLoggerExample.class.getName());

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				logger.info(line);
			}
		} catch (IOException e) {
			logger.severe("File reading error:" + e.getMessage());
		}
	}
}
