package products;

import java.util.ArrayList;

import loader.DataField;

public class CoffeeBrewer extends Product{
	private String model;
	private String typeOfWaterSupply;
	private int capacity;
	
	public CoffeeBrewer(String code, String description, double price,
			String model,String typeOfWaterSupply,int capacity){
		super(code,description,price);
		this.setModel(model);
		this.setTypeOfWaterSupply(typeOfWaterSupply);
		this.setCapacity(capacity);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getTypeOfWaterSupply() {
		return typeOfWaterSupply;
	}

	public void setTypeOfWaterSupply(String typeOfWaterSupply) {
		this.typeOfWaterSupply = typeOfWaterSupply;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String toString(){
		return super.toString()+ model + "_" + typeOfWaterSupply + "_" + capacity;
		
	}
	
	public ArrayList<DataField> getDataFields(){
		ArrayList<DataField> dataFields = new ArrayList<DataField>();
		DataField model = new DataField("model",this.getModel());
		DataField Roast = new DataField("Roast",this.getTypeOfWaterSupply());
		DataField Capacity = new DataField("Capacity",String.valueOf(this.getCapacity()));
		dataFields.addAll(super.getDataFields());
		dataFields.add(model);
		dataFields.add(Roast);
		dataFields.add(Capacity);
		return dataFields;
	}
}
