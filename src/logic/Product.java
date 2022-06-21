package logic;

import java.util.Comparator;

public class Product {
	
	/*
	 * barcode: The barcode of each product
	 * quantity: The quantity of the product
	 * brand: The brand of the product
	 * color: the color of the product
	 * retailPrice: the retail price of the product
	 * originalPrice: the original price of the product
	 * inStock: whether the product is in stock or not
	 * connectionType: how is each product connecting to the computer
	 */
	private String barcode;
	private int quantity;
	private String brand;
	private String color;
	private double retailPrice;
	private double originalPrice;
	private Stock inStock;
	private String connectionType;
	
	public Product(String barcode, String brand, String color, String connectionType, int quantity, double originalPrice, double retailPrice, Stock inStock) {
		this.barcode = barcode;
		this.quantity = quantity;
		this.brand = brand;
		this.color = color;
		this.retailPrice = retailPrice;
		this.originalPrice = originalPrice;
		this.connectionType = connectionType;
		this.inStock = inStock;
	}
	
	//getters and setters of the internal variables
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public double getRetailPrice() {
		return retailPrice;
	}
	
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public Stock getInStock() {
		return inStock;
	}

	public void setInStock(Stock inStock) {
		this.inStock = inStock;
	}
	
	//used to compare the Products by their quantity
	public static Comparator<Product> QuantityComparator = new Comparator<Product>() {

		public int compare(Product p1, Product p2) {
		   int quantity1 = p1.getQuantity();
		   int quantity2 = p2.getQuantity();

		   return quantity2-quantity1;

	    }
	};

}
