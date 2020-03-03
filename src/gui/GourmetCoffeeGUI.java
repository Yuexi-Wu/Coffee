package gui;

import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Formatter.HTMLSalesFormatter;
import Formatter.PlainTextSalesFormatter;
import Formatter.SalesFormatter;
import Formatter.XMLSalesFormatter;
import loader.CatalogLoader;
import loader.DataField;
import loader.DataFormatException;
import loader.FileCatalogLoader;
import products.Coffee;
import products.CoffeeBrewer;
import products.OrderItem;
import products.Product;
import sale.Catalog;
import sale.Order;
import sale.Sales;

import java.io.*;
import java.text.*;

/**
 * Gourmet Coffee System.
 *
 * @author 
 * @version 1.1.0
 * @date 2016/12/29
 * @see Product
 * @see Coffee
 * @see CoffeeBrewer
 * @see Catalog
 * @see OrderItem
 * @see Order
 * @see Sales
 * @see CatalogLoader
 * @see FileCatalogLoader
 * @see DataFormatException
 * @see SalesFormatter
 * @see PlainTextSalesFormatter
 * @see HTMLSalesFormatter
 * @see XMLSalesFormatter
 * @see DataField
 */
public class GourmetCoffeeGUI extends JPanel {

	/* Standar error stream */
	static private PrintWriter stdErr = new PrintWriter(System.err, true);

	/* Window width in pixels */
	static private int WIDTH = 720;

	/* Window height in pixels */
	static private int HEIGHT = 530;

	/* Size of the catalog list cell in pixels */
	static private int CATALOG_CELL_SIZE = 50;

	/* Visible rows in catalog list */
	static private int CATALOG_LIST_ROWS = 14;

	/* Size of the order list cell in pixels */
	static private int ORDER_CELL_SIZE = 10;

	/* Visible rows in order list */
	static private int ORDER_LIST_ROWS = 6;

	/* Size quantity text field */
	static private int QUANTITY__TEXTFIELD_SIZE = 5;

	/* Size total text field */
	static private int TOTAL__TEXTFIELD_SIZE = 8;

	/* Rows in status text area rows */
	static private int STATUS_ROWS = 5;

	/* Rows in status text area cols */
	static private int STATUS_COLS = 40;

	private JList catalogList;
	private JList orderList;
	private JButton addModifyButton;
	private JButton removeButton;
	private JButton registerSaleButton;
	private JButton displaySalesButton;
	private JButton VIPButton;
	private JButton takeoutButton;
	private JButton saveSalesButton;
	private JPanel productPanel;
	private JLabel quantityLabel;
	private JLabel totalLabel;
	private JLabel timeLabel;
	private JLabel chargeLabel;
	private JLabel cashLabel;
	private JTextField quantityTextField;
	private JTextField totalTextField;
	private JTextArea statusTextArea;
	private JTextField timeTextField;
	private JTextField cashTextField;
	private JTextField chargeTextField;
	private JRadioButton plainRadioButton;
	private JRadioButton HTMLRadioButton;
	private JRadioButton XMLRadioButton;

	private JFileChooser fileChooser;
	private Catalog catalog;
	private Order currentOrder;
	private Sales sales;
	private SalesFormatter salesFormatter;
	private NumberFormat dollarFormatter;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Instantiates the components and arranges them in a window.
	 *
	 * @param initialCatalog
	 *            a product catalog.
	 */
	public GourmetCoffeeGUI(Catalog initialCatalog) {
		// create the components
		catalogList = new JList();
		orderList = new JList();
		catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catalogList.setVisibleRowCount(CATALOG_LIST_ROWS);
		catalogList.setFixedCellWidth(CATALOG_CELL_SIZE);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.setVisibleRowCount(ORDER_LIST_ROWS);
		orderList.setFixedCellWidth(ORDER_CELL_SIZE);
		addModifyButton = new JButton("Add|Modify Order Item");
		removeButton = new JButton("Remove Order Item");
		registerSaleButton = new JButton("Register Sale of Current Order");
		displaySalesButton = new JButton("Display Sales");
		saveSalesButton = new JButton("Save Sales");
		VIPButton = new JButton("VIP");
		takeoutButton = new JButton("take-out");
		quantityLabel = new JLabel("Quantity:");
		totalLabel = new JLabel("Total:");
		timeLabel = new JLabel("CurrentTime:");
		chargeLabel = new JLabel("Charge:");
		cashLabel = new JLabel("Cash:");
		quantityTextField = new JTextField("", QUANTITY__TEXTFIELD_SIZE);
		totalTextField = new JTextField("$0.00", TOTAL__TEXTFIELD_SIZE);
		totalTextField.setEditable(false);
		cashTextField = new JTextField("", TOTAL__TEXTFIELD_SIZE);

		chargeTextField = new JTextField("$0.00", TOTAL__TEXTFIELD_SIZE);
		chargeTextField.setEditable(false);
		statusTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
		statusTextArea.setEditable(false);
		plainRadioButton = new JRadioButton("Plain", true);
		HTMLRadioButton = new JRadioButton("HTML");
		XMLRadioButton = new JRadioButton("XML");
		timeTextField = new JTextField();

		
		Timer timer = new Timer();
	        timer.schedule(new ShowTime(), new Date(), 1000);		

		ButtonGroup group = new ButtonGroup();

		group.add(plainRadioButton);
		group.add(HTMLRadioButton);
		group.add(XMLRadioButton);

		// Product Information panel
		productPanel = new JPanel();
		productPanel.setBorder(BorderFactory.createTitledBorder("Product Information"));

		// Catalog panel
		JPanel catalogPanel = new JPanel();

		catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
		catalogPanel.add(new JScrollPane(catalogList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		// "Add|Modify Product" panel
		JPanel centralPanel = new JPanel(new BorderLayout());
		JPanel addModifyPanel = new JPanel(new GridLayout(2, 1));
		JPanel quantityPanel = new JPanel();

		quantityPanel.add(quantityLabel);
		quantityPanel.add(quantityTextField);
		addModifyPanel.add(quantityPanel);
		addModifyPanel.add(addModifyButton);
		centralPanel.add(productPanel, BorderLayout.CENTER);
		centralPanel.add(addModifyPanel, BorderLayout.SOUTH);

		// Order panel
		JPanel orderPanel = new JPanel(new BorderLayout());

		orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));

		JPanel totalPanel = new JPanel(new GridLayout(4, 1,5,10));

		totalPanel.add(timeLabel);
		totalPanel.add(timeTextField);
		totalPanel.add(totalLabel);
		totalPanel.add(totalTextField);
		
		totalPanel.add(cashLabel);
		totalPanel.add(cashTextField);
		totalPanel.add(chargeLabel);
		totalPanel.add(chargeTextField);
		
		

		JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));

		buttonsPanel.add(removeButton);
		buttonsPanel.add(registerSaleButton);
		buttonsPanel.add(VIPButton);
		buttonsPanel.add(takeoutButton);
		
		orderPanel.add(totalPanel, BorderLayout.NORTH);
		orderPanel.add(new JScrollPane(orderList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		orderPanel.add(buttonsPanel, BorderLayout.SOUTH);

		// Status panel
		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.setBorder(BorderFactory.createTitledBorder("Status"));

		JPanel salesButtonsPanel = new JPanel(new GridLayout(1, 5));

		salesButtonsPanel.add(plainRadioButton);
		salesButtonsPanel.add(HTMLRadioButton);
		salesButtonsPanel.add(XMLRadioButton);
		salesButtonsPanel.add(displaySalesButton);
		salesButtonsPanel.add(saveSalesButton);
		bottomPanel.add(new JScrollPane(statusTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		bottomPanel.add(salesButtonsPanel, BorderLayout.SOUTH);

		// arrange panels in window
		setLayout(new BorderLayout());
		add(catalogPanel, BorderLayout.WEST);
		add(centralPanel, BorderLayout.CENTER);
		add(orderPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		// start listening for list and buttons events
		catalogList.addListSelectionListener(new DisplayProductListener());
		addModifyButton.addActionListener(new AddModifyListener());
		removeButton.addActionListener(new RemoveListener());
		registerSaleButton.addActionListener(new RegisterSaleListener());
		displaySalesButton.addActionListener(new DisplaySalesListener());
		saveSalesButton.addActionListener(new SaveSalesListener());
		plainRadioButton.addActionListener(new PlainListener());
		HTMLRadioButton.addActionListener(new HTMLListener());
		XMLRadioButton.addActionListener(new XMLListener());
		VIPButton.addActionListener(new VIPListener());
		takeoutButton.addActionListener(new takeoutListener());

		// populate the product catalog
		catalog = initialCatalog;
		catalogList.setListData(catalog.getCodes());

		currentOrder = new Order();
		sales = new Sales();
		salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
		fileChooser = new JFileChooser();
		dollarFormatter = NumberFormat.getCurrencyInstance(Locale.US);
	}
	
	
	/*
	 * Obtains a {@link JPanel} object with the information of a product.
	 *
	 * @param dataFields an {@link ArrayList} of {@link DataField} with the
	 * product information.
	 * 
	 * @return a {@link JPanel} with the product information.
	 */
	private JPanel getDataFieldsPanel(ArrayList<DataField> dataFields) {

		/* COPY YOUR CODE FROM PREVIOUS EXERCISE */

		JPanel panel = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		String code = (String) catalogList.getSelectedValue();
		Product myproduct = catalog.getProduct(code);
		dataFields = myproduct.getDataFields();
		JLabel label = null;
		JTextField tf = null;
		for (DataField datafield : dataFields) {
			label = new JLabel(datafield.getName());
			tf = new JTextField(datafield.getValue(), 20);
			p1.add(label);
			p2.add(tf);
			tf.setEditable(false);
		}
		p1.setLayout(new GridLayout(dataFields.size(), 1, 0, 10));
		p2.setLayout(new GridLayout(dataFields.size(), 1, 0, 0));
		panel.add(p1, BorderLayout.EAST);
		panel.add(p2, BorderLayout.WEST);
		JPanel jp = new JPanel(new GridLayout(9,1)); 
		for(int i=0;i<dataFields.size();i++){
			jp.add(new JLabel(dataFields.get(i).getName()));
			jp.add(new JTextField(dataFields.get(i).getValue()));
		}
		return jp;
	}
	
	class VIPListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			totalTextField.setText((dollarFormatter.format(currentOrder.getTotalOrderMoney()*0.8)));
			double d = currentOrder.getItem().getProduct().getPrice();
			d*=0.8;
			currentOrder.getItem().getProduct().setPrice(d);
			orderList.setListData((currentOrder).getItems().toArray());
		}
	}
	
	class takeoutListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	takeoutGUI frame = new takeoutGUI();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
		}
	}

	/**
	 * This inner class handles list-selection events.
	 */
	class DisplayProductListener implements ListSelectionListener {

		/**
		 * Displays the information of the selected product.
		 *
		 * @param event
		 *            the event object.
		 */
		public void valueChanged(ListSelectionEvent event) {

			if (!catalogList.getValueIsAdjusting()) {

				String code = (String) catalogList.getSelectedValue();
				Product product = catalog.getProduct(code);

				productPanel.removeAll();
				productPanel.setVisible(false); 
				productPanel.add(getDataFieldsPanel(product.getDataFields()));
				productPanel.setVisible(true); 

				statusTextArea.setText("Product " + code + " has been displayed");	
			}
		}
	}
	
	class ShowTime extends TimerTask {
        public void run() {
        	timeTextField.setText(sdf.format(new Date()));
            repaint();
        }
}
	
	
	/**
	 * This inner class processes <code>addModifyButton</code> events.
	 */
	class AddModifyListener implements ActionListener {

		/**
		 * Adds an order item to the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			/* PLACE YOUR CODE HERE */
			String code = (String) catalogList.getSelectedValue();
			try {
				int quantity = Integer.parseInt(quantityTextField.getText());
				if (quantity < 1) {
					statusTextArea.setText("Please input a positive integer!");
				} else {
					Product product = catalog.getProduct(code);
					OrderItem orderitem = currentOrder.getItem(product);
					if (orderitem == null) {
						orderitem = new OrderItem(product, quantity);
						currentOrder.addItem(orderitem);
						orderList.setListData((currentOrder).getItems().toArray());
						totalTextField.setText(dollarFormatter.format(currentOrder.getTotalOrderMoney()));
						//String s = cashTextField.getText().substring(1, cashTextField.getText().length());
						double c = Double.parseDouble(cashTextField.getText());
						//double value = Double.valueOf(s);
						double b = Double.valueOf(currentOrder.getTotalOrderMoney());
						//double d=value-b;
						double d=c-b;
						chargeTextField.setText(dollarFormatter.format(d));

					} else {
						orderitem.setQuantity(quantity);
						orderList.setListData(currentOrder.getItems().toArray());
						totalTextField.setText(dollarFormatter.format(currentOrder.getTotalOrderMoney()));
						String s = cashTextField.getText().substring(1, cashTextField.getText().length());
						double value = Double.valueOf(s);
						double b = Double.valueOf(currentOrder.getTotalOrderMoney());
						double d=value-b;
						chargeTextField.setText(dollarFormatter.format(d));
					}
					quantityTextField.setText("");
				}
			} catch (Exception e) {
				statusTextArea.setText("Please input an integer!");
			}

		}
	}
	

	/**
	 * This inner class processes <code>removeButton</code> events.
	 */
	class RemoveListener implements ActionListener {

		/**
		 * Removes an order item from the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			/* PLACE YOUR CODE HERE */
			if (currentOrder.getOrderNumber() == 0) {
				statusTextArea.setText("The order is empty.");
			} else {
				OrderItem orderitem = (OrderItem) orderList.getSelectedValue();
				currentOrder.removeItem(orderitem);
				orderList.setListData(currentOrder.getItems().toArray());
				totalTextField.setText(dollarFormatter.format(currentOrder.getTotalOrderMoney()));
				statusTextArea.setText("The order has been removed.");
			}

		}
	}

	/**
	 * This inner class processes <code>registerSaleButton</code> button events.
	 */
	class RegisterSaleListener implements ActionListener {
		/**
		 * Registers the sale of the current order.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (currentOrder.getOrderNumber() == 0) {
				statusTextArea.setText("The order is empty.");
			} else {
				sales.addOrder(currentOrder);
				currentOrder = new Order();
				orderList.setListData(currentOrder.getItems().toArray());
				totalTextField.setText(dollarFormatter.format(0));
				statusTextArea.setText("The sale has been registered.");
				
			}
		}
	}

	/**
	 * This inner class processes <code>displaySalesButton</code>events.
	 */
	class DisplaySalesListener implements ActionListener {

		/**
		 * Displays the sales information.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (sales.getNumberOfOrders() == 0) {
				statusTextArea.setText("No orders have been sold.");
			} else {
				statusTextArea.setText(salesFormatter.formatSales(sales));
			}
		}
	}

	/*
	 * This inner class processes <code>saveSalesButton</code> events.
	 */
	class SaveSalesListener implements ActionListener {

		/**
		 * Saves the sales informations in a file.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			/* PLACE YOUR CODE HERE */
			if (sales.getNumberOfOrders() == 0) {
				statusTextArea.setText("Order is empty.");
			} else {
				fileChooser = new JFileChooser();
				int result = fileChooser.showSaveDialog(GourmetCoffeeGUI.this);
				if (result == JFileChooser.APPROVE_OPTION) { 
					try {

						File file = fileChooser.getSelectedFile();
						PrintWriter writer = new PrintWriter(new FileWriter(file));
						String formatString = "";
						formatString = salesFormatter.formatSales(sales);
						writer.print(formatString);
						writer.close();
						statusTextArea.setText("The sales information has been saved.");

					} catch (IOException ioException) {
						stdErr.println(ioException);
						stdErr.flush();

					}
				}
			}
		}
	}

	/*
	 * This inner class processes <code>plainRadioButton</code> events.
	 */
	class PlainListener implements ActionListener {

		/**
		 * Sets the sales formatter to plain text.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>HTMLRadioButton</code> events.
	 */
	class HTMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to HTML.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {
			salesFormatter = HTMLSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>XMLRadioButton</code> events.
	 */
	class XMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to XML.
		 *
		 * @param event
		 *            the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = XMLSalesFormatter.getSingletonInstance();
		}
	}
	
}