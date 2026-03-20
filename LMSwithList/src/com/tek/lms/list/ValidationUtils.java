package com.tek.lms.list;

public class ValidationUtils {
	public static boolean validateStringInput(String input) {
		return (input != null && !input.trim().equals(""));
	}

	public static boolean validateNumericInput(float numericInput) {
		return (numericInput > 0);
	}
}
