//Frame used by Librarian to Add Books to database. 
//Called from LibrarianSection.java
//Calls to NewBookForm.java

package com.lms.ui.librarian;

//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lms.common.Utility;
import com.lms.service.BookServices;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField tf_ISBN;
	private JTextField tf_quantity;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddBook = new JLabel("Add Book");
		lblAddBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddBook.setBounds(185, 22, 85, 24);
		contentPane.add(lblAddBook);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIsbn.setBounds(123, 74, 48, 14);
		contentPane.add(lblIsbn);
		
		tf_ISBN = new JTextField();
		tf_ISBN.setBounds(193, 71, 96, 20);
		contentPane.add(tf_ISBN);
		tf_ISBN.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(123, 130, 48, 14);
		contentPane.add(lblQuantity);
		
		tf_quantity = new JTextField();
		tf_quantity.setBounds(193, 127, 48, 20);
		contentPane.add(tf_quantity);
		tf_quantity.setColumns(10);
		
		JButton btnAddBooks = new JButton("Add Books");
		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_addBook();
			}
		});
		btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddBooks.setBounds(152, 179, 107, 34);
		contentPane.add(btnAddBooks);
		
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
	
	private void do_addBook() {
		String errorMessage = null;
		String isbn = tf_ISBN.getText();
		String bookQuantity = tf_quantity.getText();
		
		//boolean  validateInputResult = Utility.validateInput(isbn,bookQuantity);
		if(Utility.validateInput(isbn,bookQuantity)) {
			int bookQuantityInt = Utility.validateID(bookQuantity);
			if(bookQuantityInt != -1) {
				
				//Check if Book Details are present
				int isIsbnPresent = BookServices.isIsbnPresent(isbn);
				if (isIsbnPresent == 1) {
					
					//Book details previously present. Add books to DB referencing ISBN
					BookServices bookServices =  new BookServices();
					int bookCount = bookServices.addBook(isbn,bookQuantityInt);

					if (bookCount != 0) {
						JOptionPane.showMessageDialog(null, "Succesfully added " + bookCount + " book details");
						
					}else{errorMessage =  "No books were added";}
					
				}else if (isIsbnPresent == 0) {
					//First registration of a new Book
					NewBookForm newBookForm = new NewBookForm(isbn,bookQuantityInt);
					newBookForm.setVisible(true);
					
					
				}else if (isIsbnPresent == -1) {errorMessage = "Please handle Exception, contact Admin";}
			}else {errorMessage = "please enter a valid quantity";}
		}else {errorMessage = "please fill all details";}
		
		if(errorMessage != null && !errorMessage.isEmpty())  {
			JOptionPane.showMessageDialog(getContentPane(), errorMessage, "", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

}
