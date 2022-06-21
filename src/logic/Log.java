package logic;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Log {
	
	/*
	 *entry: The entry of the log into the ActivityLog.txt
	 *status: status of the log, e.g. purchase, cancelled
	 *payment: Payment type for purchase
	 *date: the date that the log was registered
	 *productsInfo: the product registered in the log
	 *userId: the user's id that purchased an item
	 *postalCode: the user's postal code
	 *quantity: the quantity of the product registered
	 */
	
	private String entry;
	private String status;
	private String payment;
	private Date date;
	private Product productsInfo;
	private int userId;
	private String postalCode;
	private int quantity;
	
	
	public Log(String status, String payment, Product productsInfo, int userId, String postalCode, int quantity, String theDate) {
		this.status = status;
		this.payment = payment;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.date = dateFormat.parse(theDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.productsInfo = productsInfo;
		this.userId = userId;
		this.postalCode = postalCode;
		this.quantity = quantity;
		entry = this.userId + ", " + this.postalCode + ", " + this.productsInfo.getBarcode() + ", " + this.productsInfo.getRetailPrice() + 
				", " + this.quantity +", "+ this.status + ", " + this.payment +", " + dateFormat.format(date);
	}

	//getters and setters of the internal variables of the object
	public String getStatus() {
		return status;
	}

	public String getPayment() {
		return payment;
	}

	public Date getDate() {
		return date;
	}

	public String getEntry() {
		return entry;
	}

	public Product getProductsInfo() {
		return productsInfo;
	}

	public int getUserId() {
		return userId;
	}

	public String getPostalCode() {
		return postalCode;
	}
	
	public void saveLog() {
		DataManager save = new DataManager("ActivityLog.txt");
		save.addData(entry);
	}
	
	public int getQuantity() {
		return quantity;
	}
}
