package Formatter;
import products.OrderItem;
import sale.Order;
import sale.Sales;

public class XMLSalesFormatter implements SalesFormatter{
	private static XMLSalesFormatter singletonInstance = new XMLSalesFormatter(); // 饿汉模式
	
	private XMLSalesFormatter () {
		
	}
	public static XMLSalesFormatter getSingletonInstance() {
		return singletonInstance;
	}
	public String formatSales(Sales sales) {
		String str = "\n" + "<Sales>" + "\n";
		for (Order order : sales) {
			str += "    <Order total=" + "\"" + order.getTotalOrderMoney() + "\"" + ">" + "\n";
			for (OrderItem orderitem : order) {
				str += "   <OrderItem quantity=" + "\"" + orderitem.getQuantity() + "\"" + "price=" + "\""
						+ orderitem.getProduct().getPrice() + "\"" + ">" + orderitem.getProduct().getCode()
						+ "</OrderItem>";
			}
			str += "\n" + "  </Order>";
		}
		str += "\n" + "<Sales>";
		return str;
	}
}
