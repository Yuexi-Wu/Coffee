package sale;

import java.util.ArrayList;
import java.util.Iterator;

public class Sales implements Iterable<Order>{
	private ArrayList<Order> orders;
	
	public Sales(){
		orders = new ArrayList<>();
	}
	
	public void addOrder(Order order){
		orders.add(order);
	}
	
	public Iterator<Order> iterator(){
		return this.orders.iterator();
	}
	
	public int getNumberOfOrders(){
		return this.orders.size();
	}
	
	public ArrayList<Order> getOrders(){
		return orders;
	}

}
