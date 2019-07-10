//All interaction with Database with regards to Table Transaction;

package com.lms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import com.lms.common.Utility;
import com.lms.db.util.SQLConnection;
import com.lms.model.Transaction;

public class TransactionServices {

	//Primary method to issue Books
	public static String issueBooks(int bookID, int userID) {
		
		//Proceed further only if book is in Available status
		if(BookServices.bookStatus(bookID, "AVAILABLE")) {
			
			//Check if user exist and retrieve his Membership type (GOLD/REGULAR)
			String userMembership = UserServices.checkMembership(userID);
			if (!userMembership.equals("NO SUCH USER")) {
				
				//Proceed further only if Active Book count is less than prescribed for membership
				if(isUserEligible(userID, userMembership)) {
					
					int transactionID = getLastTransactionID() + 1;
					if (transactionID != 0) {
					
						//Retrieve ID of Librarian currently Logged in 						
						Preferences prefs = Preferences.userNodeForPackage(com.lms.ui.librarian.LibrarianLogin.class);
						int librarianID = prefs.getInt("userId", 0);
						
						//Format Issue Date in dd-MM-YYYY format
						String issueDate = Utility.formatDate(Calendar.getInstance().getTime());
						
						//Transaction Object initiation and Setting attributes
						Transaction transaction = new Transaction();
						transaction.setDetails(transactionID, bookID , librarianID , userID , issueDate );
						
						//Proceed further if Transaction is recorded
						if(addTransaction(transaction) == 1) {
							//Change book status to Issued post transaction.
							if(BookServices.changeBookStatus("ISSUED", bookID, transactionID) == 1) {
								return "Success";
							}
							
							
						}else {return "Unable to add transaction";}						
					}else {return "Error retreiving transaction ID, contact admin";}
				}else {return "user reached maximum book limit";}				
			}else {return "This user does not exist";}
		}else {return "Book is not Available";}
		return null;
		
	}
	
	//Method to check if user is within his borrowing limit based on membership. 
	private static boolean isUserEligible(int userID, String userMembership) {
		
		int maximumBookCount;
		if (userMembership.equals("GOLD")){maximumBookCount = 5;}
		else if (userMembership.equals("REGULAR")) {maximumBookCount = 3;}
		else {maximumBookCount = 0;}
		
		
		String checkActiveBookCountQuery = "SELECT COUNT(*) FROM TRANSACTIONS WHERE USER_ID = ? AND RETURN_DATE IS NULL";
		try (Connection conn = SQLConnection.getDBConnection();
        		PreparedStatement pstmt = conn.prepareStatement(checkActiveBookCountQuery);) {
        	
            pstmt.setInt(1, userID);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.getInt("COUNT(*)") < maximumBookCount) {return true;}
                else {return false;}
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        	}
		
		return false;
	}
	
	//Method to get ID of last transaction in TRANSACTIONS table
	private static int getLastTransactionID() {
		String getLastTransactionIDQuery = "SELECT MAX(TRANSACTION_ID) FROM TRANSACTIONS";
        try (Connection conn = SQLConnection.getDBConnection(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(getLastTransactionIDQuery)) {

            
            if (rs.next() == false) {return 0;}
            else {
            	return rs.getInt("MAX(TRANSACTION_ID)");
            }

        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
        return -1;
	}
	
	//Method receives Transaction object and inserts the attributes to TRANSACTIONS table
	private static int addTransaction(Transaction transactionObj) {
		String insertTransactionDetails = "INSERT INTO TRANSACTIONS VALUES (?,?,?,?,?,?)";
        try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(insertTransactionDetails);) {

            pstmt.setInt(1, transactionObj.getTransactionID());
            pstmt.setInt(2, transactionObj.getBookID());
            pstmt.setInt(3, transactionObj.getLibrarianID());
            pstmt.setInt(4, transactionObj.getUserID());
            pstmt.setString(5, transactionObj.getIssueDate());
            pstmt.setNull(6, java.sql.Types.VARCHAR);
            int i = pstmt.executeUpdate();
            return i;

        } catch (SQLException e) {

        }
        return -1;
	}
	
	//Method to delete Transaction in case of Roll Back
	public static void deleteTransaction(int transactionID) {
		
        String deleteTransactionQuery = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID = ?";
        
        try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(deleteTransactionQuery);) {

            pstmt.setInt(1, transactionID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }


	}

	//Primary method to return Books
	public static String returnBooks(int bookID, int userID) {
		
		//Proceed further only if Book Status is issued
		if(BookServices.bookStatus(bookID, "ISSUED")) {
			
			//Get transaction ID recorded when Book was issued
			int transactionID = getFirstTransactionID(bookID,userID);
			if (transactionID != -1) {
				
				//Update transaction with Return Date
				if(updateTransaction(transactionID) == 1) {
					
					//Change status of Book to Available
					if(BookServices.changeBookStatus("AVAILABLE", bookID, transactionID) == 1) {
						return "Success";
					}
				}else {return "Unable to update transaction";}	
			}else {return "No records of the book being issued to this user";}
	    }else {return "Book in available status, cannot be returned";}
		return null;
	}

	//Method to Update transaction with return date
	private static int updateTransaction(int transactionID) {
		String updateTransactionQuery = "UPDATE TRANSACTIONS SET RETURN_DATE = ? WHERE TRANSACTION_ID = ?";
        try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(updateTransactionQuery);) {

            pstmt.setString(1, Utility.formatDate(Calendar.getInstance().getTime()));
            pstmt.setInt(2, transactionID);
            int i =pstmt.executeUpdate();
            return i;
            
        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}		
        return -1;
	}
	
	//Method to retrieve transaction ID recorded at Issue of Book
	private static int getFirstTransactionID(int bookID, int userID) {
		String getTransactionIDQuery = "SELECT TRANSACTION_ID FROM TRANSACTIONS WHERE BOOK_ID = ? AND USER_ID = ? AND RETURN_DATE IS NULL";
		try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(getTransactionIDQuery);) {
			pstmt.setInt(1, bookID);
            pstmt.setInt(2, userID);
            
            try (ResultSet rs = pstmt.executeQuery()) {
            	if (rs.next() == false) {return -1;}
                else {
                	return rs.getInt("TRANSACTION_ID");
                }
            }catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}		
		}catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}	  		
        return -1;    	
	}

}
