package com.lms.model;

import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import com.lms.common.Utility;
import com.lms.service.BookServices;
import com.lms.service.TransactionServices;
import com.lms.service.UserServices;
import com.lms.ui.librarian.NewBookForm;

public class Librarian extends User {
	
	public static String addBook(String isbn, String bookQuantity) {
		
		String errorMessage = "Unable to process";
		if(Utility.validateInput(isbn,bookQuantity)) {
			int bookQuantityInt = Utility.validateID(bookQuantity);
			if(bookQuantityInt != -1) {
				
				//Check if Book Details are present
				int isIsbnPresent = BookServices.isIsbnPresent(isbn);
				if (isIsbnPresent == 1) {
					
					//Book details previously present. Add books to DB referencing ISBN
					BookServices bookServices =  new BookServices();
					int bookCount = bookServices.addBook(isbn,bookQuantityInt);

					if (bookCount != 0) {
						JOptionPane.showMessageDialog(null, "Succesfully added " + bookCount + " book details");
						return "Success";
					}else{errorMessage =  "No books were added";}
					
				}else if (isIsbnPresent == 0) {
					//First registration of a new Book
					NewBookForm newBookForm = new NewBookForm(isbn,bookQuantityInt);
					newBookForm.setVisible(true);
					return "Success";
					
				}else if (isIsbnPresent == -1) {errorMessage = "Please handle Exception, contact Admin";}
			}else {errorMessage = "please enter a valid quantity";}
		}else {errorMessage = "please fill all details";}
		
		
		return errorMessage;
		
	}

	public static String issueBooks(String strBookID, String strUserID) {

		String errorMessage = "Unable to Issue Books";
		
		//Check if user has entered input
		if(Utility.validateInput(strBookID, strUserID)) {
			//Convert input texts to integers and validate
			int bookID = Utility.validateID(strBookID);
			int userID = Utility.validateID(strUserID);
			
			if (bookID != -1 && userID != -1) {
				//Issue book primary method in service class
				String issueBookStatus = TransactionServices.issueBooks(bookID , userID);
				
				if (issueBookStatus.equals("Success")){
					JOptionPane.showMessageDialog(null, "Succesfully Issued Book");
					return issueBookStatus;
				}else {errorMessage = issueBookStatus;}	
			}else {errorMessage = "please enter valid IDs";}
		}else {errorMessage = "please fill all details";}
		
		return errorMessage;
		
	}
	
	public static boolean librarianLogin(String userName,String password) {

		
		//Check if user has provided valid input
		if(Utility.validateInput(userName,password)) {
			//get Librarian ID from username and password
			int librarianID = UserServices.userLogin(userName,password);
			if (librarianID != -1) {
				//Store Librarian ID in preference API for future reference until logout.
				Preferences prefs = Preferences.userNodeForPackage(com.lms.ui.librarian.LibrarianLogin.class);
				prefs.putInt("userId", librarianID);
				return true;
			}
		}		
		return false; 
		}

	public static String returnBook(String strBookID, String strUserID) {

		String errorMessage = "Unable to return book";
		// Validate user Input and convert text to integer
		if(Utility.validateInput(strBookID, strUserID)) {
			int bookID = Utility.validateID(strBookID);
			int userID = Utility.validateID(strUserID);
			
			if (bookID != -1 && userID != -1) {
				
				//primary method to return book from transaction class
				String IssueBookStatus = TransactionServices.returnBooks(bookID , userID);
				
				if (IssueBookStatus.equals("Success")){
					JOptionPane.showMessageDialog(null, "Succesfully Returned Book");
					return "Success";
				}else {errorMessage = IssueBookStatus;}	
				
			}else {errorMessage = "please enter valid IDs";}
		}else {errorMessage = "please fill all details";}
		
		
		return errorMessage;
		
	}
}
