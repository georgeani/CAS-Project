package gui;

import logic.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Color;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Admin admin;
	public Customer customer;
	private Login login;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		
		//the window cannot be resized
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 653);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//instantiating the login class in order to get the available usernames
		login = new Login();
		ArrayList<String> usernamesList = login.getUsernames();
		String[] toJList = new String[usernamesList.size()];
		ArrayList<Role> roles = login.getRoles();
		
		//creating the list with all the the usernames
		JList<String> username = new JList<>(usernamesList.toArray(toJList));
		username.setBackground(Color.WHITE);
		username.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		username.setBounds(314, 257, 159, 201);
		contentPane.add(username);
		
		//The welcoming Label in the top of the window
		JLabel welcomeLabel = new JLabel("Welcome to the Computer Accessory Store");
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		welcomeLabel.setBounds(186, 50, 439, 91);
		contentPane.add(welcomeLabel);
		
		//the username Label on top of the list
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameLabel.setBounds(353, 203, 86, 27);
		contentPane.add(usernameLabel);
		
		//the log-in button used to log the user
		JButton loginButton = new JButton("Log In");
		loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		loginButton.setBounds(314, 493, 159, 27);
		contentPane.add(loginButton);
		
		/*
		 * This is the action triggered by the pressing the "Log In"
		 * button. It checks whether the username belongs to a customer or to
		 * an administrator. It then creates the proper object and loads the proper GUI
		 * JFrame before destroying this JFrame.
		 */
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = username.getSelectedIndex();
				if(roles.get(index).equals(Role.ADMIN)) {
					login.createAppropriateUser(Role.ADMIN, index, username.getSelectedValue());
					admin = login.getAdmin();
					AdminInterfaceGUI ainterface = new AdminInterfaceGUI(admin);
					ainterface.setVisible(true);
					dispose();
				} else if(roles.get(index).equals(Role.CUSTOMER)) {
					login.createAppropriateUser(Role.CUSTOMER, index, username.getSelectedValue());
					customer = login.getCustomer();
					CustomerInterfaceGUI customer2 = new CustomerInterfaceGUI(customer);
					customer2.setVisible(true);
					dispose();
				}
			}
		});
	}
	
}
