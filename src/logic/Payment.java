package logic;

public class Payment {
	
	/*
	 * total: The total price for all the items purchased
	 * amountOfProducts: an integer of all the different products purchased*/
	private double total;
	private int amountOfProducts;
	
	public Payment(double total, int amountOfProducts) {
		this.setTotal(total);
		this.setAmountOfProducts(amountOfProducts);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAmountOfProducts() {
		return amountOfProducts;
	}

	public void setAmountOfProducts(int amountOfProducts) {
		this.amountOfProducts = amountOfProducts;
	}
	
}
