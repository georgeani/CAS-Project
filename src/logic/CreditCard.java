package logic;

public class CreditCard extends Payment{
	
	/*
	 * cardNumber: the cards 16 digit long code
	 * cvc: the security number at the back of the card
	 * */
	private String cardNumber;
	private String cvc;

	public CreditCard(double total, int amountOfProducts, String cardNumber, String cvc) {
		super(total, amountOfProducts);
		this.setCardNumber(cardNumber);
		this.setCvc(cvc);
	}

	//the getters and setters of the internal variables
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
	
}
