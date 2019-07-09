//All interaction with Database with regards to Table Transaction;

package com.lms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import com.lms.common.Utility;
import com.lms.db.util.SQLConnection;
import com.lms.model.Transaction;

public class TransactionServices {

	public static String issueBooks(int bookID, int userID) {
		
		if(BookServices.bookStatus(bookID, "AVAILABLE")) {
			String userMembership = UserTools.checkMembership(userID);
			if (!userMembership.equals("NO SUCH USER")) {
				if(isUserEligible(userID, userMembership)) {
					
					int transactionID = getLastTransactionID() + 1;
					System.out.println("transactionID: " + transactionID);
					if (transactionID != 0) {
					
						//Transaction transaction = new Transaction();
						
						Preferences prefs = Preferences.userNodeForPackage(com.lms.ui.librarian.LibrarianLogin.class);
						int librarianID = prefs.getInt("userId", 0);
						String issueDate = Utility.formatDate(Calendar.getInstance().getTime());
						
						
						Transaction transaction = new Transaction();
						int addTransactionStatus = addTransaction(transaction);
						transaction.setDetails(transactionID, bookID , librarianID , userID , issueDate );
						
						if(addTransaction(transaction) == 1) {
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
		
	private static boolean isUserEligible(int userID, String userMembership) {
		
		int maximumBookCount;
		if (userMembership.equals("GOLD")){maximumBookCount = 5;}
		else if (userMembership.equals("REGULAR")) {maximumBookCount = 3;}
		else {maximumBookCount = 0;}
		
		
		String checkActiveBookCountQuery = "SELECT COUNT(*) FROM TRANSACTIONS WHERE USER_ID = ? AND RETURN_DATE IS NULL";
		try (Connection conn = SQLConnection.dbConnector();
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
	
	private static int getLastTransactionID() {
		String getLastTransactionIDQuery = "SELECT MAX(TRANSACTION_ID) FROM TRANSACTIONS";
        try (Connection conn = SQLConnection.dbConnector(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(getLastTransactionIDQuery)) {

            
            if (rs.next() == false) {return 0;}
            else {
            	return rs.getInt("MAX(TRANSACTION_ID)");
            }

        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
        return -1;
	}
	
	private static int addTransaction(Transaction transactionObj) {
		String insertTransactionDetails = "INSERT INTO TRANSACTIONS VALUES (?,?,?,?,?,?)";
        try (Connection conn = SQLConnection.dbConnector(); 
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

	public static void deleteTransaction(int transactionID) {
		
        String deleteTransactionQuery = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID = ?";
        
        try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(deleteTransactionQuery);) {

            pstmt.setInt(1, transactionID);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }


	}

	public static String returnBooks(int bookID, int userID) {
		if(BookServices.bookStatus(bookID, "ISSUED")) {
			
			int transactionID = getFirstTransactionID(bookID,userID);
			if (transactionID != -1) {
				if(updateTransaction(transactionID) == 1) {
					if(BookServices.changeBookStatus("AVAILABLE", bookID, transactionID) == 1) {
						return "Success";
					}
				}else {return "Unable to update transaction";}	
			}else {return "No records of the book being issued to this user";}
	    }else {return "Book in available status, cannot be returned";}
		return null;
	}

	private static int updateTransaction(int transactionID) {
		String updateTransactionQuery = "UPDATE TRANSACTIONS SET RETURN_DATE = ? WHERE TRANSACTION_ID = ?";
        try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(updateTransactionQuery);) {

            pstmt.setString(1, Utility.formatDate(Calendar.getInstance().getTime()));
            pstmt.setInt(2, transactionID);
            int i =pstmt.executeUpdate();
            return i;
            
        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}		
        return -1;
	}
	
	private static int getFirstTransactionID(int bookID, int userID) {
		String getTransactionIDQuery = "SELECT TRANSACTION_ID FROM TRANSACTIONS WHERE BOOK_ID = ? AND USER_ID = ? AND RETURN_DATE IS NULL";
		try (Connection conn = SQLConnection.dbConnector(); 
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
