//Frame for Librarian to Login.
//Called from FirstPage.java

package com.lms.ui.librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LibrarianLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tf_librarianUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianLogin frame = new LibrarianLogin();
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
	public LibrarianLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibrarianLoginForm = new JLabel("Librarian Login Form");
		lblLibrarianLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLibrarianLoginForm.setBounds(154, 31, 141, 26);
		contentPane.add(lblLibrarianLoginForm);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(120, 95, 83, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(120, 135, 83, 14);
		contentPane.add(lblPassword);
		
		tf_librarianUserName = new JTextField();
		tf_librarianUserName.setBounds(227, 93, 96, 20);
		contentPane.add(tf_librarianUserName);
		tf_librarianUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(227, 133, 96, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(178, 194, 89, 35);
		contentPane.add(btnLogin);
	}

}
