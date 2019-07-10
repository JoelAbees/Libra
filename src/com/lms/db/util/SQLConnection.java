package com.lms.db.util;

import javax.swing.JOptionPane;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLConnection {
	Connection conn = null;
	public static Connection getDBConnection() {
		try {
			FileInputStream fis=new FileInputStream("Resources/connection.prop"); 
	        Properties p=new Properties (); 
	        p.load(fis); 
			Class.forName((String) p.get ("Dname"));
			Connection conn = DriverManager.getConnection((String) p.get ("URL"));
			//JOptionPane.showMessageDialog(null, "Connection Successfully Established");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
