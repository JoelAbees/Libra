//Landing frame of the Application. 
//Called from Main.java
//Calls to AdminLogin.java and LibrarianLogin.java

package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class FirstPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
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
	public FirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLibraryManagement = new JLabel("Library Management");
		lblLibraryManagement.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLibraryManagement.setBounds(157, 39, 139, 28);
		contentPane.add(lblLibraryManagement);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdminLogin.setBounds(160, 98, 123, 41);
		contentPane.add(btnAdminLogin);
		
		JButton btnLibrarianLogin = new JButton("Librarian Login");
		btnLibrarianLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLibrarianLogin.setBounds(157, 162, 126, 41);
		contentPane.add(btnLibrarianLogin);
	}
}
