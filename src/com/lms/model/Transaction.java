package com.lms.model;

public class Transaction {
	private int transactionID;
	private int bookID;
	private int librarianID;
	private int userID;
	private String issueDate;
	private String returnDate;
	
	/*public void setTransactionID(int transactionID) {this.transactionID = transactionID;}
	public void setBookID(int bookID) {this.bookID = bookID;}
	public void setLibrarianID(int librarianID) {this.librarianID = librarianID;}
	public void setUserID(int userID) {this.userID = userID;}
	public void setIssueDate(String issueDate) {this.issueDate = issueDate;}
	public void setReturnDate(String returnDate) {this.returnDate = returnDate;}*/
	
	public void setDetails(int transactionID, int bookID , int librarianID , int userID , String issueDate ) {
		this.transactionID = transactionID;
		this.bookID = bookID;
		this.librarianID = librarianID;
		this.userID = userID;
		this.issueDate = issueDate;
	}
	
	
	public int getTransactionID() {return transactionID;}
	public int getBookID() {return bookID;}
	public int getLibrarianID() {return librarianID;}
	public int getUserID() {return userID;}
	public String getIssueDate() {return issueDate;}
	public String getreturnDate() {return returnDate;}
	

	
}
