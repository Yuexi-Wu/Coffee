package Formatter;
import java.io.*;
import java.util.*;

import products.Coffee;
import products.CoffeeBrewer;
import products.OrderItem;
import products.Product;
import sale.Catalog;
import sale.Order;
import sale.Sales;

import java.text.*;

/**
 * This class implements a gourmet coffee system.
 *
 * @author 
 * @version 1.1.0
 * @see Product
 * @see Coffee
 * @see CoffeeBrewer
 * @see Catalog
 * @see OrderItem
 * @see Order
 */
public class GourmetCoffee  {

	private static BufferedReader  stdIn =
		new  BufferedReader(new  InputStreamReader(System.in));
	private static PrintWriter  stdOut =
		new  PrintWriter(System.out, true);
	private static PrintWriter  stdErr =
		new  PrintWriter(System.err, true);

	private static final NumberFormat CURRENCY =
		NumberFormat.getCurrencyInstance();

	private Catalog  catalog; // 当前商品目录， 即总的商品种类
	private Order  currentOrder; // 当前订单，是还未支付的
	private Sales  sales; // 已完成订单，是Order的集合
	private SalesFormatter salesFormatter; // 已完成订单的显示的format 
	private Scanner scanner;

	// 设置format改变当前sales显示的format
	private void setSalesFormatter(SalesFormatter newFormatter) {
		this.salesFormatter = newFormatter;  
	}
	//  展示当前sale的信息
	private void displaySales() {
		System.out.println(salesFormatter.formatSales(sales));
	}
	// 向用户执行选择菜单，可以选择显示的方式
	void run() throws IOException {
		System.out.println("Please enter the format you wanna show about the sale list.");
		System.out.println("Ranging from 1 to 3");
		
		scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		switch (i) {
			case 1:
					salesFormatter = PlainTextSalesFormatter.getSingletonInstance();	
					this.displaySales();
					break;
			case 2: 
					salesFormatter = HTMLSalesFormatter.getSingletonInstance();
					this.displaySales();
					break;
			case 3:
					salesFormatter = XMLSalesFormatter.getSingletonInstance();
					this.displaySales();
					break;
			default:
				System.out.println("Error! Please enter the number from 1 to 3");
				break;
		}
	}
	
	/**
	 * Loads data into the catalog and starts the application.
	 *
	 * @param args  String arguments.  Not used.
	 * @throws IOException if there are errors in the input.
	 */
	public static void  main(String[]  args) throws IOException  {

		GourmetCoffee  application = new  GourmetCoffee();
		application.run();

	}

	/**
	 * Constructs a <code>GourmetCoffee</code> object and
	 * initializes the catalog and sales data.
	 *
	 * @param initialCatalog a product catalog
	 */
	GourmetCoffee() {

		this.catalog = loadCatalog();
		this.sales = loadSales(this.catalog);
		this.currentOrder = new Order();
	}

	/**
	 * Creates an empty catalog and then add products to it.
	 *
	 * @return a product catalog
	 */
	private Catalog loadCatalog() {

		Catalog catalog = new Catalog();

		catalog.addItems(new Coffee(
			"C001", "Colombia, Whole, 1 lb", 17.99,
			"Colombia", "Medium", "Rich and Hearty", "Rich", "Medium", "Full"));
		catalog.addItems(new Coffee(
			"C002", "Colombia, Ground, 1 lb", 18.75,
			"Colombia", "Medium", "Rich and Hearty", "Rich", "Medium","Full"));
		catalog.addItems(new Coffee(
			"C003", "Italian Roasts, Whole, 1 lb", 16.80,
			"Latin American Blend", "Italian Roast", "Dark and heavy",
			"Intense", "Low", "Medium"));
		catalog.addItems(new Coffee(
			"C004", "Italian Roasts, Ground, 1 lb", 17.55,
			"Latin American Blend", "Italian Roast", "Dark and heavy",
			"Intense", "Low", "Medium"));
		catalog.addItems(new Coffee(
			"C005", "French Roasts, Whole, 1 lb", 16.80,
			"Latin American Blend", "French Roast", "Bittersweet, full intense",
			"Intense, full", "None", "Medium"));
		catalog.addItems(new CoffeeBrewer(
			"B001", "Home Coffee Brewer", 150.00,
			"Brewer 100", "Pourover", 6));
		catalog.addItems(new CoffeeBrewer(
			"B002", "Coffee Brewer, 2 Warmers", 200.00,
			"Brewer 200", "Pourover", 12));
		catalog.addItems(new CoffeeBrewer(
			"B003", "Coffee Brewer, 3 Warmers", 280.00,
			"Brewer 210", "Pourover", 12));

		catalog.addItems(
			new Product("A001", "Almond Flavored Syrup", 9.00));
		catalog.addItems(
			new Product("A002", "Irish Creme Flavored Syrup", 9.00));
		catalog.addItems(
			new Product("A003", "Mint Flavored syrup", 9.00));
		catalog.addItems(
			new Product("A004", "Caramel Flavored Syrup", 9.00));
		catalog.addItems(
			new Product("A005", "Gourmet Coffee Cookies", 12.00));

		return catalog;
	}

	/**
	 * Initializes the sales object.
	 */
	private Sales loadSales(Catalog catalog) {

		Sales sales = new Sales();
		Order[] orders = new Order[6];

		orders[0] = new Order();
		orders[0].addItem(new OrderItem(catalog.getProduct("C001"), 5));
		sales.addOrder(orders[0]);

		orders[1] = new Order();
		orders[1].addItem(new OrderItem(catalog.getProduct("C002"), 2));
		orders[1].addItem(new OrderItem(catalog.getProduct("A001"), 2));
		orders[1].addItem(new OrderItem(catalog.getProduct("A003"), 2));
		sales.addOrder(orders[1]);

		orders[2] = new Order();
		orders[2].addItem(new OrderItem(catalog.getProduct("B002"), 1));
		orders[2].addItem(new OrderItem(catalog.getProduct("A003"), 3));
		sales.addOrder(orders[2]);

		orders[3] = new Order();
		orders[3].addItem(new OrderItem(catalog.getProduct("B003"), 2));
		orders[3].addItem(new OrderItem(catalog.getProduct("C001"), 3));
		orders[3].addItem(new OrderItem(catalog.getProduct("C003"), 3));
		orders[3].addItem(new OrderItem(catalog.getProduct("C005"), 3));
		orders[3].addItem(new OrderItem(catalog.getProduct("A001"), 3));
		orders[3].addItem(new OrderItem(catalog.getProduct("A004"), 2));
		sales.addOrder(orders[3]);
		
		orders[4] = new Order();
		orders[4].addItem(new OrderItem(catalog.getProduct("B001"), 1));
		orders[4].addItem(new OrderItem(catalog.getProduct("C002"), 2));
		orders[4].addItem(new OrderItem(catalog.getProduct("C003"), 2));
		orders[4].addItem(new OrderItem(catalog.getProduct("A001"), 2));
		orders[4].addItem(new OrderItem(catalog.getProduct("A002"), 6));
		sales.addOrder(orders[4]);
		
		orders[5] = new Order();
		orders[5].addItem(new OrderItem(catalog.getProduct("B001"), 1));
		orders[5].addItem(new OrderItem(catalog.getProduct("C001"), 1));
		orders[5].addItem(new OrderItem(catalog.getProduct("C005"), 5));
		orders[5].addItem(new OrderItem(catalog.getProduct("A001"), 5));
		orders[5].addItem(new OrderItem(catalog.getProduct("A004"), 4));
		sales.addOrder(orders[5]);
		
		return sales;
	}

	
	/*
	 * Displays a menu of options and verifies the user's choice.
	 *
	 * @return an integer in the range [0,7]
	 */
	private int  getChoice() throws IOException  {

		int  input;

		do  {
			try  {
				stdErr.println();
				stdErr.print(
					  "[0] Quit\n"
					+ "[1] Display catalog\n"
					+ "[2] Display product\n"
					+ "[3] Display current order\n"
					+ "[4] Add|modify product to|in current order\n"
					+ "[5] Remove product from current order\n"
					+ "[6] Register sale of current order\n"
					+ "[7] Display sales\n"
					+ "[8] Display number of orders with a specific product\n"
					+ "[9] Display the total quantity sold for each product\n"
					+ "choice> ");
				stdErr.flush();

				input = Integer.parseInt(stdIn.readLine());

				stdErr.println();

				if (0 <= input && 9 >= input)  {
					break;
				} else {
					stdErr.println("Invalid choice:  " + input);
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);

		return  input;
	}

	/**
	 * Displays the catalog.
	 */
	public void displayCatalog() {

		int size = this.catalog.totalNumberOfProduct();

		if (size == 0) {
			stdErr.println("The catalog is empty");
		} else {
			for (Product  product : this.catalog) {

				stdOut.println(product.getCode() + " " +
					product.getDescription());
			}
		}
	}

	/**
	 * Displays the information of a product
	 */
	public void displayProductInfo() throws IOException  {

		Product product = readProduct();

		stdOut.println("  Description: " + product.getDescription());
		stdOut.println("  Price: " + product.getPrice());
		if (product instanceof Coffee) {

			Coffee coffee = (Coffee) product;

			stdOut.println("  Origin: " + coffee.getCountryOfOrigin());
			stdOut.println("  Roast: " + coffee.getTypeOfRoast());
			stdOut.println("  Flavor: " + coffee.getFlavour());
			stdOut.println("  Aroma: " + coffee.getAroma());
			stdOut.println("  Acidity: " + coffee.getAcidity());
			stdOut.println("  Body: " + coffee.getBody());
		} else if (product instanceof CoffeeBrewer) {

			CoffeeBrewer brewer = (CoffeeBrewer) product;

			stdOut.println("  Model: " + brewer.getModel());
			stdOut.println("  Water Supply: " + brewer.getTypeOfWaterSupply());
			stdOut.println("  Number of Cups: " + brewer.getCapacity());
		}
	}

	/**
	 * Displays the current order.
	 */
	public void displayOrder() {

		int size = this.currentOrder.getOrderNumber();

		if (size == 0) {
			stdErr.println("The current order is empty");
		} else {
			for (OrderItem  orderItem : this.currentOrder) {
				stdOut.println(orderItem.toString());
			}
			stdOut.println("Total: " +
				CURRENCY.format(this.currentOrder.getTotalOrderMoney()));
		}
	}

	/**
	 * Modifies the current order: if the specified product is not already
	 * part of the order, it is added; otherwise, the quantity of the
	 * specified product is updated.
	 */
	public void addModifyProduct()  throws IOException  {

		Product product = readProduct();
		int quantity = readQuantity();
		OrderItem orderItem = this.currentOrder.getItem(product);

		if (orderItem == null) {
			this.currentOrder.addItem(new OrderItem(product, quantity));
			stdOut.println("The product " + product.getCode()
			 + " has been added");
		} else {
			orderItem.setQuantity(quantity);
			stdOut.println("The quantity of the product " +
				product.getCode() + " has been modified");
		}
	}

	/**
	 * Removes a product from the current order.
	 */
	public void removeProduct()  throws IOException  {

		Product product = readProduct();
		OrderItem orderItem = this.currentOrder.getItem(product);

		if (orderItem != null) {
			this.currentOrder.removeItem(orderItem);
			stdOut.println("The product " + product.getCode()
				 + " has been removed from the current order");
		} else {
			stdErr.println(
				"There are no products in the current order with that code");
		}
	}

	/**
	 * Registers the sale of the current order.
	 */
	public void saleOrder()  {

		if (this.currentOrder.getOrderNumber() > 0) {
			this.sales.addOrder(this.currentOrder);
			this.currentOrder = new Order();
			stdOut.println("The sale of the order has been registered");
		} else {
			stdErr.println("The current order is empty");
		}
	}

	/**
	 * Displays the orders that have been sold.
	 */
	public void displayOrdersSold() {

		int numOrders = this.sales.getNumberOfOrders();

		if (numOrders != 0) {
			int orderNumber = 1;
			for (Order  order : this.sales) {

				stdOut.println("Order " + orderNumber++);

				for (OrderItem  orderItem : order) {
					stdOut.println("   " + orderItem.toString());
				}
				stdOut.println("   Total: " +
					CURRENCY.format(order.getTotalOrderMoney()));
			}
		} else {
			stdErr.println("There are no sales");
		}
	}
	

	/**
	 * Displays the number of orders that contain a specified product
	 *
	 * @param product the <code>Product</code> object to be displayed.
	 */
	public void displayNumberOfOrders(Product product) {
		int num = 0; // 初始化包含特殊商品的订单数量为0
		
		Iterator<Order> iter=sales.iterator();
		while(iter.hasNext())
		{
			Order order=iter.next();
			if(order.getItem(product)!=null)
			{
				num++;
			}
		}
		System.out.println("The number of orders in the sales object that contain the specified product is  "+num);
		
	}

	/**
	 * Displays the total quantity of product that have has sold for each
	 * product in the catalog.
	 */
	public void displayTotalQuantityOfProducts() {
		Iterator<Product> item = catalog.iterator(); // 先遍历catalog， 取出里面所有的product
		while (item.hasNext()) {
			int number = 0;  // 为每种商品的数量都初始化为0
			Product pro = item.next();  // 将每种product都记录下来 
			for (Order order : sales) {  // 遍历sales， 判断里面的已完成订单中是否含有该商品
				if (order.getItem(pro) != null) {
					number += order.getItem(pro).getQuantity();	 // 若有该商品，则将有该商品的订单取出，然后取出该订单中该商品的数量 			
				}
			}
			System.out.println("  "+pro.getCode()+"  "+number);
		}
	}

	/*
	 * Prompts user for a product code and locates the associated
	 * <code>Product</code> object.
	 *
	 * @return reference to the <code>Product</code> object with
	 *         the specified code
	 */
	private Product readProduct() throws IOException  {

		do  {			
			stdErr.print("Product code> ");
			stdErr.flush();
			
			Product product = this.catalog.getProduct(stdIn.readLine());
			
			if (product != null) {

				return product;
			
			} else {	
				stdErr.println("There are no products with that code");
			}
		}  while (true);
	}

	/*
	 * Prompts user for the product quantity and verifies the
	 * user's response.
	 *
	 * @return a positive integer
	 */
	private int readQuantity() throws IOException  {

		int quantity;

		do  {
			try  {

				stdErr.print("Quantity> ");
				stdErr.flush();
				quantity = Integer.parseInt(stdIn.readLine());
				if (quantity > 0) {

					return quantity;

				} else  {
					stdErr.println(
						"Invalid input. Please enter a positive integer");
				}
			} catch (NumberFormatException  nfe)  {
				stdErr.println(nfe);
			}
		}  while (true);
	}
}