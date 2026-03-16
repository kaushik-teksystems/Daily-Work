package com.tek.logging;
import java.util.logging.*;

public class LoggingExample {
	private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());
	public static void main(String[] args) {
		logger.setLevel(Level.SEVERE);
		logger.info("Application Started");
		logger.warning("Low Memory warning");
		logger.severe("System failure");
	}
}
