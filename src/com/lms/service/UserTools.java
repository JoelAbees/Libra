//All interaction with Database with regards to Tables User and User_Membership;

package com.lms.service;

import com.lms.db.util.SQLConnection;
import com.lms.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserTools {
	
	
	//Method to Add Librarian to DB. Call from AddLibrarian Jframe.
	public int addUser(User userObj) {
		int i = 0;
		try {
			Connection conn = SQLConnection.dbConnector();
			String insertNewUserQuery = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(insertNewUserQuery);
			int userID = getLastUserID() + 1;
			JOptionPane.showMessageDialog(null, "The userID is " + userID );
			
			if (userID == 0) {return i;}
			else {
				pstmt.setInt(1, userID);
				pstmt.setString(2, userObj.getName());
				pstmt.setString(3, userObj.getUserName());
				pstmt.setString(4, userObj.getPassword());
				pstmt.setString(5, userObj.getEmail());
				pstmt.setString(6, userObj.getPhoneNumber());
				pstmt.setString(7, userObj.getType());
				i = pstmt.executeUpdate();
				
				pstmt.close();
				conn.close();
			}	
				
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return i;
		
	}
	
	//Method to get Last User ID from Database. Called from AddLibrarian()
	private int getLastUserID() {
		
		try {
			Connection conn = SQLConnection.dbConnector();
			String getLastUserIDQuery = "SELECT MAX(USER_ID) FROM USERS";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(getLastUserIDQuery); 
			int lastUserID = rs.getInt("MAX(USER_ID)");
			stmt.close();
			conn.close();
			return lastUserID;
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return -1;
		}
				
	}

	public static boolean isUsernameUnique(String inputUsername) {
		Connection conn = SQLConnection.dbConnector();
		String checkUsernameQuery = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(checkUsernameQuery);
			pstmt.setString(1, inputUsername);
			ResultSet rs = pstmt.executeQuery(); 
			int lastUserID = rs.getInt("COUNT(*)");
			pstmt.close();
			conn.close();
			if (lastUserID == 0) {
				return true;
			}else {return false;}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
		
		
	}
	
	//Method to pass table containing Librarian Details from DB. Called from ViewLibrarian Jframe.
	public static DefaultTableModel viewUsers(String usertype)
	        throws SQLException {
		String getAllLibrarianQuery = "SELECT * FROM USERS WHERE TYPE = ?";
		Connection conn = SQLConnection.dbConnector();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(getAllLibrarianQuery);
			pstmt.setString(1, usertype);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Method to get Name of the user by passing userID and userType
	public static String getNameFromUserID(int userID, String userType) {
		try {
			Connection conn = SQLConnection.dbConnector();
			String getNameQuery = "SELECT NAME FROM USERS WHERE USER_ID = ? AND TYPE = ?";
			PreparedStatement pstmt = conn.prepareStatement(getNameQuery);
			pstmt.setInt(1, userID);
			pstmt.setString(2, userType);
			ResultSet rs = pstmt.executeQuery(); 
			if (rs.next() == false) { 
				return "ERROR NO USER"; 
				} else {
					
						String userName = rs.getString("NAME");
						pstmt.close();
						conn.close();
						return userName;

				      }
					

				

		
		}catch (SQLException e) {
	
				JOptionPane.showMessageDialog(null, e);
				return "ERROR";
			}
				
		}
	
	//Method to delete user from DB. DeleteLibrarian.java
	public static int deleteUser(int userID, String userType) {
		int i=-1;
		String deleteUserQuery="DELETE FROM USERS WHERE USER_ID = ? AND TYPE = ?";
		Connection conn = SQLConnection.dbConnector();
		try {
			PreparedStatement pstmt = conn.prepareStatement(deleteUserQuery);
			pstmt.setInt(1, userID);
			pstmt.setString(2, userType);
			i=pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return -1;
		}
		return i;
	}
}
