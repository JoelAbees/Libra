//All interaction with Database with regards to Table Books and Book_Details

package com.lms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.lms.common.Utility;
import com.lms.db.util.SQLConnection;
import com.lms.model.Book;

public class BookServices {
	
	//Method to verify if Book is previously registered in BOOK_DETAILS table
	public static int isIsbnPresent(String ISBN) {
		String checkISBNQuery = "SELECT COUNT(*) FROM BOOK_DETAILS WHERE ISBN = ?";
		try (Connection conn = SQLConnection.getDBConnection();
        		PreparedStatement pstmt = conn.prepareStatement(checkISBNQuery);) {
			
			pstmt.setString(1, ISBN);
			
			try (ResultSet rs = pstmt.executeQuery()) {

                int isbnCount = rs.getInt("COUNT(*)");
                if (isbnCount == 1) {return 1;} 
                else {return 0;}


            } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}		
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
		
		return -1;
	}
	
	//Method receives Book Object and inserts the attributes of object to DB table BOOK_DETAILS
	public int addBookDetails (Book bookObj) {
		
		String insertBookDetails = "INSERT INTO BOOK_DETAILS VALUES (?,?,?,?,?,?)";
        try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(insertBookDetails);) {

            pstmt.setString(1, bookObj.getISBN());
            pstmt.setString(2, bookObj.getTitle());
            pstmt.setString(3, bookObj.getAuthor());
            pstmt.setString(4, bookObj.getPublisher());
            pstmt.setString(5, bookObj.getPrice());
            pstmt.setString(6, bookObj.getGenre());
            int i = pstmt.executeUpdate();
            return i;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
		return -1;
	}

	//Method to Add book to db table BOOKS.
	public int addBook(String ISBN, int quantity) {
		 int i = 0;
	     int bookID = getLastBookID() + 1;
	     System.out.println(bookID);
	     
	     if (bookID == 0) {
	            return i;
	     } else {
	        	
	        	String insertNewUserQuery = "INSERT INTO BOOKS VALUES(?,?,?)";
	            try (Connection conn = SQLConnection.getDBConnection(); 
	            		PreparedStatement pstmt = conn.prepareStatement(insertNewUserQuery);) {
	            	
	            	while(i<quantity) {
	            		//System.out.println(i);
	            		bookID = bookID + 1;
	            		pstmt.setInt(1, bookID);
	            		pstmt.setString(2, "AVAILABLE"); //Initial status of all newly added books
	            		pstmt.setString(3, ISBN);
	            		i = i + pstmt.executeUpdate();
	            		
	            	}
	            	return i;

	            } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
	     }
	     return i;
	}
	
	//Method to get last BOOK_ID from DB Table BOOKS.
	private int getLastBookID() {
		String getLastBookIDQuery = "SELECT MAX(BOOK_ID) FROM BOOKS";
        try (Connection conn = SQLConnection.getDBConnection(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(getLastBookIDQuery)) {

            
            if (rs.next() == false) {return 0;}
            else {
            	return rs.getInt("MAX(BOOK_ID)");
            }

        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
        return -1;
	}

	//Method to populate JFrame table with Books. Receives three main filters as Parameters. 
	public static DefaultTableModel viewBooks(String genre, String status, String isbn) 
			throws SQLException{
		
		
		String getAllBooksQuery = "SELECT BOOK_ID, TITLE , AUTHOR, PUBLISHER, PRICE, GENRE, BOOKS.ISBN, STATUS FROM BOOKS, BOOK_DETAILS WHERE BOOKS.ISBN = BOOK_DETAILS.ISBN";
		if (!genre.contentEquals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOK_DETAILS.GENRE LIKE '%" + genre + "%'";
		}
		if(!status.equals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOKS.STATUS = '" + status + "'";
		}
		if(!isbn.equals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOKS.ISBN = '" + isbn + "'";
		}
		
		
		
		try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(getAllBooksQuery);
				ResultSet rs = pstmt.executeQuery()) {
			
			
			return Utility.queryResultToTableConverter(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        
		}
		return null;
	}

	//Method returns true if the Book matches the Status passed as parameter
	//Used as additional validation before issuing/returning books
	public static boolean bookStatus(int bookID, String status) {
		String isBookAvailableQuery = "SELECT COUNT(*) FROM BOOKS WHERE BOOK_ID = ? AND STATUS = ?";
		try (Connection conn = SQLConnection.getDBConnection();
        		PreparedStatement pstmt = conn.prepareStatement(isBookAvailableQuery);) {
			
			pstmt.setInt(1, bookID);
			pstmt.setString(2, status);
			
			try (ResultSet rs = pstmt.executeQuery()) {

                int isbnCount = rs.getInt("COUNT(*)");
                if (isbnCount == 1) {return true;} 


            } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}		
		} catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
		
		
		return false;
	}

	//Method to change book status in BOOKS table after issue/return of book
	public static int changeBookStatus(String status, int bookID , int transactionID) {
		String updateBookStatusQuery = "UPDATE BOOKS SET STATUS = ? WHERE BOOK_ID = ?";
		
		try (Connection conn = SQLConnection.getDBConnection(); 
		  		PreparedStatement pstmt = conn.prepareStatement(updateBookStatusQuery);) {
			pstmt.setString(1,status);
            pstmt.setInt(2, bookID);
            int i = pstmt.executeUpdate();
            return i;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			TransactionServices.deleteTransaction(transactionID);
			}
		
		return -1;
	}

	//Method to populate JFrame Table with Issued Books.
	public static DefaultTableModel viewIssuedBooks() 
			throws SQLException{
		String getAllIssuedBooksQuery = "SELECT B.BOOK_ID , BD.TITLE, U.USER_ID , U.NAME, T.ISSUE_DATE FROM BOOKS AS B, BOOK_DETAILS AS BD, USERS AS U, TRANSACTIONS AS T WHERE B.STATUS = 'ISSUED' AND B.BOOK_ID = T.BOOK_ID AND B.ISBN = BD.ISBN AND U.USER_ID = T.USER_ID";
		try (Connection conn = SQLConnection.getDBConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(getAllIssuedBooksQuery);
				ResultSet rs = pstmt.executeQuery()) {
			
			return Utility.queryResultToTableConverter(rs);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        
		}
		return null;
	}
	
}
