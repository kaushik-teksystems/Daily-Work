package log.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LogFileAnalyzer {

	InputStream getFileFromResources(String fileName) throws FileNotFoundException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new FileNotFoundException("FATAL: File '" + fileName + "' missing.");
		}
		return inputStream;
	}

	Map<String, Integer> parseLogCounts(InputStream inputStream) throws IOException {
		Map<String, Integer> counts = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				processLine(line, counts);
			}
		} catch (IOException e) {
			throw new IOException("Failed to read stream", e);
		}
		return counts;
	}

	void processLine(String line, Map<String, Integer> counts) {
		if (line.contains(":")) {
			String key = line.split(":")[0].trim().toUpperCase();
			counts.put(key, counts.getOrDefault(key, 0) + 1);
		}
	}

	void printLogCount(Map<String, Integer> counts) {
		System.out.println("--- Log Count As Per Log File ---");
		if (counts.isEmpty()) {
			System.out.println("No valid data found.");
		} else {
			counts.forEach((k, v) -> System.out.println(k + " count: " + v));
		}
	}

	public static void main(String[] args) throws IOException {
		LogFileAnalyzer analyzer = new LogFileAnalyzer();
		InputStream inputStream = analyzer.getFileFromResources("logs.txt");
		Map<String, Integer> counts = analyzer.parseLogCounts(inputStream);
		analyzer.printLogCount(counts);
	}
}