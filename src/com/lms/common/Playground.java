package com.lms.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lms.db.util.SQLConnection;

public class Playground {

	public static void main(String[] args) {
		
		System.out.println(validateID(null));
		
		
		
		
		
		
		/*Connection conn = SQLConnection.dbConnector();
		try {
			String getLastUserIDQuery = "SELECT MAX(USER_ID) FROM USERS";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(getLastUserIDQuery); 
			int lastUserID = rs.getInt("MAX(USER_ID)");
			System.out.println(lastUserID);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	
	
	public static boolean validateID(String inputID) {
		try {
			int id = Integer.parseInt(inputID);
			return true;
		}
		catch (NumberFormatException ex) {
			return "Enter a valid number";
		}
	}

}
