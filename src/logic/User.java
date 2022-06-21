package logic;

public abstract class User {
	
	/*
	 * userID: the user's ID
	 * username: the user's username
	 * surname: the user's surname
	 * postcode: the user's postcode
	 * houseNumber: the user's house number
	 * city: the user's city
	 * role: the user's role*/
	private int userID;
	private String username;
	private String surname;
	private String postcode;
	private int houseNumber;
	private String city;
	private Role role;
	
	public User(int userID, String username, String surname, String postcode, int houseNumber, String city, Role role) {
		this.userID = userID;
		this.surname = surname;
		this.username = username;
		this.postcode = postcode;
		this.houseNumber = houseNumber;
		this.city = city;
		this.role = role;
	}
	
	//the setters of the class
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public int getHouseNumber() {
		return houseNumber;
	}
	
	public String getCity() {
		return city;
	}
	
	public Role getRole() {
		return role;
	}
}
