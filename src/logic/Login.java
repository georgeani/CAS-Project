package logic;

import java.util.ArrayList;

public class Login {
	
	/*
	 * usernames: An ArrayList with all the usernames of all registered users
	 * roles: An ArrayList with all the roles of all registered users
	 * customer: The customer object that can be created if the user chooses to login with a customer username
	 * admin: The admin object that can be created if the user chooses to login with an admin username*/
	private static ArrayList<String> usernames = new ArrayList<>();
	private ArrayList<Role> roles = new ArrayList<>();
	private Customer customer;
	private Admin admin;
	
	public Login() {
		DataManager accesslogin = new DataManager("UserAccounts.txt");
		ArrayList<String> temp = accesslogin.getData();
		String[] temp2 = null;
		
		for(int i=0;i<temp.size();i++) {
			temp2 = temp.get(i).split(",");

			String temp3 = temp2[1].replaceAll("\\s", "");
			usernames.add(temp3);
			
			if(temp2[6].replaceAll("\\s", "")
					.equals("admin")) {
				roles.add(Role.ADMIN);
			}else {
				roles.add(Role.CUSTOMER);
			}
		}
	}
	
	//the getter methods of all the internal variables of the class
	public ArrayList<String> getUsernames() {
		return usernames;
	}

	public ArrayList<Role> getRoles() {
		return roles;
	}
	
	//the method that depending the user input it either produces a customer or an admin object
	public void createAppropriateUser(Role role, int index, String username) {
		if(role.equals(Role.ADMIN)) {
			DataManager accesslogin = new DataManager("UserAccounts.txt");
			ArrayList<String> temp = accesslogin.getData();
			
			String[] temp2 = temp.get(index).split(",");
			admin = new Admin(Integer.parseInt(temp2[0].replaceAll("\\s", "")), username, temp2[2].replaceAll("\\s", ""), Integer.parseInt(temp2[3].replaceAll("\\s", "")), temp2[4].substring(1), temp2[5].replaceAll("\\s", ""));
			
		} else {
			DataManager accesslogin = new DataManager("UserAccounts.txt");
			ArrayList<String> temp = accesslogin.getData();
			
			String[] temp2 = temp.get(index).split(",");
			customer = new Customer(Integer.parseInt(temp2[0].replaceAll("\\s", "")), username, temp2[2].replaceAll("\\s", ""), Integer.parseInt(temp2[3].replaceAll("\\s", "")), temp2[4].substring(1), temp2[5].replaceAll("\\s", ""));
		}
	}

	public Customer getCustomer() {
		return customer;
	}


	public Admin getAdmin() {
		return admin;
	}
	
}
