//Frame used by Librarian to view Issued Books
//Called from LibrarianSection.java

package com.lms.ui.librarian;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.service.BookServices;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewIssuedBooks extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewIssuedBooks frame = new ViewIssuedBooks();
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
	public ViewIssuedBooks() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewIssuedBooks = new JLabel("View Issued Books");
		lblViewIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewIssuedBooks.setBounds(231, 24, 131, 24);
		contentPane.add(lblViewIssuedBooks);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 81, 539, 318);
		contentPane.add(scrollPane);
		
		try {
			//Primary View Issued Books method from Book Services 
			table = new JTable(BookServices.viewIssuedBooks());
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(23, 412, 89, 23);
		contentPane.add(btnClose);
	}
}
