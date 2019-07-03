//Frame used by admin to Add Librarian to database. 
//Called from AdminSection.java

package com.lms.ui.admin;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.lms.common.Utility;
import com.lms.model.User;
import com.lms.service.UserTools;
import com.lms.ui.main.FirstPage;

public class AddLibrarian extends JFrame {

	private JPanel contentPane;
	private JTextField tf_librarianName;
	private JTextField tf_librarianUserName;
	private JTextField tf_librarianPassword;
	private JTextField tf_librarianEmail;
	private JTextField tf_librarianPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibrarian frame = new AddLibrarian();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddLibrarian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddLibrarian = new JLabel("Add Librarian");
		lblAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddLibrarian.setBounds(211, 11, 122, 26);
		contentPane.add(lblAddLibrarian);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(52, 68, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(52, 113, 75, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(52, 157, 75, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailId.setBounds(284, 68, 57, 14);
		contentPane.add(lblEmailId);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(284, 113, 106, 14);
		contentPane.add(lblPhoneNumber);
		
		tf_librarianName = new JTextField();
		tf_librarianName.setBounds(150, 66, 96, 20);
		contentPane.add(tf_librarianName);
		tf_librarianName.setColumns(10);
		
		tf_librarianUserName = new JTextField();
		tf_librarianUserName.setBounds(150, 111, 96, 20);
		contentPane.add(tf_librarianUserName);
		tf_librarianUserName.setColumns(10);
		
		tf_librarianPassword = new JTextField();
		tf_librarianPassword.setBounds(150, 155, 96, 20);
		contentPane.add(tf_librarianPassword);
		tf_librarianPassword.setColumns(10);
		
		tf_librarianEmail = new JTextField();
		tf_librarianEmail.setBounds(385, 66, 146, 20);
		contentPane.add(tf_librarianEmail);
		tf_librarianEmail.setColumns(10);
		
		tf_librarianPhoneNumber = new JTextField();
		tf_librarianPhoneNumber.setBounds(385, 111, 96, 20);
		contentPane.add(tf_librarianPhoneNumber);
		tf_librarianPhoneNumber.setColumns(10);
		
		JButton btnAddLibrarian = new JButton("Add Librarian");
		btnAddLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_insertLibrarian();
			}
		});
		btnAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddLibrarian.setBounds(218, 196, 123, 43);
		contentPane.add(btnAddLibrarian);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminSection adminSection = new AdminSection();
				adminSection.setVisible(true);
			}
		});
		btnBack.setBounds(10, 270, 89, 23);
		contentPane.add(btnBack);
	}
	
	// triggers addition of new Librarian to DB
	protected void do_insertLibrarian() {
		
		String errorMessage = null;
		User librarian = new User();
		UserTools userTool =  new UserTools();
		
		//Validate if all input fields are filled
		boolean  validateInputResult = Utility.validateInput(tf_librarianName.getText(),tf_librarianUserName.getText(),tf_librarianPassword.getText(),tf_librarianEmail.getText(),tf_librarianPhoneNumber.getText());
		
		
		if (validateInputResult) {
			if(UserTools.isUsernameUnique(tf_librarianUserName.getText())) {
			//Add Librarian to DB
			librarian.setDetails(tf_librarianName.getText(),tf_librarianUserName.getText(),tf_librarianPassword.getText(),tf_librarianEmail.getText(),tf_librarianPhoneNumber.getText(), "LIBRARIAN");
			int i = userTool.addUser(librarian); 
			if (i == 1) { 
				JOptionPane.showMessageDialog(null, "Succesfully added user");
				return;
			} else {errorMessage =  "Adding User Failed";}
			}else {errorMessage =  "Username already taken";}
		}else {errorMessage =  "Please fill all details";}
		
		
		if(errorMessage != null && !errorMessage.isEmpty())  {
			JOptionPane.showMessageDialog(getContentPane(), errorMessage, "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	};
	
}
