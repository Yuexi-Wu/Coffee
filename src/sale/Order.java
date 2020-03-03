package sale;

import java.util.ArrayList;
import java.util.Iterator;

import products.OrderItem;
import products.Product;


public class Order implements Iterable<OrderItem>{
	private ArrayList<OrderItem> items;
	
	public Order(){
		items = new ArrayList<OrderItem>();
	}
	
	public ArrayList<OrderItem> getItems(){
		return items;
	}
	
	public void addItem(OrderItem o){
		items.add(o);
	}
	
	public void removeItem(OrderItem o){
		items.remove(o);
	}
	
	public OrderItem getItem(Product p){
		OrderItem order = null;
		for(OrderItem o:items){
			if(o.getProduct().equals(p)){
				order = o;
			}
		}
		return order;
	}
	
	public OrderItem getItem(){
		OrderItem order = null;
		for(OrderItem o:items){
				order = o;			
		}
		return order;
	}

	
	public int getOrderNumber(){
		return items.size();
	}
	
	public double getTotalOrderMoney(){
		double value = 0;
		for(OrderItem order : items) {
			value += order.getValue();
		}
		return value;

	}
	
	public Iterator<OrderItem> iterator() {
		return this.items.iterator();
	}
}
