//All interaction with Database with regards to Tables User and User_Membership;

package com.lms.service;

import com.lms.db.util.SQLConnection;
import com.lms.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserTools {
	Connection conn = null;
	
	public UserTools() {
		conn = SQLConnection.dbConnector();
	}
	//public boolean librarianLogin(String username, String password) {return true;}
	
	public int addLibrarian(User userObj) {
		int i = 0;
		String sql = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			int userID = getLastUserID() + 1;
			if (userID == 0) {return i;}
			else {
				st.setInt(1, userID);
				st.setString(2, userObj.getName());
				st.setString(3, userObj.getUserName());
				st.setString(4, userObj.getPassword());
				st.setString(5, userObj.getEmail());
				st.setString(5, userObj.getPhoneNumber());
				st.setString(5, userObj.getType());
				i = st.executeUpdate();
				
				st.close();
				conn.close();
			}	
				
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return i;
		
	}
	
	public String checkMembership(int userID) {return "GOLD";}
	
	private int getLastUserID() {

		String sqlQuery = "SELECT MAX(USER_ID) AS LAST_USER_ID FROM USERS;";
		try {
			PreparedStatement pst = conn.prepareStatement(sqlQuery);
			ResultSet rs = pst.executeQuery(sqlQuery);
			
			pst.close();
			conn.close();
			if (rs.wasNull()) {return 0;} else {return rs.getInt("LAST_USER_ID");}
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return -1;
		}
				
	}
	
}
