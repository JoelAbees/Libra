//Frame used by Librarian to return books
//Called from LibrarianSection.java

package com.lms.ui.librarian;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.common.Utility;
import com.lms.service.TransactionServices;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField tf_bookID;
	private JTextField tf_userID;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ReturnBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReturnBook.setBounds(175, 11, 115, 25);
		contentPane.add(lblReturnBook);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookId.setBounds(124, 75, 48, 14);
		contentPane.add(lblBookId);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserId.setBounds(124, 131, 48, 14);
		contentPane.add(lblUserId);
		
		tf_bookID = new JTextField();
		tf_bookID.setBounds(221, 73, 96, 20);
		contentPane.add(tf_bookID);
		tf_bookID.setColumns(10);
		
		tf_userID = new JTextField();
		tf_userID.setBounds(221, 129, 96, 20);
		contentPane.add(tf_userID);
		tf_userID.setColumns(10);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookID = tf_bookID.getText();
				String userID = tf_userID.getText();
				do_ReturnBook(bookID,userID);
			}
		});
		btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturnBook.setBounds(163, 187, 127, 36);
		contentPane.add(btnReturnBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LibrarianSection librarianSection = new LibrarianSection();
				librarianSection.setVisible(true);
			}
		});
		btnBack.setBounds(10, 252, 89, 23);
		contentPane.add(btnBack);
	}
	
	private void do_ReturnBook(String strBookID, String strUserID) {

		String errorMessage = null;
		// Validate user Input and convert text to integer
		if(Utility.validateInput(strBookID, strUserID)) {
			int bookID = Utility.validateID(strBookID);
			int userID = Utility.validateID(strUserID);
			
			if (bookID != -1 && userID != -1) {
				
				//primary method to return book from transaction class
				String IssueBookStatus = TransactionServices.returnBooks(bookID , userID);
				
				if (IssueBookStatus.equals("Success")){
					JOptionPane.showMessageDialog(null, "Succesfully Returned Book");
					
				}else {errorMessage = IssueBookStatus;}	
				
			}else {errorMessage = "please enter valid IDs";}
		}else {errorMessage = "please fill all details";}
		
		if(errorMessage != null && !errorMessage.isEmpty())  {
			JOptionPane.showMessageDialog(getContentPane(), errorMessage, "", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

}
