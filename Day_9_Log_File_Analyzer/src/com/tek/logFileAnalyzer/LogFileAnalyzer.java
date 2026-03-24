package com.tek.logFileAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GenerateLogs {
	private static final Logger logger = Logger.getLogger("LogFile");

	public static void main(String[] args) {
		try {
			File logDir = new File("logs");
			if (!logDir.exists())
				logDir.mkdir();
			FileHandler fh = new FileHandler("logs/app.log", true);
			logger.addHandler(fh);

			// 3. Simple formatting (so it's readable)
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// 4. Generate Logs
			logger.info("Application Started");
			logger.severe("DB Connection Failed");
			logger.warning("Low Memory Detected");
			logger.severe("Disk Space Full");

			System.out.println("Logs generated in logs/app.log");

		} catch (IOException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
