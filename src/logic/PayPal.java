package logic;

public class PayPal extends Payment{
	
	//email: the email used as a payment method.
	private String email;

	public PayPal(double total, int amountOfProducts, String email) {
		super(total, amountOfProducts);
		this.setEmail(email);
	}

	//the getters and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
