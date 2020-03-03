package products;

import java.util.ArrayList;

import loader.DataField;

public class Product {
	protected String code;
	protected String description;
	protected double price;
	
	public Product(String code, String description, double price){
		this.setCode(code);
		this.setDescription(description);
		this.setPrice(price);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return code + "_" + description + "_" + price + "_";
	}
	
	public boolean equals(Object object) {
		if (object instanceof Product) {
			if (((Product) object).getCode() == this.code) {	
				return true;
			}
		}
		return false;
	}

	public ArrayList<DataField> getDataFields(){
		ArrayList<DataField> dataFields = new ArrayList<DataField>();
		DataField Code = new DataField("Code",this.getCode());
		DataField Description = new DataField("Description",this.getDescription());
		DataField Price = new DataField("Price",String.valueOf(getPrice()));
		dataFields.add(Code);
		dataFields.add(Description);
		dataFields.add(Price);
		return dataFields;
	}

}
