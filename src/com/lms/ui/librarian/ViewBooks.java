//Frame used by Librarian to view books and filter them based on genre, availability and ISBN Code
//Called from LibrarianSection.java

package com.lms.ui.librarian;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.service.BookServices;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ViewBooks extends JFrame {

	private JPanel contentPane;
	//public static JTable table = new JTable();
	private JTable table;
	private JTextField tf_ISBN;

	/**
	 * Launch the application.
	 
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
	}*/

	/**
	 * Create the frame.
	 */
	public ViewBooks() {
		//String genre, status, isbn;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 668, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 632, 288);
		contentPane.add(scrollPane);
		
		try {
			
			//Primary View Books method from Book Services 
			table = new JTable(BookServices.viewBooks("","",""));
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		JLabel lblViewBooks = new JLabel("View Books");
		lblViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViewBooks.setBounds(273, 0, 110, 29);
		contentPane.add(lblViewBooks);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(10, 58, 48, 14);
		contentPane.add(lblGenre);
		
		JLabel lblAvailability = new JLabel("Availability");
		lblAvailability.setBounds(185, 58, 63, 14);
		contentPane.add(lblAvailability);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(383, 58, 48, 14);
		contentPane.add(lblIsbn);
		
		tf_ISBN = new JTextField();
		tf_ISBN.setBounds(415, 55, 119, 20);
		contentPane.add(tf_ISBN);
		tf_ISBN.setColumns(10);
		
		//Status Drop Down
		String[] statuses = { "", "AVAILABLE","ISSUED"};
		final JComboBox<String> comboBox_status = new JComboBox<String>(statuses);
		comboBox_status.setBounds(253, 54, 96, 22);
		contentPane.add(comboBox_status);
		
		//Genre Drop Down
		String[] genres = { "", "Romance","Fiction","Crime","Mystry","Non-Fiction","Other"};
		final JComboBox<String> comboBox_genre = new JComboBox<String>(genres);
		comboBox_genre.setBounds(49, 54, 89, 22);
		contentPane.add(comboBox_genre);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String genre = comboBox_genre.getItemAt(comboBox_genre.getSelectedIndex());
					String status = comboBox_status.getItemAt(comboBox_status.getSelectedIndex());
					String isbn = tf_ISBN.getText();
					
					//Primary View Books method from Book Services 
					table = new JTable(BookServices.viewBooks(genre,status,isbn));
					scrollPane.setViewportView(table);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnRefresh.setBounds(553, 54, 89, 23);
		contentPane.add(btnRefresh);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(20, 402, 89, 23);
		contentPane.add(btnClose);
		

		

	}
}
