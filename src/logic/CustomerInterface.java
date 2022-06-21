package logic;

import java.util.ArrayList;

public class CustomerInterface {
	
	/*The variables used in the class
	 * availableProducts: all the available products
	 * totalCost: the total cost of all products chosen
	 * basket: The customer's basket that has the products he has selected
	 * */
	private ArrayList<Product> availableProducts = new ArrayList<>();
	private double totalCost = 0;
	private Customer customer;
	private Basket basket = null;
	
	public CustomerInterface(Customer customer) {
		this.customer = customer;
		basket = new Basket(customer);
	}
	
	//this methods finds all of the available products that a user has
	public void getAvailableProducts() {
		availableProducts.clear();
		DataManager manager = new DataManager("Stock.txt");
		
		ArrayList<String> temp = manager.getData();
		
		for(int i=0;i<temp.size();i++) {
			String[] tempString = temp.get(i).split(",");
			
			if(tempString[1].replaceAll(" ", "").equals("mouse")) {
				Stock inStock;
				if(Integer.parseInt(tempString[6].replaceAll(" ", ""))>0) {
					inStock = Stock.AVAILABLE;
					Mouse mouse = new Mouse(tempString[0], tempString[3].substring(1), tempString[4].substring(1), tempString[5].replaceAll(" ",""), Integer.parseInt(tempString[6].replaceAll(" ","")), Double.parseDouble(tempString[7].replaceAll(" ","")), Double.parseDouble(tempString[8].replaceAll(" ","")), inStock, tempString[2].replaceAll(" ",""), Integer.parseInt(tempString[9].replaceAll(" ","")));
					availableProducts.add(mouse);
				}
			} else {
				Stock inStock;
				if(Integer.parseInt(tempString[6].replaceAll(" ", ""))>0) {
					inStock = Stock.AVAILABLE;
					Keyboard keyboard = new Keyboard(tempString[0], tempString[3].substring(1), tempString[4].substring(1), tempString[5].replaceAll(" ",""), Integer.parseInt(tempString[6].replaceAll(" ","")), Double.parseDouble(tempString[7].replaceAll(" ","")), Double.parseDouble(tempString[8].replaceAll(" ","")), inStock, tempString[2].replaceAll(" ",""), tempString[9].replaceAll(" ", ""));
					availableProducts.add(keyboard);
				}
			}
		}
	}
	
	//this method returns an arrayList with all the products that meet the user's search parameters
	public ArrayList<Product> search(String brand, String layout, String appliedFilters){
		
		ArrayList<Product> results = new ArrayList<Product>();
		
		availableProducts.clear();
		getAvailableProducts();
		
		for(int i=0;i<availableProducts.size();i++) {
			
			if(appliedFilters.equals("1") && availableProducts.get(i).getBrand().toLowerCase().contains(brand.toLowerCase())) {
				results.add(availableProducts.get(i));
			}else if((availableProducts.get(i))instanceof Keyboard) {
				Keyboard keyboard = (Keyboard)availableProducts.get(i);
				
				if(keyboard.getLayout().equals(layout) && appliedFilters.equals("2")) {
					results.add(availableProducts.get(i));
				} else if(keyboard.getLayout().equals(layout) && appliedFilters.equals("3") && availableProducts.get(i).getBrand().toLowerCase().contains(brand.toLowerCase())) {
					results.add(availableProducts.get(i));
				}
				
			}
		}
		
		return results;
		
	}
	
	//returns the ArrayList of all the available products
	public ArrayList<Product> seeAvailableProducts(){
		availableProducts.clear();
		getAvailableProducts();
		return availableProducts;
	}
	
	//used to put a product in the basket
	public void selectProduct(Product product, int quantity) {
		basket.addProduct(product, quantity);
	}
	
	//used to remove a product from the basket
	public void removeProduct(Product product) {
		basket.removeItem(product);
	}

	//returns the total cost
	public double getTotalCost() {
		return totalCost;
	}
	
	//recalculates the total cost of the items selected
	public void calcCost() {
		totalCost = basket.getTotal();
	}

	//returns the customer's basket
	public Basket getBasket() {
		return basket;
	}
	
	//pays for the items selected
	public boolean buy(Payment payment) {
		boolean success = basket.buy(payment);
		System.out.println("Payment " + success);
		return success;
	}
	
	//saves the items selected
	public void save() {
		basket.saveForLater();
	}
	
	//cancels the items selected
	public void cancel() {
		basket.cancelled();
	}

	//returns the customer in the class
	public Customer getCustomer() {
		return customer;
	}
}
