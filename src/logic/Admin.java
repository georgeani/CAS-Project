package logic;

public class Admin extends User {
	
	
	/*
	 *userID: the admin's id
	 *username: the admin's username */
	private int userID;
	private String username;
	
	public Admin(int userID, String username, String surname, int houseNumber,String postcode, String city) {
		super(userID, username, surname, postcode, houseNumber, city, Role.ADMIN);
		// TODO Auto-generated constructor stub
		this.userID = userID;
		this.username = username;
	}
	
	//getters of the internal variables of the user class
	public int getUserId() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	 
}
