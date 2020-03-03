package Formatter;
import products.OrderItem;
import sale.Order;
import sale.Sales;

public class HTMLSalesFormatter implements SalesFormatter {
	
	private static HTMLSalesFormatter singletonInstance = null;
	
	private HTMLSalesFormatter () {	
	}
	
	public static HTMLSalesFormatter getSingletonInstance() {
		if (singletonInstance == null) {
			singletonInstance = new HTMLSalesFormatter ();
		}
		return singletonInstance;
	}
	
	@Override
	public String formatSales(Sales sales) {
		String str = "\n" + "<html>" + "\n" + " " + "<body>" + "\n" + "  " + "<center><h2>Orders</h2></center>";
		for (Order order : sales) {
			str += "\n" + "  <hr>" + "\n" + "  <h4>Total=" + order.getTotalOrderMoney() + "<h4>";
			for (OrderItem orderitem : order) {
				str += "\n" + "   <p>" + "\n" + "    <b>code:<b>" + orderitem.getProduct().getCode() + "<br>" + "\n"
						+ "    <b>quantity:</b> " + orderitem.getQuantity() + "<br>" + "\n" + "    <b>price:</b> "
						+ orderitem.getProduct().getPrice() + "\n" + "   </p>";
			}
		}
		str += "   " + "\n" + " " + "</body>" + "\n" + "</html>";
		return str;
	}
}
