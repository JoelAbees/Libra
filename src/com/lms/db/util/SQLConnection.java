package com.lms.db.util;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	Connection conn = null;
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:LibraryManagaement.db");
			JOptionPane.showMessageDialog(null, "Connection Succesfully Established");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
