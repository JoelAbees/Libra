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
        int userID = getLastUserID() + 1;
        if (userID == 0) {
            return i;
        } else {

            String insertNewUserQuery = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?)";
            try (Connection conn = SQLConnection.dbConnector(); 
            		PreparedStatement pstmt = conn.prepareStatement(insertNewUserQuery);) {

                pstmt.setInt(1, userID);
                pstmt.setString(2, userObj.getName());
                pstmt.setString(3, userObj.getUserName());
                pstmt.setString(4, userObj.getPassword());
                pstmt.setString(5, userObj.getEmail());
                pstmt.setString(6, userObj.getPhoneNumber());
                pstmt.setString(7, userObj.getType());
                i = pstmt.executeUpdate();


            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
            return i;
        }
    }

    //Method to get Last User ID from Database. Called from AddLibrarian()
    private int getLastUserID(){
    	
        String getLastUserIDQuery = "SELECT MAX(USER_ID) FROM USERS";
        try (Connection conn = SQLConnection.dbConnector(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(getLastUserIDQuery)) {

            int lastUserID = rs.getInt("MAX(USER_ID)");
            return lastUserID;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //throw new Exception("");
            return -1;
        }

    }

    //Method to check if the userName entered by admin is unique
    public static boolean isUsernameUnique(String inputUsername) {

        String checkUsernameQuery = "SELECT COUNT(*) FROM USERS WHERE USERNAME = ?";
   
        try (Connection conn = SQLConnection.dbConnector();
        		PreparedStatement pstmt = conn.prepareStatement(checkUsernameQuery);) {
        	
            pstmt.setString(1, inputUsername);

            try (ResultSet rs = pstmt.executeQuery()) {

                int lastUserID = rs.getInt("COUNT(*)");
                if (lastUserID == 0) {return true;} else {return false;}

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                return false;
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    

    //Method to pass table containing Librarian Details from DB. Called from ViewLibrarian Jframe.
    public static DefaultTableModel viewUsers(String usertype)
    throws SQLException {
        String getAllLibrarianQuery = "SELECT * FROM USERS WHERE TYPE = ?";

        try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(getAllLibrarianQuery);) {

            pstmt.setString(1, usertype);
            
            try (ResultSet rs = pstmt.executeQuery()) {

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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    //Method to get Name of the user by passing userID and userType
    public static String getNameFromUserID(int userID, String userType) {
        String getNameQuery = "SELECT NAME FROM USERS WHERE USER_ID = ? AND TYPE = ?";
        try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(getNameQuery);) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, userType);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() == false) {return "ERROR NO USER";}
                else {
                    String userName = rs.getString("NAME");
                    return userName;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                return "ERROR";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return "ERROR";
        }
    }

    //Method to delete user from DB. DeleteLibrarian.java
    public static int deleteUser(int userID, String userType) {
        int i = -1;
        String deleteUserQuery = "DELETE FROM USERS WHERE USER_ID = ? AND TYPE = ?";
        
        try (Connection conn = SQLConnection.dbConnector(); 
        		PreparedStatement pstmt = conn.prepareStatement(deleteUserQuery);) {

            pstmt.setInt(1, userID);
            pstmt.setString(2, userType);
            i = pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return -1;
        }

        return i;
    }
    
    //method to validate login and return userID
    public static int userLogin(String username, String password) {
    	String checkUserLoginQuery = "SELECT USER_ID FROM USERS WHERE USERNAME = ? and PASSWORD = ?";
    	try (Connection conn = SQLConnection.dbConnector();
        		PreparedStatement pstmt = conn.prepareStatement(checkUserLoginQuery);) {
    		pstmt.setString(1, username);
    		pstmt.setString(2, password);
    		
    		try (ResultSet rs = pstmt.executeQuery()) {
    			if (rs.next() == false) {return -1;}
                else {
                	return rs.getInt("USER_ID");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    	}catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e);
    	}
    	
    return -1;
    }

}