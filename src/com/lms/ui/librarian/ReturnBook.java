//Frame used by Librarian to return books
//Called from LibrarianSection.java

package com.lms.ui.librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
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
	}

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
		
		textField = new JTextField();
		textField.setBounds(221, 73, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(221, 129, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturnBook.setBounds(163, 187, 127, 36);
		contentPane.add(btnReturnBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 252, 89, 23);
		contentPane.add(btnBack);
	}

}
