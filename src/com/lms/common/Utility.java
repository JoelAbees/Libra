package com.lms.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Utility {
	
	//Method that takes variable number of arguments and check if they are null/empty
	public static boolean validateInput(String ...inputTexts) {
		for (int i=0; i<=inputTexts.length-1; i++) {
			if(inputTexts[i] == null || "".equals(inputTexts[i])) {
				return false;
			}
		}
		return true;
		
	}
	
	//Method to get ID of user/librarian/book check if its a numeral and then return formatted integer
	public static int validateID(String inputID) {
		try {
			int id = Integer.parseInt(inputID);
			return id;
		}
		catch (NumberFormatException ex) {
			return -1;
		}
	}
	
	//Method to format Java Date to String
	//used to formate Issue Date, Return Date
	public static String formatDate(Date date) {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(new Date());
	
	}

	//Method that converts Query result from DB to Jframe Table Model
	//used to view Librarians, View Books and View Issued Books. 
	public static DefaultTableModel queryResultToTableConverter(ResultSet rs) throws SQLException {

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
	
			
		}	
}
