package logic;

import java.util.ArrayList;

public class AdminInterface {
	
	/*
	 * products: An ArrayList that holds all products
	 * admin: the Admin object that uses the AdminInterface
	 */
	private ArrayList<Product> products = new ArrayList<>();
	@SuppressWarnings("unused")
	private Admin admin;
	
	public AdminInterface(Admin admin) {
		this.admin = admin;
	}
	
	//loads all the products stored in Stock.txt to the ArrayList products
	public void getProducts() {
		products.clear();
		DataManager manager = new DataManager("Stock.txt");
		
		ArrayList<String> temp = manager.getData();
		
		for(int i=0;i<temp.size();i++) {
			String[] tempString = temp.get(i).split(",");
			
			if(tempString[1].replaceAll(" ", "").equals("mouse")) {
				Stock inStock;
				if(Integer.parseInt(tempString[6].replaceAll(" ", ""))>0) {
					inStock = Stock.AVAILABLE;
				}else inStock = Stock.UNAVAILABLE;
				Mouse mouse = new Mouse(tempString[0], tempString[3].substring(1), tempString[4].substring(1), tempString[5].replaceAll(" ",""), Integer.parseInt(tempString[6].replaceAll(" ","")), Double.parseDouble(tempString[7].replaceAll(" ","")), Double.parseDouble(tempString[8].replaceAll(" ","")), inStock, tempString[2].replaceAll(" ",""), Integer.parseInt(tempString[9].replaceAll(" ","")));
				products.add(mouse);
			} else {
				Stock inStock;
				if(Integer.parseInt(tempString[6].replaceAll(" ", ""))>0) {
					inStock = Stock.AVAILABLE;
				}else inStock = Stock.UNAVAILABLE;
				Keyboard keyboard = new Keyboard(tempString[0], tempString[3].substring(1), tempString[4].substring(1), tempString[5].replaceAll(" ",""), Integer.parseInt(tempString[6].replaceAll(" ","")), Double.parseDouble(tempString[7].replaceAll(" ","")), Double.parseDouble(tempString[8].replaceAll(" ","")), inStock, tempString[2].replaceAll(" ",""), tempString[9].replaceAll(" ", ""));
				products.add(keyboard);
			}
		}
	}
	
	//returns the ArrayList of all products
	public ArrayList<Product> getAllProducts(){
		products.clear();
		getProducts();
		return products;
	}
	
	//returns an ArrayList with all the products that much the search parameters
	public ArrayList<Product> search(String barcode, String brand, String color, boolean searchMode) {
		ArrayList<Product> returnProducts = new ArrayList<Product>();
		Product returnProduct;
		products.clear();
		getProducts();
		
		try {
			for(int i=0;i<products.size();i++) {
				if(products.get(i).getBarcode().toLowerCase().contains(barcode.toLowerCase()) && products.get(i).getBrand().toLowerCase().contains(brand.toLowerCase()) && products.get(i).getColor().toLowerCase().contains(color.toLowerCase()) && searchMode) {
					returnProduct = products.get(i);
					returnProducts.add(returnProduct);
				} else if(products.get(i).getBarcode().toLowerCase().equals(barcode.toLowerCase())) {
					returnProduct = products.get(i);
					returnProducts.add(returnProduct);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return returnProducts;
	}
	
	//creates a product object with the information that the user has added
	public Product createProduct(String barcode, String typeProduct, String type, String brand, String color, String connection, int quantity, double initial, double retail, String layoutOrButtons) {
		Product product = null;
		Stock inStock = Stock.UNAVAILABLE;
		if(quantity>0) inStock = Stock.AVAILABLE;
		
		if(typeProduct.toLowerCase().equals("mouse")) {
			Mouse mouse = new Mouse(barcode, brand, color, connection, quantity, initial, retail, inStock, type, Integer.parseInt(layoutOrButtons));
			product = mouse;
		}else if(typeProduct.toLowerCase().equals("keyboard")) {
			Keyboard keyboard = new Keyboard(barcode, brand, color, connection, quantity, initial, retail, inStock, type, layoutOrButtons);
			product = keyboard;
		}
		
		return product;
	}
	
	//saves the product created in the Stock.txt
	public void saveProduct(Product product) {
		DataManager save = new DataManager("Stock.txt");
		
		if((product)instanceof Mouse) {
			Mouse finalProduct = (Mouse) product;
			save.addDataEnd(finalProduct.toString());
		} else if((product)instanceof Keyboard) {
			Keyboard finalProduct = (Keyboard) product;
			save.addDataEnd(finalProduct.toString());
		}
	}
	
	//it is used to modify an existing product in the database
	public void modifyProduct(Product product, String barcode, String brand,String type, String color, String connection, int quantity, double initial, double retail, String layoutOrButtons) {
		DataManager data = new DataManager("Stock.txt");
		if((product)instanceof Mouse) {
			Mouse mouse = (Mouse)product;
			String orig = mouse.toString();
			mouse.setBarcode(barcode);
			mouse.setBrand(brand);
			mouse.setType(type);
			mouse.setColor(color);
			mouse.setConnectionType(connection);
			mouse.setQuantity(quantity);
			mouse.setRetailPrice(retail);
			mouse.setOriginalPrice(initial);
			mouse.setButtonsNum(Integer.parseInt(layoutOrButtons));
			data.modifyData(orig, mouse.toString());
		}else {
			Keyboard keyboard = (Keyboard)product;
			String orig = keyboard.toString();
			keyboard.setBarcode(barcode);
			keyboard.setBrand(brand);
			keyboard.setType(type);
			keyboard.setColor(color);
			keyboard.setConnectionType(connection);
			keyboard.setQuantity(quantity);
			keyboard.setRetailPrice(retail);
			keyboard.setOriginalPrice(initial);
			keyboard.setLayout(layoutOrButtons);
			data.modifyData(orig, keyboard.toString());
		}
	}
	
	//loads the log from the ActivityLog.txt
	public ArrayList<Log> seeLog(){
		DataManager getLog = new DataManager("ActivityLog.txt");
		ArrayList<String> logs = getLog.getData();
		ArrayList<Log> finalLogs = new ArrayList<Log>();
		
		for(int i=0;i<logs.size();i++) {
			String[] temp = logs.get(i).split(",");
			
			Log temp2 = new Log(temp[5].replaceAll(" ", ""), temp[6].replaceAll(" ", ""), new Product(temp[2].replaceAll(" ", ""), "", "", "", 0, 0, Double.parseDouble(temp[3].replaceAll(" ", "")), Stock.AVAILABLE),Integer.parseInt(temp[0].replaceAll(" ", "")), temp[1].substring(1), Integer.parseInt(temp[4].replaceAll(" ", "")), temp[7].replaceAll(" ", ""));
			finalLogs.add(temp2);
		}
		
		return finalLogs;
	}
	
}
