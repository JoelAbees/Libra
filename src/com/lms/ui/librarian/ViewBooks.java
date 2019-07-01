//Frame used by Librarian to view books and filter them based on genre, availability and ISBN Code
//Called from LibrarianSection.java

package com.lms.ui.librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ViewBooks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_genre;
	private JTextField tf_availability;
	private JTextField tf_ISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks frame = new ViewBooks();
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
	public ViewBooks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 632, 288);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblViewBooks = new JLabel("View Books");
		lblViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewBooks.setBounds(273, 0, 110, 29);
		contentPane.add(lblViewBooks);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(10, 58, 48, 14);
		contentPane.add(lblGenre);
		
		tf_genre = new JTextField();
		tf_genre.setBounds(45, 55, 96, 20);
		contentPane.add(tf_genre);
		tf_genre.setColumns(10);
		
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setBounds(185, 58, 63, 14);
		contentPane.add(lblAvailability);
		
		tf_availability = new JTextField();
		tf_availability.setBounds(247, 55, 96, 20);
		contentPane.add(tf_availability);
		tf_availability.setColumns(10);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(383, 58, 48, 14);
		contentPane.add(lblIsbn);
		
		tf_ISBN = new JTextField();
		tf_ISBN.setBounds(415, 55, 119, 20);
		contentPane.add(tf_ISBN);
		tf_ISBN.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(553, 54, 89, 23);
		contentPane.add(btnRefresh);
	}

}
