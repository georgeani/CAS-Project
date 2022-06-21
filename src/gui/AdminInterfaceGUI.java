package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logic.Admin;
import logic.AdminInterface;
import logic.Keyboard;
import logic.Log;
import logic.Mouse;
import logic.Product;
import logic.Stock;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;

public class AdminInterfaceGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Admin admin;
	private Product preModification = null;
	private Product product;
	private JTable seeAllProductsTable;
	private JTextField barcodeTextField;
	private JTextField quantityField;
	private JTextField brandField;
	private JTextField colorField;
	private JTextField originalPriceField;
	private JTextField retailPriceField;
	
	private ArrayList<Log> logs;
	boolean switchProduct = false;
	private JTextField typeTextField;
	private JTextField layoutTextField;
	private JTextField barcodeSearchTextField;
	private JTextField colorSearchTextField;
	private JTextField brandSearchTextField;
	private JTable searchTable;
	private JTable logTable;
	private JTable modifyAProductTable;
	private JTextField barcodeModifyTextField;
	private JTextField brandModifyTextField;
	private JTextField quantityModifyTextField;
	private JTextField colorModifyTextField;
	private JTextField typeModifyTextField;
	private JTextField originalPriceModifyTextField;
	private JTextField retailPriceModifyTextField;
	private JTextField layoutModifyTextField;
	
	private DefaultTableModel tableModel;
	private JTextField searchBarcodeModifyTextField;
	private JTextField connectionTextField;
	private JTextField connectionModifyTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInterfaceGUI frame = new AdminInterfaceGUI(new Admin(101,
							"user1", "Smith", 12, "LE11 3TU", "Loughborough"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminInterfaceGUI(Admin admin) {
		
		//requesting the an Admin object that is important for the use of this class
		this.admin = admin;
		AdminInterface interfaceUser = new AdminInterface(this.admin);
		
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 909);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contents = new JPanel();
		contents.setBounds(0, 240, 972, 622);
		contentPane.add(contents);
		contents.setLayout(new CardLayout(0, 0));
		
		JPanel addProduct = new JPanel();
		addProduct.setBackground(new Color(204, 0, 0));
		contents.add(addProduct, "name_16880221124100");
		addProduct.setLayout(null);
		
		JLabel addProductLabel = new JLabel("Mouse");
		addProductLabel.setForeground(Color.WHITE);
		addProductLabel.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		addProductLabel.setBounds(409, 36, 116, 32);
		addProduct.add(addProductLabel);
		
		JLabel lblLayoutOrButtons = new JLabel("Number of buttons");
		lblLayoutOrButtons.setForeground(Color.WHITE);
		lblLayoutOrButtons.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblLayoutOrButtons.setBounds(467, 199, 128, 27);
		addProduct.add(lblLayoutOrButtons);
		
		JButton switchTypeButton = new JButton("Change Product Type");
		switchTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//this action changes the type of product that the Administrator adds
				
				switchProduct = !switchProduct;
				if(switchProduct) {
					addProductLabel.setText("Keyboard");
					lblLayoutOrButtons.setText("Layout");
					lblLayoutOrButtons.setBounds(514, 199, 81, 27);
					
				} else {
					addProductLabel.setText("Mouse");
					lblLayoutOrButtons.setText("Number of buttons");
					lblLayoutOrButtons.setBounds(467, 199, 128, 27);
				}
			}
		});
		switchTypeButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		switchTypeButton.setBounds(607, 353, 183, 27);
		addProduct.add(switchTypeButton);
		
		JLabel barcodeLabel = new JLabel("Barcode");
		barcodeLabel.setForeground(Color.WHITE);
		barcodeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		barcodeLabel.setBounds(42, 148, 81, 27);
		addProduct.add(barcodeLabel);
		
		barcodeTextField = new JTextField();
		barcodeTextField.setBounds(135, 151, 116, 22);
		addProduct.add(barcodeTextField);
		barcodeTextField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.WHITE);
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblQuantity.setBounds(42, 193, 81, 27);
		addProduct.add(lblQuantity);
		
		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setForeground(Color.WHITE);
		lblBrand.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBrand.setBounds(42, 233, 81, 27);
		addProduct.add(lblBrand);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblColor.setBounds(42, 273, 81, 27);
		addProduct.add(lblColor);
		
		JLabel lblOriginalPrice = new JLabel("Original Price");
		lblOriginalPrice.setForeground(Color.WHITE);
		lblOriginalPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblOriginalPrice.setBounds(12, 313, 111, 27);
		addProduct.add(lblOriginalPrice);
		
		JLabel lblRetailPrice = new JLabel("Retail Price");
		lblRetailPrice.setForeground(Color.WHITE);
		lblRetailPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblRetailPrice.setBounds(22, 352, 91, 27);
		addProduct.add(lblRetailPrice);
		
		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(135, 196, 116, 22);
		addProduct.add(quantityField);
		
		brandField = new JTextField();
		brandField.setColumns(10);
		brandField.setBounds(135, 236, 116, 22);
		addProduct.add(brandField);
		
		colorField = new JTextField();
		colorField.setColumns(10);
		colorField.setBounds(135, 276, 116, 22);
		addProduct.add(colorField);
		
		originalPriceField = new JTextField();
		originalPriceField.setColumns(10);
		originalPriceField.setBounds(135, 311, 116, 22);
		addProduct.add(originalPriceField);
		
		retailPriceField = new JTextField();
		retailPriceField.setColumns(10);
		retailPriceField.setBounds(135, 355, 116, 22);
		addProduct.add(retailPriceField);
		
		typeTextField = new JTextField();
		typeTextField.setBounds(607, 151, 116, 22);
		addProduct.add(typeTextField);
		typeTextField.setColumns(10);
		
		layoutTextField = new JTextField();
		layoutTextField.setColumns(10);
		layoutTextField.setBounds(607, 196, 116, 22);
		addProduct.add(layoutTextField);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblType.setBounds(514, 148, 81, 27);
		addProduct.add(lblType);
		
		JButton saveNewProductButton = new JButton("Save Product");
		saveNewProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*
				 * this action checks that the user input
				 * is correct and if it is it saves the new product.
				 * If not it tells to the user that some of the
				 * data inputed is wrong.
				 * It also checks whether this barcode is used.
				 * If it is used it informs the user that this is
				 * the case
				 */
				String barcode = barcodeTextField.getText();
				String quantity = quantityField.getText();
				String brand = brandField.getText();
				String origPrice = originalPriceField.getText();
				String retailPrice = retailPriceField.getText();
				String type = typeTextField.getText();
				String layoutOrButtons = layoutTextField.getText();
				String color = colorField.getText();
				String connection = connectionTextField.getText();
				
				boolean correctPricing = false;
				
				if(barcode.isEmpty() || quantity.isEmpty() || brand.isEmpty() || origPrice.isEmpty()
						|| retailPrice.isEmpty() || type.isEmpty() || layoutOrButtons.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Not all data has been inputted");
				} else {
					double initial = 0;
					double retail = 0;
					int q = 0;
					boolean t = false;
					boolean m = true;
					int buttons = 0;
					try {
						initial = Double.parseDouble(origPrice);
						retail = Double.parseDouble(retailPrice);
						q = Integer.parseInt(quantity);
						t = true;
												
						if (BigDecimal.valueOf(initial).scale() <= 2 && BigDecimal.valueOf(retail).scale() <= 2) {
							correctPricing = true;
						}
					}catch(Exception e) {
						t = false;
					}
					
					try {
						buttons = Integer.parseInt(layoutOrButtons);
						m = true;
					}catch(Exception e) {
						m = false;
					}
					
					ArrayList<Product> check = interfaceUser.search(barcode, "", "", false);

					if (check.isEmpty()) {
						if(switchProduct && t && initial >0 && retail>0 && q>=0 && (layoutOrButtons.equals("US") || layoutOrButtons.equals("UK")) && retail > initial && correctPricing &&  barcode.length()== 6) {
							
							int decision = JOptionPane.showConfirmDialog(null, "Do you want to add this product?");
							
							product = interfaceUser.createProduct(barcode, "keyboard", type, brand, color, connection, q, initial, retail, layoutOrButtons);
							
							if (decision == JOptionPane.YES_OPTION) {
								
								interfaceUser.saveProduct(product);
								JOptionPane.showMessageDialog(null, "The product was added successfully");
								 
								barcodeTextField.setText("");
								quantityField.setText("");
								brandField.setText("");
								originalPriceField.setText("");
								retailPriceField.setText("");
								typeTextField.setText("");
								layoutTextField.setText("");
								colorField.setText("");
								connectionTextField.setText("");
								product = null;
							}
							
						} else if(!switchProduct && m && initial > 0 && retail> 0 && q>=0 && buttons >= 0 && retail > initial && correctPricing &&  barcode.length()== 6) {
							product = interfaceUser.createProduct(barcode, "mouse", type, brand, color, connection,
									q, initial, retail, layoutOrButtons);
							
							int decision = JOptionPane.showConfirmDialog(null, "Do you want to add this product?");
							
							if (decision == JOptionPane.YES_OPTION) {
								
								interfaceUser.saveProduct(product);
								
								JOptionPane.showMessageDialog(null, "The product was added successfully");
								
								barcodeTextField.setText("");
								quantityField.setText("");
								brandField.setText("");
								originalPriceField.setText("");
								retailPriceField.setText("");
								typeTextField.setText("");
								layoutTextField.setText("");
								colorField.setText("");
								connectionTextField.setText("");
								product = null;
								
							}
							
						} else {
							JOptionPane.showMessageDialog(null, "The data inputted is wrong");
						}
					} else {
						JOptionPane.showMessageDialog(null, "This item already exists");
					}
				}
			}
		});
		
		saveNewProductButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		saveNewProductButton.setBounds(607, 460, 160, 46);
		addProduct.add(saveNewProductButton);
		
		JLabel lblConnectionType = new JLabel("Connection Type");
		lblConnectionType.setForeground(Color.WHITE);
		lblConnectionType.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblConnectionType.setBounds(467, 233, 128, 27);
		addProduct.add(lblConnectionType);
		
		connectionTextField = new JTextField();
		connectionTextField.setColumns(10);
		connectionTextField.setBounds(607, 236, 116, 22);
		addProduct.add(connectionTextField);
		
		JPanel seeAllProducts = new JPanel();
		seeAllProducts.setBackground(new Color(0, 102, 255));
		seeAllProducts.setLayout(null);
		contents.add(seeAllProducts, "name_16880221124101");
		
		JScrollPane seeAllProductsPane = new JScrollPane();
		seeAllProductsPane.setBounds(12, 13, 948, 596);
		seeAllProducts.add(seeAllProductsPane);
		//seeAllProducts.add(seeAllProductsTable);
		
		JPanel searchProducts = new JPanel();
		searchProducts.setBackground(new Color(102, 102, 255));
		contents.add(searchProducts, "name_16880221124102");
		searchProducts.setLayout(null);
		
		barcodeSearchTextField = new JTextField();
		barcodeSearchTextField.setBounds(38, 96, 116, 22);
		searchProducts.add(barcodeSearchTextField);
		barcodeSearchTextField.setColumns(10);
		
		colorSearchTextField = new JTextField();
		colorSearchTextField.setColumns(10);
		colorSearchTextField.setBounds(38, 220, 116, 22);
		searchProducts.add(colorSearchTextField);
		
		brandSearchTextField = new JTextField();
		brandSearchTextField.setColumns(10);
		brandSearchTextField.setBounds(38, 166, 116, 22);
		searchProducts.add(brandSearchTextField);
		
		JLabel lblNewLabel = new JLabel("Search A Product\r\n");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(421, 13, 145, 52);
		searchProducts.add(lblNewLabel);
		
		JScrollPane searchPane = new JScrollPane();
		searchTable = new JTable();
		searchTable.setEnabled(false);
		searchTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchProducts.add(searchPane);
		searchPane.setBounds(185, 69, 775, 540);
		
		JLabel barcodeSearchLabel = new JLabel("Barcode");
		barcodeSearchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		barcodeSearchLabel.setBounds(67, 69, 58, 22);
		searchProducts.add(barcodeSearchLabel);
		
		JLabel brandSearchLabel = new JLabel("Brand");
		brandSearchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		brandSearchLabel.setBounds(67, 131, 58, 22);
		searchProducts.add(brandSearchLabel);
		
		JLabel colorSearchLabel = new JLabel("Color");
		colorSearchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		colorSearchLabel.setBounds(67, 195, 58, 22);
		searchProducts.add(colorSearchLabel);
		
		JButton searchNewButton = new JButton("Search");
		searchNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//this button triggers the searching parameters for the administrator's search capacities
				String brand = brandSearchTextField.getText();
				String barcode = barcodeSearchTextField.getText();
				String color = colorSearchTextField.getText();
				
				if(brand.isEmpty() && barcode.isEmpty() && color.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The search fields are empty");
				} else {
					ArrayList<Product> products = interfaceUser.search(barcode, brand, color, true);
					Collections.sort(products, Product.QuantityComparator);
					loadProductsTable(products, searchTable, searchPane);
					
				}
			}
		});
		searchNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		searchNewButton.setBounds(38, 281, 116, 52);
		searchProducts.add(searchNewButton);
		
		JPanel seeLog = new JPanel();
		seeLog.setBackground(new Color(102, 153, 153));
		contents.add(seeLog, "name_16880221124103");
		seeLog.setLayout(null);
		
		logTable = new JTable();
		logTable.setEnabled(false);
		JScrollPane log = new JScrollPane();
		log.setBounds(12, 13, 948, 596);
		seeLog.add(log);
		
		JPanel modifyAProduct = new JPanel();
		modifyAProduct.setBackground(new Color(102, 102, 255));
		contents.add(modifyAProduct, "name_16880221124104");
		modifyAProduct.setLayout(null);
		
		modifyAProductTable = new JTable();
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Initial Price","Retail","Characteristics"});
		modifyAProductTable.setModel(tableModel);
		
		modifyAProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSelection = modifyAProductTable.getSelectionModel();
		rowSelection.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {

		    	/*
		    	 * Used to select an element from the modify a product table
		    	 * the contents from the table are then
		    	 * inputed in the text boxes
		    	 * once there you can modify the product and save it.
		    	 */
		    	
		        if (e.getValueIsAdjusting()) return;
		        
		        ListSelectionModel listSelection =
		            (ListSelectionModel)e.getSource();
		        if (listSelection.isSelectionEmpty()) {
		            //no rows are selected
		        } else {
		            int indexOfRow = listSelection.getMinSelectionIndex();
		            
		            
		            barcodeModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 0).toString());
		            typeModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 2).toString());
		            brandModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 3).toString());
		            colorModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 4).toString());
		            connectionModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 5).toString());
		            quantityModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 6).toString());
		            originalPriceModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 7).toString());
		            retailPriceModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 8).toString());
		            layoutModifyTextField.setText(modifyAProductTable.getModel().getValueAt(indexOfRow, 9).toString());
		            
		            String productType = modifyAProductTable.getModel().getValueAt(indexOfRow, 1).toString();
		            
		            Stock stock = Stock.UNAVAILABLE;
		            if(Integer.parseInt(quantityModifyTextField.getText())>0) {
		            	stock = Stock.AVAILABLE;
		            }
		            
		            if(productType.equals("mouse")) {
		            	preModification = new Mouse(barcodeModifyTextField.getText(), brandModifyTextField.getText(), colorModifyTextField.getText(),
		            			connectionModifyTextField.getText(), Integer.parseInt(quantityModifyTextField.getText()),
		            			Double.parseDouble(originalPriceModifyTextField.getText()), Double.parseDouble(retailPriceModifyTextField.getText()),
		            			stock, typeModifyTextField.getText(), Integer.parseInt(layoutModifyTextField.getText()));
		            } else {
		            	preModification = new Keyboard(barcodeModifyTextField.getText(), brandModifyTextField.getText(), colorModifyTextField.getText(),
		            			connectionModifyTextField.getText(), Integer.parseInt(quantityModifyTextField.getText()),
		            			Double.parseDouble(originalPriceModifyTextField.getText()), Double.parseDouble(retailPriceModifyTextField.getText()),
		            			stock, typeModifyTextField.getText(), layoutModifyTextField.getText());
		            }
		            
		        }
		    }
		});
		
		JScrollPane modify = new JScrollPane();
		modify.setBounds(12, 52, 711, 544);
		modifyAProduct.add(modify);
		modify.setViewportView(modifyAProductTable);
		
		barcodeModifyTextField = new JTextField();
		barcodeModifyTextField.setBounds(844, 86, 116, 22);
		modifyAProduct.add(barcodeModifyTextField);
		barcodeModifyTextField.setColumns(10);
		
		brandModifyTextField = new JTextField();
		brandModifyTextField.setColumns(10);
		brandModifyTextField.setBounds(844, 146, 116, 22);
		modifyAProduct.add(brandModifyTextField);
		
		quantityModifyTextField = new JTextField();
		quantityModifyTextField.setColumns(10);
		quantityModifyTextField.setBounds(844, 199, 116, 22);
		modifyAProduct.add(quantityModifyTextField);
		
		colorModifyTextField = new JTextField();
		colorModifyTextField.setColumns(10);
		colorModifyTextField.setBounds(844, 254, 116, 22);
		modifyAProduct.add(colorModifyTextField);
		
		typeModifyTextField = new JTextField();
		typeModifyTextField.setColumns(10);
		typeModifyTextField.setBounds(844, 302, 116, 22);
		modifyAProduct.add(typeModifyTextField);
		
		originalPriceModifyTextField = new JTextField();
		originalPriceModifyTextField.setColumns(10);
		originalPriceModifyTextField.setBounds(844, 348, 116, 22);
		modifyAProduct.add(originalPriceModifyTextField);
		
		retailPriceModifyTextField = new JTextField();
		retailPriceModifyTextField.setColumns(10);
		retailPriceModifyTextField.setBounds(844, 396, 116, 22);
		modifyAProduct.add(retailPriceModifyTextField);
		
		layoutModifyTextField = new JTextField();
		layoutModifyTextField.setColumns(10);
		layoutModifyTextField.setBounds(844, 446, 116, 22);
		modifyAProduct.add(layoutModifyTextField);
		
		JLabel barcodeModifyLabel = new JLabel("Barcode");
		barcodeModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		barcodeModifyLabel.setBounds(776, 88, 56, 16);
		modifyAProduct.add(barcodeModifyLabel);
		
		JLabel brandModifyLabel = new JLabel("Brand");
		brandModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		brandModifyLabel.setBounds(787, 148, 45, 16);
		modifyAProduct.add(brandModifyLabel);
		
		JLabel quantityModifyLabel = new JLabel("Quantity");
		quantityModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		quantityModifyLabel.setBounds(776, 201, 56, 16);
		modifyAProduct.add(quantityModifyLabel);
		
		JLabel colorModifyLabel = new JLabel("Color");
		colorModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		colorModifyLabel.setBounds(787, 256, 45, 16);
		modifyAProduct.add(colorModifyLabel);
		
		JLabel typeModifyLabel = new JLabel("Type");
		typeModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		typeModifyLabel.setBounds(795, 304, 37, 16);
		modifyAProduct.add(typeModifyLabel);
		
		JLabel originalPriceModifyLabel = new JLabel("Original Price");
		originalPriceModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		originalPriceModifyLabel.setBounds(750, 350, 82, 16);
		modifyAProduct.add(originalPriceModifyLabel);
		
		JLabel retailPriceModifyLabel = new JLabel("Retail Price");
		retailPriceModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		retailPriceModifyLabel.setBounds(750, 398, 82, 16);
		modifyAProduct.add(retailPriceModifyLabel);
		
		JLabel layoutModifyLabel = new JLabel("Layout/Buttons");
		layoutModifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		layoutModifyLabel.setBounds(735, 448, 97, 16);
		modifyAProduct.add(layoutModifyLabel);
		
		JButton saveModifyBtn = new JButton("Save");
		saveModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*
				 * this button is used to save the modify parameters from
				 * the user in the database
				 * it also updates the table of the modify a product table
				 */
				
				String barcode = barcodeModifyTextField.getText();
	            String brand = brandModifyTextField.getText();
	            String quantity = quantityModifyTextField.getText();
	            String color = colorModifyTextField.getText();
	            String type = typeModifyTextField.getText();
	            String original = originalPriceModifyTextField.getText();
	            String retail = retailPriceModifyTextField.getText(); 
	            String layout = layoutModifyTextField.getText();
	            String connection = connectionModifyTextField.getText();
	            
	            ArrayList<Product> check = interfaceUser.search(barcode, "", "", false);
	            
	            if (preModification.getBarcode().equals(barcode)) {
					check.clear();
				}
	            
	            if (barcode.isEmpty() || brand.isEmpty() || quantity.isEmpty() || color.isEmpty()
	            		|| type.isEmpty() || original.isEmpty() || retail.isEmpty() || layout.isEmpty() || connection.isEmpty() || barcode.length() != 6 || !check.isEmpty()) {
	            	JOptionPane.showMessageDialog(null, "One or more of the values is empty");
	            } else {
	            	double retailNum = 0;
	            	double origNum = 0;
	            	int q = 0;
	            	boolean conversion = false;
	            	boolean mconversion = false;
	            	int buttons = 0;
	            	
	            	try {
	            		
	            		retailNum = Double.parseDouble(retail);
	            		origNum = Double.parseDouble(original);
	            		q = Integer.parseInt(quantity);
	            		
	            		if (BigDecimal.valueOf(origNum).scale() <= 2 && BigDecimal.valueOf(retailNum).scale() <= 2) {
	            			conversion = true;
						}
	            		
	            	}catch(Exception e) {
	            		conversion = false;
	            	}
	            	
	            	try {
	            		buttons = Integer.parseInt(layout.replaceAll(" ", ""));
	            		mconversion = true;
	            	}catch (NumberFormatException e) {
	            		mconversion = false;
	            	}
	            	
	            	boolean success = false;
	            	
	            	if((preModification)instanceof Mouse && conversion && mconversion && buttons >= 0 && retailNum>=0 &&
	            			origNum>=0 && q>=0 && retailNum > origNum) {
	            		
	            		int decision = JOptionPane.showConfirmDialog(null, "Do you want to save the changes to this product?");
	            		
	            		if (decision == JOptionPane.YES_OPTION) {
							
	            			interfaceUser.modifyProduct(preModification,
		            				barcode, brand, type, color, connection, Integer.parseInt(quantity), Double.parseDouble(original),
		            				Double.parseDouble(retail), layout);
		            		success = true;
	            			
						}
	            	
	            	}else if((preModification)instanceof Keyboard && conversion && retailNum>=0 && origNum>=0 && q>=0 && retailNum > origNum) {
	            		
	            		int decision = JOptionPane.showConfirmDialog(null, "Do you want to save the changes to this product?");
	            		
	            		if (decision == JOptionPane.YES_OPTION) {
							
	            			interfaceUser.modifyProduct(preModification, barcode, brand, type, color, connection, q, origNum,
		            				retailNum, layout);
		            		success = true;
	            			
						}
	            		
	            	} else {
	            		JOptionPane.showMessageDialog(null, "The values inputted are wrong");
	            	}
	            	
	            	if (success) {
						
	            		JOptionPane.showMessageDialog(null, "The product was successfully changed");
	            		
	            		barcodeModifyTextField.setText("");
	    	            brandModifyTextField.setText("");
	    	            quantityModifyTextField.setText("");
	    	            colorModifyTextField.setText("");
	    	            typeModifyTextField.setText("");
	    	            originalPriceModifyTextField.setText("");
	    	            retailPriceModifyTextField.setText("");
	    	            layoutModifyTextField.setText("");
	    	            connectionModifyTextField.setText("");
	            		
	    	            ArrayList<Product> products = interfaceUser.getAllProducts();
	    	            Collections.sort(products, Product.QuantityComparator);
	    	            loadProductsTable(products, modifyAProductTable, modify);
	    	            
					}
	            }
			}
		});
		
		saveModifyBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		saveModifyBtn.setBounds(787, 544, 109, 52);
		modifyAProduct.add(saveModifyBtn);
		
		JLabel searchBarcodemodifyLabel = new JLabel("Barcode");
		searchBarcodemodifyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchBarcodemodifyLabel.setBounds(215, 19, 56, 16);
		modifyAProduct.add(searchBarcodemodifyLabel);
		
		searchBarcodeModifyTextField = new JTextField();
		searchBarcodeModifyTextField.setColumns(10);
		searchBarcodeModifyTextField.setBounds(283, 17, 116, 22);
		modifyAProduct.add(searchBarcodeModifyTextField);
		
		JButton searchBarcodeToModify = new JButton("Search");
		searchBarcodeToModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to search the barcode of a product that it is to be modified
				
				String barcode = searchBarcodeModifyTextField.getText();
				
				if(!barcode.isEmpty()) {
					ArrayList<Product> products = interfaceUser.search(barcode, "", "", true);
					
					loadProductsTable(products, modifyAProductTable, modify);
					
				}
			}
		});
		searchBarcodeToModify.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		searchBarcodeToModify.setBounds(425, 16, 97, 25);
		modifyAProduct.add(searchBarcodeToModify);
		
		JLabel lblConnectionModify = new JLabel("Connection");
		lblConnectionModify.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblConnectionModify.setBounds(735, 492, 97, 16);
		modifyAProduct.add(lblConnectionModify);
		
		connectionModifyTextField = new JTextField();
		connectionModifyTextField.setColumns(10);
		connectionModifyTextField.setBounds(844, 490, 116, 22);
		modifyAProduct.add(connectionModifyTextField);
		
		JPanel menuSelection = new JPanel();
		menuSelection.setBackground(new Color(51, 153, 255));
		menuSelection.setBounds(0, 88, 972, 151);
		contentPane.add(menuSelection);
		menuSelection.setLayout(null);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//used to change to "add a product" JPanel
				contents.removeAll();
				contents.add(addProduct);
				contents.repaint();
				contents.revalidate();
			}
		});
		btnAddProduct.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAddProduct.setBounds(12, 72, 148, 41);
		menuSelection.add(btnAddProduct);
		
		JButton btnSeeAllProducts = new JButton("See All Products");
		btnSeeAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to change to "See All Products" JPanel
				
				contents.removeAll();
				contents.add(seeAllProducts);
				contents.repaint();
				contents.revalidate();
				
				seeAllProductsTable = new JTable();
				seeAllProductsTable.setEnabled(false);
				seeAllProductsTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
				seeAllProductsTable.setBorder(new CompoundBorder());
				
				ArrayList<Product> products = interfaceUser.getAllProducts();
				Collections.sort(products, Product.QuantityComparator);
				loadProductsTable(products, seeAllProductsTable, seeAllProductsPane);
				
			}
		});
		btnSeeAllProducts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSeeAllProducts.setBounds(203, 72, 148, 41);
		menuSelection.add(btnSeeAllProducts);
		
		JButton btnSearchProducts = new JButton("Search Products");
		btnSearchProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//used to change to "Search Products" JPanel
				
				tableModel.setRowCount(0);
				contents.removeAll();
				contents.add(searchProducts);
				contents.repaint();
				contents.revalidate();
				
				ArrayList<Product> products = interfaceUser.getAllProducts();
				Collections.sort(products, Product.QuantityComparator);
				
				loadProductsTable(products, searchTable, searchPane);
				
			}
		});
		
		btnSearchProducts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSearchProducts.setBounds(390, 72, 148, 41);
		menuSelection.add(btnSearchProducts);
		
		JButton btnSeeLog = new JButton("See Log");
		btnSeeLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//used to change to the "See Log" JPanel and load the log
				
				contents.removeAll();
				contents.add(seeLog);
				contents.repaint();
				contents.revalidate();
				logTable.setFont(new Font("Times New Roman", Font.PLAIN, 17));
				
				tableModel = new DefaultTableModel();
				tableModel.setColumnIdentifiers(new Object[] {"User ID","Postal Code","Barcode","Price","Quantity","Status","Method","Date"});
				logTable.setModel(tableModel);
				log.setViewportView(logTable);
				
				logs = interfaceUser.seeLog();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
				for(int i=0;i<logs.size();i++) {
					Object[] object = {logs.get(i).getUserId(), logs.get(i).getPostalCode(), logs.get(i).getProductsInfo().getBarcode(),
							logs.get(i).getProductsInfo().getRetailPrice(),
							logs.get(i).getQuantity(), logs.get(i).getStatus(), logs.get(i).getPayment(), dateFormat.format(logs.get(i).getDate())};
					
					tableModel.addRow(object);
				}
				
			}
		});
		btnSeeLog.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSeeLog.setBounds(579, 72, 148, 41);
		menuSelection.add(btnSeeLog);
		
		JButton btnModifyAProduct = new JButton("Modify a Product");
		btnModifyAProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//used to change to "Modify a Product" JPanel
				
				contents.removeAll();
				contents.add(modifyAProduct);
				contents.repaint();
				contents.revalidate();
				
				ArrayList<Product> products = interfaceUser.getAllProducts();
				Collections.sort(products, Product.QuantityComparator);
				loadProductsTable(products, modifyAProductTable, modify);
				
			}
		});
		
		btnModifyAProduct.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnModifyAProduct.setBounds(777, 72, 148, 41);
		menuSelection.add(btnModifyAProduct);
		
		JLabel titleLabel = new JLabel("Admin Interface");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		titleLabel.setBounds(403, 35, 182, 40);
		contentPane.add(titleLabel);
	}
	
	/*
	 * This method is used to reload the product tables in the Admin Interface GUI
	 * you only need to input the products ArrayList, the JTable and the JScrollPane
	 */
	public void loadProductsTable(ArrayList<Product> products, JTable table, JScrollPane pane) {
		
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Initial Price","Retail","Characteristics"});
		table.setModel(tableModel);
		pane.setViewportView(table);
		
		for(int i=0;i<products.size();i++) {
			
			String productType = "";
			Mouse mouse=null;
			Keyboard keyboard = null;
			String type = null;
			String layoutButtons =  null;
			if((products.get(i))instanceof Mouse) {
				productType = "mouse";
				mouse = (Mouse)products.get(i);
				type = mouse.getType();
				layoutButtons = "" + mouse.getButtonsNum();
			} else {
				productType = "keyboard";
				keyboard = (Keyboard)products.get(i);
				type = keyboard.getType();
				layoutButtons = keyboard.getLayout();
			}
			
			Object[] object = {products.get(i).getBarcode(), productType, type, products.get(i).getBrand(), products.get(i).getColor(),
					products.get(i).getConnectionType(),
					products.get(i).getQuantity(), products.get(i).getOriginalPrice(), products.get(i).getRetailPrice(), layoutButtons};
			
			tableModel.addRow(object);
		}
		
	}
}
