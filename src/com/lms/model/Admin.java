package com.lms.model;

import javax.swing.JOptionPane;

import com.lms.common.Utility;
import com.lms.service.UserServices;
import com.lms.ui.admin.AdminSection;

public class Admin extends User {
	
	public static String addLibrarian(String librarianName , String librarianUserName, String librarianPassword , String librarianEmail, String librarianPhoneNumber) {
		
		String errorMessage = "Unable to add Librarian";
		User librarian = new Librarian();
		UserServices userTool =  new UserServices();
		
		//Validate if all input fields are filled
		boolean  validateInputResult = Utility.validateInput(librarianName,librarianUserName,librarianPassword,librarianEmail,librarianPhoneNumber);
		
		
		if (validateInputResult) {
			if(UserServices.isUsernameUnique(librarianUserName)) {
			//Add Librarian to DB
			librarian.setDetails(librarianName,librarianUserName,librarianPassword,librarianEmail,librarianPhoneNumber, "LIBRARIAN");
			int i = userTool.addUser(librarian); 
			if (i == 1) { 
				JOptionPane.showMessageDialog(null, "Succesfully added user");
				return "Success";
			} else {errorMessage =  "Adding User Failed";}
			}else {errorMessage =  "Username already taken";}
		}else {errorMessage =  "Please fill all details";}
		
		
		return errorMessage;
		
		
	};
	
	public static boolean adminLogin(String userName,String password) {
		
						
			if(userName.equals("admin") && password.equals("admin123")) {
				return true;						
			}else {
				return false;
			}
			
	
	}
	
	public static String deleteLibrarian(String librarianID_str) {
		
		String errorMessage = "Unable to delete Librarian";
		
		//validate the input to check if integer and return value in int format
		int librarianID = Utility.validateID(librarianID_str);
		if(librarianID != -1) {
			
			//Check is such user Exist
			String librarianName = UserServices.getNameFromUserID(librarianID , "LIBRARIAN");
			
			if(librarianName.equals("ERROR NO USER")) {
				errorMessage = "This user doesn't exist";
			}else if(librarianName.equals("ERROR")){
				errorMessage = "Unable to delete user";
			}else {
				
				//Pre-delete confirmation. Returns 0 if user selects yes. 
				int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you really want to delete " + librarianName + "?" ,"Delete", JOptionPane.YES_NO_OPTION);
				if(confirmDelete == 0) {
					
					//Delete user from DB
					int postDeleteConfirmation = UserServices.deleteUser(librarianID,"LIBRARIAN");	
					if (postDeleteConfirmation == 1) {
						JOptionPane.showMessageDialog(null, "Succesfully Deleted user");
						return "Success";
					} else {errorMessage = "Unable to delete user";}
				}
			}		
		}else {	errorMessage = "Please enter valid userID";}
		
		return errorMessage;
	}
}
