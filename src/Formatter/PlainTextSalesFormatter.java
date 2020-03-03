package Formatter;
import java.text.DecimalFormat;

import products.OrderItem;
import sale.Order;
import sale.Sales;


public class PlainTextSalesFormatter implements SalesFormatter{

	public static PlainTextSalesFormatter singletonInstance = null ;
	private PlainTextSalesFormatter(){
 	}
	
  	static public PlainTextSalesFormatter getSingletonInstance(){
  		if(singletonInstance == null){
  			synchronized (PlainTextSalesFormatter.class) {
				if(singletonInstance == null){
					singletonInstance = new PlainTextSalesFormatter();
				}
			}
  		}
  		return singletonInstance;
  	}
  	
 	public String formatSales(Sales sales){
 		String str = "";
		int count = 1;
		DecimalFormat df = new DecimalFormat("0.00");
		for (Order order : sales) {
			str += "\n" + "------------------------" + "\n" + "Order " + +count + "\n" + "\n";
			count++;
			for (OrderItem orderitem : order) {
				str += orderitem.getQuantity() + " " + orderitem.getProduct().getCode() + " "
						+ df.format(orderitem.getProduct().getPrice());
			}
			str += "\n" + "\n" + "Total = " + df.format(order.getTotalOrderMoney());
		}
		return str;

	}


}
