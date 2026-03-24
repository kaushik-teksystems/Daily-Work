package com.tek.logFileAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogFileAnalyzer {
	public static void main(String[] args) {
		String fileName = "logs.txt";
		Map<String, Integer> logCounts = new HashMap<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(":")) {
					String key = line.split(":")[0].trim().toUpperCase();
					logCounts.put(key, logCounts.getOrDefault(key, 0) + 1);
				}
			}

			System.out.println("Log Count Statistics:");
			logCounts.forEach((key, count) -> System.out.println(key + " count: " + count));

		} catch (IOException e) {
			System.err.println("Error: Could not read file '" + fileName + "'. Please ensure the file exists.");
		}
	}
}