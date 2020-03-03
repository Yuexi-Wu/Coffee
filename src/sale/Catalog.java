package sale;

import java.util.*;

import products.Product;


public class Catalog implements Iterable<Product>{
	private ArrayList<Product> product;
	
	public Catalog(){
		product = new ArrayList<Product>();
	}
	
	public void addItems(Product p1){
		product.add(p1);
	}
	
	public void removeItem(Product p2){
		product.remove(p2);
	}
	
	public Product getProduct(String code){
		Product target = null;
		for(Product product1:product)
			if(product1.getCode().equals(code)){
				target = product1;
		}
		return target;
	}
	
	public int totalNumberOfProduct(){
		return product.size();
	}
	
	public Iterator<Product> iterator(){
    	return product.iterator();
    }
	
	public String[] getCodes(){
		ArrayList<String> codesList = new ArrayList<String>();
		for(Product pro : product){
			codesList.add(pro.getCode());
		}
		String[] codesArray = new String[codesList.size()];
		codesArray = codesList.toArray(codesArray);
		return codesArray;
	}
}
