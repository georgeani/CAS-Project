package logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Basket {
	
	/*
	 * total: the total cost of all products purchased
	 * user: the customer that this basket belongs to
	 * products: the products he has chosen'
	 * quantityOfProducts: an ArrayList of the quantities of each product
	 */
	private double total = 0;
	private Customer user;
	private ArrayList<Product> products = new ArrayList<>();
	private ArrayList<Integer> quantityOfProducts = new ArrayList<>();
	
	public Basket(Customer user) {
		this.setUser(user);
	}

	//the getters and setters of Basket's internal variables
	public double getTotal() {
		calcTotal();
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}
	
	public void addProduct(Product product, int quantity) {
		products.add(product);
		quantityOfProducts.add(quantity);
		calcTotal();
	}
	
	public ArrayList<Product> getProducts(){
		return products;
	}
	
	public ArrayList<Integer> getQuantities(){
		return quantityOfProducts;
	}
	
	//calculating the total cost of all products
	private void calcTotal() {
		total = 0;
		if(!products.isEmpty() && !quantityOfProducts.isEmpty()) {
			for(int i=0; i<products.size();i++) {
				total = total + (products.get(i).getRetailPrice() * quantityOfProducts.get(i));
			}
		} else total = 0;
	}
	
	//removing an item from a basket
	public void removeItem(Product product) {
		
		for (int i = 0; i < products.size(); i++) {
			
			if (products.get(i).getBarcode().equals(product.getBarcode())) {
				
				products.remove(i);
				quantityOfProducts.remove(i);
			}
			
		}
	}
	
	//cancelling the basket
	public void cancelled() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		
		for(int i=0;i<products.size();i++) {
			Log log = new Log("cancelled", "", products.get(i), user.getUserID(), user.getPostcode(), quantityOfProducts.get(i), dateFormat.format(date));
			log.saveLog();
		}
		
		products.clear();
		quantityOfProducts.clear();
	}
	
	//saving the contents of the basket for later
	public void saveForLater() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();

		for(int i=0;i<products.size();i++) {
			Log log = new Log("saved", "", products.get(i), user.getUserID(), user.getPostcode(), quantityOfProducts.get(i), dateFormat.format(date));
			log.saveLog();
		}
		products.clear();
		quantityOfProducts.clear();
	}
	
	//buying the contents of the basket, it can be bought by both payment methods
	public boolean buy(Payment payment) {
		boolean success = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String paymentType = null;
		ArrayList<Log> logs = new ArrayList<>();
		ArrayList<String> original = new ArrayList<>();
		ArrayList<String> newOne = new ArrayList<>();
		
		if ((payment)instanceof CreditCard) {
			paymentType = "Credit Card";
		} else {
			paymentType = "PayPal";
		}
		
		
		for(int i=0;i<products.size();i++) {
			System.out.println(products.size());
			
			if(products.get(i).getQuantity()>=quantityOfProducts.get(i)) {
				
				Log log = new Log("purchased", paymentType, products.get(i), user.getUserID(), user.getPostcode(), quantityOfProducts.get(i), dateFormat.format(date));
				logs.add(log);
				String orig = products.get(i).toString();
				original.add(orig);
				System.out.println(orig);
				
				int q = products.get(i).getQuantity() - quantityOfProducts.get(i);
				products.get(i).setQuantity(q);
				newOne.add(products.get(i).toString());
				
				System.out.println(products.get(i).toString());
				
				success = true;
			} else {
				success = false;
				break;
			}
		}
		
		if (success) {
			DataManager save = new DataManager("Stock.txt");
			for (int i = 0; i < original.size(); i++) {
				System.out.println(original.get(i));
				System.out.println(newOne.get(i));
				save.modifyData(original.get(i), newOne.get(i));
				logs.get(i).saveLog();
			}
			
			products.clear();
			quantityOfProducts.clear();
		}
		
		return success;
	}
	
}
