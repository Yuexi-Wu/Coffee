package products;

import java.util.ArrayList;

import loader.DataField;

public class Coffee extends Product{
	private String countryOfOrigin;
	private String typeOfRoast;
	private String flavour;
	private String aroma;
	private String acidity;
	private String body;
	
	public Coffee(String code, String description, double price,String countryOfOrigin,String typeOfRoast,
			String flavour,String aroma,String acidity,String body){
		super(code, description, price);
		this.setCountryOfOrigin(countryOfOrigin);
		this.setTypeOfRoast(typeOfRoast);
		this.setFlavour(flavour);
		this.setAroma(aroma);
		this.setAcidity(acidity);
		this.setBody(body);
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getTypeOfRoast() {
		return typeOfRoast;
	}

	public void setTypeOfRoast(String typeOfRoast) {
		this.typeOfRoast = typeOfRoast;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	public String getAroma() {
		return aroma;
	}

	public void setAroma(String aroma) {
		this.aroma = aroma;
	}

	public String getAcidity() {
		return acidity;
	}

	public void setAcidity(String acidity) {
		this.acidity = acidity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String toString(){
		return super.toString()+countryOfOrigin
                + "_" + typeOfRoast + "_" + flavour + "_" + aroma + "_"
                + acidity + "_" + body;
	}
	
	public ArrayList<DataField> getDataFields(){
		ArrayList<DataField> dataFields = new ArrayList<DataField>();
		DataField Origin = new DataField("Origin",this.getCountryOfOrigin());
		DataField Roast = new DataField("Roast",this.getTypeOfRoast());
		DataField flavour = new DataField("flavour",this.getFlavour());
		DataField aroma = new DataField("aroma",this.getAroma());
		DataField acidity = new DataField("acidity",this.getAcidity());
		DataField body = new DataField("body",this.getBody());
		dataFields.addAll(super.getDataFields());
		dataFields.add(Origin);
		dataFields.add(Roast);
		dataFields.add(flavour);
		dataFields.add(aroma);
		dataFields.add(acidity);
		dataFields.add(body);
		
		return dataFields;
	}

}
