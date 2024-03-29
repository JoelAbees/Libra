//Frame for Admin to delete Librarian. 
//Called from AdminSection.java

package com.lms.ui.admin;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.common.Utility;
import com.lms.model.Admin;
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
				
				String deleteLibrarianStatus = Admin.deleteLibrarian(tf_LibrarianID.getText());
				if(!deleteLibrarianStatus.equals("Success")) {
					JOptionPane.showMessageDialog(getContentPane(), deleteLibrarianStatus, "", JOptionPane.WARNING_MESSAGE);
				}
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
	
	
}
