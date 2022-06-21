package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logic.Basket;
import logic.CreditCard;
import logic.Customer;
import logic.CustomerInterface;
import logic.Keyboard;
import logic.Mouse;
import logic.PayPal;
import logic.Product;
import logic.Stock;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class CustomerInterfaceGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Customer customer;
	private Basket usersBasket;
	private ArrayList<Product> products = new ArrayList<>();
	private CustomerInterface customerInterface;
	private Product selectedProduct;
	private JPanel contentPane;
	private JPanel creditCardPayment;
	private JPanel payPalPayment;
	private JPanel searchProductsPanel;
	private JTable searchProductsTable;
	private DefaultTableModel searchTableModel;
	private DefaultTableModel seeAllProductsModel;
	
	private DefaultTableModel basketTableModel;
	
	private JPanel seeAllProducts;
	private JTable seeAllProductsTable;
	private JLabel quantitySeeAllProductsLabel;
	private JTextField seeAllProductsQuantityTextField;
	private JButton searchAddInBasketButton;
	private JPanel basket;
	private JTable basketTable;
	private JTextField searchProductsTextField;
	private JTextField quantityTextField;
	private JLabel lblQuantity;
	
	private JScrollPane scrollPane;
	
	private JLabel totalLabel;
	private JTextField digitTextField;
	private JTextField cvcTextField;
	private JTextField emailTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInterfaceGUI frame = new CustomerInterfaceGUI(new Customer(104, "user4", "Lee", 57, "PA3 2SW", "Glasgow"));
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
	public CustomerInterfaceGUI(Customer customer) {
		
		this.customer = customer;
		customerInterface = new CustomerInterface(customer);
		usersBasket = new Basket(customer);
		
		setTitle("Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 813);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel selectPanel = new JPanel();
		selectPanel.setBackground(new Color(65, 105, 225));
		selectPanel.setBounds(0, 108, 957, 159);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		
		JButton searchProductsButton = new JButton("Search Products");
		
		searchProductsButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		searchProductsButton.setBounds(43, 58, 138, 48);
		selectPanel.add(searchProductsButton);
		
		JButton btnSeeAllProducts = new JButton("See All Products");
		
		btnSeeAllProducts.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSeeAllProducts.setBounds(386, 58, 138, 48);
		selectPanel.add(btnSeeAllProducts);
		
		JButton btnBasket = new JButton("Basket");
		btnBasket.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnBasket.setBounds(722, 58, 138, 48);
		selectPanel.add(btnBasket);
		
		JPanel usesPanel = new JPanel();
		usesPanel.setBounds(0, 267, 957, 499);
		contentPane.add(usesPanel);
		usesPanel.setLayout(new CardLayout(0, 0));
		
		searchProductsPanel = new JPanel();
		searchProductsPanel.setBackground(new Color(70, 130, 180));
		usesPanel.add(searchProductsPanel, "name_424136645699700");
		searchProductsPanel.setLayout(null);
		
		searchProductsTable = new JTable();
		searchProductsTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JScrollPane searchProductsScrollPane = new JScrollPane();
		searchProductsScrollPane.setBounds(251, 13, 694, 473);
		searchProductsPanel.add(searchProductsScrollPane);
		
		products = customerInterface.seeAvailableProducts();
		Collections.sort(products, Product.QuantityComparator);
		loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
		
		
		searchProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSelection = searchProductsTable.getSelectionModel();
		rowSelection.addListSelectionListener(new ListSelectionListener() {
			
			/*This action is triggered when a row is selected in the searchProductsTable
			 * it creates a new product which is then is used to select the quantity that the user wants
			 */
			
		    public void valueChanged(ListSelectionEvent e) {
		        if (e.getValueIsAdjusting()) return;
		        
		        ListSelectionModel listSelection =
		            (ListSelectionModel)e.getSource();
		        if (listSelection.isSelectionEmpty()) {
		            
		        } else {
		            int indexOfRow = listSelection.getMinSelectionIndex();
		            quantityTextField.setVisible(true);
		            lblQuantity.setVisible(true);
		            searchAddInBasketButton.setVisible(true);
		            
		            String typeOfProduct = searchProductsTable.getModel().getValueAt(indexOfRow, 1).toString();
		            
		            if(typeOfProduct.equals("mouse")) {
		            
		            	selectedProduct = new Mouse(searchProductsTable.getModel().getValueAt(indexOfRow, 0).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 3).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 4).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 6).toString()), products.get(indexOfRow).getOriginalPrice(), Double.parseDouble(searchProductsTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, searchProductsTable.getModel().getValueAt(indexOfRow, 2).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 8).toString()));

		            } else {
		            	selectedProduct = new Keyboard(searchProductsTable.getModel().getValueAt(indexOfRow, 0).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 3).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 4).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 6).toString()), products.get(indexOfRow).getOriginalPrice(), Double.parseDouble(searchProductsTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, searchProductsTable.getModel().getValueAt(indexOfRow, 2).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 8).toString());

		            }
		            
		        }
		     }
		});
		
		JLabel lblBrandSearch = new JLabel("Brand");
		lblBrandSearch.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblBrandSearch.setBounds(12, 42, 46, 32);
		searchProductsPanel.add(lblBrandSearch);
		
		searchProductsTextField = new JTextField();
		searchProductsTextField.setColumns(10);
		searchProductsTextField.setBounds(70, 46, 155, 26);
		searchProductsPanel.add(searchProductsTextField);
		
		JButton searchButton = new JButton("Search");
		
		searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		searchButton.setBounds(56, 193, 123, 32);
		searchProductsPanel.add(searchButton);
		
		searchAddInBasketButton = new JButton("Add in basket");
		
		searchAddInBasketButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchAddInBasketButton.setBounds(58, 362, 131, 32);
		searchProductsPanel.add(searchAddInBasketButton);
		searchAddInBasketButton.setVisible(false);
		
		JCheckBox ukLayoutCheckBox = new JCheckBox("UK Layout Keyboards");
		ukLayoutCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ukLayoutCheckBox.setBounds(47, 111, 178, 25);
		searchProductsPanel.add(ukLayoutCheckBox);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblQuantity.setBounds(12, 293, 68, 32);
		searchProductsPanel.add(lblQuantity);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(73, 299, 116, 22);
		searchProductsPanel.add(quantityTextField);
		quantityTextField.setColumns(10);
		lblQuantity.setVisible(false);
		quantityTextField.setVisible(false);
		
		seeAllProducts = new JPanel();
		seeAllProducts.setBackground(new Color(65, 105, 225));
		usesPanel.add(seeAllProducts);
		seeAllProducts.setLayout(null);
		
		seeAllProductsTable = new JTable();
		seeAllProductsTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JScrollPane seeAllProductsScrollPane = new JScrollPane();
		seeAllProductsScrollPane.setBounds(12, 13, 742, 473);
		seeAllProducts.add(seeAllProductsScrollPane);
		
		JButton addToBasketButton = new JButton("Add to basket");
		addToBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*This action is triggered when the user wants to add a product in his basket.
				 * it checks if the user's input is correct and if this is the case it adds the product in the basket
				 * with the selected quantity*/
				
				seeAllProductsTable.repaint();
				searchTableModel.fireTableDataChanged();
				
				String quantityString = seeAllProductsQuantityTextField.getText();
				int quantity = -1;
				boolean modificationWorks = false;
				
				try {
					
					quantity = Integer.parseInt(quantityString);
					modificationWorks = true;
					
				} catch (Exception e) {
					modificationWorks = false;
				}
				
				if(modificationWorks && !quantityString.isEmpty()) {
					if (quantity<=selectedProduct.getQuantity() && quantity>0) {
						
						if (searchProducts(customerInterface.getBasket().getProducts(), selectedProduct) > -1) {
							
							int q = customerInterface.getBasket().getQuantities().get(searchProducts(customerInterface.getBasket().getProducts(), selectedProduct));
							
							System.out.println(selectedProduct.toString());
							
							updateBasket(selectedProduct, quantity + q);
							
						}else {
							customerInterface.selectProduct(selectedProduct, quantity);
						}
						
						Collections.sort(products, Product.QuantityComparator);
						seeAllProductsModel.fireTableStructureChanged();
						loadSeeAllProductsTable(seeAllProductsScrollPane, products);
			            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
						
						JOptionPane.showMessageDialog(null, "The item was successfully inputted in your basket");

					} else JOptionPane.showMessageDialog(null, "The quantity selected is incorrect");
				} else {
					
					JOptionPane.showMessageDialog(null, "The quantity has not been inputed or it is incorrect");
				}
				
				seeAllProductsQuantityTextField.setText("");
				seeAllProductsQuantityTextField.setVisible(false);
				quantitySeeAllProductsLabel.setVisible(false);
				addToBasketButton.setVisible(false);
				
				
			}
		});
		
		addToBasketButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		addToBasketButton.setBounds(786, 429, 122, 41);
		seeAllProducts.add(addToBasketButton);
		
		quantitySeeAllProductsLabel = new JLabel("Quantity");
		quantitySeeAllProductsLabel.setForeground(Color.WHITE);
		quantitySeeAllProductsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));        quantitySeeAllProductsLabel.setBounds(766, 269, 71, 20);
		seeAllProducts.add(quantitySeeAllProductsLabel);
		quantitySeeAllProductsLabel.setVisible(false);
		
		seeAllProductsQuantityTextField = new JTextField();
		seeAllProductsQuantityTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		seeAllProductsQuantityTextField.setBounds(829, 269, 116, 22);
		seeAllProducts.add(seeAllProductsQuantityTextField);
		seeAllProductsQuantityTextField.setColumns(10);
		seeAllProductsQuantityTextField.setVisible(false);
		
		basket = new JPanel();
		basket.setBackground(new Color(0, 139, 139));
		usesPanel.add(basket);
		basket.setLayout(null);
		
		basketTable = new JTable();
		basketTable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 716, 473);
		basket.add(scrollPane);
		
		creditCardPayment = new JPanel();
		creditCardPayment.setBackground(SystemColor.activeCaption);
		usesPanel.add(creditCardPayment, "name_424136645699701");
		creditCardPayment.setLayout(null);
		
		JLabel payCreditTitleLabel = new JLabel("Pay With Credit Card");
		payCreditTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		payCreditTitleLabel.setBounds(391, 31, 181, 47);
		creditCardPayment.add(payCreditTitleLabel);
		
		JLabel digitLabel = new JLabel("16-digit Number:");
		digitLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		digitLabel.setBounds(12, 101, 137, 31);
		creditCardPayment.add(digitLabel);
		
		digitTextField = new JTextField();
		digitTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		digitTextField.setBounds(140, 106, 175, 22);
		creditCardPayment.add(digitTextField);
		digitTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CVC:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(411, 100, 45, 31);
		creditCardPayment.add(lblNewLabel);
		
		cvcTextField = new JTextField();
		cvcTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		cvcTextField.setBounds(482, 107, 100, 22);
		creditCardPayment.add(cvcTextField);
		cvcTextField.setColumns(10);
		
		JLabel informationCreditLabel = new JLabel("Make sure that the inputted 16 digited number and cvc have no spaces");
		informationCreditLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		informationCreditLabel.setBounds(199, 179, 508, 61);
		creditCardPayment.add(informationCreditLabel);
		
		JLabel lblAmountPaidCard = new JLabel("paid using Credit Card");
		lblAmountPaidCard.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblAmountPaidCard.setBounds(676, 101, 230, 31);
		creditCardPayment.add(lblAmountPaidCard);
		
		JButton cancelCreditButton = new JButton("Cancel");
		cancelCreditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//loads the basket JPanel in the JFrame from the pay with credit card JPanel
				
				usesPanel.removeAll();
				usesPanel.add(basket);
				usesPanel.repaint();
				usesPanel.revalidate();
				
				digitTextField.setText("");
				cvcTextField.setText("");
				
			}
		});
		cancelCreditButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cancelCreditButton.setBounds(82, 333, 152, 47);
		creditCardPayment.add(cancelCreditButton);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//triggers the checking of the user inputed CVC and 16-digit number, if correct it performs the purchase
				
				String digits = digitTextField.getText().replaceAll(" ", "");
				digits.replaceAll(".", "");
				
				String cvc = cvcTextField.getText().replaceAll(" ", "");
				
				boolean allDigits = false;
				
				try {
					Long.parseLong(digits);
					Integer.parseInt(cvc);
					if (Long.parseLong(digits) > 0 && Integer.parseInt(cvc) > 0  && !digits.contains("+") && !cvc.contains("-")) {
						allDigits = true;
					}
					
				} catch (Exception e2) {
					allDigits = false;
				}
								
				if (digits.isEmpty() && cvc.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You have not entered your credit card details");
				} else if ((digits.length()<16 || digits.length()>16) || (cvc.length()>3 || cvc.length()<3) || !allDigits) {
					JOptionPane.showMessageDialog(null, "The details you inputted are incorrect");
				} else {					
						boolean success = customerInterface.buy(new CreditCard(customerInterface.getTotalCost(), usersBasket.getProducts().size(), digits, cvc));
						
						if (success) {
							
							emptyBasketTable();
							
							products = customerInterface.seeAvailableProducts();
							Collections.sort(products, Product.QuantityComparator);
				            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
							
							customerInterface.calcCost();
							totalLabel.setText("Total: " + customerInterface.getTotalCost());
							
						} else {
							JOptionPane.showMessageDialog(null, "There is not enough stock to perform this Transaction");
						}
						
						usesPanel.removeAll();
						usesPanel.add(basket);
						usesPanel.repaint();
						usesPanel.revalidate();
						digitTextField.setText("");
						cvcTextField.setText("");
						
				}
				
			}
		});
		
		btnPay.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPay.setBounds(676, 333, 152, 47);
		creditCardPayment.add(btnPay);
		
		JButton payWithCreditButton = new JButton("Pay with Credit Card");
		
		payWithCreditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//loads the Credit Card payment JPanel, it only loads if the user's basket has one or more items
				
				usersBasket = customerInterface.getBasket();				
				if (!usersBasket.getProducts().isEmpty()) {
					usesPanel.removeAll();
					usesPanel.add(creditCardPayment);
					usesPanel.repaint();
					usesPanel.revalidate();
					
					DecimalFormat format = new DecimalFormat("#.00");
					lblAmountPaidCard.setText(format.format(usersBasket.getTotal()) + "  paid using Credit Card");
				} else {
					JOptionPane.showMessageDialog(null, "You have no products to check out");
				}
				
			}
		});
		
		payWithCreditButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		payWithCreditButton.setBounds(747, 49, 184, 35);
		basket.add(payWithCreditButton);
		
		payPalPayment = new JPanel();
		payPalPayment.setBackground(SystemColor.activeCaption);
		usesPanel.add(payPalPayment);
		payPalPayment.setLayout(null);
		
		JLabel payPalTitle = new JLabel("Pay With PayPal");
		payPalTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		payPalTitle.setBounds(398, 26, 142, 50);
		payPalPayment.add(payPalTitle);
		
		JLabel emailLabel = new JLabel("Enter your Email:");
		emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		emailLabel.setBounds(36, 96, 132, 31);
		payPalPayment.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		emailTextField.setBounds(174, 101, 172, 22);
		payPalPayment.add(emailTextField);
		emailTextField.setColumns(10);
		
		
		JLabel informationPayPalLabel = new JLabel("Make sure that the email inputted is correct");
		informationPayPalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		informationPayPalLabel.setBounds(293, 177, 320, 40);
		payPalPayment.add(informationPayPalLabel);
		
		JButton cancelPayPalbutton = new JButton("Cancel");
		cancelPayPalbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//goes back to the basket JFrame from the PayPal payment JFrame
				
				usesPanel.removeAll();
				usesPanel.add(basket);
				usesPanel.repaint();
				usesPanel.revalidate();
				
				digitTextField.setText("");
				cvcTextField.setText("");
				
			}
		});
		cancelPayPalbutton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		
		cancelPayPalbutton.setBounds(54, 318, 152, 47);
		payPalPayment.add(cancelPayPalbutton);
		
		JButton btnPayPayPal = new JButton("Pay");
		
		btnPayPayPal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//triggers the payment procedure using PayPal
				//if the user's input is correct then the payment goes ahead
				//if not the user has to correctly re-input his email address
				
				String email = emailTextField.getText();
				
				if (email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No email has been inputted");
				} else {
					if (!email.contains("@")) {
						JOptionPane.showMessageDialog(null, "The email inputted is wrong");
					} else {
						boolean success = customerInterface.buy(new PayPal(customerInterface.getTotalCost(), usersBasket.getProducts().size(), email));
						
						if (success) {
							
							emptyBasketTable();
							products = customerInterface.seeAvailableProducts();
							Collections.sort(products, Product.QuantityComparator);
				            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);

							customerInterface.calcCost();
							totalLabel.setText("Total: " + customerInterface.getTotalCost());
							
						} else {
							JOptionPane.showMessageDialog(null, "There is not enough stock to perform this Transaction");
						}
						
						usesPanel.removeAll();
						usesPanel.add(basket);
						usesPanel.repaint();
						usesPanel.revalidate();
						emailTextField.setText("");
						
					}
				}
				
			}
		});
		
		btnPayPayPal.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPayPayPal.setBounds(728, 318, 152, 47);
		payPalPayment.add(btnPayPayPal);
		
		JLabel paidPayPal = new JLabel("paid using PayPal");
		paidPayPal.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		paidPayPal.setBounds(547, 92, 300, 31);
		payPalPayment.add(paidPayPal);
		
		
		JButton btnPayWithPaypal = new JButton("Pay with PayPal");
		btnPayWithPaypal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//used to load the PayPal payment JPanel
				
				usersBasket = customerInterface.getBasket();
				if (!usersBasket.getProducts().isEmpty()) {
					usesPanel.removeAll();
					usesPanel.add(payPalPayment);
					usesPanel.repaint();
					usesPanel.revalidate();
					
					DecimalFormat format = new DecimalFormat("#.00");
					customerInterface.calcCost();
					paidPayPal.setText(format.format(customerInterface.getTotalCost()) + "  paid using PayPal");
					
				} else {
					JOptionPane.showMessageDialog(null, "You have no products to check out");
				}
				
			}
		});
		
		
		btnPayWithPaypal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnPayWithPaypal.setBounds(761, 123, 157, 35);
		basket.add(btnPayWithPaypal);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//asks the user if the order needs to be cancelled
				//if so it cancels the order
				
				int decision = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel your basket?");
				
				if (decision == JOptionPane.YES_OPTION) {
					
					customerInterface.cancel();
					emptyBasketTable();
					
					customerInterface.calcCost();
					totalLabel.setText("Total: " + customerInterface.getTotalCost());
					
					products.clear();
					products = customerInterface.seeAvailableProducts();
					Collections.sort(products, Product.QuantityComparator);
		            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
					
				}
				
			}
		});
		
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnCancel.setBounds(761, 195, 157, 35);
		basket.add(btnCancel);
		
		JButton btnSaveForLater = new JButton("Save for Later");
		btnSaveForLater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//it asks the user whether or not the order should be saved, if it is it saves the order and empties the basket
				
				int decision = JOptionPane.showConfirmDialog(null, "Do you want to save your order?");
				
				if (decision == JOptionPane.YES_OPTION) {
					
					customerInterface.save();
					emptyBasketTable();
					
					customerInterface.calcCost();
					totalLabel.setText("Total: " + customerInterface.getTotalCost());
					
					products.clear();
					products = customerInterface.seeAvailableProducts();
					Collections.sort(products, Product.QuantityComparator);
		            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);

				}
				
			}
		});
		btnSaveForLater.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSaveForLater.setBounds(761, 277, 157, 35);
		basket.add(btnSaveForLater);
		
		JButton removeElementButton = new JButton("Remove from basket");
		removeElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//it is used to remove elements from the basket
				int decision = JOptionPane.showConfirmDialog(null, "Do you want to remove this product from your basket?");
				
				if (decision == JOptionPane.YES_OPTION) {
					
					
					customerInterface.removeProduct(selectedProduct);
					
					Collections.sort(products, Product.QuantityComparator);
					
					usersBasket = customerInterface.getBasket();
					ArrayList<Product> product = usersBasket.getProducts();
					ArrayList<Integer> quantitySelected = usersBasket.getQuantities();
					loadBasket(product, quantitySelected);
					
					customerInterface.calcCost();
					totalLabel.setText("Total: " + customerInterface.getTotalCost());
					removeElementButton.setVisible(false);
		            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);

				}
				
				
			}
		});
		
		removeElementButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		removeElementButton.setBounds(761, 350, 157, 35);
		basket.add(removeElementButton);
		
		totalLabel = new JLabel("Total");
		totalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		totalLabel.setBounds(759, 430, 122, 35);
		basket.add(totalLabel);
		removeElementButton.setVisible(false);
		
		JLabel titleLabel = new JLabel("Customer");
		titleLabel.setForeground(new Color(255, 255, 255));
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		titleLabel.setBounds(415, 42, 101, 33);
		contentPane.add(titleLabel);
		
		searchProductsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to select the product from the Search Products table
				
				usesPanel.removeAll();
				usesPanel.add(searchProductsPanel);
				usesPanel.repaint();
				usesPanel.revalidate();
				quantityTextField.setText("");
				
				searchProductsTextField.setText("");
				ukLayoutCheckBox.setSelected(false);
				quantityTextField.setVisible(false);
	            lblQuantity.setVisible(false);
	            searchAddInBasketButton.setVisible(false);
				
	            Collections.sort(products, Product.QuantityComparator);
	            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
	    		
			}
		});
		
		btnSeeAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//changes to "See All Products" JPanel
				
				usesPanel.removeAll();
				usesPanel.add(seeAllProducts);
				usesPanel.repaint();
				usesPanel.revalidate();
				
				addToBasketButton.setVisible(false);
				seeAllProductsQuantityTextField.setVisible(false);
				seeAllProductsQuantityTextField.setText("");
				quantitySeeAllProductsLabel.setVisible(false);
				Collections.sort(products, Product.QuantityComparator);
				loadSeeAllProductsTable(seeAllProductsScrollPane, products);
	            loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
				
				
			}
		});
		
		seeAllProductsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSelection2 = seeAllProductsTable.getSelectionModel();
		rowSelection2.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e2) {

		    	//used to fetch the product that the user wants from the seeAllProductsTable
		    	
		        if (e2.getValueIsAdjusting()) return;
		        
		        ListSelectionModel listSelection1 =
		            (ListSelectionModel)e2.getSource();
		        if (listSelection1.isSelectionEmpty()) {

		        	
		        } else {
		            int indexOfRow = listSelection1.getMinSelectionIndex();
		            seeAllProductsQuantityTextField.setVisible(true);
		            quantitySeeAllProductsLabel.setVisible(true);
		            addToBasketButton.setVisible(true);
		            selectedProduct = null;
		            				            
		            String typeOfProduct = searchProductsTable.getModel().getValueAt(indexOfRow, 1).toString();
		            				            
		            if(typeOfProduct.equals("mouse")) {
		            
		            	selectedProduct = new Mouse(searchProductsTable.getModel().getValueAt(indexOfRow, 0).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 3).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 4).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 6).toString()), products.get(indexOfRow).getOriginalPrice(), Double.parseDouble(searchProductsTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, searchProductsTable.getModel().getValueAt(indexOfRow, 2).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 8).toString()));

		            } else {
		            	selectedProduct = new Keyboard(searchProductsTable.getModel().getValueAt(indexOfRow, 0).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 3).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 4).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(searchProductsTable.getModel().getValueAt(indexOfRow, 6).toString()), products.get(indexOfRow).getOriginalPrice(), Double.parseDouble(searchProductsTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, searchProductsTable.getModel().getValueAt(indexOfRow, 2).toString(), searchProductsTable.getModel().getValueAt(indexOfRow, 8).toString());

		            }
		            
		            System.out.println(selectedProduct.toString());
		            				            
		        }
		     }
		});
		
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to change to the "Basket" JPanel
				usesPanel.removeAll();
				usesPanel.add(basket);
				usesPanel.repaint();
				usesPanel.revalidate();
				removeElementButton.setVisible(false);
				
				usersBasket = customerInterface.getBasket();
				ArrayList<Product> product = usersBasket.getProducts();
				ArrayList<Integer> quantitySelected = usersBasket.getQuantities();
				loadBasket(product, quantitySelected);
				
				DecimalFormat format = new DecimalFormat("#.00");
				totalLabel.setText("Total: " + format.format(usersBasket.getTotal()));
				
				basketTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				ListSelectionModel rowSelection = basketTable.getSelectionModel();
				rowSelection.addListSelectionListener(new ListSelectionListener() {
				    public void valueChanged(ListSelectionEvent e) {
				    	
				    	//used to select the products in the basketTable that will be removed
				    	
				        if (e.getValueIsAdjusting()) return;
				        
				        ListSelectionModel listSelection =
				            (ListSelectionModel)e.getSource();
				        if (listSelection.isSelectionEmpty()) {
				            
				        } else {
				            int indexOfRow = listSelection.getMinSelectionIndex();
				            
				            removeElementButton.setVisible(true);
				            
				            String typeOfProduct = basketTable.getModel().getValueAt(indexOfRow, 1).toString();
				            
				            if(typeOfProduct.equals("mouse")) {
				            
				            	selectedProduct = new Mouse(basketTable.getModel().getValueAt(indexOfRow, 0).toString(), basketTable.getModel().getValueAt(indexOfRow, 3).toString(), basketTable.getModel().getValueAt(indexOfRow, 4).toString(), basketTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(basketTable.getModel().getValueAt(indexOfRow, 6).toString()), 0, Double.parseDouble(basketTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, basketTable.getModel().getValueAt(indexOfRow, 2).toString(), Integer.parseInt(basketTable.getModel().getValueAt(indexOfRow, 8).toString()));

				            } else {
				            	selectedProduct = new Keyboard(basketTable.getModel().getValueAt(indexOfRow, 0).toString(), basketTable.getModel().getValueAt(indexOfRow, 3).toString(), basketTable.getModel().getValueAt(indexOfRow, 4).toString(), basketTable.getModel().getValueAt(indexOfRow, 5).toString(), Integer.parseInt(basketTable.getModel().getValueAt(indexOfRow, 6).toString()), 0, Double.parseDouble(basketTable.getModel().getValueAt(indexOfRow, 7).toString()), Stock.AVAILABLE, basketTable.getModel().getValueAt(indexOfRow, 2).toString(), basketTable.getModel().getValueAt(indexOfRow, 8).toString());

				            }
				        }
				     }
				});
				
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to initiate the search function
				
				String brandName = searchProductsTextField.getText();
				boolean ukLayoutOn = ukLayoutCheckBox.isSelected();
				
				ArrayList<Product> products = new ArrayList<>();
				
				boolean success = false;
				
				String layout = "";
				if(ukLayoutOn) {
					layout = "UK";
				}
				
				
				if(!brandName.isEmpty() && !ukLayoutOn) {
					products = customerInterface.search(brandName, layout, "1");
					success = true;
				} else if(brandName.isEmpty() && ukLayoutOn) {
					products = customerInterface.search(brandName, layout, "2");
					success = true;
				} else if(!brandName.isEmpty() && ukLayoutOn) {
					products = customerInterface.search(brandName, layout, "3");
					success = true;
				} else {
					JOptionPane.showMessageDialog(null, "The search Parameters are empty");
				}
				
				if(success) {
					
					Collections.sort(products, Product.QuantityComparator);
					loadSearchTable(searchProductsScrollPane, searchProductsTable, products);
					
				}
			}
		});
		
		searchAddInBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//used to add a selected product from the search products table to the basket
				
				String quantityString = quantityTextField.getText();
				int quantity = -1;
				boolean modificationWorks = false;
				
				try {
					
					quantity = Integer.parseInt(quantityString);
					modificationWorks = true;
					
				} catch (Exception e) {
					modificationWorks = false;
				}
				
				if(modificationWorks && !quantityString.isEmpty()) {
					if (quantity<=selectedProduct.getQuantity() && quantity > 0) {
						
						if (searchProducts(customerInterface.getBasket().getProducts(), selectedProduct) > -1) {
							
							int q = customerInterface.getBasket().getQuantities().get(searchProducts(customerInterface.getBasket().getProducts(), selectedProduct));
							
							int temp = quantity + q;
							System.out.println(selectedProduct.toString());
														
							updateBasket(selectedProduct, temp);
							
						}else {
							customerInterface.selectProduct(selectedProduct, quantity);
						}
						
						Collections.sort(products, Product.QuantityComparator);
						loadSearchTable(searchProductsScrollPane, searchProductsTable, products);

						JOptionPane.showMessageDialog(null, "The item was successfully inputted in your basket");
						quantityTextField.setText("");
						quantityTextField.setVisible(false);
						lblQuantity.setVisible(false);
						searchAddInBasketButton.setVisible(false);
						
					} else JOptionPane.showMessageDialog(null, "The quantity selected is incorect");
				} else {
					JOptionPane.showMessageDialog(null, "The quantity has not been inputed or it is incorrect");
				}
			}
		});
		
	}
	
	/*
	 * This method is used to load the product tables in the search JPanel Customer Interface GUI
	 * you only need to input the products ArrayList, the JTable and the JScrollPane
	 */
	
	public void loadSearchTable(JScrollPane pane, JTable table, ArrayList<Product> product) {
		
		searchTableModel = new DefaultTableModel();
		searchTableModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Retail","Characteristics"});
		table.setModel(searchTableModel);
		pane.setViewportView(table);
		searchTableModel.setRowCount(0);
		searchTableModel.fireTableStructureChanged();
		
		for(int i=0;i<product.size();i++) {
			
			String productType = "";
			Mouse mouse=null;
			Keyboard keyboard = null;
			String type = null;
			String layoutButtons =  null;
			if((product.get(i))instanceof Mouse) {
				productType = "mouse";
				mouse = (Mouse)product.get(i);
				type = mouse.getType();
				layoutButtons = "" + mouse.getButtonsNum();
			} else {
				productType = "keyboard";
				keyboard = (Keyboard)product.get(i);
				type = keyboard.getType();
				layoutButtons = keyboard.getLayout();
			}
			
			Object[] object = {product.get(i).getBarcode(), productType, type, product.get(i).getBrand(), product.get(i).getColor(), product.get(i).getConnectionType(),
					product.get(i).getQuantity(), product.get(i).getRetailPrice(), layoutButtons};
			
			searchTableModel.addRow(object);
		}
		
	}
	
	//this method is used load the seeAllProductsTable
	public void loadSeeAllProductsTable(JScrollPane pane, ArrayList<Product> product) {
		
		seeAllProductsModel = new DefaultTableModel();
		seeAllProductsModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Retail","Characteristics"});
		seeAllProductsTable.setModel(seeAllProductsModel);
		pane.setViewportView(seeAllProductsTable);
		seeAllProductsModel.setRowCount(0);
		seeAllProductsModel.fireTableStructureChanged();
		
		for(int i=0;i<product.size();i++) {
			
			String productType = "";
			Mouse mouse=null;
			Keyboard keyboard = null;
			String type = null;
			String layoutButtons =  null;
			if((product.get(i))instanceof Mouse) {
				productType = "mouse";
				mouse = (Mouse)product.get(i);
				type = mouse.getType();
				layoutButtons = "" + mouse.getButtonsNum();
			} else {
				productType = "keyboard";
				keyboard = (Keyboard)product.get(i);
				type = keyboard.getType();
				layoutButtons = keyboard.getLayout();
			}
			
			Object[] object = {product.get(i).getBarcode(), productType, type, product.get(i).getBrand(), product.get(i).getColor(), product.get(i).getConnectionType(),
					product.get(i).getQuantity(), product.get(i).getRetailPrice(), layoutButtons};
			
			seeAllProductsModel.addRow(object);
		}
		
	}
	
	/*This method is used to load the Basket table
	 * it only requires the ArrayList of the products and of the selected quantities*/
	
	public void loadBasket(ArrayList<Product> products, ArrayList<Integer> quantitySelected) {
		
		basketTableModel = new DefaultTableModel();
		basketTableModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Retail","Characteristics"});
		basketTable.setModel(basketTableModel);
		scrollPane.setViewportView(basketTable);
		
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
			
			Object[] object = {products.get(i).getBarcode(), productType, type, products.get(i).getBrand(), products.get(i).getColor(), products.get(i).getConnectionType(),
					quantitySelected.get(i), products.get(i).getRetailPrice(), layoutButtons};
			
			basketTableModel.addRow(object);
		}
		
	}
	
	//this method is used to empty the basket's product table
	public void emptyBasketTable() {
		
		basketTableModel = new DefaultTableModel();
		basketTableModel.setColumnIdentifiers(new Object[] {"Barcode","Product","Type","Brand","Color","Connection","Quantity","Retail","Characteristics"});
		basketTable.setModel(basketTableModel);
		scrollPane.setViewportView(basketTable);
		basketTableModel.setRowCount(0);
		
	}
	
	/*This method is used to search through a Product ArrayList
	 * and find the index of a product, it is used
	 * when looking for a similar product that may exist in the
	 * customer's basket*/
	private int searchProducts(ArrayList<Product> products, Product product) {
		
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getBarcode().equals(product.getBarcode())) {
				return i;
			}
		}
		
		return -1;
	}
	
	/*This method is used to update a product in the basket with the the new quantity that the user has requested
	 * it is used in order to avoid multiple entries of the same product in the basket*/
	private void updateBasket(Product product, int quantity) {		
		
		customerInterface.getBasket().removeItem(product);
		customerInterface.getBasket().addProduct(product, quantity);
	}
	
}
