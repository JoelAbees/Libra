//Frame that gives functionality of Admin. 
//Called from AdminLogin.java
//Calls to AddLibrarian.java , ViewLibrarian.java , DeleteLibrarian.java

package com.lms.ui.admin;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.ui.main.FirstPage;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminSection frame = new AdminSection();
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
	public AdminSection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminSection = new JLabel("Admin Section");
		lblAdminSection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAdminSection.setBounds(178, 11, 93, 29);
		contentPane.add(lblAdminSection);
		
		JButton btnAddLibrarian = new JButton("Add Librarian");
		btnAddLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddLibrarian addLibrarian = new AddLibrarian();
				addLibrarian.setVisible(true);
			}
		});
		btnAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddLibrarian.setBounds(157, 64, 135, 29);
		contentPane.add(btnAddLibrarian);
		
		JButton btnViewLibrarian = new JButton("View Librarian");
		btnViewLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewLibrarian viewLibrarian = new ViewLibrarian();
				viewLibrarian.setVisible(true);
			}
		});
		btnViewLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewLibrarian.setBounds(157, 110, 135, 29);
		contentPane.add(btnViewLibrarian);
		
		JButton btnDeleteLibrarian = new JButton("Delete Librarian");
		btnDeleteLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DeleteLibrarian deleteLibrarian = new DeleteLibrarian();
				deleteLibrarian.setVisible(true);
			}
		});
		btnDeleteLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteLibrarian.setBounds(157, 159, 135, 29);
		contentPane.add(btnDeleteLibrarian);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FirstPage firstPage = new FirstPage();
				firstPage.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogOut.setBounds(157, 206, 135, 29);
		contentPane.add(btnLogOut);
	}

}
