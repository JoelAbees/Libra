package com.lms.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	//Method that takes variable number of arguments and check if they are null/empty
	public static boolean validateInput(String ...inputTexts) {
		for (int i=0; i<=inputTexts.length-1; i++) {
			if(inputTexts[i] == null || "".equals(inputTexts[i])) {
				return false;
			}
		}
		return true;
		
	}
	
	//Method to get ID of user/librarian/book check if its a numeral and then return formatted integer
	public static int validateID(String inputID) {
		try {
			int id = Integer.parseInt(inputID);
			return id;
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}
	
	public static String formatDate(Date date) {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	
	}
}
