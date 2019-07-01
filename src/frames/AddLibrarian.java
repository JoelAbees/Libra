//Frame used by admin to Add Librarian to database. 
//Called from AdminSection.java

package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddLibrarian extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLibrarian frame = new AddLibrarian();
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
	public AddLibrarian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddLibrarian = new JLabel("Add Librarian");
		lblAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddLibrarian.setBounds(211, 11, 122, 26);
		contentPane.add(lblAddLibrarian);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(52, 68, 48, 14);
		contentPane.add(lblName);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(52, 113, 75, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(52, 157, 75, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailId.setBounds(284, 68, 57, 14);
		contentPane.add(lblEmailId);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(284, 113, 106, 14);
		contentPane.add(lblPhoneNumber);
		
		textField = new JTextField();
		textField.setBounds(150, 66, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 111, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 155, 96, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(385, 66, 146, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(385, 111, 96, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAddLibrarian = new JButton("Add Librarian");
		btnAddLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddLibrarian.setBounds(218, 196, 123, 43);
		contentPane.add(btnAddLibrarian);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 270, 89, 23);
		contentPane.add(btnBack);
	}

}
