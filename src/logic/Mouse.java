package logic;

import java.math.BigDecimal;

public class Mouse extends Product{
	
	/*
	 * type: The type of the product, e.g. gaming mouse
	 * buttonsNum: The number of buttons that the mouse has.
	 */
	private String type;
	private int buttonsNum;

	public Mouse(String barcode, String brand, String color, String connectionType, int quantity, double originalPrice,
			double retailPrice, Stock inStock, String type, int buttonsNum) {
		super(barcode, brand, color, connectionType, quantity, originalPrice, retailPrice, inStock);
		this.type = type;
		this.buttonsNum = buttonsNum;
	}

	//the getters and setters of the internal variables
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getButtonsNum() {
		return buttonsNum;
	}

	public void setButtonsNum(int buttonsNum) {
		this.buttonsNum = buttonsNum;
	}
	
	//A special implementation of the toString method.
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
		} else {
			formattString = "" + getRetailPrice();
		}
		
		return getBarcode() + ", " + "mouse" + ", " + type + ", " + getBrand() + ", " + getColor() + ", " + getConnectionType()+", " + getQuantity() + ", " + formatted1 + ", "+formattString + ", " + buttonsNum;
	}
	
}
