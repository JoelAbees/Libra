//Frame librarian use to Issue Book
//Called from LibrarianSection.java

package com.lms.ui.librarian;

//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lms.common.Utility;
import com.lms.model.Librarian;
import com.lms.service.TransactionServices;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField tf_bookID;
	private JTextField tf_UserID;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIssueBook = new JLabel("Issue Book");
		lblIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIssueBook.setBounds(181, 11, 112, 24);
		contentPane.add(lblIssueBook);
		
		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookId.setBounds(69, 78, 85, 24);
		contentPane.add(lblBookId);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserId.setBounds(69, 124, 85, 24);
		contentPane.add(lblUserId);
		
		tf_bookID = new JTextField();
		tf_bookID.setBounds(197, 81, 96, 20);
		contentPane.add(tf_bookID);
		tf_bookID.setColumns(10);
		
		tf_UserID = new JTextField();
		tf_UserID.setBounds(197, 127, 96, 20);
		contentPane.add(tf_UserID);
		tf_UserID.setColumns(10);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookID = tf_bookID.getText();
				String userID = tf_UserID.getText();
				String issueBookStatus =  Librarian.issueBooks(bookID,userID);
				
				if(!issueBookStatus.equals("Success")){
					JOptionPane.showMessageDialog(getContentPane(), issueBookStatus, "", JOptionPane.WARNING_MESSAGE);
				};
				
			}
		});
		btnIssueBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIssueBook.setBounds(164, 183, 112, 35);
		contentPane.add(btnIssueBook);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LibrarianSection librarianSection = new LibrarianSection();
				librarianSection.setVisible(true);
			}
		});
		btnBack.setBounds(10, 227, 89, 23);
		contentPane.add(btnBack);
	}
	
	

}
