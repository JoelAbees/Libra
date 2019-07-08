//Frame that provides Librarian Functionality
//Called from LibrarianLogin.java
//Calls to AddBook.java , ViewBook.java, ViewIssuedBook.java , IssueBook.java , ReturnBook.java

package com.lms.ui.librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.ui.admin.AddLibrarian;
import com.lms.ui.main.FirstPage;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibrarianSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianSection frame = new LibrarianSection();
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
	public LibrarianSection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibrarianSection = new JLabel("Librarian Section");
		lblLibrarianSection.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLibrarianSection.setBounds(161, 27, 116, 24);
		contentPane.add(lblLibrarianSection);
		
		JButton btnAddBooks = new JButton("Add Books");
		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddBook addBook = new AddBook();
				addBook.setVisible(true);
			}
		});
		btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddBooks.setBounds(140, 75, 147, 40);
		contentPane.add(btnAddBooks);
		
		JButton btnViewBooks = new JButton("View Books");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooks viewBooks = new ViewBooks();
				viewBooks.setVisible(true);
			}
		});
		btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewBooks.setBounds(139, 126, 148, 38);
		contentPane.add(btnViewBooks);
		
		JButton btnIssueBooks = new JButton("Issue Books");
		btnIssueBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				IssueBook issueBook = new IssueBook();
				issueBook.setVisible(true);
			}
		});
		btnIssueBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIssueBooks.setBounds(140, 175, 147, 40);
		contentPane.add(btnIssueBooks);
		
		JButton btnViewIssuedBooks = new JButton("View Issued Books");
		btnViewIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewIssuedBooks viewIssuedBooks = new ViewIssuedBooks();
				viewIssuedBooks.setVisible(true);
			}
		});
		btnViewIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewIssuedBooks.setBounds(139, 226, 148, 38);
		contentPane.add(btnViewIssuedBooks);
		
		JButton btnReturnBooks = new JButton("Return Book");
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ReturnBook returnBook = new ReturnBook();
				returnBook.setVisible(true);
			}
		});
		btnReturnBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReturnBooks.setBounds(139, 281, 148, 40);
		contentPane.add(btnReturnBooks);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FirstPage firstPage = new FirstPage();
				firstPage.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogOut.setBounds(139, 334, 148, 38);
		contentPane.add(btnLogOut);
	}

}
