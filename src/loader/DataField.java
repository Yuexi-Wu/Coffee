package loader;

public class DataField extends Object{
	private String initName;
	private String initValue;
	
	public DataField(String initName, String initValue){
		this.initName = initName;
		this.initValue = initValue;
	}
	
	public String getName(){
		return initName;
	}
	
	public String getValue(){
		return initValue;
	}

}
