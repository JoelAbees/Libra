//Frame for Admin to Login. 
//Called from FirstPage.java

package com.lms.ui.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.model.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tf_adminUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminLoginForm = new JLabel("Admin Login Form");
		lblAdminLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdminLoginForm.setBounds(167, 26, 121, 30);
		contentPane.add(lblAdminLoginForm);
		
		JLabel lblEnterUsername = new JLabel("Enter UserName");
		lblEnterUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterUsername.setBounds(90, 100, 95, 16);
		contentPane.add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnterPassword.setBounds(90, 135, 95, 22);
		contentPane.add(lblEnterPassword);
		
		tf_adminUserName = new JTextField();
		tf_adminUserName.setBounds(223, 99, 121, 17);
		contentPane.add(tf_adminUserName);
		tf_adminUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(223, 137, 121, 20);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userName = tf_adminUserName.getText();
				String password = passwordField.getText();
				
				if(Admin.adminLogin(userName, password)) {
					dispose();
					AdminSection AdminSection = new AdminSection();
					AdminSection.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong");
					}
				
				
				}
			
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(190, 206, 89, 30);
		contentPane.add(btnLogin);
	}
}
