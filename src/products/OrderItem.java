package products;

import java.text.DecimalFormat;

public class OrderItem {
	private Product product;
	private int quantity;
	
	public OrderItem(Product product,int quantity){
		this.setProduct(product);
		this.setQuantity(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public double getValue(){
    	return product.getPrice() * quantity;
    }
	
	public double getValue1(){
    	return product.getPrice();
    }
	
	public void setValue(double value){
		product.setPrice(value);
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("0.00");
		return this.getProduct().getCode()+"\n"+"quantity:"+this.getQuantity()
		+" price:"+df.format(this.getValue());
	}

}
