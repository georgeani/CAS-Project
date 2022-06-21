package logic;

public class Customer extends User{
	
	/*
	 * userID: the user's id
	 * username: the customer's username
	 * postcode: the customer's post code
	 * houseNumber: the customer's housenumber
	 * city: the customer's city
	 * */
	private int userID;
	private String username;
	private String surname;
	private String postcode;
	private int houseNumber;
	private String city;

	public Customer(int userID, String username, String surname, int houseNumber, String postcode, String city) {
		super(userID, username, surname, postcode, houseNumber, city, Role.CUSTOMER);
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.postcode = postcode;
		this.houseNumber = houseNumber;
		this.city = city;
	}

	
	//the getter methods for the customer object internal variables
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
	
}
