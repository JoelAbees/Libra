package com.lms.common;

public class Utility {
	
	public static boolean validateInput(String ...inputTexts) {
		for (int i=0; i<=inputTexts.length-1; i++) {
			if(inputTexts[i] == null || "".equals(inputTexts[i])) {
				return false;
			}
		}
		return true;
		
	}
	
	public static int validateID(String inputID) {
		try {
			int id = Integer.parseInt(inputID);
			return id;
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}
}
