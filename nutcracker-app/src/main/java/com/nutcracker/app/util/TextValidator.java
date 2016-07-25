package com.nutcracker.app.util;

public final class TextValidator {

	private TextValidator() {
		
	}
	
	public static boolean isEmpty(String text) {
		return null == text || 
				0 == text.length();
	}
	
	public static boolean isMultiCase(String text) {
		return true;
	}
	
	public static boolean hasNumber(String text) {
		return true;
	}
	
	public static boolean hasMinimumLength(String text, int minimumLength) {
		return true;
	}
}
