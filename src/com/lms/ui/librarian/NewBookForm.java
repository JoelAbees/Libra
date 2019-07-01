//Frame Librarian use to add book details if book not already present in DB
//CalledFrom AddBook.java

package com.lms.ui.librarian;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class NewBookForm extends JFrame {

	private JPanel contentPane;
	private JTextField tf_bookTitle;
	private JTextField tf_Author;
	private JTextField tf_Publisher;
	private JTextField tf_price;
	private JTextField tf_genre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBookForm frame = new NewBookForm();
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
	public NewBookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewBookForm = new JLabel("New Book Form");
		lblNewBookForm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewBookForm.setBounds(172, 29, 118, 26);
		contentPane.add(lblNewBookForm);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(64, 92, 48, 14);
		contentPane.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAuthor.setBounds(64, 127, 48, 14);
		contentPane.add(lblAuthor);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPublisher.setBounds(64, 167, 63, 14);
		contentPane.add(lblPublisher);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrice.setBounds(64, 208, 48, 14);
		contentPane.add(lblPrice);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGenre.setBounds(64, 248, 48, 14);
		contentPane.add(lblGenre);
		
		tf_bookTitle = new JTextField();
		tf_bookTitle.setBounds(169, 90, 96, 20);
		contentPane.add(tf_bookTitle);
		tf_bookTitle.setColumns(10);
		
		tf_Author = new JTextField();
		tf_Author.setBounds(169, 125, 96, 20);
		contentPane.add(tf_Author);
		tf_Author.setColumns(10);
		
		tf_Publisher = new JTextField();
		tf_Publisher.setBounds(169, 165, 96, 20);
		contentPane.add(tf_Publisher);
		tf_Publisher.setColumns(10);
		
		tf_price = new JTextField();
		tf_price.setBounds(169, 206, 96, 20);
		contentPane.add(tf_price);
		tf_price.setColumns(10);
		
		tf_genre = new JTextField();
		tf_genre.setBounds(169, 246, 96, 20);
		contentPane.add(tf_genre);
		tf_genre.setColumns(10);
		
		JButton btnRegisterNewBook = new JButton("Register New Book");
		btnRegisterNewBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegisterNewBook.setBounds(147, 288, 143, 52);
		contentPane.add(btnRegisterNewBook);
	}
}
