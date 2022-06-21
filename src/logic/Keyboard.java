package logic;

import java.math.BigDecimal;

public class Keyboard extends Product{
	
	/*
	 * type: the type of the keyboard, e.g. gaming keyboard
	 * layout: The layout of the keyboard*/
	private String type;
	private String layout;

	public Keyboard(String barcode, String brand, String color, String connectionType, int quantity,
			double originalPrice, double retailPrice, Stock inStock, String type, String layout) {
		super(barcode, brand, color, connectionType, quantity, originalPrice, retailPrice, inStock);
		this.type = type;
		this.layout = layout;
	}

	//the getters and setters of the internal variables of the Keyboard class
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	//the toString implementation that makes sure that the String is formatted in the way it should be
	public String toString() {
		
		String formatted1 = null;
		String formattString = null;
		
		
		BigDecimal dec1 = new BigDecimal(getOriginalPrice());
		
		if (dec1.scale() == 1 && Double.parseDouble(dec1.subtract(new BigDecimal(dec1.intValue())).toPlainString()) > 0) {
			formatted1 = getOriginalPrice() + "0";
		} else {
			formatted1 = "" + getOriginalPrice();
		}
		
		BigDecimal dec2 = new BigDecimal(getRetailPrice());
		if (dec2.scale() == 1 && Double.parseDouble(dec2.subtract(new BigDecimal(dec2.intValue())).toPlainString()) > 0) {
			formattString = getRetailPrice() + "0";
		}else {
			formattString = "" + getRetailPrice();
		}
		
		return getBarcode() + ", " + "keyboard" + ", " + type + ", " + getBrand() + ", " + getColor() + ", " + getConnectionType()+", " + getQuantity() + ", " + formatted1 + ", "+formattString + ", " + layout;
	}

}
