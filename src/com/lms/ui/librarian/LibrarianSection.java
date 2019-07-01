//Frame that provides Librarian Functionality
//Called from LibrarianLogin.java
//Calls to AddBook.java , ViewBook.java, ViewIssuedBook.java , IssueBook.java , ReturnBook.java

package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

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
		btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddBooks.setBounds(140, 75, 147, 40);
		contentPane.add(btnAddBooks);
		
		JButton btnViewBooks = new JButton("View Books");
		btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewBooks.setBounds(139, 126, 148, 38);
		contentPane.add(btnViewBooks);
		
		JButton btnIssueBooks = new JButton("Issue Books");
		btnIssueBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIssueBooks.setBounds(140, 175, 147, 40);
		contentPane.add(btnIssueBooks);
		
		JButton btnViewIssuedBooks = new JButton("View Issued Books");
		btnViewIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewIssuedBooks.setBounds(139, 226, 148, 38);
		contentPane.add(btnViewIssuedBooks);
		
		JButton btnReturnBooks = new JButton("Return Book");
		btnReturnBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReturnBooks.setBounds(139, 281, 148, 40);
		contentPane.add(btnReturnBooks);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogOut.setBounds(139, 334, 148, 38);
		contentPane.add(btnLogOut);
	}

}