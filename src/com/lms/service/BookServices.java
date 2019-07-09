//All interaction with Database with regards to Table Books and Book_Details

package com.lms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.lms.db.util.SQLConnection;
import com.lms.model.Book;

public class BookServices {
	
	
	public static int isIsbnPresent(String ISBN) {
		String checkISBNQuery = "SELECT COUNT(*) FROM BOOK_DETAILS WHERE ISBN = ?";
		try (Connection conn = SQLConnection.dbConnector();
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
	
	public int addBookDetails (Book bookObj) {
		
		String insertBookDetails = "INSERT INTO BOOK_DETAILS VALUES (?,?,?,?,?,?)";
        try (Connection conn = SQLConnection.dbConnector(); 
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

	public int addBook(String ISBN, int quantity) {
		 int i = 0;
	     int bookID = getLastBookID() + 1;
	     System.out.println(bookID);
	     
	     if (bookID == 0) {
	            return i;
	     } else {
	        	
	        	String insertNewUserQuery = "INSERT INTO BOOKS VALUES(?,?,?)";
	            try (Connection conn = SQLConnection.dbConnector(); 
	            		PreparedStatement pstmt = conn.prepareStatement(insertNewUserQuery);) {
	            	
	            	while(i<quantity) {
	            		//System.out.println(i);
	            		bookID = bookID + 1;
	            		pstmt.setInt(1, bookID);
	            		pstmt.setString(2, "AVAILABLE");
	            		pstmt.setString(3, ISBN);
	            		i = i + pstmt.executeUpdate();
	            		
	            	}
	            	return i;

	            } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
	     }
	     return i;
	}
	
	private int getLastBookID() {
		String getLastBookIDQuery = "SELECT MAX(BOOK_ID) FROM BOOKS";
        try (Connection conn = SQLConnection.dbConnector(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(getLastBookIDQuery)) {

            
            if (rs.next() == false) {return 0;}
            else {
            	return rs.getInt("MAX(BOOK_ID)");
            }

        } catch (SQLException e) {JOptionPane.showMessageDialog(null, e);}
        return -1;
	}

	public static DefaultTableModel viewBooks(String genre, String status, String isbn) 
			throws SQLException{
		
		
		String getAllBooksQuery = "SELECT BOOK_ID, TITLE , AUTHOR, PUBLISHER, PRICE, GENRE, BOOKS.ISBN, STATUS FROM BOOKS, BOOK_DETAILS WHERE BOOKS.ISBN = BOOK_DETAILS.ISBN";
		if (!genre.contentEquals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOK_DETAILS.GENRE = '" + genre + "'";
		}
		if(!status.equals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOKS.STATUS = '" + status + "'";
		}
		if(!isbn.equals("")) {
			getAllBooksQuery = getAllBooksQuery + " AND BOOKS.ISBN = '" + isbn + "'";
		}
		
		
		
		try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(getAllBooksQuery);
				ResultSet rs = pstmt.executeQuery()) {
			ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector < String > columnNames = new Vector < String > ();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector < Vector < Object >> data = new Vector < Vector < Object >> ();
            while (rs.next()) {
                Vector < Object > vector = new Vector < Object > ();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        
		}
		return null;
	}

	public static boolean bookStatus(int bookID, String status) {
		String isBookAvailableQuery = "SELECT COUNT(*) FROM BOOKS WHERE BOOK_ID = ? AND STATUS = ?";
		try (Connection conn = SQLConnection.dbConnector();
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

	public static int changeBookStatus(String status, int bookID , int transactionID) {
		String updateBookStatusQuery = "UPDATE BOOKS SET STATUS = ? WHERE BOOK_ID = ?";
		
		try (Connection conn = SQLConnection.dbConnector(); 
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

	
	public static DefaultTableModel viewIssuedBooks() 
			throws SQLException{
		String getAllIssuedBooksQuery = "SELECT B.BOOK_ID , BD.TITLE, U.USER_ID , U.NAME, T.ISSUE_DATE FROM BOOKS AS B, BOOK_DETAILS AS BD, USERS AS U, TRANSACTIONS AS T WHERE B.STATUS = 'ISSUED' AND B.BOOK_ID = T.BOOK_ID AND B.ISBN = BD.ISBN AND U.USER_ID = T.USER_ID";
		try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(getAllIssuedBooksQuery);
				ResultSet rs = pstmt.executeQuery()) {
			ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector < String > columnNames = new Vector < String > ();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector < Vector < Object >> data = new Vector < Vector < Object >> ();
            while (rs.next()) {
                Vector < Object > vector = new Vector < Object > ();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        
		}
		return null;
	}
	
}
