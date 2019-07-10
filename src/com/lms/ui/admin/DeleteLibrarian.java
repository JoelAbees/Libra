//Frame for Admin to delete Librarian. 
//Called from AdminSection.java

package com.lms.ui.admin;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.common.Utility;
import com.lms.service.UserServices;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteLibrarian extends JFrame {


	private JPanel contentPane;
	private JTextField tf_LibrarianID;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteLibrarian frame = new DeleteLibrarian();
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
	public DeleteLibrarian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteLibrarian = new JLabel("Delete Librarian");
		lblDeleteLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeleteLibrarian.setBounds(156, 28, 110, 27);
		contentPane.add(lblDeleteLibrarian);
		
		JLabel lblEnterLibrarianId = new JLabel("Enter Librarian ID");
		lblEnterLibrarianId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterLibrarianId.setBounds(60, 86, 124, 27);
		contentPane.add(lblEnterLibrarianId);
		
		tf_LibrarianID = new JTextField();
		tf_LibrarianID.setBounds(213, 90, 96, 20);
		contentPane.add(tf_LibrarianID);
		tf_LibrarianID.setColumns(10);
		
		JButton btnDeleteLlibrarian = new JButton("Delete lLibrarian");
		btnDeleteLlibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				do_deleteLibrarian();
				}
		});
		btnDeleteLlibrarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteLlibrarian.setBounds(154, 153, 133, 38);
		contentPane.add(btnDeleteLlibrarian);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminSection adminSection = new AdminSection();
				adminSection.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
	}
	
	private void do_deleteLibrarian() {
		String errorMessage = null;
		
		//validate the input to check if integer and return value in int format
		int librarianID = Utility.validateID(tf_LibrarianID.getText());
		if(librarianID != -1) {
			
			//Check is such user Exist
			String librarianName = UserServices.getNameFromUserID(librarianID , "LIBRARIAN");
			
			if(librarianName.equals("ERROR NO USER")) {
				errorMessage = "This user doesn't exist";
			}else if(librarianName.equals("ERROR")){
				errorMessage = "Unable to delete user";
			}else {
				
				//Pre-delete confirmation. Returns 0 if user selects yes. 
				int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you really want to delete " + librarianName + "?" ,"Delete", JOptionPane.YES_NO_OPTION);
				if(confirmDelete == 0) {
					
					//Delete user from DB
					int postDeleteConfirmation = UserServices.deleteUser(librarianID,"LIBRARIAN");	
					if (postDeleteConfirmation == 1) {
						JOptionPane.showMessageDialog(null, "Succesfully Deleted user");
						return;
					} else {errorMessage = "Unable to delete user";}
				}
			}		
		}else {	errorMessage = "Please enter valid userID";}
		
		if(errorMessage != null && !errorMessage.isEmpty())  {
			JOptionPane.showMessageDialog(getContentPane(), errorMessage, "", JOptionPane.WARNING_MESSAGE);
			return;
		}


	}

}
